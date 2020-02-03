package com.bis.operox.inv.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bis.operox.inv.dao.CustomerOfferDao;
import com.bis.operox.inv.dao.entity.CustomerOffer;

/**
 * @author Ajith Matta
 * @Date 23/09/2016
 *
 */
@Repository
public class CustomerOfferDaoImpl implements CustomerOfferDao{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private static final Logger logger = LoggerFactory.getLogger(CustomerOfferDaoImpl.class);

	

	@Override
	@Transactional
	public CustomerOffer addCustomerOffer(CustomerOffer customerOffer) {
		Session session = this.sessionFactory.getCurrentSession();
		session.saveOrUpdate(customerOffer);
		logger.info("CustomerOffer saved successfully, CustomerOffer Details="+customerOffer);
		return customerOffer;
		
	}

	@Override
	@Transactional
	public CustomerOffer getCustomerOfferById(Long id) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();	
		CustomerOffer customerOffer = (CustomerOffer) session.load(CustomerOffer.class, new Long(id));
		logger.info("CustomerOffer loaded successfully, CustomerOffer details="+customerOffer);
		return customerOffer;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<CustomerOffer> getAllCustomerOffer() throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List<CustomerOffer> customerOfferList = session.createQuery("from CustomerOffer").list();
		return customerOfferList;
	}
	
}