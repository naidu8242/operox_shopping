package com.bis.operox.inv.dao;

import java.util.List;

import com.bis.operox.inv.dao.entity.StockReturns;


public interface StockReturnsDao {
	/**
	 * to perform to add Stock Returns
	 * @param role
	 * @return
	 */
	StockReturns addStockReturns(StockReturns StockReturns);
	/**
	 * to get Stock Returns based on id
	 * @param id
	 * @return
	 */
	StockReturns getStockReturnsById(Long id);

	/**
	 * to get all Stock Returns
	 * @return
	 */
    List<StockReturns> getAllStockReturns();
    
    /**
	 * to get all Transfer Order
	 * @return
	 */
    List<StockReturns> getAllTransferStock();
    
    /**
	 * to perform to add Stock Returns
	 * @param role
	 * @return
	 */
	StockReturns addStockTransfer(StockReturns StockReturns);
    
	
	/**
	 * to perform to get Max Stock Transfered Number
	 * @param role
	 * @return
	 */
	String getMaxStockTransferedNumber();
}