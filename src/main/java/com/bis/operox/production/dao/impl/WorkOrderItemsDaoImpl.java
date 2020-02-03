package com.bis.operox.production.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bis.operox.constants.Constants;
import com.bis.operox.production.dao.WorkOrderItemsDao;
import com.bis.operox.production.dao.entity.WorkOrder;
import com.bis.operox.production.dao.entity.WorkOrderItems;

@Repository
public class WorkOrderItemsDaoImpl implements WorkOrderItemsDao{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private static final Logger logger = LoggerFactory.getLogger(WorkOrderItemsDaoImpl.class);
	

	@Override
	@Transactional
	public WorkOrderItems saveWorkOrderItems(WorkOrderItems workOrderItems) {
		Session session = this.sessionFactory.getCurrentSession();
		session.saveOrUpdate(workOrderItems);
		logger.info("workOrderItems added successfully..!!");
		return workOrderItems;
	}


	@Override
	@Transactional 
	public List<WorkOrderItems> getWorkOrderByWorkOrderId(Long workorderId) {
		Session session = this.sessionFactory.getCurrentSession();
		List<WorkOrderItems> workOrderItemsList =  session.createQuery("from WorkOrderItems workOrderItems where workOrderItems.workOrder.id =:workorderId and workOrder.status = "+Constants.RECORD_ACTIVE+"").setLong("workorderId", workorderId).list();
		return workOrderItemsList;
	}

}
