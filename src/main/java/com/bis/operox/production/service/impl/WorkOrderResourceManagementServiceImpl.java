package com.bis.operox.production.service.impl;

import java.util.Date;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bis.operox.inv.dao.entity.User;
import com.bis.operox.inv.service.UserService;
import com.bis.operox.production.dao.WorkOrderResourceManagementDao;
import com.bis.operox.production.dao.entity.WorkOrderItems;
import com.bis.operox.production.dao.entity.WorkOrderResourceManagement;
import com.bis.operox.production.service.WorkOrderResourceManagementService;
import com.bis.operox.util.CommonUtil;

@Service
public class WorkOrderResourceManagementServiceImpl implements WorkOrderResourceManagementService {
	
		@Autowired
		private CommonUtil commonUtil;
		
		@Autowired
		private WorkOrderResourceManagementDao workOrderResourceManagementDao;
		
		@Autowired
		private UserService userService;
		
	@Override
	public WorkOrderResourceManagement addWorkOrderResourceManagement(JSONObject jsonObj,Long workOrderID,User user) throws Exception {
		
		WorkOrderResourceManagement workOrderResourceManagement = null;
		
		if("1".equalsIgnoreCase(jsonObj.getString("resourceType"))){
			String maxTsRowNum = jsonObj.getString("maxTsRowNum");
			int maxCount = Integer.parseInt(maxTsRowNum);
			for(int i=1;i<maxCount;i++){
				User resorceUser = userService.getUserById(Long.parseLong(jsonObj.getString(i+"userId")));
				if(resorceUser != null){
					workOrderResourceManagement = workOrderResourceManagementDao.getWorkOrderMgntByUserId(resorceUser.getId());
					if(workOrderResourceManagement == null){
						workOrderResourceManagement = new WorkOrderResourceManagement();
					}
				}else{
					workOrderResourceManagement = new WorkOrderResourceManagement();
				}
				WorkOrderItems workOrderItems = new WorkOrderItems();
				workOrderItems.setId(workOrderID);
				workOrderResourceManagement.setWorkOrderItems(workOrderItems);
				workOrderResourceManagement.setType("fulltime");
			
				if(jsonObj.has(i+"userId") && jsonObj.getString(i+"userId") != null && !"".equalsIgnoreCase(jsonObj.getString(i+"userId"))){
					workOrderResourceManagement.setUser(resorceUser);
				}
				
		    if(jsonObj.has(i+"resourceName") && jsonObj.getString(i+"resourceName") != null && !"".equalsIgnoreCase(jsonObj.getString(i+"resourceName"))){
				workOrderResourceManagement.setResourceName(jsonObj.getString(i+"resourceName"));
			}
			
			if(jsonObj.has(i+"startdate") && jsonObj.getString(i+"startdate") != null && !"".equalsIgnoreCase(jsonObj.getString(i+"startdate"))){
				workOrderResourceManagement.setStartDate(commonUtil.getDateFormat(jsonObj.getString(i+"startdate")));
			}
			
			if(jsonObj.has(i+"enddate") && jsonObj.getString(i+"enddate") != null && !"".equalsIgnoreCase(jsonObj.getString(i+"enddate"))){
				workOrderResourceManagement.setEndDate(commonUtil.getDateFormat(jsonObj.getString(i+"enddate")));
			}
			if(jsonObj.has(i+"totalHours") && jsonObj.getString(i+"totalHours") != null && !"".equalsIgnoreCase(jsonObj.getString(i+"totalHours"))){
				workOrderResourceManagement.setTotalHours(jsonObj.getString(i+"totalHours")); 
			}
				workOrderResourceManagement.setStatus(1);
				workOrderResourceManagement.setCreatedDate(new Date());
				workOrderResourceManagement.setUpdatedDate(new Date());
				workOrderResourceManagement.setCreatedBy(user.getUserCode());
				workOrderResourceManagement.setUpdatedBy(user.getUserCode());
				
				workOrderResourceManagementDao.addWorkOrderResourceManagement(workOrderResourceManagement);
			}
		}else{
//			Hai"maxTsRowNum":"1","type":"0","radio":"0","maxTsRowNum1":"3","1contractName":"a",
//			"1contractStartdate":"10/04/2016","1contractEnddate":"10/04/2016","1contractTotalHours":"4","2contractName":"b","2contractStartdate":"10/25/2016","2contractEnddate":"10/25/2016","2contractTotalHours":"4"
			String maxTsRowNum = jsonObj.getString("maxTsRowNum1");
			int maxCount = Integer.parseInt(maxTsRowNum);
			for(int i=1;i<maxCount;i++){
				if(jsonObj.has(i+"workOrderResourceId") && jsonObj.getString(i+"workOrderResourceId") != null && !"".equalsIgnoreCase(jsonObj.getString(i+"workOrderResourceId"))){
					workOrderResourceManagement = workOrderResourceManagementDao.getWorkOrderResourceMangtById(Long.parseLong(jsonObj.getString(i+"workOrderResourceId")));
					if(workOrderResourceManagement == null){
						workOrderResourceManagement = new WorkOrderResourceManagement();
					}
				}else{
					workOrderResourceManagement = new WorkOrderResourceManagement();
				}
				WorkOrderItems workOrderItems = new WorkOrderItems();
				workOrderItems.setId(workOrderID);
				workOrderResourceManagement.setWorkOrderItems(workOrderItems);
				workOrderResourceManagement.setType("contract");
		    if(jsonObj.has(i+"contractName") && jsonObj.getString(i+"contractName") != null && !"".equalsIgnoreCase(jsonObj.getString(i+"contractName"))){
				workOrderResourceManagement.setResourceName(jsonObj.getString(i+"contractName"));
			}
			
			if(jsonObj.has(i+"contractStartdate") && jsonObj.getString(i+"contractStartdate") != null && !"".equalsIgnoreCase(jsonObj.getString(i+"contractStartdate"))){
				workOrderResourceManagement.setStartDate(commonUtil.getDateFormat(jsonObj.getString(i+"contractStartdate")));
			}
			
			if(jsonObj.has(i+"contractEnddate") && jsonObj.getString(i+"contractEnddate") != null && !"".equalsIgnoreCase(jsonObj.getString(i+"contractEnddate"))){
				workOrderResourceManagement.setEndDate(commonUtil.getDateFormat(jsonObj.getString(i+"contractEnddate")));
			}
			if(jsonObj.has(i+"contractTotalHours") && jsonObj.getString(i+"contractTotalHours") != null && !"".equalsIgnoreCase(jsonObj.getString(i+"contractTotalHours"))){
				workOrderResourceManagement.setTotalHours(jsonObj.getString(i+"contractTotalHours")); 
			}
				workOrderResourceManagement.setStatus(1);
				workOrderResourceManagement.setCreatedDate(new Date());
				workOrderResourceManagement.setUpdatedDate(new Date());
				workOrderResourceManagement.setCreatedBy(user.getUserCode());
				workOrderResourceManagement.setUpdatedBy(user.getUserCode());
				
				workOrderResourceManagementDao.addWorkOrderResourceManagement(workOrderResourceManagement);
		}
		
		
	}
		return workOrderResourceManagement;
	
	}

	@Override
	public List<WorkOrderResourceManagement> getWorkOrderMngmt(Long workOrderId) {
		return workOrderResourceManagementDao.getWorkOrderMngmt(workOrderId);
	}

	@Override
	public List<WorkOrderResourceManagement> getWorkOrderContrctMngmt(Long workOrderId) {
		return workOrderResourceManagementDao.getWorkOrderContrctMngmt(workOrderId);
	}
	@Override
	public WorkOrderResourceManagement getWorkOrderMgntByUserId(Long id) {
		return workOrderResourceManagementDao.getWorkOrderMgntByUserId(id);
	}
	
	@Override
	public WorkOrderResourceManagement deleteWorkResourceManagmentser(WorkOrderResourceManagement workOrderResourceManagement){
	return workOrderResourceManagementDao.deleteWorkResourceManagmentser(workOrderResourceManagement);
	}
}
