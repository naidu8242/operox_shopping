package com.bis.operox.inv.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bis.operox.inv.dao.OfferStroesDao;
import com.bis.operox.inv.dao.entity.OfferBuyxGetY;
import com.bis.operox.inv.dao.entity.OfferStores;

@Repository
public class OfferStroesDaoImpl implements OfferStroesDao{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private static final Logger logger = LoggerFactory.getLogger(OfferStroesDaoImpl.class);

	@Override
	@Transactional
	public OfferStores storeOfferStroes(OfferStores offerStroes) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		session.saveOrUpdate(offerStroes);
		logger.info("OfferStroes saved successfully, Offer Details="+offerStroes);
		return offerStroes;
	}

	@Override
	@Transactional
	public List<OfferStores> getOfferStroesByOfferId(Long offerId) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List<OfferStores> offerStoresList = session.createQuery("from OfferStores offerStroes where offerStroes.offer.id = :offerId").setLong("offerId", offerId).setCacheable(true).list();
		return offerStoresList;
	}

	@Override
	@Transactional
	public OfferStores getOfferStroesById(Long id) {
		Session session = this.sessionFactory.getCurrentSession();	
		OfferStores offerStroes = (OfferStores) session.load(OfferStores.class, new Long(id));
		logger.info("Price loaded successfully, Offer details="+offerStroes);
		return offerStroes;
	}

	@Override
	@Transactional
	public void deleteOfferStore(OfferStores offerStroes) {
		Session session = this.sessionFactory.getCurrentSession();
		session.delete(offerStroes);
	}

}
