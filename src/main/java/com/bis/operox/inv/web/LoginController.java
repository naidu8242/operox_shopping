package com.bis.operox.inv.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bis.operox.util.CommonUtil;
import com.bis.operox.util.OperoxSessionManager;

@Controller
public class LoginController {
	
	@Value("${operoxUrl}")
	private String operoxUrl;
	
	@Autowired
	CommonUtil commonUtil;
	
	
	protected boolean isNullOrEmpty(String str) {
		return str == null || "".equals(str.trim());
	}
	
	 @RequestMapping(value = "/forgotUserPassword")
	 	public ModelAndView forgotPassword() {
	 			ModelAndView model = new ModelAndView();
	 			model.addObject("operoxUrl", operoxUrl);
	 			model.setViewName("forgotPassword");
	 			return model;
	 	}
	 
	 
	   @RequestMapping(value = "/resetUserPassword")
	  	public ModelAndView resetUserPassword(HttpServletRequest request) {
	  			ModelAndView model = new ModelAndView();
	  			model.setViewName("resetUserPassword");
	  			model.addObject("operoxUrl", operoxUrl);
	  			
	  			String msg = (String) OperoxSessionManager.getSessionAttribute(request, "resetPasswordMsg");
	  			if(!isNullOrEmpty(msg)){
	  				model.addObject("msg", msg);
	  				OperoxSessionManager.removeSessionAttribute(request, "resetPasswordMsg");
	  			}
	  			
	  			return model;
	  	}

}
