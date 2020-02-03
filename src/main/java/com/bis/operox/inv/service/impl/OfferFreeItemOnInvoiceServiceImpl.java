package com.bis.operox.inv.service.impl;

import java.util.Date;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bis.operox.inv.dao.OfferFreeItemOnInvoiceDao;
import com.bis.operox.inv.dao.ProductDao;
import com.bis.operox.inv.dao.entity.Offer;
import com.bis.operox.inv.dao.entity.OfferFreeItemOnInvoice;
import com.bis.operox.inv.dao.entity.Product;
import com.bis.operox.inv.dao.entity.User;
import com.bis.operox.inv.service.OfferFreeItemOnInvoiceService;

@Service
public class OfferFreeItemOnInvoiceServiceImpl implements OfferFreeItemOnInvoiceService{

	@Autowired
	OfferFreeItemOnInvoiceDao offerFreeItemOnInvoiceDao;
	
	@Autowired
	ProductDao productDao;
	
	@Override
	public OfferFreeItemOnInvoice storeOfferFreeItemOnInvoice(OfferFreeItemOnInvoice offerFreeItemOnInvoice) {
		return offerFreeItemOnInvoiceDao.storeOfferFreeItemOnInvoice(offerFreeItemOnInvoice);
	}

	@Override
	public void saveOfferFreeItemOnInvoice(JSONObject jsonObj, User user, Offer offer) throws Exception {
		
		OfferFreeItemOnInvoice offerFreeItemOnInvoice =  null;
		if(jsonObj.has("offerFreeItemOnInvoiceId") && jsonObj.getString("offerFreeItemOnInvoiceId") != null && !"".equals(jsonObj.getString("offerFreeItemOnInvoiceId"))){
			offerFreeItemOnInvoice = offerFreeItemOnInvoiceDao.getOfferFreeItemOnInvoiceById(Long.parseLong(jsonObj.getString("offerFreeItemOnInvoiceId")));
		}else{
			offerFreeItemOnInvoice =  new OfferFreeItemOnInvoice();
		}
		
		
		offerFreeItemOnInvoice.setOffer(offer);
		offerFreeItemOnInvoice.setTotalInvoiceAmount(jsonObj.getString("totalFreeItemInvoice"));
		
		String[] freeItem = jsonObj.getString("freeItemOnInvoice").split(",");
		Product freeItemProduct = productDao.getProductById(Long.parseLong(freeItem[0]));
		offerFreeItemOnInvoice.setFreeItemProduct(freeItemProduct);
		offerFreeItemOnInvoice.setFreeItem(freeItem[1]);
		
		offerFreeItemOnInvoice.setFreeItemQuantity(jsonObj.getString("freeItemQuantityOnInvoice"));
		offerFreeItemOnInvoice.setStatus(1);
		offerFreeItemOnInvoice.setCreatedBy(user.getUserCode());
		offerFreeItemOnInvoice.setCreatedDate(new Date());
		offerFreeItemOnInvoiceDao.storeOfferFreeItemOnInvoice(offerFreeItemOnInvoice);
		
		
	}

	@Override
	public OfferFreeItemOnInvoice getOfferFreeItemOnInvoiceByOfferId(Long offerId) throws Exception {
		return offerFreeItemOnInvoiceDao.getOfferFreeItemOnInvoiceByOfferId(offerId);
	}

}
