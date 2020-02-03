package com.bis.operox.inv.rest;

import java.io.Writer;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bis.operox.inv.dao.SupplierDao;
import com.bis.operox.inv.dao.entity.PurchaseOrder;
import com.bis.operox.inv.dao.entity.PurchaseOrderItems;
import com.bis.operox.inv.dao.entity.ReceivedStock;
import com.bis.operox.inv.dao.entity.Supplier;
import com.bis.operox.inv.dao.entity.User;
import com.bis.operox.inv.service.AccountPayableService;
import com.bis.operox.inv.service.PurchaseOrderService;
import com.bis.operox.inv.service.ReceivedStockService;
import com.bis.operox.inv.service.SupplierService;
import com.bis.operox.util.OperoxSessionManager;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.json.JsonHierarchicalStreamDriver;
import com.thoughtworks.xstream.io.json.JsonWriter;

@RestController
public class ReceviedItemRestController {
	
	
	@Autowired
	ReceivedStockService receivedStockService;
	
	@Autowired
	PurchaseOrderService purchaseOrderService;
	
	@Autowired
	AccountPayableService accountPayableService;
	
	@Autowired
	SupplierService supplierService ;
	
	
	@RequestMapping(value = "/getReceivedItemList", method = RequestMethod.POST)
    public String PurchaseOrderList() {  
		   
		   String resultJson;
    	   List<ReceivedStock>  receivedStockList = receivedStockService.getAllReceivedStocks();
    	   resultJson =  toJSON(receivedStockList);
		   return resultJson;
	        
	 
	}
	
	public String toJSON(Object obj){
        XStream xstream = new XStream(new JsonHierarchicalStreamDriver() {
            public HierarchicalStreamWriter createWriter(Writer writer) {
                return new JsonWriter(writer, JsonWriter.DROP_ROOT_MODE);
            }
        });
        return xstream.toXML(obj);
    }
 
	 @RequestMapping(value = "/saveReceivedProduct" , method = RequestMethod.GET)
	 public @ResponseBody  String saveReceivedProduct(@RequestParam(value="json", required=false) String json,@RequestParam(value="submissionType", required=false) String submissionType,HttpServletRequest request) throws Exception {
		 
		 User user = (User) OperoxSessionManager.getSessionAttribute(request, "user");
		 String json1 = "{" + json + "}";
		 JSONObject jsonObj = new JSONObject(json1);
		 jsonObj.append("submissionType", submissionType);
		 ReceivedStock receivedStock = receivedStockService.addReceivedStock(jsonObj,submissionType,user);
         return receivedStock.getId().toString();
			 
	   }
	 
	 @RequestMapping(value = "/saveAccountPayable" , method = RequestMethod.POST)
	 public @ResponseBody  String saveAccountPayable(@RequestParam(value="json", required=false) String json,HttpServletRequest request) throws Exception {
		 
		 User user = (User) OperoxSessionManager.getSessionAttribute(request, "user");
		 String json1 = "{" + json + "}";
		 JSONObject jsonObj = new JSONObject(json1);
		 accountPayableService.addAccountPayableDetails(jsonObj, user);
         return "Success";
			 
	   }
	 
	 
	 @RequestMapping(value = "/getSupplierBypurchaseOrderId" , method = RequestMethod.POST)
	 public @ResponseBody  String getSupplierBypurchaseOrderId(@RequestParam(value="purchaseOrderId", required=false) Long purchaseOrderId,HttpServletRequest request) throws Exception {
		 
		 String resultJson;
		 PurchaseOrder purchaseOrder =  purchaseOrderService.getPurchaseOrderById(purchaseOrderId);
		 
		 resultJson =  toJSON(purchaseOrder);
		 return resultJson;
			 
	   }
	 
	 @RequestMapping(value = "/getSupplierListInRecievedItem" , method = RequestMethod.POST)
	 public @ResponseBody  String getSupplierListInRecievedItem(HttpServletRequest request) throws Exception {
		 
		 String resultJson;
		 
		 List<Supplier> supplierList =  supplierService.getAllSupplier();
		 
		 resultJson =  toJSON(supplierList);
		 return resultJson;
			 
	   }
	 
	 
	 
}
