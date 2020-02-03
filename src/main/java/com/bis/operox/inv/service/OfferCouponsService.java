package com.bis.operox.inv.service;

import org.json.JSONObject;

import com.bis.operox.inv.dao.entity.Offer;
import com.bis.operox.inv.dao.entity.OfferCoupons;
import com.bis.operox.inv.dao.entity.OfferDiscountOnInvoice;
import com.bis.operox.inv.dao.entity.User;

public interface OfferCouponsService {
	
	OfferCoupons storeOfferCoupons(OfferCoupons offerCoupons) throws Exception;
	
	public void saveOfferCouponsDetails(JSONObject jsonObj, User user,Offer offer) throws Exception;

	OfferCoupons getOfferCouponsByOfferId(Long offerId) throws Exception;
	
	OfferCoupons getOfferCouponsByOffer(Offer offer) throws Exception;
	
	OfferCoupons getOfferCouponsBycouponName(String couponName,Long companyId) throws Exception;

}
