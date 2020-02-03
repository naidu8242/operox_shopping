package com.bis.operox.inv.dao;

import com.bis.operox.inv.dao.entity.OfferCustomerLevel;

public interface OfferCustomerLevelDao {
	
	OfferCustomerLevel storeOfferCustomerLevel(OfferCustomerLevel offerCustomerLevel) throws Exception;
	
	OfferCustomerLevel getOfferCustomerLevelByOfferId(Long offerId) throws Exception;
	
	OfferCustomerLevel getOfferCustomerLevelById(Long id);
	
}
