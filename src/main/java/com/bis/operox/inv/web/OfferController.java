package com.bis.operox.inv.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bis.operox.constants.Constants;
import com.bis.operox.inv.dao.entity.Offer;
import com.bis.operox.inv.dao.entity.OfferBuyxGetY;
import com.bis.operox.inv.dao.entity.OfferCoupons;
import com.bis.operox.inv.dao.entity.OfferCustomerLevel;
import com.bis.operox.inv.dao.entity.OfferDiscountOnInvoice;
import com.bis.operox.inv.dao.entity.OfferFreeItemOnInvoice;
import com.bis.operox.inv.dao.entity.OfferLoyaltyPoints;
import com.bis.operox.inv.dao.entity.OfferStores;
import com.bis.operox.inv.dao.entity.Product;
import com.bis.operox.inv.dao.entity.Store;
import com.bis.operox.inv.dao.entity.User;
import com.bis.operox.inv.service.OfferBuyxGetYService;
import com.bis.operox.inv.service.OfferCouponsService;
import com.bis.operox.inv.service.OfferCustomerLevelService;
import com.bis.operox.inv.service.OfferDiscountOnInvoiceService;
import com.bis.operox.inv.service.OfferFreeItemOnInvoiceService;
import com.bis.operox.inv.service.OfferLoyaltyPointsService;
import com.bis.operox.inv.service.OfferService;
import com.bis.operox.inv.service.OfferStroesService;
import com.bis.operox.inv.service.ProductService;
import com.bis.operox.inv.service.StoreService;
import com.bis.operox.util.CommonUtil;
import com.bis.operox.util.OperoxSessionManager;

@Controller
public class OfferController {
	
	@Value("${operoxUrl}")
	private String operoxUrl;
	
	@Autowired
	CommonUtil commonUtil;
	
	@Autowired
	OfferService offerService;
	
	@Autowired
	OfferBuyxGetYService offerBuyxGetYService;
	
	@Autowired
	OfferStroesService  offerStroesService;
	
	@Autowired
	OfferCustomerLevelService offerCustomerLevelService;
	
	@Autowired
	OfferDiscountOnInvoiceService offerDiscountOnInvoiceService;
	
	@Autowired
	OfferFreeItemOnInvoiceService offerFreeItemOnInvoiceService;
	 
	@Autowired
	OfferCouponsService offerCouponsService;
	
	@Autowired
	OfferLoyaltyPointsService offerLoyaltyPointsService;
	
	@Autowired
	StoreService storeService;
	
	@Autowired
	ProductService productService;
	
