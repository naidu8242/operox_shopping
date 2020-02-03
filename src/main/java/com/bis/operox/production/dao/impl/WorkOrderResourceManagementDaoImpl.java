package com.bis.operox.production.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bis.operox.inv.dao.entity.Customer;
import com.bis.operox.inv.dao.impl.CustomerDaoImpl;
import com.bis.operox.production.dao.WorkOrderResourceManagementDao;
import com.bis.operox.production.dao.entity.WorkOrderResourceManagement;

@Repository
public class WorkOrderResourceManagementDaoImpl implements WorkOrderResourceManagementDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	private static final Logger logger = LoggerFactory.getLogger(WorkOrderResourceManagementDaoImpl.class);
	
	
	
	@Override
	@Transactional
	public WorkOrderResourceManagement addWorkOrderResourceManagement(
			WorkOrderResourceManagement workOrderResourceManagement) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		session.saveOrUpdate(workOrderResourceManagement);
		logger.info("Customer saved successfully, Customer Details="+workOrderResourceManagement);
		return workOrderResourceManagement;
	}
	
	@Override
	@Transactional
	public WorkOrderResourceManagement deleteWorkResourceManagmentser(WorkOrderResourceManagement workOrderResourceManagement){
		Session session = this.sessionFactory.getCurrentSession();
		session.saveOrUpdate(workOrderResourceManagement);
		return workOrderResourceManagement;
	}


	@Override
	@Transactional
	public List<WorkOrderResourceManagement> getWorkOrderMngmt(Long workOrderId) {
		   Session session = this.sessionFactory.getCurrentSession();
		   Integer status = 1;
	        String query = "from WorkOrderResourceManagement workorder where workorder.workOrderItems.id = :workOrderId and workorder.type = 'fulltime' and workorder.status =:status";
	        List<WorkOrderResourceManagement> list = session.createQuery(query).setLong("workOrderId", workOrderId).setInteger("status", status).setCacheable(true).list();
	        return list;
	}

	@Override
	@Transactional
	public WorkOrderResourceManagement getWorkOrderMgntByUserId(Long id) {
		
		WorkOrderResourceManagement workOrderResourceManagement = null;
		Session session = this.sessionFactory.getCurrentSession();
        String query = "from WorkOrderResourceManagement workorder where workorder.user.id = :id";
        List<WorkOrderResourceManagement> list = session.createQuery(query).setLong("id", id).setCacheable(true).list();
        if(list != null && list.size() >0){
        	workOrderResourceManagement = list.get(0);
        }
        return workOrderResourceManagement;
		
	}



	@Override
	@Transactional
	public List<WorkOrderResourceManagement> getWorkOrderContrctMngmt(Long workOrderId) {
		Session session = this.sessionFactory.getCurrentSession();
        String query = "from WorkOrderResourceManagement workorder where workorder.workOrderItems.id = :workOrderId and workorder.type = 'contract'";
        List<WorkOrderResourceManagement> list = session.createQuery(query).setLong("workOrderId", workOrderId).setCacheable(true).list();
        return list;
	}



	@Override
	@Transactional
	public WorkOrderResourceManagement getWorkOrderResourceMangtById(Long workOrderResourceId) {
		WorkOrderResourceManagement workOrderResourceManagement = null;
		Session session = this.sessionFactory.getCurrentSession();
        String query = "from WorkOrderResourceManagement workorder where workorder.id = :id";
        List<WorkOrderResourceManagement> list = session.createQuery(query).setLong("id", workOrderResourceId).setCacheable(true).list();
        if(list != null && list.size() >0){
        	workOrderResourceManagement = list.get(0);
        }
        return workOrderResourceManagement;
	}

}
