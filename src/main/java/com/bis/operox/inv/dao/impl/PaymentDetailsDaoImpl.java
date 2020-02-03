package com.bis.operox.inv.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bis.operox.inv.dao.PaymentDetailsDao;
import com.bis.operox.inv.dao.entity.Address;
import com.bis.operox.inv.dao.entity.PaymentDetails;

@Repository
public class PaymentDetailsDaoImpl implements PaymentDetailsDao{

	@Autowired
	private SessionFactory sessionFactory;
	
	private static final Logger logger = LoggerFactory.getLogger(PaymentDetailsDaoImpl.class);

	

	@Override
	@Transactional(rollbackFor = { Exception.class })	
	public PaymentDetails addPaymentDetails(PaymentDetails paymentDetails) {
		Session session = this.sessionFactory.getCurrentSession();
		session.saveOrUpdate(paymentDetails);
		logger.info("PaymentDetails saved successfully, PaymentDetails Details="+paymentDetails);
		return paymentDetails;
	}

	@Override
	@Transactional(rollbackFor = { Exception.class })	
	public PaymentDetails getPaymentDetailsById(Long id) {
		 Session session = this.sessionFactory.getCurrentSession();	
	        String query = "from Address addr where addr.id = :addressId";
		     List<PaymentDetails> paymentDetailsList = session.createQuery(query).setLong("paymentDetailsId", id).setCacheable(true).list();
		    return paymentDetailsList.get(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(rollbackFor = { Exception.class })	
	public List<PaymentDetails> paymentDetailsList() {
		Session session = this.sessionFactory.getCurrentSession();
		List<PaymentDetails> paymentDetailsList = session.createQuery("from PaymentDetails").list();
		return paymentDetailsList;
	}
}