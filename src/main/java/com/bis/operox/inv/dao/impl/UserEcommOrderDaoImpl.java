package com.bis.operox.inv.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bis.operox.inv.dao.UserEcommOrderDao;
import com.bis.operox.inv.dao.entity.Address;
import com.bis.operox.inv.dao.entity.UserEcommOrder;

@Repository
public class UserEcommOrderDaoImpl implements UserEcommOrderDao{

	@Autowired
	private SessionFactory sessionFactory;
	
	private static final Logger logger = LoggerFactory.getLogger(UserEcommOrderDaoImpl.class);

	@Override
	@Transactional(rollbackFor = { Exception.class })	
	public UserEcommOrder addUserEcommOrder(UserEcommOrder userEcommOrder) {
		Session session = this.sessionFactory.getCurrentSession();
		session.saveOrUpdate(userEcommOrder);
		logger.info("UserEcommOrder saved successfully, UserEcommOrder Details="+userEcommOrder);
		return userEcommOrder;
	}

	@Override
	@Transactional(rollbackFor = { Exception.class })	
	public UserEcommOrder getUserEcommOrderById(Long id) {
		  Session session = this.sessionFactory.getCurrentSession();	
	      String query = "from UserEcommOrder user where user.id = :id";
		  List<UserEcommOrder> userEcommOrderList = session.createQuery(query).setLong("id", id).setCacheable(true).list();
		  return userEcommOrderList.get(0);
	}

	@Override
	@Transactional(rollbackFor = { Exception.class })	
	public List<UserEcommOrder> userEcommOrderList() {
		Session session = this.sessionFactory.getCurrentSession();
		List<UserEcommOrder> userEcommOrderList = session.createQuery("from UserEcommOrder").list();
		return userEcommOrderList;
	}
	
}