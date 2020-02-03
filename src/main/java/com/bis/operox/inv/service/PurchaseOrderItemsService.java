package com.bis.operox.inv.service;

import java.util.List;

import com.bis.operox.inv.dao.entity.PurchaseOrderItems;

/**
 * 
 * @author Prasad Salina
 *
 */
public interface PurchaseOrderItemsService {

    public PurchaseOrderItems addPurchaseOrderItems(PurchaseOrderItems purchaseOrderItems);
	
	public PurchaseOrderItems getPurchaseOrderItemsById(Long id);

	public List<PurchaseOrderItems> getAllPurchaseOrderItems();
	
	public List<PurchaseOrderItems> getAllPurchaseOrderItemsByPurchaseOrderId(Long purchaseOrderId);

}
