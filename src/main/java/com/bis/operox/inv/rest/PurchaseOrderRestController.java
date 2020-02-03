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

import com.bis.operox.inv.dao.entity.PurchaseOrder;
import com.bis.operox.inv.dao.entity.PurchaseOrderItems;
import com.bis.operox.inv.dao.entity.User;
import com.bis.operox.inv.dao.enums.Stock_Status;
import com.bis.operox.inv.dao.enums.Submission_Status;
import com.bis.operox.inv.service.PurchaseOrderItemsService;
import com.bis.operox.inv.service.PurchaseOrderService;
import com.bis.operox.util.CommonUtil;
import com.bis.operox.util.OperoxSessionManager;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.json.JsonHierarchicalStreamDriver;
import com.thoughtworks.xstream.io.json.JsonWriter;

@RestController
public class PurchaseOrderRestController {
	@Autowired
	PurchaseOrderService purchaseOrderService; 
	
	@Autowired 
	PurchaseOrderItemsService purchaseOrderItemsService;
	
	@RequestMapping(value = "/getPurchaseOrderList", method = RequestMethod.POST)
    public String PurchaseOrderList() throws Exception {  
		   
		   String resultJson;
    	   List<PurchaseOrder>  purchaseList = purchaseOrderService.getAllPurchaseOrders();
    	   
    	   resultJson =  toJSON(purchaseList);
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
	
	
	
	
	
	

	/**
	 * @author Sammeta David Raju
	 * @param The First Parameter of the savePurchaseOrder is json
	 * @param The First Parameter of the savePurchaseOrder is request
	 * @return {@link ResponseBody}
	 * @throws Exception
	 */
	 @RequestMapping(value = "/savePurchaseOrder" , method = RequestMethod.POST)
	 public @ResponseBody  String savePurchaseOrder(@RequestParam(value="json", required=false) String json,@RequestParam(value="submissionType", required=false) String submissionType,HttpServletRequest request) throws Exception {
		 
		 User user = (User) OperoxSessionManager.getSessionAttribute(request, "user");
		 String json1 = "{" + json + "}";
		 JSONObject jsonObj = new JSONObject(json1);
		 jsonObj.append("submissionType", submissionType);
		 purchaseOrderService.addPurchaseOrder(jsonObj,user,submissionType);
         return "Success";
			 
	   }
	 
	 @RequestMapping(value = "/getPurchaseOrderItems" , method = RequestMethod.POST)
	 public @ResponseBody  String getPurchaseOrderItems(@RequestParam(value="purchaseOrderId", required=false) Long purchaseOrderId,HttpServletRequest request) throws Exception {
		 
		 String resultJson;
		 List<PurchaseOrderItems>  purchaseOrderItemsList =  purchaseOrderItemsService.getAllPurchaseOrderItemsByPurchaseOrderId(purchaseOrderId);
		 for(PurchaseOrderItems purchaseOrderItems : purchaseOrderItemsList){
			 purchaseOrderItems.setProductName(purchaseOrderItems.getProduct().getProductName());
			 purchaseOrderItems.setProductId(purchaseOrderItems.getProduct().getId().toString());
		 }
		 resultJson =  toJSON(purchaseOrderItemsList);
		 return resultJson;
			 
	   }
	 
	 
	 
	 
	 
	 @RequestMapping(value = "/changeOrderStatus" , method = RequestMethod.POST)
	 public @ResponseBody  String changeOrderStatus(@RequestParam(value="orderStatus", required=false) String orderStatus,@RequestParam(value="orderId", required=false) String orderId,HttpServletRequest request) throws Exception {
		 
		 User user = (User) OperoxSessionManager.getSessionAttribute(request, "user");
		 PurchaseOrder purchaseOrder = purchaseOrderService.getPurchaseOrderById(Long.parseLong(orderId));
		 if(orderStatus != null && !orderStatus.isEmpty()){
	    		if("pending".equalsIgnoreCase(orderStatus)){
	    			purchaseOrder.setOrderStatus(Stock_Status.getNameByValue("pending"));
	    		}
	    		if("complete".equalsIgnoreCase(orderStatus)){
	    			purchaseOrder.setOrderStatus(Stock_Status.getNameByValue("complete"));
	    		}
	     	}
		 purchaseOrder.setUpdatedBy(user.getUserCode());
		 purchaseOrder.setUpdatedBy(user.getUserCode());
		 purchaseOrder.setUpdatedDate(CommonUtil.currentDate());;
		 purchaseOrderService.saveOrUpdatePurchaseOrder(purchaseOrder);
         return "Success";
	   }

}
