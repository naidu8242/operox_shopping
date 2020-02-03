package com.bis.operox.payroll.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bis.operox.payroll.dao.LeavesDao;
import com.bis.operox.payroll.entity.Leaves;

@Repository
public class LeavesDaoImpl implements LeavesDao{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private static final Logger logger = LoggerFactory.getLogger(LeavesDaoImpl.class);

	@Override
	@Transactional
	public Leaves addLeaves(Leaves leaves) {
		Session session = this.sessionFactory.getCurrentSession();
		session.saveOrUpdate(leaves);	
		logger.info("data added to database");
		return leaves;
	}

	@Override
	@Transactional
	public Leaves getLeavesById(Long leaveId) {
		Session session = this.sessionFactory.getCurrentSession();	
		 String query = "from Leaves leave where leave.id = :leaveId";
	     List<Leaves> leavesList = session.createQuery(query).setLong("leaveId", leaveId).setCacheable(true).list();
	    return leavesList.get(0);
	}

	@Override
	@Transactional
	public List<Leaves> listLeaves() {
		Integer status = 1;
		 	Session session = this.sessionFactory.getCurrentSession();
	        String query = "from Leaves leaves where leaves.status =:status";
	        List<Leaves> leavesList = session.createQuery(query).setInteger("status", status).setCacheable(true).list();
	        return leavesList;
	}

	@Override
	@Transactional
	public Leaves deleteLeaveById(Leaves leaves) {
		Session session = this.sessionFactory.getCurrentSession();
		session.saveOrUpdate(leaves);
		logger.info("leaves saved successfully, Customer Details="+leaves);
		return leaves;
	}

}
