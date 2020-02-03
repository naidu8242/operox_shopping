package com.bis.operox.inv.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bis.operox.inv.dao.PurchaseOrderItemsDao;
import com.bis.operox.inv.dao.entity.PurchaseOrderItems;
import com.bis.operox.inv.service.PurchaseOrderItemsService;

/**
 * 
 * @author Prasad Salina
 *
 */

@Service
public class PurchaseOrderItemsServiceImpl implements PurchaseOrderItemsService{
	
	@Autowired
	private PurchaseOrderItemsDao purchaseOrderItemsDao;
	

	@Override
	public PurchaseOrderItems addPurchaseOrderItems(PurchaseOrderItems purchaseOrderItems) {
		return purchaseOrderItemsDao.addPurchaseOrderItems(purchaseOrderItems);
	}
	
	@Override
	public PurchaseOrderItems getPurchaseOrderItemsById(Long id) {
		return this.purchaseOrderItemsDao.getPurchaseOrderItemsById(id);
	}

	@Override
	public List<PurchaseOrderItems> getAllPurchaseOrderItems() {
		return this.purchaseOrderItemsDao.getAllPurchaseOrderItems();
	}
	
	@Override
	public List<PurchaseOrderItems> getAllPurchaseOrderItemsByPurchaseOrderId(Long purchaseOrderId) {
		return this.purchaseOrderItemsDao.getAllPurchaseOrderItemsByPurchaseOrderId(purchaseOrderId);
	}
	
}
