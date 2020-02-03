package com.bis.operox.inv.service.impl;

import java.util.Date;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bis.operox.inv.dao.OfferDiscountOnInvoiceDao;
import com.bis.operox.inv.dao.entity.Offer;
import com.bis.operox.inv.dao.entity.OfferDiscountOnInvoice;
import com.bis.operox.inv.dao.entity.User;
import com.bis.operox.inv.service.OfferDiscountOnInvoiceService;

@Service
public class OfferDiscountOnInvoiceServiceImpl implements OfferDiscountOnInvoiceService{

	@Autowired
	OfferDiscountOnInvoiceDao offerDiscountOnInvoiceDao;
	
	@Override
	public OfferDiscountOnInvoice storeOfferDiscountOnInvoice(OfferDiscountOnInvoice offerDiscountOnInvoice)
			throws Exception {
		return offerDiscountOnInvoiceDao.storeOfferDiscountOnInvoice(offerDiscountOnInvoice);
	}

	@Override
	public void saveOfferDiscountOnInvoiceDetails(JSONObject jsonObj, User user, Offer offer) throws Exception {

		OfferDiscountOnInvoice offerDiscountOnInvoice = null;
		if(jsonObj.has("offerDiscountOnInvoiceId") && jsonObj.getString("offerDiscountOnInvoiceId") != null && !"".equals(jsonObj.getString("offerDiscountOnInvoiceId"))){
			offerDiscountOnInvoice = offerDiscountOnInvoiceDao.getOfferDiscountOnInvoiceById(Long.parseLong(jsonObj.getString("offerDiscountOnInvoiceId")));
		}else{
			offerDiscountOnInvoice = new OfferDiscountOnInvoice();
		}
		
		
		
		offerDiscountOnInvoice.setOffer(offer);
		/*offerDiscountOnInvoice.setSlnumber(Integer.parseInt(jsonObj.getString("discountOnInvoiceSlno")));*/
		offerDiscountOnInvoice.setInvoiceAmount(Float.parseFloat(jsonObj.getString("discountOnInvoiceAmount")));
		offerDiscountOnInvoice.setDiscountAmount(Float.parseFloat(jsonObj.getString("discountOnInvoiceDiscountAmount")));
		
		if(jsonObj.has("discountOnInvoicePercentage") && jsonObj.getString("discountOnInvoicePercentage") != null && !"".equals(jsonObj.getString("discountOnInvoicePercentage"))){
			offerDiscountOnInvoice.setIsPercentage(jsonObj.getString("discountOnInvoicePercentage"));
		}else{
			offerDiscountOnInvoice.setIsPercentage("N");
		}
		
		offerDiscountOnInvoice.setStatus(1);
		offerDiscountOnInvoice.setCreatedBy(user.getUserCode());
		offerDiscountOnInvoice.setCreatedDate(new Date());
		offerDiscountOnInvoiceDao.storeOfferDiscountOnInvoice(offerDiscountOnInvoice);
		
	}

	@Override
	public OfferDiscountOnInvoice getOfferDiscountOnInvoiceByOfferId(Long offerId) {
		return offerDiscountOnInvoiceDao.getOfferDiscountOnInvoiceByOfferId(offerId);
	}

	@Override
	public OfferDiscountOnInvoice getOfferDiscountOnInvoice(Offer offer) throws Exception {
		return offerDiscountOnInvoiceDao.getOfferDiscountOnInvoice(offer);
	}

	@Override
	public List<Float> getStoreInvoiceAmounts(Long storeId) {
		return offerDiscountOnInvoiceDao.getStoreInvoiceAmounts(storeId);
	}

	@Override
	public OfferDiscountOnInvoice getDiscountOnInvoiceAmountByCompanyId(Float invoiceAmount, Long companyId) {
		return offerDiscountOnInvoiceDao.getDiscountOnInvoiceAmountByCompanyId(invoiceAmount, companyId);
	}

}
