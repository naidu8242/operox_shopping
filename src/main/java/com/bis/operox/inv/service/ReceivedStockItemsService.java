package com.bis.operox.inv.service;

import java.util.List;

import org.json.JSONObject;

import com.bis.operox.inv.dao.entity.ReceivedStockItems;
/**
 * 
 * @author Sammeta  David Raju
 * @date 26rd Sep 2016
 *
 */

public interface ReceivedStockItemsService {
	
	/**
	 * to perform to add Received Stock Items
	 * @param role
	 * @return
	 */
	ReceivedStockItems addReceivedStockItems(JSONObject jsonObj) throws Exception;
	/**
	 * to get Received Stock Items based on id
	 * @param id
	 * @return
	 */
	ReceivedStockItems getReceivedStockItemsById(Long id);

	/**
	 * to get all Received Stock Items
	 * @return
	 */
	List<ReceivedStockItems> getAllReceivedStockItems();
	
	List<ReceivedStockItems> getAllReceivedStockItemsByReceivedStockId(Long receivedStockId);

}
