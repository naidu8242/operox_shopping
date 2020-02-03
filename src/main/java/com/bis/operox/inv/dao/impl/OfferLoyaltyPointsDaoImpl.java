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

import com.bis.operox.inv.dao.OfferLoyaltyPointsDao;
import com.bis.operox.inv.dao.entity.OfferCoupons;
import com.bis.operox.inv.dao.entity.OfferLoyaltyPoints;

@Repository
public class OfferLoyaltyPointsDaoImpl implements OfferLoyaltyPointsDao{

	

	@Autowired
	private SessionFactory sessionFactory;
	
	private static final Logger logger = LoggerFactory.getLogger(OfferLoyaltyPointsDaoImpl.class);

	
	@Override
	@Transactional
	public OfferLoyaltyPoints storeOfferLoyaltyPoints(OfferLoyaltyPoints offerLoyaltyPoints) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		session.saveOrUpdate(offerLoyaltyPoints);
		logger.info("OfferLoyaltyPoints saved successfully, Offer Details="+offerLoyaltyPoints);
		return offerLoyaltyPoints;
	}


	@Override
	@Transactional
	public OfferLoyaltyPoints getOfferLoyaltyPointsByOfferId(Long offerId) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List<OfferLoyaltyPoints> offerLoyaltyPoints = new ArrayList<OfferLoyaltyPoints>();
		logger.debug("getOfferLoyaltyPointsByOfferId user code start");
		offerLoyaltyPoints = session.createQuery("from OfferLoyaltyPoints offerLoyaltyPoints where offerLoyaltyPoints.offer.id =:offerId" ).setCacheable(true).setLong( "offerId", offerId ).setCacheable(true).list();
		logger.debug("getOfferLoyaltyPointsByOfferId user code end");
		if (offerLoyaltyPoints.size() > 0) {
			return offerLoyaltyPoints.get(0);
		} else {
			return null;
		}
}


	@Override
	@Transactional
	public OfferLoyaltyPoints getOfferLoyaltyPointsById(Long id) {
		Session session = this.sessionFactory.getCurrentSession();	
		OfferLoyaltyPoints offerLoyaltyPoints = (OfferLoyaltyPoints) session.load(OfferLoyaltyPoints.class, new Long(id));
		logger.info("OfferLoyaltyPoints loaded successfully, Offer details="+offerLoyaltyPoints);
		return offerLoyaltyPoints;
	}

}
