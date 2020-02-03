package com.bis.operox.inv.dao;

import java.util.List;

import com.bis.operox.inv.dao.entity.OfferStores;

public interface OfferStroesDao {
	
	OfferStores storeOfferStroes(OfferStores offerStroes) throws Exception;
	
	List<OfferStores> getOfferStroesByOfferId(Long offerId) throws Exception;
	
	OfferStores getOfferStroesById(Long id);
	
	public void deleteOfferStore(OfferStores offerStores);
	

}
