package com.bis.operox.inv.service;

import org.springframework.stereotype.Service;

import com.bis.operox.inv.dao.entity.ProductStockOffer;

/**
 * @author shivayogiKadagad
 *
 */
@Service
public interface ProductStockOfferService {
	public ProductStockOffer saveProductStockOffer(ProductStockOffer productStockOffer);

}
