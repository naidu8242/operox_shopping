package com.bis.operox.inv.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bis.operox.inv.dao.PriceDao;
import com.bis.operox.inv.dao.entity.Offer;
import com.bis.operox.inv.dao.entity.Price;
import com.bis.operox.inv.dao.entity.StockReturns;

@Repository
public class PriceDaoImpl implements PriceDao{

	@Autowired
	private SessionFactory sessionFactory;
	
	private static final Logger logger = LoggerFactory.getLogger(PriceDaoImpl.class);

	@Override
	@Transactional(rollbackFor = { Exception.class })
	public Price getPriceByProductStockId(Long productStockId) {
		Session session = this.sessionFactory.getCurrentSession();
		List<Price> priceList = session.createQuery("from Price price  left join fetch price.productStockId where price.productStockId.id = :productStockId and price.status = '1' ").setLong("productStockId", productStockId).setCacheable(true).list();
		Price price = null;
		if(priceList != null && !priceList.isEmpty()){
			price = priceList.iterator().next();
		}
		
		return price;
	}
	
	/**
	 * This method is using for save or update 
	 */
	@Override
	@Transactional(rollbackFor = { Exception.class })
	public Price addPrice(Price price) {
		Session session = this.sessionFactory.getCurrentSession();
		session.saveOrUpdate(price);
		logger.info("Price saved successfully, Price Details="+price);
		return price;
	}

	/**
	 * This method is using for list by id 
	 */
	@Override
	@Transactional(rollbackFor = { Exception.class })
	public Price getPriceById(Long id) {
		Session session = this.sessionFactory.getCurrentSession();	
		Price price = (Price) session.load(Price.class, new Long(id));
		logger.info("Price loaded successfully, Price details="+price);
		return price;
	}

	/**
	 * This method is using for Price list 
	 */
	@Override
	@Transactional(rollbackFor = { Exception.class })
	public List<Price> priceList() {
		Session session = this.sessionFactory.getCurrentSession();
		List<Price> priceList = session.createQuery("from Price").list();
		return priceList;
	}
	
	@Override
	@Transactional
	public Price getPriceByProductId(Long id) {
		Session session = this.sessionFactory.getCurrentSession();
	    String query = "from Price price left join fetch price.productStockId where price.productStockId.id = :id";  
	    List<Price> priceList = session.createQuery(query).setLong("id", id).setCacheable(true).list();
	    Price price = null;
	    if(priceList != null && !priceList.isEmpty()){
	    	price = priceList.iterator().next();
	    }
	    return price;
	}

}