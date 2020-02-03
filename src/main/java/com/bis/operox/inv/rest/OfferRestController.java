package com.bis.operox.inv.rest;

import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bis.operox.constants.Constants;
import com.bis.operox.inv.dao.entity.Company;
import com.bis.operox.inv.dao.entity.Offer;
import com.bis.operox.inv.dao.entity.OfferBuyxGetY;
import com.bis.operox.inv.dao.entity.OfferCoupons;
import com.bis.operox.inv.dao.entity.OfferDiscountOnInvoice;
import com.bis.operox.inv.dao.entity.OfferLoyaltyPoints;
import com.bis.operox.inv.dao.entity.Shift;
import com.bis.operox.inv.dao.entity.User;
import com.bis.operox.inv.service.OfferBuyxGetYService;
import com.bis.operox.inv.service.OfferCouponsService;
import com.bis.operox.inv.service.OfferCustomerLevelService;
import com.bis.operox.inv.service.OfferDiscountOnInvoiceService;
import com.bis.operox.inv.service.OfferFreeItemOnInvoiceService;
import com.bis.operox.inv.service.OfferLoyaltyPointsService;
import com.bis.operox.inv.service.OfferService;
import com.bis.operox.inv.service.OfferStroesService;
import com.bis.operox.production.dao.entity.WorkOrder;
import com.bis.operox.util.OperoxSessionManager;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.json.JsonHierarchicalStreamDriver;
import com.thoughtworks.xstream.io.json.JsonWriter;

@RestController
public class OfferRestController {
	
	@Autowired
	OfferService offerService;
	
	@Autowired
	OfferBuyxGetYService offerBuyxGetYService;
	
	@Autowired
	OfferStroesService offerStroesService;

	@Autowired
	OfferCustomerLevelService offerCustomerLevelService;
	
	@Autowired
	OfferFreeItemOnInvoiceService offerFreeItemOnInvoiceService;
	
	@Autowired
	OfferDiscountOnInvoiceService offerDiscountOnInvoiceService;
	
	@Autowired
	OfferCouponsService offerCouponsService;
	
