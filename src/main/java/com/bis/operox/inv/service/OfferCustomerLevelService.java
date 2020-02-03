package com.bis.operox.inv.service;

import org.json.JSONObject;

import com.bis.operox.inv.dao.entity.Offer;
import com.bis.operox.inv.dao.entity.OfferCustomerLevel;
import com.bis.operox.inv.dao.entity.User;

public interface OfferCustomerLevelService {
	
	OfferCustomerLevel storeOfferCustomerLevel(OfferCustomerLevel offerCustomerLevel) throws Exception;
	
	public void saveOfferCustomerLevelDetails(JSONObject jsonObj, User user,Offer offer) throws Exception;
	
	OfferCustomerLevel getOfferCustomerLevelByOfferId(Long offerId) throws Exception;

}
