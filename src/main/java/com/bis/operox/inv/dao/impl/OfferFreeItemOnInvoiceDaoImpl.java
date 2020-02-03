package com.bis.operox.inv.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bis.operox.inv.dao.OfferFreeItemOnInvoiceDao;
import com.bis.operox.inv.dao.entity.OfferBuyxGetY;
import com.bis.operox.inv.dao.entity.OfferFreeItemOnInvoice;

@Repository
public class OfferFreeItemOnInvoiceDaoImpl implements OfferFreeItemOnInvoiceDao{

	
	@Autowired
	private SessionFactory sessionFactory;
	
	private static final Logger logger = LoggerFactory.getLogger(OfferFreeItemOnInvoiceDaoImpl.class);
	
	@Override
	@Transactional
	public OfferFreeItemOnInvoice storeOfferFreeItemOnInvoice(OfferFreeItemOnInvoice offerFreeItemOnInvoice) {
		Session session = this.sessionFactory.getCurrentSession();
		session.saveOrUpdate(offerFreeItemOnInvoice);
		logger.info("OfferFreeItemOnInvoice saved successfully, Offer Details="+offerFreeItemOnInvoice);
		return offerFreeItemOnInvoice;
	}

	@Override
	@Transactional
	public OfferFreeItemOnInvoice getOfferFreeItemOnInvoiceByOfferId(Long offerId) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List<OfferFreeItemOnInvoice> offerFreeItemOnInvoice = new ArrayList<OfferFreeItemOnInvoice>();
		logger.debug("getOfferFreeItemOnInvoiceByOfferId user code start");
		offerFreeItemOnInvoice = session.createQuery("from OfferFreeItemOnInvoice offerFreeItemOnInvoice where offerFreeItemOnInvoice.offer.id =:offerId" ).setCacheable(true).setLong( "offerId", offerId ).setCacheable(true).list();
		logger.debug("getOfferFreeItemOnInvoiceByOfferId user code end");
		if (offerFreeItemOnInvoice.size() > 0) {
			return offerFreeItemOnInvoice.get(0);
		} else {
			return null;
		}
}

	@Override
	@Transactional
	public OfferFreeItemOnInvoice getOfferFreeItemOnInvoiceById(Long id) {
		Session session = this.sessionFactory.getCurrentSession();	
		OfferFreeItemOnInvoice offerFreeItemOnInvoice = (OfferFreeItemOnInvoice) session.load(OfferFreeItemOnInvoice.class, new Long(id));
		logger.info("OfferFreeItemOnInvoice loaded successfully, Offer details="+offerFreeItemOnInvoice);
		return offerFreeItemOnInvoice;
	}

}
