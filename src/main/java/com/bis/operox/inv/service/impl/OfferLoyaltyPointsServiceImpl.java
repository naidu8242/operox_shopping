package com.bis.operox.inv.service.impl;

import java.util.Date;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bis.operox.inv.dao.OfferLoyaltyPointsDao;
import com.bis.operox.inv.dao.entity.Offer;
import com.bis.operox.inv.dao.entity.OfferDiscountOnInvoice;
import com.bis.operox.inv.dao.entity.OfferLoyaltyPoints;
import com.bis.operox.inv.dao.entity.User;
import com.bis.operox.inv.service.OfferLoyaltyPointsService;

@Repository
public class OfferLoyaltyPointsServiceImpl implements OfferLoyaltyPointsService{
	
	@Autowired
	OfferLoyaltyPointsDao offerLoyaltyPointsDao;

	@Override
	public OfferLoyaltyPoints storeOfferLoyaltyPoints(OfferLoyaltyPoints offerLoyaltyPoints) throws Exception {
		return offerLoyaltyPointsDao.storeOfferLoyaltyPoints(offerLoyaltyPoints);
	}

	@Override
	public void saveOfferLoyaltyPointsDetails(JSONObject jsonObj, User user, Offer offer) throws Exception {
		
		OfferLoyaltyPoints offerLoyaltyPoints = null;
		
		if(jsonObj.has("offerLoyaltyPointsId") && jsonObj.getString("offerLoyaltyPointsId") != null && !"".equals(jsonObj.getString("offerLoyaltyPointsId"))){
			offerLoyaltyPoints = offerLoyaltyPointsDao.getOfferLoyaltyPointsById(Long.parseLong(jsonObj.getString("offerLoyaltyPointsId")));
		}else{
			offerLoyaltyPoints = new OfferLoyaltyPoints();
		}
		
		
		offerLoyaltyPoints.setOffer(offer);
		offerLoyaltyPoints.setInvoiceAmount(jsonObj.getString("loyaltyInvoiceAmount"));
		offerLoyaltyPoints.setLoyalityPoints(jsonObj.getString("invoiceLoyaltyPoints"));
		offerLoyaltyPoints.setLoyalityPointValue(jsonObj.getString("oneLoyaltyPoint"));
		offerLoyaltyPoints.setStatus(1);
		offerLoyaltyPoints.setCreatedBy(user.getCreatedBy());
		offerLoyaltyPoints.setCreatedDate(new Date());
		offerLoyaltyPointsDao.storeOfferLoyaltyPoints(offerLoyaltyPoints);
		
		
	}

	@Override
	public OfferLoyaltyPoints getOfferLoyaltyPointsByOfferId(Long offerId) throws Exception {
		return offerLoyaltyPointsDao.getOfferLoyaltyPointsByOfferId(offerId);
	}

}
