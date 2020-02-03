package com.bis.operox.inv.rest;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bis.operox.inv.dao.entity.Store;
import com.bis.operox.inv.dao.entity.Tax;
import com.bis.operox.inv.dao.entity.User;
import com.bis.operox.inv.service.TaxService;
import com.bis.operox.inv.service.UserService;
import com.bis.operox.util.CommonUtil;
import com.bis.operox.util.OperoxSessionManager;

@RestController
public class TaxRestController {
	
	@Autowired
	private TaxService taxService;
	
	@Autowired 
	private CommonUtil commonUtil;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/addTax",  method = RequestMethod.POST)
	public  String addTax(@RequestParam(value="json", required=false) String json, HttpServletRequest request) throws Exception {
	
		 User user = (User) OperoxSessionManager.getSessionAttribute(request, "user");
		 Store store = (Store) OperoxSessionManager.getSessionAttribute(request, "store");
         json = "{"+json+"}";
		 
		 JSONObject jsonObj = new JSONObject(json);
		 taxService.storeTax(jsonObj, user, store);
		return "taxHome";
	}
	
    @RequestMapping(value = "/getTaxList")
	@ResponseBody
	public String getTaxList(HttpServletRequest request) throws Exception {
    	Store store = (Store) OperoxSessionManager.getSessionAttribute(request, "store");
		List<Tax> taxList = taxService.getTaxListByStoreId(store.getId());
		ArrayList<Tax> taxesList = new ArrayList<Tax>();
		User user = null;
			for(Tax tax :taxList){
				if(tax.getCategory() != null){
					tax.setCategoryName(tax.getCategory().getCategoryName());
				}
				user  = userService.getUserByUserCode(tax.getUpdatedBy());
				if(user!=null){
					tax.setUpdatedBy(user.getFullName());
				}
				taxesList.add(tax);
			}
		String resultJson = commonUtil.toJSON(taxesList);
		return resultJson;
	}
	
	 @RequestMapping(value = "/removeTax", method = RequestMethod.POST)
	 public @ResponseBody String removeTax(@RequestParam(value = "taxId", required = false) Long taxId, HttpServletRequest request) throws Exception{
		   User user = (User) OperoxSessionManager.getSessionAttribute(request, "user");
		   taxService.removeTaxById(taxId,user.getUserCode());
	   
		   return "success";
	}
	 
	
	 @RequestMapping(value = "/getTaxByTaxName" , method = RequestMethod.POST)
	 public @ResponseBody  Boolean getTaxByTaxName(@RequestParam(value="taxName", required=false) String taxName, HttpServletRequest request) throws Exception {
		 	Boolean flag = false;
		 	Tax tax = taxService.getTaxByTaxName(taxName);
		    if(tax == null){
		    	flag = true;
		    }
			return flag;
	   }


}	

