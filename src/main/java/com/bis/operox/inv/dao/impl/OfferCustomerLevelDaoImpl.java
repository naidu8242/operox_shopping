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

import com.bis.operox.inv.dao.OfferCustomerLevelDao;
import com.bis.operox.inv.dao.entity.OfferBuyxGetY;
import com.bis.operox.inv.dao.entity.OfferCustomerLevel;

@Repository
public class OfferCustomerLevelDaoImpl implements OfferCustomerLevelDao{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private static final Logger logger = LoggerFactory.getLogger(OfferCustomerLevelDaoImpl.class);

	@Override
	@Transactional
	public OfferCustomerLevel storeOfferCustomerLevel(OfferCustomerLevel offerCustomerLevel) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		session.saveOrUpdate(offerCustomerLevel);
		logger.info("OfferCustomerLevel saved successfully, Offer Details="+offerCustomerLevel);
		return offerCustomerLevel;
	}

	@Override
	@Transactional
	public OfferCustomerLevel getOfferCustomerLevelByOfferId(Long offerId) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List<OfferCustomerLevel> offerCustomerLevel = new ArrayList<OfferCustomerLevel>();
		logger.debug("getOfferCustomerLevelByOfferId user code start");
		offerCustomerLevel = session.createQuery("from OfferCustomerLevel ocl where ocl.offer.id =:offerId" ).setCacheable(true).setLong( "offerId", offerId ).setCacheable(true).list();
		logger.debug("getOfferCustomerLevelByOfferId user code end");
		if (offerCustomerLevel.size() > 0) {
			return offerCustomerLevel.get(0);
		} else {
			return null;
		}
}

	@Override
	@Transactional
	public OfferCustomerLevel getOfferCustomerLevelById(Long id) {
		Session session = this.sessionFactory.getCurrentSession();	
		OfferCustomerLevel offerCustomerLevel = (OfferCustomerLevel) session.load(OfferCustomerLevel.class, new Long(id));
		logger.info("OfferCustomerLevel loaded successfully, Offer details="+offerCustomerLevel);
		return offerCustomerLevel;
	}

}
