package com.bis.operox.inv.dao;

import java.util.List;

import com.bis.operox.inv.dao.entity.Offer;

public interface OfferDao {

	Offer addOffer(Offer offer);
	
	Offer getOfferById(Long id);

	List<Offer> offerList();
	
	List<Offer> getofferListByCompanyId(Long companyId);
	
	Offer getOfferByOfferDiscountOnInvoiceAmountAndStoreId(Long storeId,Float billAmount);
	
	Offer getOfferByOfferCoupounAmountByCouponNumberAndStoreId(Long storeId,String couponName);


}