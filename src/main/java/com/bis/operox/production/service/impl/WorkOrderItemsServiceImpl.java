package com.bis.operox.production.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bis.operox.production.dao.WorkOrderItemsDao;
import com.bis.operox.production.dao.entity.WorkOrderItems;
import com.bis.operox.production.service.WorkOrderItemsService;

@Repository
public class WorkOrderItemsServiceImpl implements WorkOrderItemsService {

	@Autowired
	WorkOrderItemsDao workOrderItemsDao;
	
	@Override
	public List<WorkOrderItems> getProductsByworkorderId(Long workorderId) {
		return workOrderItemsDao.getWorkOrderByWorkOrderId(workorderId);
	}

}
