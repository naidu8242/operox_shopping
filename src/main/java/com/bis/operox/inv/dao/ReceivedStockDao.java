package com.bis.operox.inv.dao;

import java.util.List;

import com.bis.operox.inv.dao.entity.ReceivedStock;
import com.bis.operox.inv.dao.entity.User;
/**
 * 
 * @author Sammeta David Raju
 * @date 26rd Sep 2016
 * Handles all Received Stock details
 *
 */

public interface ReceivedStockDao {
	/**
	 * to perform to add Received Stock
	 * @param role
	 * @return
	 */
	ReceivedStock addReceivedStock(ReceivedStock receivedStock,User user);
	/**
	 * to get Received Stock based on id
	 * @param id
	 * @return
	 */
	ReceivedStock getReceivedStockById(Long id);

	/**
	 * to get all Received Stock
	 * @return
	 */
    List<ReceivedStock> getAllReceivedStocks();
    
    String getMaxReceivedNumber();
    
}
