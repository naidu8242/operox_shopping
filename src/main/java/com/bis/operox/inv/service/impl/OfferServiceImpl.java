package com.bis.operox.inv.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.bis.operox.constants.Constants;
import com.bis.operox.inv.dao.OfferDao;
import com.bis.operox.inv.dao.entity.Offer;
import com.bis.operox.inv.dao.entity.User;
import com.bis.operox.inv.service.OfferService;

@Service
@Repository
public class OfferServiceImpl implements OfferService{
	
	@Autowired
	private OfferDao offerDao;

	@Override
	public Offer addOffer(Offer offer) {
		return offerDao.addOffer(offer);
	}

	@Override
	public Offer getOfferById(Long id) {
		return offerDao.getOfferById(id);
	}

	@Override
	public List<Offer> offerList() {
		return this.offerDao.offerList();
	}

	@Override
	public Offer storeOfferDetails(JSONObject jsonObj, User user) throws Exception {
		
		String pattern = "MM/dd/yyyy";
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		
		Offer offer = null;
		
		if(jsonObj.has("offerId") && jsonObj.getString("offerId") != null && !"".equals(jsonObj.getString("offerId"))){
			offer = offerDao.getOfferById(Long.parseLong(jsonObj.getString("offerId")));
		}else{
			offer = new Offer();
		}
		
		offer.setOfferName(jsonObj.getString("offerName"));
		if(jsonObj.has("offerType") && jsonObj.getString("offerType") != null && !"".equals(jsonObj.getString("offerType"))){
			offer.setOfferType(jsonObj.getString("offerType"));
		}
		
		offer.setCompany(user.getStore().getCompany());
		if(jsonObj.has("startDate") && jsonObj.getString("startDate") != null && !"".equals(jsonObj.getString("startDate"))){
			Date startDate = format.parse(jsonObj.getString("startDate"));
			offer.setOfferStartDate(startDate);
		}
		
		if(jsonObj.has("endDate") && jsonObj.getString("endDate") != null && !"".equals(jsonObj.getString("endDate"))){
			Date endDate = format.parse(jsonObj.getString("endDate"));
			offer.setOfferEndDate(endDate);
		}
		
		if(jsonObj.has("sunday") && jsonObj.getString("sunday") != null && !"".equals(jsonObj.getString("sunday"))){
			offer.setSunday(jsonObj.getString("sunday"));
		}else{
			offer.setSunday("N");
		}
		
		if(jsonObj.has("monday") && jsonObj.getString("monday") != null && !"".equals(jsonObj.getString("monday"))){
			offer.setMonday(jsonObj.getString("monday"));
		}else{
			offer.setMonday("N");
		}
		
		if(jsonObj.has("tuesday") && jsonObj.getString("tuesday") != null && !"".equals(jsonObj.getString("tuesday"))){
			offer.setTuesday(jsonObj.getString("tuesday"));
		}else{
			offer.setTuesday("N");
		}
		
		if(jsonObj.has("wednesday") && jsonObj.getString("wednesday") != null && !"".equals(jsonObj.getString("wednesday"))){
			offer.setWednesday(jsonObj.getString("wednesday"));
		}else{
			offer.setWednesday("N");
		}
		
		if(jsonObj.has("thursday") && jsonObj.getString("thursday") != null && !"".equals(jsonObj.getString("thursday"))){
			offer.setThursday(jsonObj.getString("thursday"));
		}else{
			offer.setThursday("N");
		}
		
		if(jsonObj.has("friday") && jsonObj.getString("friday") != null && !"".equals(jsonObj.getString("friday"))){
			offer.setFriday(jsonObj.getString("friday"));
		}else{
			offer.setFriday("N");
		}
		
		if(jsonObj.has("saturday") && jsonObj.getString("saturday") != null && !"".equals(jsonObj.getString("saturday"))){
			offer.setSaturday(jsonObj.getString("saturday"));
		}else{
			offer.setSaturday("N");
		}
		
		if(jsonObj.has("startTime") && jsonObj.getString("startTime") != null && !"".equals(jsonObj.getString("startTime"))){
			offer.setOfferStartTime(jsonObj.getString("startTime"));
		}
		
		if(jsonObj.has("endTime") && jsonObj.getString("endTime") != null && !"".equals(jsonObj.getString("endTime"))){
			offer.setOfferEndTime(jsonObj.getString("endTime"));
		}
		offer.setStatus(1);
		offer.setCreatedDate(new Date());
		offer.setCreatedBy(user.getUserCode());
		
		offerDao.addOffer(offer);
		
		return offer;
	}

	@Override
	public List<Offer> getofferListByCompanyId(Long companyId) {
		return offerDao.getofferListByCompanyId(companyId);
	}

	@Override
	public Offer getOfferByOfferDiscountOnInvoiceAmountAndStoreId(Long storeId, Float billAmount) {
		return offerDao.getOfferByOfferDiscountOnInvoiceAmountAndStoreId(storeId, billAmount);
	}

	@Override
	public Offer getOfferByOfferCouponAmountByCoupounNumberAndStoreId(Long storeId, String couponName) {
		return offerDao.getOfferByOfferCoupounAmountByCouponNumberAndStoreId(storeId, couponName);
	}
}
