package com.bis.operox.inv.dao;

import java.util.List;

import com.bis.operox.inv.dao.entity.PurchaseOrderItems;

/**
 * 
 * @author Prasad Salina
 *
 */
 
public interface PurchaseOrderItemsDao {
	

	public PurchaseOrderItems addPurchaseOrderItems(PurchaseOrderItems purchaseOrderItems);
	
	public PurchaseOrderItems getPurchaseOrderItemsById(Long id);
	
	public PurchaseOrderItems getPurchaseOrderItemsByPurchaseOrderIdAndRawMaterialId(Long purchaseOrderId, String rawMaterialId) throws Exception;

	public List<PurchaseOrderItems> getAllPurchaseOrderItems();
	
	public List<PurchaseOrderItems> getAllPurchaseOrderItemsByPurchaseOrderId(Long purchaseOrderId);

}