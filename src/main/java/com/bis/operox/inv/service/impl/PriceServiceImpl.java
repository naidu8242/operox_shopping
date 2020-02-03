package com.bis.operox.inv.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.bis.operox.inv.dao.PriceDao;
import com.bis.operox.inv.dao.entity.Price;
import com.bis.operox.inv.service.PriceService;

@Service
@Repository
public class PriceServiceImpl implements PriceService{

	@Autowired
	private PriceDao priceDao;
	
	@Override
	public Price addPrice(Price price) {
		return priceDao.addPrice(price);
	}
	
	@Override
	public Price getPriceByProductStockId(Long productStockId) {
		// TODO Auto-generated method stub
		return priceDao.getPriceByProductStockId(productStockId);
	}

	@Override
	public Price getPriceById(Long id) {
		return priceDao.getPriceById(id);
	}

	@Override
	public List<Price> priceList() {
		return this.priceDao.priceList();
	}

	
	@Override
	public Price getPriceByProductId(Long id) {
		return priceDao.getPriceByProductId(id);
	}
	
}
