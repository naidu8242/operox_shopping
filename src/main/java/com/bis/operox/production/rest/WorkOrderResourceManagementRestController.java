package com.bis.operox.production.rest;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bis.operox.inv.dao.entity.Customer;
import com.bis.operox.inv.dao.entity.User;
import com.bis.operox.production.dao.entity.WorkOrderResourceManagement;
import com.bis.operox.production.service.WorkOrderResourceManagementService;
import com.bis.operox.util.OperoxSessionManager;

@RestController
public class WorkOrderResourceManagementRestController {

	private static final Logger logger = LoggerFactory.getLogger(WorkOrderResourceManagementRestController.class);
	
	@Autowired
	WorkOrderResourceManagementService  workOrderResourceManagementService;

	 @RequestMapping(value = "/saveResource" , method = RequestMethod.POST)
	 public @ResponseBody  String saveResource(@RequestParam(value="json", required=false) String json,
			                                   @RequestParam(value="workOrderId", required=false) Long workOrderId,HttpServletRequest request) throws Exception {
		 
		 User user = (User) OperoxSessionManager.getSessionAttribute(request, "user");
		 String json1 = "{" + json + "}";
		 JSONObject jsonObj = new JSONObject(json1);
		 WorkOrderResourceManagement workOrderResourceManagement = workOrderResourceManagementService.addWorkOrderResourceManagement(jsonObj,workOrderId,user);
        
		 return "success";
			 
	   }
	 
//	 saveResourceYYYYY
	 
	 
	 @RequestMapping(value = "/removeResource", method = RequestMethod.POST)
	 public @ResponseBody String removeResource(@RequestParam(value="userid", required=false)Long userid,HttpServletRequest request) throws Exception{
		/* User user = (User) OperoxSessionManager.getSessionAttribute(request, "user");*/
		 WorkOrderResourceManagement workOrderresource = workOrderResourceManagementService.getWorkOrderMgntByUserId(userid);
		 workOrderresource.setStatus(0);
		 workOrderResourceManagementService.deleteWorkResourceManagmentser(workOrderresource);
		 return "success";
		
		
	}

}