	@RequestMapping(value = "/viewOffer/{offerId}")
	public ModelAndView viewOffer(@PathVariable Long offerId,HttpServletRequest request) throws Exception{
		commonUtil.highLightMenu(Constants.Setup, request);
		ModelAndView model = new ModelAndView();
		Offer offer = offerService.getOfferById(offerId);
		
		model.addObject("offer",offer);
		
		if(offer.getOfferType().equalsIgnoreCase("Buy-X Get-Y")){
			OfferBuyxGetY offerBuyxGetY = offerBuyxGetYService.getOfferBuyxGetYByOfferId(offer.getId());
			model.addObject("offerBuyxGetY",offerBuyxGetY);
		}
		
		if(offer.getOfferType().equalsIgnoreCase("Discount on Invoice")){
			OfferDiscountOnInvoice offerDiscountOnInvoice = offerDiscountOnInvoiceService.getOfferDiscountOnInvoiceByOfferId(offer.getId());
			model.addObject("offerDiscountOnInvoice",offerDiscountOnInvoice);
		}
		
		if(offer.getOfferType().equalsIgnoreCase("Free Item on Invoice")){
			OfferFreeItemOnInvoice offerFreeItemOnInvoice = offerFreeItemOnInvoiceService.getOfferFreeItemOnInvoiceByOfferId(offer.getId());
			model.addObject("offerFreeItemOnInvoice",offerFreeItemOnInvoice);
		}
		
		if(offer.getOfferType().equalsIgnoreCase("Coupons")){
			OfferCoupons offerCoupons = offerCouponsService.getOfferCouponsByOfferId(offer.getId());
			model.addObject("offerCoupons",offerCoupons);
		}
		
		if(offer.getOfferType().equalsIgnoreCase("Loyalty points")){
			OfferLoyaltyPoints offerLoyaltyPoints = offerLoyaltyPointsService.getOfferLoyaltyPointsByOfferId(offer.getId());
			model.addObject("offerLoyaltyPoints",offerLoyaltyPoints);
		}
		
		List<OfferStores> offerStroesList = offerStroesService.getOfferStroesByOfferId(offer.getId());
		model.addObject("offerStroesList",offerStroesList);
		
		OfferCustomerLevel offerCustomerLevel = offerCustomerLevelService.getOfferCustomerLevelByOfferId(offer.getId());
		if(offerCustomerLevel != null){
			model.addObject("offerCustomerLevel",offerCustomerLevel);
		}
		
		
		
		model.addObject("operoxUrl",operoxUrl);
		model.setViewName("setup/viewOffer");
		return model;
	}
	
	
	@RequestMapping(value = "/editOffer/{offerId}")
	public ModelAndView editOffer(@PathVariable Long offerId,HttpServletRequest request) throws Exception{
		
		commonUtil.highLightMenu(Constants.Setup, request);
		ModelAndView model = new ModelAndView();
		User user = (User) OperoxSessionManager.getSessionAttribute(request, "user");
		
		Offer offer = offerService.getOfferById(offerId);
		if(null != offer.getOfferStartDate()){
			offer.setOfferStartDateStr(Constants.DF_MDY.format(offer.getOfferStartDate()));
		}
		if(null != offer.getOfferEndDate()){
			offer.setOfferEndDateStr(Constants.DF_MDY.format(offer.getOfferEndDate()));
		}
		
		
		model.addObject("offer",offer);
		
		List<Product> productsList = productService.getAllProducts(user.getStore().getCompany().getId());
		model.addObject("productsList", productsList);
		
		List<Store> storesList = null;
		ArrayList<Store> storeList = new ArrayList<Store>();
		try {
			 storesList =  storeService.storesListByCompanyId(user.getStore().getCompany().getId());
			 if(null != storesList){
				 for(Store store : storesList){
					 List<OfferStores> offerStroesList = offerStroesService.getOfferStroesByOfferId(offer.getId());
					 for(OfferStores offerStroes : offerStroesList){
						 if(store.getId().equals(offerStroes.getStore().getId())){
							 store.setIsStoreSelected("Y");;
						 }
					 }
					 storeList.add(store);
				 }
				 
				 model.addObject("storesList", storeList);
			 }
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		if(offer.getOfferType().equalsIgnoreCase("Buy-X Get-Y")){
			OfferBuyxGetY offerBuyxGetY = offerBuyxGetYService.getOfferBuyxGetYByOfferId(offer.getId());
			model.addObject("offerBuyxGetY",offerBuyxGetY);
		}
		
		if(offer.getOfferType().equalsIgnoreCase("Discount on Invoice")){
			OfferDiscountOnInvoice offerDiscountOnInvoice = offerDiscountOnInvoiceService.getOfferDiscountOnInvoiceByOfferId(offer.getId());
			model.addObject("offerDiscountOnInvoice",offerDiscountOnInvoice);
		}
		
		if(offer.getOfferType().equalsIgnoreCase("Free Item on Invoice")){
			OfferFreeItemOnInvoice offerFreeItemOnInvoice = offerFreeItemOnInvoiceService.getOfferFreeItemOnInvoiceByOfferId(offer.getId());
			model.addObject("offerFreeItemOnInvoice",offerFreeItemOnInvoice);
		}
		
		if(offer.getOfferType().equalsIgnoreCase("Coupons")){
			OfferCoupons offerCoupons = offerCouponsService.getOfferCouponsByOfferId(offer.getId());
			model.addObject("offerCoupons",offerCoupons);
		}
		
		if(offer.getOfferType().equalsIgnoreCase("Loyalty points")){
			OfferLoyaltyPoints offerLoyaltyPoints = offerLoyaltyPointsService.getOfferLoyaltyPointsByOfferId(offer.getId());
			model.addObject("offerLoyaltyPoints",offerLoyaltyPoints);
		}
		
		
		OfferCustomerLevel offerCustomerLevel = offerCustomerLevelService.getOfferCustomerLevelByOfferId(offer.getId());
		if(offerCustomerLevel != null){
			model.addObject("offerCustomerLevel",offerCustomerLevel);
		}

		model.addObject("operoxUrl",operoxUrl);
		model.setViewName("setup/addOffer");
		return model;
	}

}
