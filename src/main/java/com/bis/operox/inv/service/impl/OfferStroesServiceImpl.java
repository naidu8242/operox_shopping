package com.bis.operox.inv.service.impl;

import java.util.Date;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bis.operox.inv.dao.OfferStroesDao;
import com.bis.operox.inv.dao.StoreDao;
import com.bis.operox.inv.dao.entity.Offer;
import com.bis.operox.inv.dao.entity.OfferStores;
import com.bis.operox.inv.dao.entity.Store;
import com.bis.operox.inv.dao.entity.User;
import com.bis.operox.inv.service.OfferStroesService;

@Service
public class OfferStroesServiceImpl implements OfferStroesService{

	@Autowired
	OfferStroesDao offerStroesDao;
	
	@Autowired
	StoreDao storeDao;
	
	@Override
	public OfferStores storeOfferStroes(OfferStores offerStroes) throws Exception {
		return offerStroesDao.storeOfferStroes(offerStroes);
	}

	@Override
	public void saveOfferStroesDetails(JSONObject jsonObj, User user, Offer offer) throws Exception {
		
		 OfferStores offerStroes = null;
		 String selectedStoreIds = jsonObj.getString("selectedStores");
		 selectedStoreIds = selectedStoreIds.replaceAll("\\[", "").replaceAll("\\]","");
         String[] storeIds = selectedStoreIds.split(",");
         
         List<OfferStores> offerStoresList = offerStroesDao.getOfferStroesByOfferId(offer.getId());
         if(offerStoresList != null){
        	 for(OfferStores offerStores : offerStoresList){
        		 offerStroesDao.deleteOfferStore(offerStores);
        	 }
         }
         
         for(int i=0; i<storeIds.length; i++){
        	 
        	 offerStroes = new OfferStores();
        	 offerStroes.setOffer(offer);
        	 Store store = storeDao.getStoreById(Long.parseLong(storeIds[i].replace("\"", "")));
        	 offerStroes.setStore(store);
        	 offerStroes.setStatus(1);
        	 offerStroes.setCreatedBy(user.getUserCode());
        	 offerStroes.setCreatedDate(new Date());
        	 offerStroesDao.storeOfferStroes(offerStroes);
         }
         
	}

	@Override
	public List<OfferStores> getOfferStroesByOfferId(Long offerId) throws Exception {
		return offerStroesDao.getOfferStroesByOfferId(offerId);
	}

}
