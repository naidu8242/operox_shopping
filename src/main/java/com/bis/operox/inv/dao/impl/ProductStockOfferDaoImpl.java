package com.bis.operox.inv.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.bis.operox.inv.dao.ProductStockOfferDao;
import com.bis.operox.inv.dao.entity.ProductStockOffer;

@Repository
public class ProductStockOfferDaoImpl implements ProductStockOfferDao {
	SessionFactory sessionfactory;

	@Override
	public ProductStockOffer saveProductStockOffer(ProductStockOffer productStockOffer) {
		sessionfactory.getCurrentSession().saveOrUpdate(productStockOffer);
		return productStockOffer;
	}

}
