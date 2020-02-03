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
import com.bis.operox.production.service.WorkOrderRawMaterialService;
import com.bis.operox.util.OperoxSessionManager;

@RestController
public class WorkOrderRawMaterialRestController {
	
	@Autowired
	private WorkOrderRawMaterialService workOrderRawMaterialService;

	 @RequestMapping(value = "/saveworkOrderRawMaterial" , method = RequestMethod.POST)
	 public @ResponseBody  String saveworkOrderRawMaterial(@RequestParam(value="json", required=false) String json,HttpServletRequest request) throws Exception {
		 
		 User user = (User) OperoxSessionManager.getSessionAttribute(request, "user");
		 String json1 = "{" + json + "}";
		 JSONObject jsonObj = new JSONObject(json1);
		 workOrderRawMaterialService.addWorkOrderRawMaterialDetails(jsonObj, user);
        return "Success";
			 
	   }
}
