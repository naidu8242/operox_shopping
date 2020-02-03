package com.bis.operox.production.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bis.operox.constants.Constants;
import com.bis.operox.inv.dao.UserDao;
import com.bis.operox.inv.dao.entity.StockReturns;
import com.bis.operox.inv.dao.entity.Supplier;
import com.bis.operox.inv.dao.entity.User;
import com.bis.operox.production.dao.WorkOrderDao;
import com.bis.operox.production.dao.WorkOrderMachineryManagementDao;
import com.bis.operox.production.dao.entity.WorkOrder;
import com.bis.operox.production.dao.entity.WorkOrderMachineryManagement;
import com.bis.operox.production.dao.entity.WorkOrderRawMaterial;
import com.bis.operox.production.service.WorkOrderMachineryManagementService;

@Service
public class WorkOrderMachineryManagementServiceImpl implements WorkOrderMachineryManagementService{

	@Autowired
	UserDao userDao;
	
	@Autowired
	WorkOrderDao workOrderDao;
	
	@Autowired
	WorkOrderMachineryManagementDao workOrderMachineryManagementDao;
	
	
	@Override
	public WorkOrderMachineryManagement saveWorkOrderMachineryManagement(WorkOrderMachineryManagement womm) {
		return workOrderMachineryManagementDao.saveWorkOrderMachineryManagement(womm);
	}

	@Override
	public WorkOrderMachineryManagement addWorkOrderMachineryManagementDetails(JSONObject jsonObj, User user) throws Exception {
		
		
		WorkOrderMachineryManagement womm= null;
		
		String pattern = "MM/dd/yyyy";
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		
		if(jsonObj.has("id") && !jsonObj.getString("id").isEmpty()){
			String ids = jsonObj.getString("id");
			ids = ids.replaceAll("\\[", "").replaceAll("\\]","");
			String[] workOrderMachineryManagementIds = ids.split(",");
			for(int i=0 ;i<workOrderMachineryManagementIds.length;i++ ){
				WorkOrderMachineryManagement oldWorkOrderMachineryManagement = workOrderMachineryManagementDao.getWorkOrderMachineryManagementById(Long.parseLong(workOrderMachineryManagementIds[i].replace("\"", "")));
				workOrderMachineryManagementDao.deleteWorkOrderMachineryManagement(oldWorkOrderMachineryManagement);
			}
		}
		
		
		
		String maxMachineryRowNum = jsonObj.getString("maxMachineryRowNum");
		int maxCount = Integer.parseInt(maxMachineryRowNum);
		for (long c = 1; c < maxCount; c++) {
			
			if(jsonObj.has("workOrderId") && jsonObj.getString("workOrderId") != null && !"".equals(jsonObj.getString("workOrderId"))){
			if(jsonObj.has(c+"startDate") && !jsonObj.getString(c+"startDate").isEmpty()){
				womm = new WorkOrderMachineryManagement();
			
			
		if(jsonObj.has(c+"machineryId") && jsonObj.getString(c+"machineryId") != null && !"".equals(jsonObj.getString(c+"machineryId"))){
			womm.setMachineryId(jsonObj.getString(c+"machineryId"));
			}
		
		if(jsonObj.has(c+"machineryName") && jsonObj.getString(c+"machineryName") != null && !"".equals(jsonObj.getString(c+"machineryName"))){
		womm.setMachineryName(jsonObj.getString(c+"machineryName"));
		}
		
		if(jsonObj.has(c+"startDate") && jsonObj.getString(c+"startDate") != null && !"".equals(jsonObj.getString(c+"startDate"))){
			Date startDate = format.parse(jsonObj.getString(c+"startDate"));
			womm.setStartDate(startDate);
		}
		
		if(jsonObj.has(c+"endDate") && jsonObj.getString(c+"endDate") != null && !"".equals(jsonObj.getString(c+"endDate"))){
			Date endDate = format.parse(jsonObj.getString(c+"endDate"));
			womm.setEndDate(endDate);
		}
		
		if(jsonObj.has(c+"totalHours") && jsonObj.getString(c+"totalHours") != null && !"".equals(jsonObj.getString(c+"totalHours"))){
		womm.setTotalHours(jsonObj.getString(c+"totalHours"));
		}
		
		if(jsonObj.has("workOrderId") && jsonObj.getString("workOrderId") != null && !"".equals(jsonObj.getString("workOrderId"))){
			WorkOrder wo =new WorkOrder();
			wo.setId(Long.parseLong(jsonObj.getString("workOrderId")));
			womm.setWorkOrder(wo);
		}
		
		
		
		womm.setStatus(Constants.RECORD_ACTIVE);
		womm.setCreatedBy(user.getUserCode());
		womm.setCreatedDate(new Date());
		womm = workOrderMachineryManagementDao.saveWorkOrderMachineryManagement(womm);
		
			}
			
		}
			continue;
			}	
	   return womm;
		
	}

	@Override
	public List<WorkOrderMachineryManagement> getAllWorkOrderMachineryManagementByWorkOrderId(Long workoderId)
			throws Exception {
		
		 List<WorkOrderMachineryManagement> workOrderMachineryManagementsList  = workOrderMachineryManagementDao.getAllWorkOrderMachineryManagementByWorkOrderId(workoderId);
		for(WorkOrderMachineryManagement wormm :workOrderMachineryManagementsList){
			
			if(wormm.getStartDate()!=null){
				wormm.setStartDateStr(Constants.DF_MDY.format(wormm.getStartDate()));
			}
			
			if(wormm.getEndDate()!=null){
				wormm.setEndDateStr(Constants.DF_MDY.format(wormm.getEndDate()));
			}
		 }
		
		return workOrderMachineryManagementsList;
	}

	@Override
	public WorkOrderMachineryManagement deleteMachineryManagement(
			WorkOrderMachineryManagement workOrderMachineryManagement) {
		return workOrderMachineryManagementDao.deleteMachineryManagement(workOrderMachineryManagement);
	}

	@Override
	public WorkOrderMachineryManagement getWorkOrderMachineryManagementById(Long id) {
		return workOrderMachineryManagementDao.getWorkOrderMachineryManagementById(id);
	}

	

}
