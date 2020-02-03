package com.bis.operox.inv.dao;

import java.util.List;

import com.bis.operox.inv.dao.entity.SupplierInvoice;

 /**
  * @author Teja
  * 
  */
public interface SupplierInvoiceDao {
	
	
	public SupplierInvoice addSupplierInvoice(SupplierInvoice SupplierInvoice);
	
	public SupplierInvoice getSupplierInvoiceById(int id);
	
	public List<SupplierInvoice> listSupplierInvoice();
	
	SupplierInvoice getSupplierInvoiceByStoreReceivedId(Long id);

}
