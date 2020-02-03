package com.bis.operox.inv.dao.impl;

import java.math.BigInteger;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bis.operox.inv.dao.PurchaseOrderDao;
import com.bis.operox.inv.dao.entity.PurchaseOrder;
import com.bis.operox.inv.dao.entity.ReceivedStock;
import com.bis.operox.inv.dao.entity.Role;
/**
 * 
 * @author Rajashekar
 * @date 23rd Sep 2016
 *
 */
@Repository
public class PurchaseOrderDaoImpl implements PurchaseOrderDao{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private static final Logger logger = LoggerFactory.getLogger(PurchaseOrderDaoImpl.class);

	@Override
	@Transactional
	public PurchaseOrder addPurchaseOrder(PurchaseOrder purchaseOrder) {
		Session session = this.sessionFactory.getCurrentSession();
		session.saveOrUpdate(purchaseOrder);
		logger.info("purchaseOrder added successfully..!!");
		return purchaseOrder;
	}

	
	@Override
	@Transactional
	public PurchaseOrder getPurchaseOrderById(Long id) {
			Session session = this.sessionFactory.getCurrentSession();
	        String query = "from PurchaseOrder purchaseOrder left join fetch purchaseOrder.Supplier where purchaseOrder.id = :id";  
	        List<PurchaseOrder> purchaseOrderList = session.createQuery(query).setLong("id", id).setCacheable(true).list();
	        PurchaseOrder purchaseOrder = null;
	        if(purchaseOrderList != null && !purchaseOrderList.isEmpty()){
	        	purchaseOrder = purchaseOrderList.iterator().next();
	        }
	        return purchaseOrder;
	}
	
	
	@Override
	@Transactional
	public PurchaseOrder getPurchaseOrderBySupplierIdAndWorkOrderId(String suppierId, String workOrderId) throws Exception {
			Session session = this.sessionFactory.getCurrentSession();
	        String query = "from PurchaseOrder purchaseOrder where purchaseOrder.Supplier.id = :suppierId and purchaseOrder.workOrder.id = :workOrderId ";  
	        List<PurchaseOrder> purchaseOrderList = session.createQuery(query).setString("suppierId", suppierId).setString("workOrderId", workOrderId).setCacheable(true).list();
	        PurchaseOrder purchaseOrder = null;
	        if(purchaseOrderList != null && !purchaseOrderList.isEmpty()){
	        	purchaseOrder = purchaseOrderList.iterator().next();
	        }
	        return purchaseOrder;
	}

	@Override
	@Transactional
	public List<PurchaseOrder> getAllPurchaseOrders() {
		Session session = this.sessionFactory.getCurrentSession();
		List<PurchaseOrder> purchaseOrder = session.createQuery("from PurchaseOrder pOrder left join fetch pOrder.Supplier left join fetch pOrder.storeId").list();
		return purchaseOrder;
	}
	
	@Override
	@Transactional
	public String getMaxPurchaseOrderId() {
		
		Session session = this.sessionFactory.getCurrentSession();
		
		/*SELECT MAX(CAST((SUBSTRING([column_name] , [digit])) as UNSIGNED)) FROM `[table_name]`;
		
		List purchaseOrderList =  session.createQuery("SELECT MAX(po.purchaseNumber) from PurchaseOrder po").list();
		PurchaseOrder purchaseOrder = null;
		if(purchaseOrderList != null && !purchaseOrderList.isEmpty())
		{
			purchaseOrder = (PurchaseOrder) purchaseOrderList.iterator().next();
		}
		if(purchaseOrder != null){
			return purchaseOrder.getPurchaseNumber();
		}else{
			return "";
		}*/
		
		
		
		String query = "SELECT  MAX(CAST(SUBSTR(TRIM(PURCHASE_NUMBER),2) AS UNSIGNED)) FROM operox.PURCHASE_ORDER";
		List<BigInteger> maxCountList = session.createSQLQuery(query).list();
        Long count = null;
        if(maxCountList != null && !maxCountList.isEmpty() && maxCountList.get(0) != null ){
            count = (Long) maxCountList.iterator().next().longValue();
            return count.toString();
        }else{
        	return "";
        }
	}

}
