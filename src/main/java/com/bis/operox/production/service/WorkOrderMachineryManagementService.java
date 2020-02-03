package com.bis.operox.production.service;

import java.util.List;

import org.json.JSONObject;

import com.bis.operox.inv.dao.entity.User;
import com.bis.operox.production.dao.entity.WorkOrderMachineryManagement;

public interface WorkOrderMachineryManagementService {

	WorkOrderMachineryManagement saveWorkOrderMachineryManagement(WorkOrderMachineryManagement womm);

	
	WorkOrderMachineryManagement addWorkOrderMachineryManagementDetails(JSONObject jsonObj,User user) throws Exception;
	
	List<WorkOrderMachineryManagement> getAllWorkOrderMachineryManagementByWorkOrderId(Long workoderId) throws Exception;
	
	public WorkOrderMachineryManagement deleteMachineryManagement(WorkOrderMachineryManagement workOrderMachineryManagement);
	
	public WorkOrderMachineryManagement getWorkOrderMachineryManagementById(Long id);

}
