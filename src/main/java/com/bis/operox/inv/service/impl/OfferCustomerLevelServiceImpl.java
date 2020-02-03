package com.bis.operox.inv.service.impl;

import java.util.Date;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bis.operox.inv.dao.OfferCustomerLevelDao;
import com.bis.operox.inv.dao.entity.Offer;
import com.bis.operox.inv.dao.entity.OfferCustomerLevel;
import com.bis.operox.inv.dao.entity.User;
import com.bis.operox.inv.service.OfferCustomerLevelService;

@Service
public class OfferCustomerLevelServiceImpl implements OfferCustomerLevelService{
	
	@Autowired
	OfferCustomerLevelDao  offerCustomerLevelDao;

	@Override
	public OfferCustomerLevel storeOfferCustomerLevel(OfferCustomerLevel offerCustomerLevel) throws Exception {
		return offerCustomerLevelDao.storeOfferCustomerLevel(offerCustomerLevel);
	}

	@Override
	public void saveOfferCustomerLevelDetails(JSONObject jsonObj, User user, Offer offer) throws Exception {
		
		
		OfferCustomerLevel offerCustomerLevel = null;
		
		if(jsonObj.has("offerCustomerId") && jsonObj.getString("offerCustomerId") != null && !"".equals(jsonObj.getString("offerCustomerId"))){
			offerCustomerLevel = offerCustomerLevelDao.getOfferCustomerLevelById(Long.parseLong(jsonObj.getString("offerCustomerId")));
		}else{
			offerCustomerLevel = new OfferCustomerLevel();
		}
		
		
		offerCustomerLevel.setOffer(offer);
		
		if(jsonObj.has("retail") && jsonObj.getString("retail") != null && !"".equals(jsonObj.getString("retail"))){
			offerCustomerLevel.setRetailCustomer("Y");
		 }else{
			 offerCustomerLevel.setRetailCustomer("N");
		 }
		
		if(jsonObj.has("wholesale") && jsonObj.getString("wholesale") != null && !"".equals(jsonObj.getString("wholesale"))){
			offerCustomerLevel.setWholeSaleCustomer("Y");
		 }else{
			 offerCustomerLevel.setWholeSaleCustomer("N");
		 }
		
		offerCustomerLevel.setStatus(1);
		offerCustomerLevel.setCreatedBy(user.getUserCode());
		offerCustomerLevel.setCreatedDate(new Date());
		offerCustomerLevelDao.storeOfferCustomerLevel(offerCustomerLevel);
		
	}

	@Override
	public OfferCustomerLevel getOfferCustomerLevelByOfferId(Long offerId) throws Exception {
		return offerCustomerLevelDao.getOfferCustomerLevelByOfferId(offerId);
	}

}
