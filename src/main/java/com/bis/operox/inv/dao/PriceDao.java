package com.bis.operox.inv.dao;

import java.util.List;

import com.bis.operox.inv.dao.entity.Price;

public interface PriceDao {

	Price getPriceByProductStockId(Long productStockId);
	
	Price addPrice(Price price);
	
	Price getPriceById(Long id);

	List<Price> priceList();

	Price getPriceByProductId(Long id);
	
	
}