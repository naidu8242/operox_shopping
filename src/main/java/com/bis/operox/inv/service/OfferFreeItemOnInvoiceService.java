package com.bis.operox.inv.service;

import org.json.JSONObject;

import com.bis.operox.inv.dao.entity.Offer;
import com.bis.operox.inv.dao.entity.OfferFreeItemOnInvoice;
import com.bis.operox.inv.dao.entity.User;

public interface OfferFreeItemOnInvoiceService {
	
	OfferFreeItemOnInvoice storeOfferFreeItemOnInvoice(OfferFreeItemOnInvoice offerFreeItemOnInvoice);
	
	public void saveOfferFreeItemOnInvoice(JSONObject jsonObj, User user,Offer offer) throws Exception;
	
	OfferFreeItemOnInvoice getOfferFreeItemOnInvoiceByOfferId(Long offerId) throws Exception;

}
