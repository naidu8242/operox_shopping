package com.bis.operox.inv.service;

import java.util.List;

import org.json.JSONObject;

import com.bis.operox.inv.dao.entity.Offer;
import com.bis.operox.inv.dao.entity.OfferDiscountOnInvoice;
import com.bis.operox.inv.dao.entity.User;

public interface OfferDiscountOnInvoiceService {
	
	OfferDiscountOnInvoice storeOfferDiscountOnInvoice(OfferDiscountOnInvoice offerDiscountOnInvoice) throws Exception;

	public void  saveOfferDiscountOnInvoiceDetails(JSONObject jsonObj, User user,Offer offer) throws Exception;
	
	OfferDiscountOnInvoice getOfferDiscountOnInvoiceByOfferId(Long offerId);
	
	OfferDiscountOnInvoice getOfferDiscountOnInvoice(Offer offer) throws Exception;
	
	List<Float> getStoreInvoiceAmounts(Long storeId);
	
	OfferDiscountOnInvoice getDiscountOnInvoiceAmountByCompanyId(Float invoiceAmount,Long companyId);
}
