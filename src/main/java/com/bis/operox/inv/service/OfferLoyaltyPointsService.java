package com.bis.operox.inv.service;

import org.json.JSONObject;

import com.bis.operox.inv.dao.entity.Offer;
import com.bis.operox.inv.dao.entity.OfferLoyaltyPoints;
import com.bis.operox.inv.dao.entity.User;

public interface OfferLoyaltyPointsService {

	OfferLoyaltyPoints storeOfferLoyaltyPoints(OfferLoyaltyPoints offerLoyaltyPoints) throws Exception;
	
	public void saveOfferLoyaltyPointsDetails(JSONObject jsonObj, User user,Offer offer) throws Exception;
	
	OfferLoyaltyPoints getOfferLoyaltyPointsByOfferId(Long offerId ) throws Exception;
}
