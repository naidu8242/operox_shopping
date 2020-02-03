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

import com.bis.operox.inv.dao.OfferBuyxGetYDao;
import com.bis.operox.inv.dao.entity.Offer;
import com.bis.operox.inv.dao.entity.OfferBuyxGetY;

@Repository
public class OfferBuyxGetYDaoImpl implements OfferBuyxGetYDao{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private static final Logger logger = LoggerFactory.getLogger(OfferBuyxGetYDaoImpl.class);

	@Override
	@Transactional
	public OfferBuyxGetY storeOfferBuyxGetY(OfferBuyxGetY offerBuyxGetY) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		session.saveOrUpdate(offerBuyxGetY);
		logger.info("OfferBuyxGetY saved successfully, Offer Details="+offerBuyxGetY);
		return offerBuyxGetY;
	}

	@Override
	@Transactional
	public OfferBuyxGetY getOfferBuyxGetYByOfferId(Long offerId) {
		Session session = this.sessionFactory.getCurrentSession();
		List<OfferBuyxGetY> offerBuyxGetY = new ArrayList<OfferBuyxGetY>();
		logger.debug("getOfferBuyxGetYByOfferId user code start");
		offerBuyxGetY = session.createQuery("from OfferBuyxGetY offerBuyxGetY where offerBuyxGetY.offer.id =:offerId" ).setCacheable(true).setLong( "offerId", offerId ).setCacheable(true).list();
		logger.debug("getOfferBuyxGetYByOfferId user code end");
		if (offerBuyxGetY.size() > 0) {
			return offerBuyxGetY.get(0);
		} else {
			return null;
		}
}

	@Override
	@Transactional
	public OfferBuyxGetY getOfferBuyxGetYById(Long id) {
		Session session = this.sessionFactory.getCurrentSession();	
		OfferBuyxGetY offerBuyxGetY = (OfferBuyxGetY) session.load(OfferBuyxGetY.class, new Long(id));
		logger.info("Price loaded successfully, Offer details="+offerBuyxGetY);
		return offerBuyxGetY;
	}

	@Override
	@Transactional
	public OfferBuyxGetY getOfferBuyxGetYByBuyItemProductIdAndCompanyId(Long buyItemProductId, Long companyId)
			throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List<OfferBuyxGetY> offerBuyxGetY = new ArrayList<OfferBuyxGetY>();
		logger.debug("getOfferBuyxGetYByBuyItemProductIdAndCompanyId user code start");
		offerBuyxGetY = session.createQuery("from OfferBuyxGetY offerBuyxGetY where offerBuyxGetY.buyItemProduct.id =:buyItemProductId and offerBuyxGetY.offer.company.id = :companyId " ).setCacheable(true).setLong( "buyItemProductId", buyItemProductId).setLong( "companyId", companyId).setCacheable(true).list();
		logger.debug("getOfferBuyxGetYByBuyItemProductIdAndCompanyId user code end");
		if (offerBuyxGetY.size() > 0) {
			return offerBuyxGetY.get(0);
		} else {
			return null;
		}
}

	

}
