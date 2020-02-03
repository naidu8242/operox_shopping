package com.bis.operox.inv.service;

import java.util.List;

import org.json.JSONObject;

import com.bis.operox.inv.dao.entity.StockReturns;
import com.bis.operox.inv.dao.entity.User;

public interface StockReturnsService {
	
	/**
	 * to perform to add Purchase Order
	 * @param role
	 * @return
	 */
	StockReturns addStockReturns(JSONObject jsonObj,User user) throws Exception;
	/**
	 * to get Purchase Order based on id
	 * @param id
	 * @return
	 */
	StockReturns getStockReturnsById(Long id);

	/**
	 * to get all Purchase Orders
	 * @return
	 */
    List<StockReturns> getAllStockReturns();
    
    /**
     * to get all Transfer Order
     * @return
     */
    
    List<StockReturns> getAllTransferStock();

	 
    
    /**
	 * to perform to add Purchase Order
	 * @param role
	 * @return
	 */
	StockReturns addStockTransfer(JSONObject jsonObj,String submissionType,User user) throws Exception;

	
	
	String getMaxStockTransferedNumber();
}
