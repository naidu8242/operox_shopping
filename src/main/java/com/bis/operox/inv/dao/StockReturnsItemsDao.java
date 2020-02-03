package com.bis.operox.inv.dao;

import java.util.List;

import com.bis.operox.inv.dao.entity.StockReturnsItems;
/**
 * 
 * @author Sammeta David Raju
 * @date 26rd Sep 2016
 * Handles all Stock Returns Items details
 *
 */

public interface StockReturnsItemsDao {
	/**
	 * to perform to Stock Returns Items Order
	 * @param role
	 * @return
	 */
	StockReturnsItems addStockReturnsItems(StockReturnsItems stockReturnsItems);
	/**
	 * to get Stock Returns Items based on id
	 * @param id
	 * @return
	 */
	StockReturnsItems getStockReturnsItemsById(Long id);

	/**
	 * to get all Stock Returns Items
	 * @return
	 */
    List<StockReturnsItems> getAllStockReturnsItems();
    
    List<StockReturnsItems> getAllStockReturnsItemsByStockReturnsId(Long stockReturnsId);
}
