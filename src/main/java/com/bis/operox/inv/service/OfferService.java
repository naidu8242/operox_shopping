package com.bis.operox.inv.service;

import java.util.List;

import org.json.JSONObject;

import com.bis.operox.inv.dao.entity.Offer;
import com.bis.operox.inv.dao.entity.User;

public interface OfferService {

    Offer addOffer(Offer offer);
	
	Offer getOfferById(Long id);

	List<Offer> offerList();
	
	Offer storeOfferDetails(JSONObject jsonObj, User user) throws Exception;
	
	List<Offer> getofferListByCompanyId(Long companyId);
	
	Offer getOfferByOfferDiscountOnInvoiceAmountAndStoreId(Long storeId,Float billAmount);
	
	Offer getOfferByOfferCouponAmountByCoupounNumberAndStoreId(Long storeId,String couponName);

}
