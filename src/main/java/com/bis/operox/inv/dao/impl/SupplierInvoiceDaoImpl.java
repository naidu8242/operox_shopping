package com.bis.operox.inv.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bis.operox.inv.dao.SupplierInvoiceDao;
import com.bis.operox.inv.dao.entity.SupplierInvoice;

/**
 * 
 * @author Teja
 * @Date 23/09/2016
 * 
 */
@Repository
public class SupplierInvoiceDaoImpl implements SupplierInvoiceDao{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private static final Logger logger = LoggerFactory.getLogger(SupplierInvoiceDaoImpl.class);


	@Transactional
	public SupplierInvoice addSupplierInvoice(SupplierInvoice si) {
		Session session = this.sessionFactory.getCurrentSession();
		session.saveOrUpdate(si);
		logger.info("SupplierInvoic saved successfully, SupplierInvoic Details="+si);
		return si;
		
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<SupplierInvoice> listSupplierInvoice() {
		Session session = this.sessionFactory.getCurrentSession();
		List<SupplierInvoice> supplierInvoiceList = session.createQuery("from SupplierInvoice").list();
		
		return supplierInvoiceList;
	}

	@Transactional
	public SupplierInvoice getSupplierInvoiceById(int id) {
		Session session = this.sessionFactory.getCurrentSession();	
		
		SupplierInvoice si = (SupplierInvoice) session.load(SupplierInvoice.class, new Integer(id));
		logger.info("SupplierInvoice loaded successfully, SupplierInvoice details="+si);
		return si;
	}

	@Override
	@Transactional
	public SupplierInvoice getSupplierInvoiceByStoreReceivedId(Long stockId) {
		Session session = this.sessionFactory.getCurrentSession();
		List<SupplierInvoice> supplierInvoiceList = session.createQuery("from SupplierInvoice si where si.StockReceivedId.id = :stockId ").setParameter("stockId",stockId).list();
		
		if (supplierInvoiceList.size() > 0) {
			return supplierInvoiceList.get(0);
		} else {
			return null;
		}
		 
	}
	
}
