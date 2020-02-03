package com.bis.operox.inv.service;

import java.util.List;

import org.json.JSONObject;

import com.bis.operox.inv.dao.entity.Offer;
import com.bis.operox.inv.dao.entity.OfferStores;
import com.bis.operox.inv.dao.entity.User;

public interface OfferStroesService {
	
	OfferStores storeOfferStroes(OfferStores offerStroes) throws Exception;
	
	public void saveOfferStroesDetails(JSONObject jsonObj, User user,Offer offer) throws Exception;
	
	List<OfferStores> getOfferStroesByOfferId(Long offerId) throws Exception;

}
