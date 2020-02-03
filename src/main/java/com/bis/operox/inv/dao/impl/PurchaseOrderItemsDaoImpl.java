package com.bis.operox.inv.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bis.operox.inv.dao.PurchaseOrderItemsDao;
import com.bis.operox.inv.dao.entity.PurchaseOrder;
import com.bis.operox.inv.dao.entity.PurchaseOrderItems;

/**
 * 
 * @author Prasad Salina
 *
 */
 
@Repository
public class PurchaseOrderItemsDaoImpl implements PurchaseOrderItemsDao{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private static final Logger logger = LoggerFactory.getLogger(PurchaseOrderItemsDaoImpl.class);


	@Override
	@Transactional
	public PurchaseOrderItems addPurchaseOrderItems(PurchaseOrderItems purchaseOrderItems) {
		Session session = this.sessionFactory.getCurrentSession();
		session.saveOrUpdate(purchaseOrderItems);
		logger.info("PurchaseOrderItems saved successfully, PurchaseOrderItems Details="+purchaseOrderItems);
		return purchaseOrderItems;
		
	}

	@Override
	@Transactional
	public PurchaseOrderItems getPurchaseOrderItemsById(Long id) {
		Session session = this.sessionFactory.getCurrentSession();	
		PurchaseOrderItems purchaseOrderItems = (PurchaseOrderItems) session.load(PurchaseOrderItems.class, new Long(id));
		logger.info("PurchaseOrderItems loaded successfully, PurchaseOrderItems details="+purchaseOrderItems);
		return purchaseOrderItems;
	}
	

	@Override
	@Transactional
	public PurchaseOrderItems getPurchaseOrderItemsByPurchaseOrderIdAndRawMaterialId(Long purchaseOrderId, String rawMaterialId) throws Exception{
			Session session = this.sessionFactory.getCurrentSession();
	        String query = "from PurchaseOrderItems poi where poi.purchaseOrder.id = :purchaseOrderId and poi.rawMaterial.id = :rawMaterialId ";  
	        List<PurchaseOrderItems> orderItemsList = session.createQuery(query).setLong("purchaseOrderId", purchaseOrderId).setString("rawMaterialId", rawMaterialId).setCacheable(true).list();
	        PurchaseOrderItems purchaseOrderItems = null;
	        if(orderItemsList != null && !orderItemsList.isEmpty()){
	        	purchaseOrderItems = orderItemsList.iterator().next();
	        }
	        return purchaseOrderItems;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<PurchaseOrderItems> getAllPurchaseOrderItems() {
		Session session = this.sessionFactory.getCurrentSession();
		List<PurchaseOrderItems> purchaseOrderItemsList = session.createQuery("from PurchaseOrderItems").list();
		return purchaseOrderItemsList;
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<PurchaseOrderItems> getAllPurchaseOrderItemsByPurchaseOrderId(Long purchaseOrderId) {
		Session session = this.sessionFactory.getCurrentSession();
		List<PurchaseOrderItems> purchaseOrderItemsList = session.createQuery("from PurchaseOrderItems POItems where POItems.purchaseOrder =:purchaseOrderId").setLong("purchaseOrderId", purchaseOrderId).setCacheable(true).list();
		return purchaseOrderItemsList;
	}
	
}