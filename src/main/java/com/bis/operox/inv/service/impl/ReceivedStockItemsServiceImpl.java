package com.bis.operox.inv.service.impl;

import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bis.operox.inv.dao.ReceivedStockItemsDao;
import com.bis.operox.inv.dao.entity.ReceivedStockItems;
import com.bis.operox.inv.service.ReceivedStockItemsService;
/**
 * provide the implementaion for service methods
 * @author root
 *
 */
@Service
public class ReceivedStockItemsServiceImpl implements ReceivedStockItemsService{
	
	@Autowired
	ReceivedStockItemsDao receivedStockItemsDao;


	@Override
	public ReceivedStockItems addReceivedStockItems(JSONObject jsonObj)  throws Exception  {
		
		ReceivedStockItems receivedStockItems = new ReceivedStockItems();
		receivedStockItems = receivedStockItemsDao.addReceivedStockItems(receivedStockItems);
			return receivedStockItems;
		}
	

	@Override
	public ReceivedStockItems getReceivedStockItemsById(Long id) {
		return receivedStockItemsDao.getReceivedStockItemsById(id);
	}

	@Override
	public List<ReceivedStockItems> getAllReceivedStockItems() {
		return receivedStockItemsDao.getAllReceivedStockItems();
	}
	
	@Override
	public List<ReceivedStockItems> getAllReceivedStockItemsByReceivedStockId(Long receivedStockId) {
		return receivedStockItemsDao.getAllReceivedStockItemsByReceivedStockId(receivedStockId);
	}

}
