package com.bis.operox.inv.dao;

import com.bis.operox.inv.dao.entity.OfferFreeItemOnInvoice;

public interface OfferFreeItemOnInvoiceDao {
	
	OfferFreeItemOnInvoice storeOfferFreeItemOnInvoice(OfferFreeItemOnInvoice offerFreeItemOnInvoice);
	
	OfferFreeItemOnInvoice getOfferFreeItemOnInvoiceByOfferId(Long offerId) throws Exception;
	
	OfferFreeItemOnInvoice getOfferFreeItemOnInvoiceById(Long id);

}
