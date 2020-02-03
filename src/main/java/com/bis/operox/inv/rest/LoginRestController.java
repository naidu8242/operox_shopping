package com.bis.operox.inv.rest;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bis.operox.inv.dao.entity.PasswordResets;
import com.bis.operox.inv.dao.entity.User;
import com.bis.operox.inv.service.PasswordResetsService;
import com.bis.operox.inv.service.UserService;
import com.bis.operox.util.OperoxRandomCodeHelper;
import com.bis.operox.util.OperoxSessionManager;

@RestController
public class LoginRestController {
	
	@Autowired
	PasswordResetsService passwordResetsService;
	
	@Autowired
	UserService userService;
	
	
	
	@RequestMapping(value = "/resetPasswordWithUserName" , method = RequestMethod.GET)
	public String resetPasswordWithUserName(@RequestParam(value = "json", required = false) String json,
			HttpServletRequest request) throws Exception {

	    json = "{"+json+"}";
		JSONObject jsonObj = new JSONObject(json);
		PasswordResets passwordResets = null;
		User user = userService.findByUserName(jsonObj.getString("email"));
		if (null != user) {
			try {
				
				passwordResets = passwordResetsService.getPasswordResetsByUserId(user.getId());
				if (null == passwordResets) {
					String verificationCode = passwordResetsService.addPasswordResets(user);
					userService.resetPasswordMail(user, verificationCode);
				} else {
					passwordResets.setVerificationCode(OperoxRandomCodeHelper.generateRandomVerificationCode());
					passwordResets = passwordResetsService.savePasswordResets(passwordResets);
					// email to user
					userService.resetPasswordMail(user, passwordResets.getVerificationCode());
				}
				OperoxSessionManager.setSessionAttribute(request, "resetPasswordMsg",
						"Verification Code sent to your mail.");
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "valid";
		} else {
			return "Invalid";
		}

	}
	
	
	
	@RequestMapping(value = "/verifyResetPassword" , method = RequestMethod.GET)
	   public String verifyResetPassword(@RequestParam(value="json", required=false) String json ,HttpServletRequest request) throws Exception {
		
		json = "{"+json+"}";
		JSONObject jsonObj = new JSONObject(json);
		
		PasswordResets passwordResets  = passwordResetsService.getPasswordResetsByVerificationCode(jsonObj.getString("verificationCode"));
		
		if(null != passwordResets){
			try {
				passwordResetsService.resetPassword(passwordResets, jsonObj.getString("password"));
				passwordResetsService.deletePasswordResets(passwordResets);
				
				 OperoxSessionManager.setSessionAttribute(request, "resetPasswordSucessfulMsg", "Your password has reset sucessfully. Login with new password");
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "login";
		}else{
			return "Invalid";
		}
		
	}
	
}
