package com.bis.operox.inv.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bis.operox.inv.dao.ReceivedStockItemsDao;
import com.bis.operox.inv.dao.entity.ReceivedStockItems;
/**
 * 
 * @author Sammeta David Raju
 * @date 26rd Sep 2016
 *
 */
@Repository
public class ReceivedStockItemsDaoImpl implements ReceivedStockItemsDao{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private static final Logger logger = LoggerFactory.getLogger(ReceivedStockItemsDaoImpl.class);

	@Override
	@Transactional
	public ReceivedStockItems addReceivedStockItems(ReceivedStockItems receivedStockItems) {
		Session session = this.sessionFactory.getCurrentSession();
		session.saveOrUpdate(receivedStockItems);
		logger.info("receivedStockItems added successfully..!!");
		return receivedStockItems;
	}

	@Override
	@Transactional
	public ReceivedStockItems getReceivedStockItemsById(Long id) {
		 Session session = this.sessionFactory.getCurrentSession();	
		 ReceivedStockItems receivedStockItems = (ReceivedStockItems) session.load(ReceivedStockItems.class, new Long(id));
			logger.info("ReceivedStockItems loaded successfully");
			return receivedStockItems;
	}

	@Override
	@Transactional
	public List<ReceivedStockItems> getAllReceivedStockItems() {
		Session session = this.sessionFactory.getCurrentSession();
		List<ReceivedStockItems> receivedStockItems = session.createQuery("from ReceivedStockItems").list();
		return receivedStockItems;
	}
	
	@Override
	@Transactional
	public List<ReceivedStockItems> getAllReceivedStockItemsByReceivedStockId(Long receivedStockId) {
		Session session = this.sessionFactory.getCurrentSession();
		List<ReceivedStockItems> receivedStockItems = session.createQuery("from ReceivedStockItems RSItems where RSItems.receivedStock =:receivedStockId").setLong("receivedStockId", receivedStockId).setCacheable(true).list();
		return receivedStockItems;
	}

}
