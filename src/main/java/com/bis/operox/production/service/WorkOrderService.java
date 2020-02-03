package com.bis.operox.production.service;

import java.util.List;

import org.json.JSONObject;

import com.bis.operox.inv.dao.entity.User;
import com.bis.operox.production.dao.entity.WorkOrder;

public interface WorkOrderService {
	
	List<WorkOrder> getWorkOrderByCompanyId(Long company) throws Exception;
	
	WorkOrder saveWorkOrder(WorkOrder workOrder);
	
	WorkOrder addWorkOrderDetails(JSONObject jsonObj,User user) throws Exception;
	
	WorkOrder getWorkOrderById(Long id);
	
	WorkOrder getWorkOrderByWorkOrderNumber(String workOrderNumber,Long companyId) throws Exception;
	

	List<WorkOrder>  listWorkOrders();

	WorkOrder getWorkOrderByWorkOrderId(Long workOrderId);
}
