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

import com.bis.operox.inv.dao.entity.StockReturns;
import com.bis.operox.inv.dao.entity.User;
import com.bis.operox.inv.service.StockReturnsService;
import com.bis.operox.util.CommonUtil;
import com.bis.operox.util.OperoxSessionManager;

@RestController
public class TransferStockRestController {
	
	@Autowired
	StockReturnsService stockReturnsService;
	
	@Autowired
	CommonUtil commonUtil;
	
	
	/**
	 * 
	 * @author Sarath Kumar 
	 * This method is to store Transfer Stock
	 * @param json
	 * @return {@link ResponseBody}
	 * @throws NullPointerException
	 */
	@RequestMapping(value = "/storeTransferStock" , method = RequestMethod.POST)
	 public @ResponseBody  String storeProject(@RequestParam(value="json", required=false) String json,@RequestParam(value="submissionType", required=false) String submissionType,HttpServletRequest request) throws Exception {
		   
		    User user = (User) OperoxSessionManager.getSessionAttribute(request, "user");
		    String json1 = "{"+json+"}";
		    JSONObject jsonObj = new JSONObject(json1);
		    StockReturns stockReturns =  stockReturnsService.addStockTransfer(jsonObj,submissionType,user);
		    return "success";		
	 }
	
	
	
	/**
	 * @author Sarath Kumar 
	 * 
	 * This method is to get Transfer List
	 * @return {@link String}
	 * @throws NullPointerException
	 */
	@RequestMapping(value = "/getTransferStockList", method = RequestMethod.POST)
    public String getTransferStockList() {  
		   
		   String resultJson;
    	   List<StockReturns>  transferList = stockReturnsService.getAllTransferStock();
    	   
    	   resultJson =  commonUtil.toJSON(transferList);
		   return resultJson;
	        
	}
	
	
	

}