	@Autowired
	OfferLoyaltyPointsService offerLoyaltyPointsService;
	
	
	@RequestMapping(value = "/storeOffer",  method = RequestMethod.POST)
	public  String storeOffer(@RequestParam(value="json", required=false) String json, HttpServletRequest request) throws Exception {
	
		 User user = (User) OperoxSessionManager.getSessionAttribute(request, "user");
         json = "{"+json+"}";
		 
		 JSONObject jsonObj = new JSONObject(json);

		 
		 if((jsonObj.has("sunday") || jsonObj.has("monday") || jsonObj.has("tuesday") || jsonObj.has("wednesday") || jsonObj.has("thursday")
						|| jsonObj.has("friday") || jsonObj.has("saturday"))){
			 
			 if(jsonObj.has("selectedStores") && jsonObj.getString("selectedStores") != null && !"".equals(jsonObj.getString("selectedStores"))){
				 
				 Offer offer = offerService.storeOfferDetails(jsonObj, user);
				 
				 if(offer.getOfferType().equalsIgnoreCase("Buy-X Get-Y")){
					 offerBuyxGetYService.saveOfferBuyxGetYDetails(jsonObj, user, offer);
				 }
				 
				 if(offer.getOfferType().equalsIgnoreCase("Free Item on Invoice")){
					 offerFreeItemOnInvoiceService.saveOfferFreeItemOnInvoice(jsonObj, user, offer);
				 }
				 
				 if(offer.getOfferType().equalsIgnoreCase("Discount on Invoice")){
					 offerDiscountOnInvoiceService.saveOfferDiscountOnInvoiceDetails(jsonObj, user, offer);
				 }
				 
				 if(offer.getOfferType().equalsIgnoreCase("Coupons")){
					 offerCouponsService.saveOfferCouponsDetails(jsonObj, user, offer);
				 }
				 
				 if(offer.getOfferType().equalsIgnoreCase("Loyalty points")){
					 offerLoyaltyPointsService.saveOfferLoyaltyPointsDetails(jsonObj, user, offer);
				 }
				 
				 offerStroesService.saveOfferStroesDetails(jsonObj, user, offer);
				 
				 /*if((jsonObj.has("retail") && jsonObj.getString("retail") != null && !"".equals(jsonObj.getString("retail"))) || 
						 jsonObj.has("wholesale") && jsonObj.getString("wholesale") != null && !"".equals(jsonObj.getString("wholesale"))){
					 
					 offerCustomerLevelService.saveOfferCustomerLevelDetails(jsonObj, user, offer);
					 
				 }*/
			 	return "offerHome";
			 
				}else{
					 return "Invalid Stores";
				 }
			 }else{
				 return "Invaid Day";
			 }
		 
		 
	}
	
	
	
	
	@RequestMapping(value = "/getOffersList")
	@ResponseBody
	public String getOffersList(HttpServletRequest request) {
		
	 	String resultJson = null;
		List<Offer> offersList = new ArrayList<Offer>();
		ArrayList<Offer> offerList = new ArrayList<Offer>();
		
		User user = (User) OperoxSessionManager.getSessionAttribute(request, "user");
		
		try {
			offersList = offerService.getofferListByCompanyId(user.getStore().getCompany().getId());
			for(Offer offer : offersList){
				if(null != offer.getOfferStartDate()){
					offer.setOfferStartDateStr(Constants.DF_DMY.format(offer.getOfferStartDate()));
				}
				
				if(null != offer.getOfferEndDate()){
					offer.setOfferEndDateStr(Constants.DF_DMY.format(offer.getOfferEndDate()));
				}
				offerList.add(offer);
			}
					
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		 resultJson = toJSON(offerList);
		 return resultJson;

	}
	
	
	@RequestMapping(value = "/removeOffer",  method = RequestMethod.POST)
	public  String removeOffer(@RequestParam(value="offerId", required=false) Long offerId, HttpServletRequest request) throws Exception {
		 User user = (User) OperoxSessionManager.getSessionAttribute(request, "user");
		 Offer offer = offerService.getOfferById(offerId);
		 offer.setStatus(0);
		 offer.setUpdatedBy(user.getUserCode());
		 offer.setUpdatedDate(new Date());
		 offerService.addOffer(offer);
		
		return "offerHome";
	}
	
	
	
	 
	 @RequestMapping(value = "/validateBuyItemProduct",  method = RequestMethod.POST)
		public  String validateBuyItemProduct(@RequestParam(value="buyItem", required=false) String buyItem,HttpServletRequest request) throws Exception {
		
			 Company company = (Company) OperoxSessionManager.getSessionAttribute(request, "company");
			 
			 String[] product = buyItem.split(",");
			 
			 OfferBuyxGetY offerBuyxGetY = offerBuyxGetYService.getOfferBuyxGetYByBuyItemProductIdAndCompanyId(Long.parseLong(product[0]), company.getId());
			 
			 if(offerBuyxGetY == null){
				 return "valid";
			 }else{
				 return "invalid";
			 }
		}
	 
	 
	 
	 @RequestMapping(value = "/validateInvoiceAmount",  method = RequestMethod.POST)
		public  String validateInvoiceAmount(@RequestParam(value="invoiceAmount", required=false) Float invoiceAmount,HttpServletRequest request) throws Exception {
		
			 Company company = (Company) OperoxSessionManager.getSessionAttribute(request, "company");
			 OfferDiscountOnInvoice odi = offerDiscountOnInvoiceService.getDiscountOnInvoiceAmountByCompanyId(invoiceAmount, company.getId());
			 
			 if(odi == null){
				 return "valid";
			 }else{
				 return "invalid";
			 }
		}
	 
	 
	 @RequestMapping(value = "/validateCouponName",  method = RequestMethod.POST)
		public  String validateCouponName(@RequestParam(value="couponName", required=false) String couponName,HttpServletRequest request) throws Exception {
		
			 Company company = (Company) OperoxSessionManager.getSessionAttribute(request, "company");
			 OfferCoupons oc = offerCouponsService.getOfferCouponsBycouponName(couponName, company.getId());
			 
			 if(oc == null){
				 return "valid";
			 }else{
				 return "invalid";
			 }
		}
	
	
	private String toJSON(Object obj){
        XStream xstream = new XStream(new JsonHierarchicalStreamDriver() {
            public HierarchicalStreamWriter createWriter(Writer writer) {
                return new JsonWriter(writer, JsonWriter.DROP_ROOT_MODE);
            }
        });
        return xstream.toXML(obj);
    }
	

}
