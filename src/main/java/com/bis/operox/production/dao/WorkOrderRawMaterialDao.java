package com.bis.operox.production.dao;

import java.util.List;

import com.bis.operox.production.dao.entity.WorkOrderRawMaterial;

public interface WorkOrderRawMaterialDao {

	WorkOrderRawMaterial saveworkOrderRawMaterial(WorkOrderRawMaterial workOrderRawMaterial);
	
	List<WorkOrderRawMaterial> getAllWorkOrderRawMaterials() throws Exception;
	
	List<WorkOrderRawMaterial> getAllWorkOrderRawMaterialsByWorkOrderId(Long workoderId) throws Exception;
	
	public WorkOrderRawMaterial getWorkOrderRawMaterialById(Long id);
	
	public void deleteWorkOrderRawMaterial(WorkOrderRawMaterial workOrderRawMaterial);

}
