package com.bis.operox.inv.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bis.operox.inv.dao.StockReturnsItemsDao;
import com.bis.operox.inv.dao.entity.PurchaseOrder;
import com.bis.operox.inv.dao.entity.PurchaseOrderItems;
import com.bis.operox.inv.dao.entity.StockReturnsItems;
/**
 * 
 * @author Rajashekar
 * @date 23rd Sep 2016
 *
 */
@Repository
public class StockReturnsItemsDaoImpl implements StockReturnsItemsDao{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private static final Logger logger = LoggerFactory.getLogger(StockReturnsItemsDaoImpl.class);

	@Override
	@Transactional
	public StockReturnsItems addStockReturnsItems(StockReturnsItems stockReturnsItems) {
		Session session = this.sessionFactory.getCurrentSession();
		session.saveOrUpdate(stockReturnsItems);
		logger.info("stockReturnsItems added successfully..!!");
		return stockReturnsItems;
	}

	@Override
	@Transactional
	public StockReturnsItems getStockReturnsItemsById(Long id) {
		 Session session = this.sessionFactory.getCurrentSession();	
		 StockReturnsItems stockReturnsItems = (StockReturnsItems) session.load(StockReturnsItems.class, new Long(id));
			logger.info("StockReturnsItems loaded successfully");
			return stockReturnsItems;
	}

	@Override
	@Transactional
	public List<StockReturnsItems> getAllStockReturnsItems() {
		Session session = this.sessionFactory.getCurrentSession();
		List<StockReturnsItems> stockReturnsItems = session.createQuery("from StockReturnsItems").list();
		return stockReturnsItems;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<StockReturnsItems> getAllStockReturnsItemsByStockReturnsId(Long stockReturnsId) {
		Session session = this.sessionFactory.getCurrentSession();
		List<StockReturnsItems> stockReturnsItemsList = session.createQuery("from StockReturnsItems SRItems where SRItems.stockReturns =:stockReturnsId").setLong("stockReturnsId", stockReturnsId).setCacheable(true).list();
		return stockReturnsItemsList;
	}

}
