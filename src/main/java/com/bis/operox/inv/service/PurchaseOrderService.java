package com.bis.operox.inv.service;

import java.util.List;

import org.json.JSONObject;

import com.bis.operox.inv.dao.entity.PurchaseOrder;
import com.bis.operox.inv.dao.entity.User;
/**
 * 
 * @author Rajashekar
 * @date 23rd Sep 2016
 *
 */

public interface PurchaseOrderService {
	
	/**
	 * to perform to add Purchase Order
	 * @param role
	 * @return
	 */
	PurchaseOrder addPurchaseOrder(JSONObject jsonObj,User user,String submissionType) throws Exception;
	/**
	 * to get Purchase Order based on id
	 * @param id
	 * @return
	 */
	PurchaseOrder getPurchaseOrderById(Long id);

	/**
	 * to get all Purchase Orders
	 * @return
	 * @throws Exception 
	 */
    List<PurchaseOrder> getAllPurchaseOrders() throws Exception;
    
    String getMaxPurchaseOrderId();
    
    PurchaseOrder saveOrUpdatePurchaseOrder(PurchaseOrder purchaseOrder);

}
