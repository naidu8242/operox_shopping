package com.bis.operox.inv.rest;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.bis.operox.OperoxPasswordEncoder;
import com.bis.operox.inv.dao.entity.User;
import com.bis.operox.inv.service.UserService;
import com.bis.operox.util.OperoxSessionManager;

@RestController
public class SettingsRestController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	OperoxPasswordEncoder operoxPasswordEncoder;
	
	 @RequestMapping(value = "/saveUserProfile" , method = RequestMethod.POST)
	   public String saveUserProfile(@RequestParam(value="json", required=false) String json ,MultipartHttpServletRequest request) throws Exception {
			
		   
		    Iterator<String> itrator = request.getFileNames();
	        MultipartFile multiFile = request.getFile(itrator.next());
		
	        User user = (User) OperoxSessionManager.getSessionAttribute(request, "user");
		    json = "{"+json+"}";
			JSONObject jsonObj = new JSONObject(json);
			   try {
				user = userService.saveUserProfile(jsonObj,multiFile, user);
				
				OperoxSessionManager.setSessionAttribute(request, "user", user);
				OperoxSessionManager.setSessionAttribute(request, "userName", user.getFirstName()+" "+user.getLastName());
			} catch (Exception e) {
				e.printStackTrace();
			}
			   return "profileHome";
	  
	   
	   }

	 
	 @RequestMapping(value = "/displayUserImage/{userId}")
     public void displayUserImage(@PathVariable Long userId,HttpServletRequest request,HttpServletResponse response,HttpSession session) {
    	 
    	 User user = null;
			try {
				user = userService.getUserById(userId);
				
				if(user != null && user.getUserImage() != null)
				{
					user.setFileContentType("image/jpeg");
					InputStream in = new ByteArrayInputStream(user.getUserImage());
					BufferedImage bi = ImageIO.read(in);
					OutputStream out = response.getOutputStream();
					ImageIO.write(bi, "jpg", out);
					out.close();
					
				}
				
			} catch (IOException e) {
				e.printStackTrace();
			} 
			
     }
	 
	 
	   
	   @RequestMapping(value = "/deleteProfileImage" , method = RequestMethod.GET)
	   public String deleteProfileImage(HttpServletRequest request) throws Exception {
		   
		   User user = (User) OperoxSessionManager.getSessionAttribute(request, "user");
		   user.setUserImage(null);
		   user.setFileContentType(null);
		   userService.addUser(user);
		   OperoxSessionManager.setSessionAttribute(request, "user", user);
		   
		   return "success";
		   
	   }
	   
	   @RequestMapping(value = "/validateCurrentPassword",  method = RequestMethod.POST)
		public  String validateCurrentPassword(@RequestParam(value="currentPassword", required=false) String currentPassword,HttpServletRequest request) throws Exception {
		
			 User user = (User) OperoxSessionManager.getSessionAttribute(request, "user");
			 if(operoxPasswordEncoder.matches(currentPassword, user.getPassword())){
				 return "valid";
			 }else{
				 return "invalid";
			 }
		}
	   
	   
		@RequestMapping(value = "/resetUserPassword",  method = RequestMethod.POST)
		public  String resetUserPassword(@RequestParam(value="json", required=false) String json, HttpServletRequest request) throws Exception {
		
			 User user = (User) OperoxSessionManager.getSessionAttribute(request, "user");
	         json = "{"+json+"}";
			 
			 JSONObject jsonObj = new JSONObject(json);
			 
			 user.setPassword(operoxPasswordEncoder.encode(jsonObj.getString("password")));
			 userService.addUser(user);
			 OperoxSessionManager.setSessionAttribute(request, "user", user);
			
			return "success";
		}
	   
	 
	 
}
