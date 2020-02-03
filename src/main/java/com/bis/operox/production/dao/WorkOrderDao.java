package com.bis.operox.production.dao;

import java.util.List;

import com.bis.operox.production.dao.entity.WorkOrder;

public interface WorkOrderDao {
	
	List<WorkOrder> getWorkOrderByCompanyId(Long companyId) throws Exception;
	
	WorkOrder saveWorkOrder(WorkOrder workOrder);
	
	WorkOrder getWorkOrderById(Long id);
	
	WorkOrder getWorkOrderByWorkOrderNumber(String workOrderNumber,Long companyId) throws Exception;

	List<WorkOrder> listWorkOrders();

	WorkOrder getWorkOrderByWorkOrderId(Long workOrderId);

}
