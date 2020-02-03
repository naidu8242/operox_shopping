package com.bis.operox.inv.service;

import java.util.List;

import org.json.JSONObject;

import com.bis.operox.inv.dao.entity.ReceivedStock;
import com.bis.operox.inv.dao.entity.User;
/**
 * 
 * @author Sammeta David Raju
 * @date 26rd Sep 2016
 *
 */

public interface ReceivedStockService {
	
	/**
	 * to perform to add Received Stock
	 * @param role
	 * @return
	 */
	ReceivedStock addReceivedStock(JSONObject jsonObj,String submissionType ,User user) throws Exception;
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
