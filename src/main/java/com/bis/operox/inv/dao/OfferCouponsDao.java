package com.bis.operox.inv.dao;

import com.bis.operox.inv.dao.entity.Offer;
import com.bis.operox.inv.dao.entity.OfferCoupons;

public interface OfferCouponsDao {
	
	OfferCoupons storeOfferCoupons(OfferCoupons offerCoupons) throws Exception;
	
	OfferCoupons getOfferCouponsByOfferId(Long offerId) throws Exception;
	
	OfferCoupons getOfferCouponsById(Long id);
	
	OfferCoupons getOfferCouponsByOffer(Offer offer) throws Exception;
	
	OfferCoupons getOfferCouponsBycouponName(String couponName,Long companyId) throws Exception;
	
	

}
