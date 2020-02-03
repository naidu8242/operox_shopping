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
import com.bis.operox.production.dao.WorkOrderMachineryManagementDao;
import com.bis.operox.production.dao.entity.WorkOrderMachineryManagement;
import com.bis.operox.production.dao.entity.WorkOrderRawMaterial;
import com.bis.operox.production.dao.entity.WorkOrderResourceManagement;

@Repository
public class WorkOrderMachineryManagementDaoImpl implements WorkOrderMachineryManagementDao{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private static final Logger logger = LoggerFactory.getLogger(WorkOrderMachineryManagementDaoImpl.class);
	

	@Override
	@Transactional
	public WorkOrderMachineryManagement saveWorkOrderMachineryManagement(WorkOrderMachineryManagement womm) {
		Session session = this.sessionFactory.getCurrentSession();
		session.saveOrUpdate(womm);
		logger.info("WorkOrderMachineryManagement added successfully..!!");
		return womm;
	}


	@Override
	@Transactional
	public List<WorkOrderMachineryManagement> getAllWorkOrderMachineryManagementByWorkOrderId(Long workoderId)
			throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List<WorkOrderMachineryManagement> workOrderMachineryManagementsList =  session.createQuery("from WorkOrderMachineryManagement wormm where wormm.workOrder.id = :workoderId and wormm.status = "+Constants.RECORD_ACTIVE+"").setLong("workoderId", workoderId).list();
		return workOrderMachineryManagementsList;
	}


	@Override
	@Transactional
	public WorkOrderMachineryManagement getWorkOrderMachineryManagementById(Long id) {
		Session session = this.sessionFactory.getCurrentSession();	
		WorkOrderMachineryManagement wormm = session.load(WorkOrderMachineryManagement.class, new Long(id));
		return wormm;
	}


	@Override
	@Transactional
	public void deleteWorkOrderMachineryManagement(WorkOrderMachineryManagement womm) {
		Session session = this.sessionFactory.getCurrentSession();
		session.delete(womm);
	}


	@Override
	@Transactional
	public WorkOrderMachineryManagement deleteMachineryManagement(
			WorkOrderMachineryManagement workOrderMachineryManagement) {
		Session session = this.sessionFactory.getCurrentSession();
		session.saveOrUpdate(workOrderMachineryManagement);
		return workOrderMachineryManagement;
	}


}
