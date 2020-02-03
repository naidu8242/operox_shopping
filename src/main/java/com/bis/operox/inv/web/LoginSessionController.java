package com.bis.operox.inv.web;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bis.operox.constants.Constants;
import com.bis.operox.inv.dao.entity.Company;
import com.bis.operox.inv.dao.entity.Notifications;
import com.bis.operox.inv.dao.entity.Role;
import com.bis.operox.inv.dao.entity.Store;
import com.bis.operox.inv.dao.entity.User;
import com.bis.operox.inv.service.NotificationsService;
import com.bis.operox.inv.service.UserService;
import com.bis.operox.util.CommonUtil;
import com.bis.operox.util.OperoxSessionManager;

@Controller
@PropertySource("${propertyLocation:operoxapp.properties}")
public class LoginSessionController {
	
	
	private static final Logger logger = LoggerFactory.getLogger(LoginSessionController.class);
	
	@Autowired
	NotificationsService notificationsService;
	
	@Autowired
	UserService userService;
	
	@Value("${operoxUrl}")
	private String operoxUrl;
	
	@Autowired
	CommonUtil commonUtil;
	
	
	@RequestMapping(value = "/dashboard")
	public ModelAndView loginSession(HttpServletRequest request,Authentication authentication) throws Exception{
		
		
		User user = userService.findByUserName(authentication.getName());
		setLoginSession(request, authentication,user);
		
		ModelAndView model = new ModelAndView();
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Collection<GrantedAuthority> authorities =  (Collection<GrantedAuthority>) auth.getAuthorities();
		GrantedAuthority cachierAuthority= new SimpleGrantedAuthority("ROLE_CASHIER");
		GrantedAuthority hrManagerAuthority= new SimpleGrantedAuthority("ROLE_HR_MANAGER");
		
		if(authorities.contains(cachierAuthority)){
			commonUtil.highLightMenu(Constants.Customers, request);
			commonUtil.highLightSubMenu(Constants.Customer, request);
			model.setViewName("customers/customersHome");
		}
		else if(authorities.contains(hrManagerAuthority)){
			commonUtil.highLightMenu(Constants.Setup, request);
			commonUtil.highLightSubMenu(Constants.User, request);
			model.setViewName("setup/usersHome");
		}
		else{
			commonUtil.highLightSubMenu(null, request);
			model.setViewName("dashboard/dashboardHome");
		}
		
		return model;

	}
	
	
	
	private void setLoginSession(HttpServletRequest request,Authentication authentication,User user) throws Exception{
		
	      commonUtil.highLightMenu(Constants.Dashboard, request);
	      request.getSession().setAttribute("operoxUrl", operoxUrl);
	      setSessionUserAttributes(user,request);
	      setSessionUserStoreAttributes(user.getStore(),request);
	      setSessionUserCompanyAttributes(user.getStore().getCompany(),request);
	      setSessionUserRoleAttributes(user.getRole(),request);
	      
	    //get notification count
			String orgCode = "operox";
			String locationCode = "operoxIN";
			Integer notificationCount = notificationsService.getNotificationsCountByUserId(user.getId(),orgCode,locationCode);
			OperoxSessionManager.removeSessionAttribute(request, "notificationCount");
			OperoxSessionManager.setSessionAttribute(request, "notificationCount",notificationCount);
			
			// get notification list by user id
			Long notificationTo = user.getId();
			List<Notifications> notificationList = notificationsService.getAllNotificationsListByUserId(notificationTo,orgCode,locationCode);
			calculateDays(notificationList);
			OperoxSessionManager.removeSessionAttribute(request, "notificationList");
			OperoxSessionManager.setSessionAttribute(request, "notificationList",notificationList);
			
   }
	
	private void calculateDays(List<Notifications> notificationList) throws ParseException {
		for (Notifications notifications : notificationList) {
					//differentiating date and time
						Date date = notifications.getCreatedDate();
					    String[] parts = date.toString().split(" ");
					    notifications.setDateValue(parts[0]);
					    final SimpleDateFormat sdf = new SimpleDateFormat("H:mm");
					    final Date dateObj = sdf.parse(parts[1].toString());
					    notifications.setTime(new SimpleDateFormat("K:mm a").format(dateObj));
					    
					    // calculating days b/w dates 
					    Date currentDate = new Date();
						Date d1 = notifications.getCreatedDate();
						SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
						String strDate = dateFormat.format(currentDate);
						Date d2 = dateFormat.parse(strDate);
						long diff = d2.getTime() - d1.getTime();
						long diffDays = diff / (24 * 60 * 60 * 1000);
						notifications.setCalDays(diffDays);
					
				}
		
	}



	
	
	private void setSessionUserAttributes(User user, HttpServletRequest request)
	{
		
		OperoxSessionManager.setSessionAttribute(request, "user", user);
		OperoxSessionManager.setSessionAttribute(request, "userCode", user.getUserCode());
		OperoxSessionManager.setSessionAttribute(request, "userId", user.getId());
		OperoxSessionManager.setSessionAttribute(request, "userName", user.getFirstName()+" "+user.getLastName());
		
	}
	
	
	private void setSessionUserRoleAttributes(Role role, HttpServletRequest request)
	{
		OperoxSessionManager.setSessionAttribute(request, "roleId", role.getId());
		OperoxSessionManager.setSessionAttribute(request, "roleName", role.getRoleName());
		OperoxSessionManager.setSessionAttribute(request, "roleDisplayName", role.getDisplayName());
		
	}
	
	
	private void setSessionUserCompanyAttributes(Company company, HttpServletRequest request)
	{
		OperoxSessionManager.setSessionAttribute(request, "company", company);
		OperoxSessionManager.setSessionAttribute(request, "companyId", company.getId());
		OperoxSessionManager.setSessionAttribute(request, "companyName", company.getCompamyName());
		
	}
	
	private void setSessionUserStoreAttributes(Store store, HttpServletRequest request)
	{
		OperoxSessionManager.setSessionAttribute(request, "store", store);
		OperoxSessionManager.setSessionAttribute(request, "storeId", store.getId());
		OperoxSessionManager.setSessionAttribute(request, "storeName", store.getStoreName());
		
	}
}
