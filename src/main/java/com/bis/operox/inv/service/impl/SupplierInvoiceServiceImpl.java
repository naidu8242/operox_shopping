package com.bis.operox.inv.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bis.operox.inv.dao.SupplierInvoiceDao;
import com.bis.operox.inv.dao.entity.SupplierInvoice;
import com.bis.operox.inv.service.SupplierInvoiceService;

/**
 * 
 * @author Teja
 * @Date 23/09/2016
 * 
 */
@Service
public class SupplierInvoiceServiceImpl implements  SupplierInvoiceService {
	
    @Autowired
    private SupplierInvoiceDao supplierInvoiceDao;
    
	@Override
	public SupplierInvoice addSupplierinvoice(SupplierInvoice SupplierInvoice) {
		 
		return supplierInvoiceDao.addSupplierInvoice(SupplierInvoice);
	}

	@Override
	public SupplierInvoice getSupplierInvoiceById(int id) {
		
		return supplierInvoiceDao.getSupplierInvoiceById(id);
	}

	@Override
	public List<SupplierInvoice> listSupplierInvoice() {
		
		return supplierInvoiceDao.listSupplierInvoice();
		
	}

	@Override
	public SupplierInvoice getSupplierInvoiceByStoreReceivedId(Long storeReceivedId) {

		return supplierInvoiceDao.getSupplierInvoiceByStoreReceivedId(storeReceivedId);
	}
 

}
