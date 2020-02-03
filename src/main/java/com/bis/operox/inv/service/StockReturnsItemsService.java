package com.bis.operox.inv.service;

import java.util.List;

import org.json.JSONObject;

import com.bis.operox.inv.dao.entity.StockReturnsItems;
/**
 * 
 * @author Sammeta David Raju
 * @date 26rd Sep 2016
 *
 */

public interface StockReturnsItemsService {
	
	/**
	 * to perform to add Stock Returns Items
	 * @param role
	 * @return
	 */
	StockReturnsItems addStockReturnsItems(JSONObject jsonObj) throws Exception;
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
