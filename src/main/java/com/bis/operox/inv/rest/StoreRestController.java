package com.bis.operox.inv.rest;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bis.operox.inv.dao.entity.Counter;
import com.bis.operox.inv.dao.entity.Store;
import com.bis.operox.inv.dao.entity.User;
import com.bis.operox.inv.service.StoreService;
import com.bis.operox.util.CommonUtil;
import com.bis.operox.util.OperoxSessionManager;

@RestController
public class StoreRestController {
	
	@Autowired
	private StoreService storeService;
	
	@Autowired 
	private CommonUtil commonUtil;
	
	@RequestMapping(value = "/addStore",  method = RequestMethod.POST)
	public  String addStore(@RequestParam(value="json", required=false) String json, HttpServletRequest request) throws Exception {
	
		 User user = (User) OperoxSessionManager.getSessionAttribute(request, "user");
         json = "{"+json+"}";
		 
		 JSONObject jsonObj = new JSONObject(json);
		 storeService.addStoreDetails(jsonObj, user);
		
		return "storeHome";
	}
	
	@RequestMapping(value = "/getStoresList")
	@ResponseBody
	public String getStoresList(HttpServletRequest request) throws Exception {
		List<Store> storesList = storeService.listStores();
		String resultJson = commonUtil.toJSON(storesList);
		return resultJson;
	}
	
	 @RequestMapping(value = "/removeStore", method = RequestMethod.POST)
	 public @ResponseBody String removeStore(@RequestParam(value = "storeId", required = false) Long storeId, HttpServletRequest request) throws Exception{
		   User user = (User) OperoxSessionManager.getSessionAttribute(request, "user");
	       storeService.removeStoreById(storeId,user.getUserCode());
		   return "success";
	}
	 
	 @RequestMapping(value = "/validateStoreName" , method = RequestMethod.POST)
	 public @ResponseBody  Boolean validateStoreName(@RequestParam(value="storeName", required=false) String storeName, @RequestParam(value="storeId", required=false) String storeId, HttpServletRequest request) throws Exception {
		 	Boolean flag = false;
		 	Store store = storeService.getStoreByStoreIdAndStoreName(storeId,storeName);
		    if(store == null){
		    	flag = storeService.validateStoreName(storeName);
		    }
			return flag;
	   }
	 
	 @RequestMapping(value = "/validateStoreEmail" , method = RequestMethod.POST)
	 public @ResponseBody  Boolean validateStoreEmail(@RequestParam(value="email", required=false) String email, @RequestParam(value="storeId", required=false) String storeId, HttpServletRequest request) throws Exception {
		 	Boolean flag = false;
		 	Store store = storeService.getStoreByStoreIdAndStoreEmail(storeId, email);
		    if(store == null){
		    	flag = storeService.validateStoreEmail(email);
		    }
			return flag;
	   }
	 	 
	

}
