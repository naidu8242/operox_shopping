package com.bis.operox.inv.dao;

import java.util.List;

import com.bis.operox.inv.dao.entity.ReceivedStockItems;
/**
 * 
 * @author Sammeta David Raju
 * @date 26rd Sep 2016
 * Handles all Received Stock Items details
 *
 */

public interface ReceivedStockItemsDao {
	/**
	 * to perform to add Received Stock Items
	 * @param role
	 * @return
	 */
	ReceivedStockItems addReceivedStockItems(ReceivedStockItems receivedStockItems);
	/**
	 * to get Received Stock Items  based on id
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
