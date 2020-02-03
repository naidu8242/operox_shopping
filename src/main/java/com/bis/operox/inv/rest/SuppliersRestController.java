package com.bis.operox.inv.rest;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bis.operox.constants.Constants;
import com.bis.operox.inv.dao.entity.Supplier;
import com.bis.operox.inv.dao.entity.User;
import com.bis.operox.inv.service.SupplierService;
import com.bis.operox.inv.service.UserService;
import com.bis.operox.util.CommonUtil;
import com.bis.operox.util.OperoxSessionManager;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@PropertySource("${propertyLocation:app.properties}")
public class SuppliersRestController {
	
	@Value("${operoxUrl}")
	private String operoxUrl;
	
	@Autowired
	SupplierService supplierService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	CommonUtil commonUtil;
	
	/**
	 * This method is used for to store supplier details in to Supplier Table,Address Table
	 * @author prasad K
	 * @param json
	 * @param request
	 * @return
	 * @throws Exception
	 */
	
	@RequestMapping(value = "/storeSupplier", method = RequestMethod.POST)
	public @ResponseBody String storeSupplier(@RequestParam(value = "json", required = false) String json, HttpServletRequest request) throws Exception {
		
		User user = (User) OperoxSessionManager.getSessionAttribute(request, "user");
	    
		String jsonc = "{" + json + "}";
		JSONObject jsonObj = new JSONObject(jsonc);
		
		Supplier supplier = supplierService.addSupplier(jsonObj,user);
		
		return "success";
		
	}
	
	/**
	 * @author Venkat Kesav
	 * @param The First Parameter of the getSuppliersListByStatus is status
	 * @return resultJson
	 * @throws Exception
	 */
	@RequestMapping(value = "/getSuppliersListByStatus", method = RequestMethod.POST)
    public String getSuppliersListByStatus(@RequestParam(value="status", required=false) String status) throws Exception {  
		
		int supplierStatus;
		if(StringUtils.isNotEmpty(status) && StringUtils.equals(status, "Active")){
			supplierStatus = 1;
		}else if(StringUtils.isNotEmpty(status) && StringUtils.equals(status, "IN-Active")){
			supplierStatus = 0;
		}else{
			supplierStatus = 1;
		}
    	   List<Supplier>  purchaseList = supplierService.getAllSupplierByStatus(supplierStatus);
    	   
    	   for(Supplier supplier : purchaseList){
    		   User createdBy = userService.getUserByUserCode(supplier.getCreatedBy());
    		   
    		   String createdByName = "";
    		   if(StringUtils.isNotEmpty(createdBy.getFirstName())){
    			   createdByName = createdBy.getFirstName();
    		   }
    		   if(StringUtils.isNotEmpty(createdBy.getLastName())){
    			   createdByName = createdByName+" "+createdBy.getLastName();
    		   }
    		   supplier.setCreatedBy(createdByName);
    	   }
    	   
    	   ObjectMapper mapper = new ObjectMapper();
    	   String resultJson = mapper.writeValueAsString(purchaseList);
		   return resultJson;
	}
	
	/**
	* @author Venkat Kesav 
	* 
	* This method is used to terminate Supplier
	* 
	* @param supplierId
	* @param request
	* 
	* @return resultJson
	* 
	* @throws Exception
	*/
	@RequestMapping(value = "/terminateSupplier", method = RequestMethod.POST)
	public @ResponseBody String terminateSupplier(String supplierId, HttpServletRequest request) throws Exception {
		
		User user = (User) OperoxSessionManager.getSessionAttribute(request, "user");
		
		Supplier supplier =  supplierService.getSupplierById(Long.parseLong(supplierId));
		if(supplier != null){
			supplier.setStatus(Constants.IN_Active);
			supplier.setUpdatedBy(user.getUserCode());
			supplier.setUpdatedDate(commonUtil.currentDate());
			supplierService.saveOrUpdateSupplier(supplier);
		}
		return "success";
	}
	
	
		/**
		 * @author Prasad K
		 * Handles SupplierName duplicate or not
		 * @param SupplierName
		 * @param Id
		 * @return boolean
		 * @throws Exception
		 */
		@RequestMapping(value = "/isSupplierNameValidated", method = RequestMethod.GET)
		@ResponseBody boolean isSupplierNameValidated(
				@RequestParam(value = "supplierName", required = false) String supplierName,HttpServletRequest request,
				@RequestParam(value = "id", required = false) Long id) throws Exception {
			return supplierService.isSupplierNameValid(supplierName, id);
		}
		
		
}
