package com.bis.operox.inv.dao;

import com.bis.operox.inv.dao.entity.Offer;
import com.bis.operox.inv.dao.entity.OfferBuyxGetY;

public interface OfferBuyxGetYDao {
	
	OfferBuyxGetY storeOfferBuyxGetY(OfferBuyxGetY offerBuyxGetY) throws Exception;
	
	OfferBuyxGetY getOfferBuyxGetYByOfferId(Long offerId);
	
	OfferBuyxGetY getOfferBuyxGetYById(Long id);
	
	OfferBuyxGetY getOfferBuyxGetYByBuyItemProductIdAndCompanyId(Long buyItemProductId,Long companyId) throws Exception;

}
