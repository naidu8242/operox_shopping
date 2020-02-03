package com.bis.operox.inv.service;

import org.json.JSONObject;

import com.bis.operox.inv.dao.entity.Offer;
import com.bis.operox.inv.dao.entity.OfferBuyxGetY;
import com.bis.operox.inv.dao.entity.User;

public interface OfferBuyxGetYService {

	OfferBuyxGetY storeOfferBuyxGetY(OfferBuyxGetY offerBuyxGetY) throws Exception;
	
	OfferBuyxGetY saveOfferBuyxGetYDetails(JSONObject jsonObj, User user,Offer offer) throws Exception;
	
	OfferBuyxGetY getOfferBuyxGetYByOfferId(Long offerId);
	
	OfferBuyxGetY getOfferBuyxGetYByBuyItemProductIdAndCompanyId(Long buyItemProductId,Long companyId) throws Exception;
}
