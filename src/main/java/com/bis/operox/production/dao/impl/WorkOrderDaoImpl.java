package com.bis.operox.production.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bis.operox.constants.Constants;
import com.bis.operox.inv.dao.entity.Category;
import com.bis.operox.inv.dao.entity.Shift;
import com.bis.operox.inv.dao.entity.User;
import com.bis.operox.production.dao.WorkOrderDao;
import com.bis.operox.production.dao.entity.WorkOrder;

@Repository
public class WorkOrderDaoImpl implements WorkOrderDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private static final Logger logger = LoggerFactory.getLogger(WorkOrderDaoImpl.class);
	

	@Override
	@Transactional
	public List<WorkOrder> getWorkOrderByCompanyId(Long companyId) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List<WorkOrder> workOrderList = session.createQuery(
				"Select workOrder from WorkOrder workOrder where workOrder.store.company.id = :companyId and workOrder.status = '1' ")
				.setLong("companyId", companyId).setCacheable(true).list();
		return workOrderList;
	}


		@SuppressWarnings("unchecked")
		@Override
		@Transactional
		public List<WorkOrder> listWorkOrders() {
			Session session = this.sessionFactory.getCurrentSession();
			return session.createQuery("from WorkOrder workOrder where workOrder.status = "+Constants.RECORD_ACTIVE+"").list();
		}

		@Override
		@Transactional
		public WorkOrder getWorkOrderByWorkOrderId(Long workOrderId) {
			Session session = this.sessionFactory.getCurrentSession();
			List<WorkOrder> workOrderList =  session.createQuery("from WorkOrder workOrder where workOrder.id =:workOrderId and workOrder.status = "+Constants.RECORD_ACTIVE+"").setLong("workOrderId", workOrderId).list();
			return workOrderList.get(0);
		}


	@Override
	@Transactional
	public WorkOrder saveWorkOrder(WorkOrder workOrder) {
		Session session = this.sessionFactory.getCurrentSession();
		session.saveOrUpdate(workOrder);
		logger.info("workOrder added successfully..!!");
		return workOrder;
	}


	@Override
	@Transactional
	public WorkOrder getWorkOrderById(Long id) {
		Session session = this.sessionFactory.getCurrentSession();	
		WorkOrder workOrder = (WorkOrder) session.load(WorkOrder.class, new Long(id));
		logger.info("WorkOrder loaded successfully, Address details="+workOrder);
		return workOrder;
	}


	@Override
	@Transactional
	public WorkOrder getWorkOrderByWorkOrderNumber(String workOrderNumber,Long companyId) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List<WorkOrder> workOrder = new ArrayList<WorkOrder>();
		logger.debug("getWorkOrderByWorkOrderNumber start");
		workOrder = session.createQuery("from WorkOrder workOrder where workOrder.workOrderNumber = :workOrderNumber and workOrder.store.company.id = :companyId" ).setCacheable(true).setString("workOrderNumber", workOrderNumber).setLong("companyId", companyId).setCacheable(true).list();
		logger.debug("getWorkOrderByWorkOrderNumber  end");
		if (workOrder.size() > 0) {
			return workOrder.get(0);
		} else {
			return null;
		}
		
	}

}
