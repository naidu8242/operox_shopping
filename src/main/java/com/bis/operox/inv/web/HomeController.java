package com.bis.operox.inv.web;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.bis.operox.util.OperoxSessionManager;

/**
 * This class is used for...
 * 
 * @author: Srinivas Vemula
 * @date: 31-July-2016
 */

@Controller
@RestController
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	
	@Autowired
	private ApplicationContext applicationContext;
	
	protected boolean isNullOrEmpty(String str) {
		return str == null || "".equals(str.trim());
	}
	
	
	@PostConstruct
	public void init() {
		logger.info(" *** HomeController.init with: " + applicationContext);
	}

   @RequestMapping(value = { "/", "/welcome**" }, method = RequestMethod.GET)
	public ModelAndView defaultPage() {

			ModelAndView model = new ModelAndView();
			model.setViewName("login");
			return model;

	}
   

	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public String customExceptionPage(HttpRequestMethodNotSupportedException exception){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		 logger.error("Exception cause is : "+ExceptionUtils.getFullStackTrace(exception));
		if(auth != null && auth.getPrincipal() != null){
				return "redirect:/dashboard";
		 }
		 else{
				return "redirect:/login";
		 }
	}

	@RequestMapping(value = "/login")
	public ModelAndView login(@RequestParam(value = "logout", required = false) String logout, HttpServletRequest request) {
		ModelAndView model = new ModelAndView();

		if (logout != null) {
			model.addObject("msg", "You've been logged out successfully.");
		}
		
		 String msg = (String) OperoxSessionManager.getSessionAttribute(request, "resetPasswordSucessfulMsg");
			if(!isNullOrEmpty(msg)){
				model.addObject("msg", msg);
				OperoxSessionManager.removeSessionAttribute(request, "resetPasswordSucessfulMsg");
			}
		
		model.setViewName("login");
		return model;

	}
	

	// for 403 access denied page
	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public ModelAndView accesssDenied() {

		ModelAndView model = new ModelAndView();

		// check if user is login
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {

			model.addObject("username", auth.getPrincipal());

		}

		model.setViewName("403");
		return model;

	}
	
	@RequestMapping(value = "/rest/{name}", method = RequestMethod.GET)
	public String getMovie(@PathVariable String name) {
		return name;
	}

	
	
	
}
