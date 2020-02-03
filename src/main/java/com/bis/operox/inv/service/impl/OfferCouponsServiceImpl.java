package com.bis.operox.inv.service.impl;

import java.util.Date;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bis.operox.inv.dao.OfferCouponsDao;
import com.bis.operox.inv.dao.entity.Offer;
import com.bis.operox.inv.dao.entity.OfferCoupons;
import com.bis.operox.inv.dao.entity.OfferLoyaltyPoints;
import com.bis.operox.inv.dao.entity.User;
import com.bis.operox.inv.service.OfferCouponsService;

@Service
public class OfferCouponsServiceImpl implements OfferCouponsService {
	
	@Autowired
	OfferCouponsDao offerCouponsDao;

	@Override
	public OfferCoupons storeOfferCoupons(OfferCoupons offerCoupons) throws Exception {
		return offerCouponsDao.storeOfferCoupons(offerCoupons);
	}

	@Override
	public void saveOfferCouponsDetails(JSONObject jsonObj, User user, Offer offer) throws Exception {
		
		OfferCoupons offerCoupons =  null;
		
		
		if(jsonObj.has("offerCouponsId") && jsonObj.getString("offerCouponsId") != null && !"".equals(jsonObj.getString("offerCouponsId"))){
			offerCoupons = offerCouponsDao.getOfferCouponsById(Long.parseLong(jsonObj.getString("offerCouponsId")));
		}else{
			offerCoupons = new OfferCoupons();
		}
		
		offerCoupons.setOffer(offer);
		if(jsonObj.has("couponName") && jsonObj.getString("couponName") != null && !"".equals(jsonObj.getString("couponName"))){
			offerCoupons.setCouponName(jsonObj.getString("couponName"));
		}
		offerCoupons.setInvoiceAmount(Float.parseFloat(jsonObj.getString("couponInvoiceAmount")));
		offerCoupons.setCouponValue(Float.parseFloat(jsonObj.getString("couponValue")));
		offerCoupons.setStatus(1);
		offerCoupons.setCreatedBy(user.getUserCode());
		offerCoupons.setCreatedDate(new Date());
		offerCouponsDao.storeOfferCoupons(offerCoupons);
		
		
	}

	@Override
	public OfferCoupons getOfferCouponsByOfferId(Long offerId) throws Exception {
		return offerCouponsDao.getOfferCouponsByOfferId(offerId);
	}

	@Override
	public OfferCoupons getOfferCouponsByOffer(Offer offer) throws Exception {
		return offerCouponsDao.getOfferCouponsByOffer(offer);
	}

	@Override
	public OfferCoupons getOfferCouponsBycouponName(String couponName, Long companyId) throws Exception {
		return offerCouponsDao.getOfferCouponsBycouponName(couponName, companyId);
	}

}
