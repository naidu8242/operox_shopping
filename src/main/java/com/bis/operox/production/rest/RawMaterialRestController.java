package com.bis.operox.production.rest;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bis.operox.constants.Constants;
import com.bis.operox.inv.dao.entity.User;
import com.bis.operox.inv.service.UserService;
import com.bis.operox.production.dao.entity.RawMaterial;
import com.bis.operox.production.service.RawMaterialService;
import com.bis.operox.util.CommonUtil;
import com.bis.operox.util.OperoxSessionManager;
import com.fasterxml.jackson.databind.ObjectMapper;


@RestController
@PropertySource("${propertyLocation:operoxapp.properties}")
public class RawMaterialRestController {
	
	private static final Logger logger = LoggerFactory.getLogger(RawMaterialRestController.class);
	
	@Value("${operoxUrl}")
	private String operoxUrl;
	
	@Autowired
	UserService userService;
	
	@Autowired
	RawMaterialService rawMaterialService;
	
	@Autowired
	CommonUtil commonUtil;
	
	@RequestMapping(value = "/saveRawMaterial")
	@ResponseBody
	String saveRawMaterial(@RequestParam(value = "json", required = false) String json,HttpServletRequest request) throws Exception{
		String json1 = "{" + json + "}";
		
		 JSONObject jsonObj = new JSONObject(json1);
		 User user = (User) OperoxSessionManager.getSessionAttribute(request, "user");
		 rawMaterialService.addRawMaterialDetails(jsonObj,user);
		return "rawMaterialHome";
	}
	
	/**
	 * @author Prasad K
	 * @param The First Parameter of the getAllRawMaterialByStatus is status
	 * @return resultJson
	 * @throws Exception
	 */
	@RequestMapping(value = "/getAllRawMaterialByStatus", method = RequestMethod.POST)
    public String getAllRawMaterialByStatus(@RequestParam(value="status", required=false) String status) throws Exception {  
		
		int rawMaterialStatus;
		if(StringUtils.isNotEmpty(status) && StringUtils.equals(status, "Active")){
			rawMaterialStatus = 1;
		}else if(StringUtils.isNotEmpty(status) && StringUtils.equals(status, "IN-Active")){
			rawMaterialStatus = 0;
		}else{
			rawMaterialStatus = 1;
		}
    	   List<RawMaterial>  rawMaterialsList = rawMaterialService.getAllRawMaterialByStatus(rawMaterialStatus);
    	   
    	   for(RawMaterial rm : rawMaterialsList){
    		   User createdBy = userService.getUserByUserCode(rm.getCreatedBy());
    		   
    		   String createdByName = "";
    		   if(StringUtils.isNotEmpty(createdBy.getFirstName())){
    			   createdByName = createdBy.getFirstName();
    		   }
    		   if(StringUtils.isNotEmpty(createdBy.getLastName())){
    			   createdByName = createdByName+" "+createdBy.getLastName();
    		   }
    		   rm.setCreatedBy(createdByName);
    	   }
    	   
    	   ObjectMapper mapper = new ObjectMapper();
    	   String resultJson = mapper.writeValueAsString(rawMaterialsList);
		   return resultJson;
	}
	
	/**
	* @author Prasad K 
	* 
	* This method is used to terminate Raw Material
	* 
	* @param supplierId
	* @param request
	* 
	* @return resultJson
	* 
	* @throws Exception
	*/
	@RequestMapping(value = "/terminateRawMaterial", method = RequestMethod.POST)
	public @ResponseBody String terminateRawMaterial(String rawMaterialId, HttpServletRequest request) throws Exception {
		
		User user = (User) OperoxSessionManager.getSessionAttribute(request, "user");
		
		RawMaterial rawMaterial =  rawMaterialService.getRawMaterialById(Long.parseLong(rawMaterialId));
		if(rawMaterial != null){
			rawMaterial.setStatus(Constants.IN_Active);
			rawMaterial.setUpdatedBy(user.getUserCode());
			rawMaterial.setUpdatedDate(commonUtil.currentDate());
			rawMaterialService.saveOrUpdateRawMaterial(rawMaterial);
		}
		return "success";
	}
	
	/**
	 * @author Prasad K
	 * Handles RawMaterialCode duplicate or not
	 * @param RawMaterialCode
	 * @param Id
	 * @return boolean
	 * @throws Exception
	 */
	@RequestMapping(value = "/isRawMaterialCodeValidated", method = RequestMethod.GET)
	@ResponseBody boolean isRawMaterialCodeValid(
			@RequestParam(value = "rawMaterialCode", required = false) String rawMaterialCode,HttpServletRequest request,
			@RequestParam(value = "id", required = false) Long id) throws Exception {
		return rawMaterialService.isRawMaterialCodeValid(rawMaterialCode, id);
	}
}
