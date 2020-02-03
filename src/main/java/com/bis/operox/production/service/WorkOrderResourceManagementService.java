package com.bis.operox.production.service;

import java.util.List;

import org.json.JSONObject;

import com.bis.operox.inv.dao.entity.User;
import com.bis.operox.production.dao.entity.WorkOrderResourceManagement;

public interface WorkOrderResourceManagementService {

	WorkOrderResourceManagement addWorkOrderResourceManagement(JSONObject jsonObj, Long workOrderId, User user) throws Exception;

	List<WorkOrderResourceManagement> getWorkOrderMngmt(Long workOrderId);

	List<WorkOrderResourceManagement> getWorkOrderContrctMngmt(Long workOrderId);
	
	public WorkOrderResourceManagement getWorkOrderMgntByUserId(Long id);
	
	public WorkOrderResourceManagement deleteWorkResourceManagmentser(WorkOrderResourceManagement workOrderResourceManagement);
	

}
