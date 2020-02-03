package com.bis.operox.inv.service.impl;

import com.bis.operox.inv.dao.ProductStockOfferDao;
import com.bis.operox.inv.dao.entity.ProductStockOffer;
import com.bis.operox.inv.service.ProductStockOfferService;

/**
 * @author shivayogiKadagad
 *
 */
public class ProductStockOfferServiceImpl implements ProductStockOfferService {

	ProductStockOfferDao productStockOfferDao;

	@Override
	public ProductStockOffer saveProductStockOffer(ProductStockOffer productStockOffer) {
		productStockOfferDao.saveProductStockOffer(productStockOffer);
		return productStockOffer;
	}

}
