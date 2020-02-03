package com.bis.operox.production.dao;

import java.util.List;

import com.bis.operox.production.dao.entity.WorkOrderMachineryManagement;

public interface WorkOrderMachineryManagementDao {

	WorkOrderMachineryManagement saveWorkOrderMachineryManagement(WorkOrderMachineryManagement womm);
	
	List<WorkOrderMachineryManagement> getAllWorkOrderMachineryManagementByWorkOrderId(Long workoderId) throws Exception;
	
	public WorkOrderMachineryManagement getWorkOrderMachineryManagementById(Long id);
	
	public void deleteWorkOrderMachineryManagement(WorkOrderMachineryManagement womm);
	
	public WorkOrderMachineryManagement deleteMachineryManagement(WorkOrderMachineryManagement workOrderMachineryManagement);
	

}
