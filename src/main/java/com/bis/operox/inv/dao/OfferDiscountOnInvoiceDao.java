package com.bis.operox.inv.dao;

import java.util.List;

import com.bis.operox.inv.dao.entity.Offer;
import com.bis.operox.inv.dao.entity.OfferDiscountOnInvoice;

public interface OfferDiscountOnInvoiceDao {
	
	OfferDiscountOnInvoice storeOfferDiscountOnInvoice(OfferDiscountOnInvoice offerDiscountOnInvoice) throws Exception;
	
	OfferDiscountOnInvoice getOfferDiscountOnInvoiceByOfferId(Long offerId);
	
	OfferDiscountOnInvoice getOfferDiscountOnInvoiceById(Long id);
	
	OfferDiscountOnInvoice getOfferDiscountOnInvoice(Offer offer) throws Exception;
	
	List<Float> getStoreInvoiceAmounts(Long storeId);
	
	OfferDiscountOnInvoice getDiscountOnInvoiceAmountByCompanyId(Float invoiceAmount,Long companyId);
	

}
