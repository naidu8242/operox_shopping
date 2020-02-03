package com.bis.operox.inv.dao;

import com.bis.operox.inv.dao.entity.OfferLoyaltyPoints;

public interface OfferLoyaltyPointsDao {
	
	OfferLoyaltyPoints storeOfferLoyaltyPoints(OfferLoyaltyPoints offerLoyaltyPoints) throws Exception;
	
	OfferLoyaltyPoints getOfferLoyaltyPointsByOfferId(Long offerId ) throws Exception;
	
	OfferLoyaltyPoints getOfferLoyaltyPointsById(Long id);

}
