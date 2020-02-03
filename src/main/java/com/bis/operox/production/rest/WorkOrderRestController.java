package com.bis.operox.production.rest;

import java.io.Writer;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bis.operox.constants.Constants;
import com.bis.operox.inv.dao.entity.Company;
import com.bis.operox.inv.dao.entity.User;
import com.bis.operox.production.dao.entity.WorkOrder;
import com.bis.operox.production.dao.entity.WorkOrderItems;
import com.bis.operox.production.service.WorkOrderItemsService;
import com.bis.operox.production.service.WorkOrderService;
import com.bis.operox.util.OperoxSessionManager;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.json.JsonHierarchicalStreamDriver;
import com.thoughtworks.xstream.io.json.JsonWriter;

@RestController
public class WorkOrderRestController {

	@Autowired
	WorkOrderService workOrderService;
	
	@Autowired
	WorkOrderItemsService workOrderItemsService;
	
	@RequestMapping(value = "/getWorkOrderList")
	@ResponseBody
	public String getWorkOrderList(HttpServletRequest request) {
		
	 	String resultJson = null;
		List<WorkOrder> workordersList = new ArrayList<WorkOrder>();
		ArrayList<WorkOrder> woList = new ArrayList<WorkOrder>();
		
		User user = (User) OperoxSessionManager.getSessionAttribute(request, "user");
		Company company = (Company) OperoxSessionManager.getSessionAttribute(request, "company");
		
		try {
			workordersList = workOrderService.getWorkOrderByCompanyId(company.getId());
			for(WorkOrder workOrder : workordersList){
				
				if(null != workOrder.getCustomer()){
					workOrder.setCustomerName(workOrder.getCustomer().getCustomerName());
				}
				
				
				if(null != workOrder.getOrderDate()){
					workOrder.setOrderDateStr(Constants.DF_DMY.format(workOrder.getOrderDate()));
				}
				
				if(null != workOrder.getDueDate()){
					workOrder.setDueDateStr(Constants.DF_DMY.format(workOrder.getDueDate()));
				}
				woList.add(workOrder);
			}
					
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		 resultJson = toJSON(woList);
		 return resultJson;

	}
	
	
	 @RequestMapping(value = "/saveWorkOrder" , method = RequestMethod.POST)
	 public @ResponseBody  String savePurchaseOrder(@RequestParam(value="json", required=false) String json,HttpServletRequest request) throws Exception {
		 
		 User user = (User) OperoxSessionManager.getSessionAttribute(request, "user");
		 String json1 = "{" + json + "}";
		 JSONObject jsonObj = new JSONObject(json1);
		 workOrderService.addWorkOrderDetails(jsonObj, user);
        return "Success";
			 
	   }
	 
	 
	 
	 @RequestMapping(value = "/removeWorkorder", method = RequestMethod.POST)
	 public @ResponseBody String removeWorkorder(@RequestParam(value = "workorderId", required = false) Long workorderId,HttpServletRequest request) throws Exception{
		
		 User user = (User) OperoxSessionManager.getSessionAttribute(request, "user");
		 WorkOrder workOrder = workOrderService.getWorkOrderById(workorderId);
		 workOrder.setStatus(0);
		 workOrder.setUpdatedDate(new Date());
		 workOrder.setUpdatedBy(user.getUserCode());
		 workOrderService.saveWorkOrder(workOrder);
		 
		 return "success";
		
		
	}
	 
	 
	 
	 @RequestMapping(value = "/validateWorkOrderNumber",  method = RequestMethod.POST)
		public  String validateWorkOrderNumber(@RequestParam(value="workOrderId", required=false) String workOrderId,HttpServletRequest request) throws Exception {
		
			 Company company = (Company) OperoxSessionManager.getSessionAttribute(request, "company");
			 WorkOrder workOrder = workOrderService.getWorkOrderByWorkOrderNumber(workOrderId, company.getId());
			 
			 if(workOrder == null){
				 return "valid";
			 }else{
				 return "invalid";
			 }
		}
	
	 @RequestMapping(value = "/getProductsList", method = RequestMethod.POST)
	 public @ResponseBody String getProductsList(@RequestParam(value = "workOrderId", required = false) Long workOrderId,HttpServletRequest request) throws Exception{
		
		 User user = (User) OperoxSessionManager.getSessionAttribute(request, "user");
		 List<WorkOrderItems> workOrderItemsList = workOrderItemsService.getProductsByworkorderId(workOrderId);
		 return toJSON(workOrderItemsList);
		
		
	}
	 
	private String toJSON(Object obj){
        XStream xstream = new XStream(new JsonHierarchicalStreamDriver() {
            public HierarchicalStreamWriter createWriter(Writer writer) {
                return new JsonWriter(writer, JsonWriter.DROP_ROOT_MODE);
            }
        });
        return xstream.toXML(obj);
    }
}
