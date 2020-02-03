package com.bis.operox.inv.dao.impl;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bis.operox.inv.dao.OfferDao;
import com.bis.operox.inv.dao.entity.Offer;
import com.bis.operox.inv.dao.entity.OfferDiscountOnInvoice;

@Repository
public class OfferDaoImpl implements OfferDao{

	@Autowired
	private SessionFactory sessionFactory;
	
	private static final Logger logger = LoggerFactory.getLogger(OfferDaoImpl.class);

	/**
	 * This method is using for save or update 
	 */
	@Override
	@Transactional
	public Offer addOffer(Offer offer) {
		Session session = this.sessionFactory.getCurrentSession();
		session.saveOrUpdate(offer);
		logger.info("Offer saved successfully, Offer Details="+offer);
		return offer;
	}

	/**
	 * This method is using for list by id 
	 */
	@Override
	@Transactional
	public Offer getOfferById(Long id) {
		Session session = this.sessionFactory.getCurrentSession();	
		Offer offer = (Offer) session.load(Offer.class, new Long(id));
		logger.info("Price loaded successfully, Offer details="+offer);
		return offer;
	}

	/**
	 * This method is using for Offer list 
	 */
	@Override
	@Transactional
	public List<Offer> offerList() {
		Session session = this.sessionFactory.getCurrentSession();
		List<Offer> offerList = session.createQuery("from Offer").list();
		return offerList;
	}

	
	@Override
	@Transactional
	public List<Offer> getofferListByCompanyId(Long companyId) {
		Session session = this.sessionFactory.getCurrentSession();
		List<Offer> offerList = session.createQuery("from Offer offer where offer.company.id = :companyId and offer.status = '1' ").setLong("companyId", companyId).setCacheable(true).list();
		return offerList;
	}

	@Override
	@Transactional
	public Offer getOfferByOfferDiscountOnInvoiceAmountAndStoreId(Long storeId, Float billAmount) {
		
		Session session = this.sessionFactory.getCurrentSession();
		List<Offer> offer = new ArrayList<Offer>();
		logger.debug("getOfferByOfferDiscountOnInvoiceAmountAndStoreId user code start");
		offer = session.createQuery("from Offer offer where offer.id IN "
				+ "(select odi.offer.id from OfferDiscountOnInvoice odi where odi.invoiceAmount = :billAmount and odi.offer.id IN "
				+ "(select offerStores.offer.id from OfferStores offerStores where offerStores.store.id = :storeId))" ).setCacheable(true).setLong( "storeId", storeId).setFloat("billAmount", billAmount).setCacheable(true).list();
		logger.debug("getOfferByOfferDiscountOnInvoiceAmountAndStoreId user code end");
		if (offer.size() > 0) {
			return offer.get(0);
		} else {
			return null;
		}
}

	@Override
	@Transactional
	public Offer getOfferByOfferCoupounAmountByCouponNumberAndStoreId(Long storeId, String couponName) {
		Session session = this.sessionFactory.getCurrentSession();
		List<Offer> offer = new ArrayList<Offer>();
		logger.debug("getOfferByOfferCoupounAmountByCouponNumberAndStoreId user code start");
		offer = session.createQuery("from Offer offer where offer.id IN "
				+ "(select oc.offer.id from OfferCoupons oc where oc.couponName = :couponName and oc.offer.id IN "
				+ "(select offerStores.offer.id from OfferStores offerStores where offerStores.store.id = :storeId))" ).setCacheable(true).setLong( "storeId", storeId).setString("couponName", couponName).setCacheable(true).list();
		logger.debug("getOfferByOfferCoupounAmountByCouponNumberAndStoreId user code end");
		if (offer.size() > 0) {
			return offer.get(0);
		} else {
			return null;
		}
}
	
	
	

}