package com.bis.operox.inv.dao;

import java.util.List;

import com.bis.operox.inv.dao.entity.PurchaseOrder;
/**
 * 
 * @author Rajashekar
 * @date 23rd Sep 2016
 * Handles all purchase order details
 *
 */

public interface PurchaseOrderDao {
	/**
	 * to perform to add Purchase Order
	 * @param role
	 * @return
	 */
	PurchaseOrder addPurchaseOrder(PurchaseOrder purchaseOrder);
	/**
	 * to get Purchase Order based on id
	 * @param id
	 * @return
	 */
	PurchaseOrder getPurchaseOrderById(Long id);
	
	PurchaseOrder getPurchaseOrderBySupplierIdAndWorkOrderId(String suppierId, String workOrderId) throws Exception;

	/**
	 * to get all Purchase Orders
	 * @return
	 */
    List<PurchaseOrder> getAllPurchaseOrders();
    
    String getMaxPurchaseOrderId();
}
