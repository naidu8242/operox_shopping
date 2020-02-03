package com.bis.operox.inv.service;

import java.util.List;

import com.bis.operox.inv.dao.entity.Price;

public interface PriceService {
	
	Price getPriceByProductStockId(Long productStockId);

	Price addPrice(Price price);
	
	Price getPriceById(Long id);
	
	Price getPriceByProductId(Long id);

	List<Price> priceList();

}
