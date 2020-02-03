package com.bis.operox.production.rest;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bis.operox.inv.dao.entity.User;
import com.bis.operox.production.dao.entity.WorkOrderMachineryManagement;
import com.bis.operox.production.service.WorkOrderMachineryManagementService;
import com.bis.operox.util.OperoxSessionManager;

@RestController
public class WorkOrderMachineryManagementRestController {
	
	@Autowired
	private WorkOrderMachineryManagementService workOrderMachineryManagementService;

	 @RequestMapping(value = "/saveMachinery" , method = RequestMethod.POST)
	 public @ResponseBody  String saveMachinery(@RequestParam(value="json", required=false) String json,HttpServletRequest request) throws Exception {
		 
		 User user = (User) OperoxSessionManager.getSessionAttribute(request, "user");
		 String json1 = "{" + json + "}";
		 JSONObject jsonObj = new JSONObject(json1);
		 workOrderMachineryManagementService.addWorkOrderMachineryManagementDetails(jsonObj, user);
        return "Success";
			 
	   }
	 
	 @RequestMapping(value = "/removeMachinery", method = RequestMethod.POST)
	 public @ResponseBody String removeMachinery(@RequestParam(value="id", required=false)Long id,HttpServletRequest request) throws Exception{
		/* User user = (User) OperoxSessionManager.getSessionAttribute(request, "user");*/
		 WorkOrderMachineryManagement wmm = workOrderMachineryManagementService.getWorkOrderMachineryManagementById(id);
		 wmm.setStatus(0);
		 workOrderMachineryManagementService.deleteMachineryManagement(wmm);
		 return "success";
		
		
	}
}
