package com.bis.operox.inv.service;

import java.util.List;

import com.bis.operox.inv.dao.entity.SupplierInvoice;

public interface SupplierInvoiceService {

	
    SupplierInvoice addSupplierinvoice(SupplierInvoice SupplierInvoice);
	
	SupplierInvoice getSupplierInvoiceById(int id);
	
	SupplierInvoice getSupplierInvoiceByStoreReceivedId(Long storeReceivedId);
	
	List<SupplierInvoice> listSupplierInvoice(); 
	
}
