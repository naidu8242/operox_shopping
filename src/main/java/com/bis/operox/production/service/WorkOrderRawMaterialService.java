package com.bis.operox.production.service;

import java.util.List;

import org.json.JSONObject;

import com.bis.operox.inv.dao.entity.User;
import com.bis.operox.production.dao.entity.WorkOrderRawMaterial;

public interface WorkOrderRawMaterialService {
	
	WorkOrderRawMaterial addWorkOrderRawMaterialDetails(JSONObject jsonObj,User user) throws Exception;
	
	List<WorkOrderRawMaterial> getAllWorkOrderRawMaterials() throws Exception;
	
	List<WorkOrderRawMaterial> getAllWorkOrderRawMaterialsByWorkOrderId(Long workoderId) throws Exception;
	

}
