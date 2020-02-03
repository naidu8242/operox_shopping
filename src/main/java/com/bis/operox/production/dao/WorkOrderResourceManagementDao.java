package com.bis.operox.production.dao;

import java.util.List;

import com.bis.operox.production.dao.entity.WorkOrderResourceManagement;

public interface WorkOrderResourceManagementDao {
	
	public WorkOrderResourceManagement addWorkOrderResourceManagement(WorkOrderResourceManagement workOrderResourceManagement) throws Exception;

	public List<WorkOrderResourceManagement> getWorkOrderMngmt(Long workOrderId);

	public WorkOrderResourceManagement getWorkOrderMgntByUserId(Long id);

	public List<WorkOrderResourceManagement> getWorkOrderContrctMngmt(Long workOrderId);

	public WorkOrderResourceManagement getWorkOrderResourceMangtById(Long workOrderResourceId);
	
	public WorkOrderResourceManagement deleteWorkResourceManagmentser(WorkOrderResourceManagement workOrderResourceManagement);
}
