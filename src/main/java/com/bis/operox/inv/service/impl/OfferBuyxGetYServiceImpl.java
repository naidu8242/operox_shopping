package com.bis.operox.inv.service.impl;

import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bis.operox.inv.dao.OfferBuyxGetYDao;
import com.bis.operox.inv.dao.ProductDao;
import com.bis.operox.inv.dao.entity.Offer;
import com.bis.operox.inv.dao.entity.OfferBuyxGetY;
import com.bis.operox.inv.dao.entity.Product;
import com.bis.operox.inv.dao.entity.User;
import com.bis.operox.inv.service.OfferBuyxGetYService;

@Service
public class OfferBuyxGetYServiceImpl implements OfferBuyxGetYService{
	
	@Autowired
	OfferBuyxGetYDao offerBuyxGetYDao;
	
	@Autowired
	ProductDao  productDao;
	

	@Override
	public OfferBuyxGetY storeOfferBuyxGetY(OfferBuyxGetY offerBuyxGetY) throws Exception {
		return offerBuyxGetYDao.storeOfferBuyxGetY(offerBuyxGetY);
	}

	@Override
	public OfferBuyxGetY saveOfferBuyxGetYDetails(JSONObject jsonObj, User user,Offer offer) throws JSONException {
		
		OfferBuyxGetY offerBuyxGetY = null;
		
		if(jsonObj.has("offerBuyxGetYId") && jsonObj.getString("offerBuyxGetYId") != null && !"".equals(jsonObj.getString("offerBuyxGetYId"))){
			offerBuyxGetY = offerBuyxGetYDao.getOfferBuyxGetYById(Long.parseLong(jsonObj.getString("offerBuyxGetYId")));
		}else{
			offerBuyxGetY = new OfferBuyxGetY();
		}
		
		
		offerBuyxGetY.setOffer(offer);
		
		if(jsonObj.has("buyItem") && jsonObj.getString("buyItem") != null && !"".equals(jsonObj.getString("buyItem"))){
			String[] buyItem = jsonObj.getString("buyItem").split(",");
			Product buyItemProduct = productDao.getProductById(Long.parseLong(buyItem[0]));
			offerBuyxGetY.setBuyItemProduct(buyItemProduct);
			offerBuyxGetY.setBuyItem(buyItem[1]);
		}
		
		offerBuyxGetY.setBuyItemQuantity(jsonObj.getString("buyItemQuantity"));
		
		String[] freeItem = jsonObj.getString("freeItem").split(",");
		Product freeItemProduct = productDao.getProductById(Long.parseLong(freeItem[0]));
		offerBuyxGetY.setFreeItemProduct(freeItemProduct);
		offerBuyxGetY.setFreeItem(freeItem[1]);
		
		offerBuyxGetY.setFreeItemQuantity(jsonObj.getString("freeItemQuantity"));
		offerBuyxGetY.setStatus(1);
		offerBuyxGetY.setCreatedDate(new Date());
		offerBuyxGetY.setCreatedBy(user.getUserCode());
		try {
			offerBuyxGetYDao.storeOfferBuyxGetY(offerBuyxGetY);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return offerBuyxGetY;
		
	}

	@Override
	public OfferBuyxGetY getOfferBuyxGetYByOfferId(Long offerId) {
		return offerBuyxGetYDao.getOfferBuyxGetYByOfferId(offerId);
	}

	@Override
	public OfferBuyxGetY getOfferBuyxGetYByBuyItemProductIdAndCompanyId(Long buyItemProductId, Long companyId)
			throws Exception {
		return offerBuyxGetYDao.getOfferBuyxGetYByBuyItemProductIdAndCompanyId(buyItemProductId, companyId);
	}

	

}
