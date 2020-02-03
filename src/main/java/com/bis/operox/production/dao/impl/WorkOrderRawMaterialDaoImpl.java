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
import com.bis.operox.production.dao.WorkOrderRawMaterialDao;
import com.bis.operox.production.dao.entity.WorkOrderRawMaterial;

@Repository
public class WorkOrderRawMaterialDaoImpl implements WorkOrderRawMaterialDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private static final Logger logger = LoggerFactory.getLogger(WorkOrderRawMaterialDaoImpl.class);

	@Override
	@Transactional
	public WorkOrderRawMaterial saveworkOrderRawMaterial(WorkOrderRawMaterial workOrderRawMaterial) {
		Session session = this.sessionFactory.getCurrentSession();
		session.saveOrUpdate(workOrderRawMaterial);
		logger.info("WorkOrderRawMaterial added successfully..!!");
		return workOrderRawMaterial;
	}

	@Override
	@Transactional
	public List<WorkOrderRawMaterial> getAllWorkOrderRawMaterials() throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List<WorkOrderRawMaterial> workOrderRawMaterialsList = session.createQuery("from WorkOrderRawMaterial worm where worm.status = '1' ").setCacheable(true).list();
		return workOrderRawMaterialsList;
	}

	@Override
	@Transactional
	public List<WorkOrderRawMaterial> getAllWorkOrderRawMaterialsByWorkOrderId(Long workoderId) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List<WorkOrderRawMaterial> workOrderRawMaterialsList =  session.createQuery("from WorkOrderRawMaterial worm where worm.workOrderItems.id IN (select woi.id from WorkOrderItems woi where woi.workOrder.id = :workoderId and woi.status = "+Constants.RECORD_ACTIVE+") and worm.status = "+Constants.RECORD_ACTIVE+"").setLong("workoderId", workoderId).list();
		return workOrderRawMaterialsList;
	}

	@Override
	@Transactional
	public WorkOrderRawMaterial getWorkOrderRawMaterialById(Long id) {
		Session session = this.sessionFactory.getCurrentSession();	
		WorkOrderRawMaterial workOrderRawMaterial = session.load(WorkOrderRawMaterial.class, new Long(id));
		return workOrderRawMaterial;
	}

	@Override
	@Transactional
	public void deleteWorkOrderRawMaterial(WorkOrderRawMaterial workOrderRawMaterial) {
		Session session = this.sessionFactory.getCurrentSession();
		session.delete(workOrderRawMaterial);
		
	}

	

}
