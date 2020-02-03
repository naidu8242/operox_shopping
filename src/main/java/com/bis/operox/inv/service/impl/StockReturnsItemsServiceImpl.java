package com.bis.operox.inv.service.impl;

import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bis.operox.inv.dao.StockReturnsItemsDao;
import com.bis.operox.inv.dao.entity.StockReturnsItems;
import com.bis.operox.inv.service.StockReturnsItemsService;
/**
 * provide the implementaion for service methods
 * @author root
 *
 */
@Service
public class StockReturnsItemsServiceImpl implements StockReturnsItemsService{
	
	@Autowired
	StockReturnsItemsDao stockReturnsItemsDao;


	@Override
	public StockReturnsItems addStockReturnsItems(JSONObject jsonObj)  throws Exception  {
		
		StockReturnsItems stockReturnsItems = new StockReturnsItems();
		stockReturnsItems = stockReturnsItemsDao.addStockReturnsItems(stockReturnsItems);
			return stockReturnsItems;
		}

	@Override
	public StockReturnsItems getStockReturnsItemsById(Long id) {
		return stockReturnsItemsDao.getStockReturnsItemsById(id);
	}

	@Override
	public List<StockReturnsItems> getAllStockReturnsItems() {
		return stockReturnsItemsDao.getAllStockReturnsItems();
	}
	
	@Override
	public List<StockReturnsItems> getAllStockReturnsItemsByStockReturnsId(Long stockReturnsId) {
		return stockReturnsItemsDao.getAllStockReturnsItemsByStockReturnsId(stockReturnsId);
	}

}
