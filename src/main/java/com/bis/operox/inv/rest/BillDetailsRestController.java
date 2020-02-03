package com.bis.operox.inv.rest;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.TreeSet;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bis.operox.constants.Constants;
import com.bis.operox.inv.dao.entity.Bill;
import com.bis.operox.inv.dao.entity.BillItems;
import com.bis.operox.inv.dao.entity.Company;
import com.bis.operox.inv.dao.entity.Offer;
import com.bis.operox.inv.dao.entity.OfferCoupons;
import com.bis.operox.inv.dao.entity.OfferDiscountOnInvoice;
import com.bis.operox.inv.dao.entity.Price;
import com.bis.operox.inv.dao.entity.ProductStock;
import com.bis.operox.inv.dao.entity.Store;
import com.bis.operox.inv.dao.entity.User;
import com.bis.operox.inv.dao.enums.Bill_Status;
import com.bis.operox.inv.service.BillItemsService;
import com.bis.operox.inv.service.BillService;
import com.bis.operox.inv.service.OfferCouponsService;
import com.bis.operox.inv.service.OfferDiscountOnInvoiceService;
import com.bis.operox.inv.service.OfferService;
import com.bis.operox.inv.service.PriceService;
import com.bis.operox.inv.service.ProductStockService;
import com.bis.operox.util.AutoCompleteUtil;
import com.bis.operox.util.CommonUtil;
import com.bis.operox.util.OperoxSessionManager;

/**
 * Prasad Salina
 * @author bis113
 *
 */

@RestController
public class BillDetailsRestController {

	private static final Logger logger = LoggerFactory.getLogger(BillDetailsRestController.class);
	
	@Autowired
	private ApplicationContext applicationContext;
	
	@Autowired
	private PriceService priceService;
	
	@Autowired
	private BillService billService;
	
	@Autowired
	private BillItemsService billItemsService;
	
	@Autowired
	private ProductStockService productStockService;
	
	@Autowired
	private OfferDiscountOnInvoiceService offerDiscountOnInvoiceService;
	
	@Autowired
	private OfferCouponsService offerCouponsService;
	
	@Autowired 
	private CommonUtil commonUtil;
	
	@Autowired
	OfferService offerService;
	
	
	@PostConstruct
	public void init() {
		logger.debug(" *** AutoCompleteController init with: " + applicationContext);
	}

    
    @RequestMapping(value = "/prepareBillSetup")
    public String prepareBillSetup(HttpServletRequest request) throws Exception {
    	User user = (User) OperoxSessionManager.getSessionAttribute(request, "user");
    	String storeCode = null;
    	if(user.getStore() != null && StringUtils.isNotEmpty(user.getStore().getStoreName())){
    	    storeCode =  user.getStore().getStoreName().substring(0, 3).toUpperCase();
    	}
    	else if(user.getStore() != null && user.getStore().getCompany() != null &&  StringUtils.isNotEmpty(user.getStore().getCompany().getCompamyName()) ){
    		 storeCode =  user.getStore().getCompany().getCompamyName().substring(0, 2).toUpperCase();
    	}
    	else{
    		storeCode = "BIL";
    	}
    	String storeAddress = null;
    	if(user.getStore() != null && user.getStore().getAddress() != null && StringUtils.isNotEmpty(user.getStore().getAddress().getCity())){
    		storeAddress = user.getStore().getAddress().getCity();
    	}
    	user.setStoreAddress(storeAddress);
    	NumberFormat nf = new DecimalFormat("00000");
    	Long currentMaxBillNumber = billService.getBillNumberByStoreName(storeCode);
    	String billNumber = storeCode+nf.format(currentMaxBillNumber+1);
    	user.setBillNumber(billNumber);
    	user.setBillingDate(Constants.DF_DMY.format(new Date()));
    	HashSet<User> userList = new HashSet<User>();
    	userList.add(user);
		String resultJson = commonUtil.toJSON(userList);
		return resultJson;
	}
    
    @RequestMapping(value = "/getProductsByProductName")
    public String getProductsByProductName(HttpServletRequest request,@RequestParam(value="productName", required=false) String productName) throws Exception {
    	String resultJson = null;
    	User user = (User) OperoxSessionManager.getSessionAttribute(request, "user");
		LinkedHashMap<String, ProductStock> hm = new LinkedHashMap<String, ProductStock>();
		List<ProductStock> productStockList = productStockService.getProductStockByProductNameAndStoreId(productName, user.getStore().getId());
		if (productStockList!= null && !productStockList.isEmpty()) {
			for(ProductStock productStock : productStockList) {
				Price price =  priceService.getPriceByProductStockId(productStock.getId());
			    if(price != null)
			    productStock.setMrpStr(price.getMrp());
				hm.put(productStock.getProductId().getProductName() ,productStock);
			}
		}
		AutoCompleteUtil acUtil = new AutoCompleteUtil();
		acUtil.setProductStockList(hm);
		resultJson = commonUtil.toJSON(acUtil);
		return resultJson;
	}
    
    
    
    
    @RequestMapping(value = "/evaluateDiscountOnInvoice")
    public String evaluateDiscountOnInvoice(HttpServletRequest request ,@RequestParam(value="invoiceAmount", required=false) Float invoiceAmount) throws Exception {
    	String resultJson = null;
    	User user = (User) OperoxSessionManager.getSessionAttribute(request, "user");
    	Store store = (Store) OperoxSessionManager.getSessionAttribute(request, "store");
    	Float discountOnInvoice = 0f;
    	Offer offer = null;
    	List<Float> invoiceAmountsList = offerDiscountOnInvoiceService.getStoreInvoiceAmounts(store.getId());
    	TreeSet<Float> ns = new TreeSet<Float>(invoiceAmountsList);
    	
    	Float amount = ns.floor(invoiceAmount);
    	
    	if(null != amount){
    		offer = offerService.getOfferByOfferDiscountOnInvoiceAmountAndStoreId(store.getId(), ns.floor(invoiceAmount));
    	}
    	
        
    	if(null != offer){
    		OfferDiscountOnInvoice odi = offerDiscountOnInvoiceService.getOfferDiscountOnInvoice(offer);
    		if(null != odi){
    			if(odi.getIsPercentage().equalsIgnoreCase("Y")){
        			discountOnInvoice = (Float)(odi.getInvoiceAmount() / 100)*odi.getDiscountAmount();
        		}else{
        			discountOnInvoice = odi.getDiscountAmount();
        		}
    		}
    	}
    	
    	resultJson = commonUtil.toJSON(discountOnInvoice);
		return resultJson;
	}
    
    
    
    
    @RequestMapping(value = "/evaluateCoupon")
    public String evaluateCoupon(HttpServletRequest request ,@RequestParam(value="couponName", required=false) String couponName) throws Exception {
    	String resultJson = null;
    	User user = (User) OperoxSessionManager.getSessionAttribute(request, "user");
    	Store store = (Store) OperoxSessionManager.getSessionAttribute(request, "store");
    	Company company = (Company) OperoxSessionManager.getSessionAttribute(request, "company");
    	Offer offer = null;
    	Float couponValue = 0f;
    	
    	OfferCoupons offerCoupons = null;
    	offerCoupons = offerCouponsService.getOfferCouponsBycouponName(couponName, company.getId()); 
    	
    	if(null != offerCoupons){
        		offer = offerService.getOfferByOfferCouponAmountByCoupounNumberAndStoreId(store.getId(), couponName);
        	if(null != offer){
        		OfferCoupons oc = offerCouponsService.getOfferCouponsByOffer(offer);
        		if(null != oc){
        			couponValue = oc.getCouponValue();
        		}
        	}else{
        		return "Invalid Store";
        	}
        	
    	}else{
    		return "Invalid Coupon";
    	}
    	
    	resultJson = commonUtil.toJSON(couponValue);
		return resultJson;
	}
    
    
    
    @RequestMapping(value = "/getProductsByBarCode")
    public String getProductsByBarCode(HttpServletRequest request,@RequestParam(value="barCode", required=false) String barCode) throws Exception {
    	String resultJson = null;
    	User user = (User) OperoxSessionManager.getSessionAttribute(request, "user");
		LinkedHashMap<String, ProductStock> hm = new LinkedHashMap<String, ProductStock>();
		ProductStock ps = productStockService.getProductStockByProductBarCodeAndStoreId(barCode, user.getStore().getId());
		if (ps != null) {
			    Price price =  priceService.getPriceByProductStockId(ps.getId());
			    if(price != null)
			    ps.setMrpStr(price.getMrp());
				hm.put(ps.getProductId().getProductName() ,ps);
		}
		AutoCompleteUtil acUtil = new AutoCompleteUtil();
		acUtil.setProductStockList(hm);
		resultJson = commonUtil.toJSON(acUtil);
		return resultJson;
	}
    
    @RequestMapping(value = "/getProductsBySearchCode")
    public String getProductsBySearchCode(HttpServletRequest request,@RequestParam(value="prodCode", required=false) String prodCode) throws Exception {
    	String resultJson = null;
    	User user = (User) OperoxSessionManager.getSessionAttribute(request, "user");
		LinkedHashMap<String, ProductStock> hm = new LinkedHashMap<String, ProductStock>();
		ProductStock ps = productStockService.getProductStockByProductSearchCodeAndStoreId(prodCode, user.getStore().getId());
		if (ps != null) {
				Price price =  priceService.getPriceByProductStockId(ps.getId());
			    if(price != null)
			    ps.setMrpStr(price.getMrp());
				hm.put(ps.getProductId().getProductName() ,ps);
		}
		AutoCompleteUtil acUtil = new AutoCompleteUtil();
		acUtil.setProductStockList(hm);
		resultJson = commonUtil.toJSON(acUtil);
		return resultJson;
	}
    
    @RequestMapping(value = "/getonHoldBillItems")
    public String getonHoldBillItems(HttpServletRequest request,@RequestParam(value="billId", required=false) Long billId) throws Exception {
    	String resultJson = null;
		List<BillItems> billList = billItemsService.getBillItemsByBillId(billId);
		for (BillItems billItems : billList) {
		    billItems.setProductName(billItems.getProductStockId().getProductId().getProductName());
		    billItems.setMaxQuantity(billItems.getProductStockId().getCurrentQuantity());
		}
		resultJson = commonUtil.toJSON(billList);
		return resultJson;
	}
    
    @RequestMapping(value = "/pickInvoice")
    public String pickInvoice(HttpServletRequest request,@RequestParam(value="counterId", required=false) Long counterId) throws Exception {
    	String resultJson = null;
    	User user = (User) OperoxSessionManager.getSessionAttribute(request, "user");
		LinkedHashMap<String, Bill> hm = new LinkedHashMap<String, Bill>();
		List<Bill> billList = billService.getOnHoldInvoicesByCounterIdAndCashierUserId(counterId,user.getId());
		if(billList != null && !billList.isEmpty()){
			for(Bill bill:billList){
				bill.getDisplayBillDate();
				hm.put(bill.getBillNumber(), bill);
			}
		}
		AutoCompleteUtil acUtil = new AutoCompleteUtil();
		acUtil.setOnHoldBillList(hm);
		resultJson = commonUtil.toJSON(acUtil);
		return resultJson;
	}
    
    @RequestMapping(value = "/saveBillingDetails")
	public String saveBillingDetails(@RequestParam(value="json", required=false) String json,@RequestParam(value="status", required=false) String status,@RequestParam(value="paymentjson", required=false) String paymentjson,HttpServletRequest request) throws Exception{
		String json1 = "{" + json + "}";
		User user = (User) OperoxSessionManager.getSessionAttribute(request, "user");
		JSONObject jsonObj = new JSONObject(json1);
		JSONObject paymentjsonjsonObj = null;
		if(StringUtils.isNotEmpty(paymentjson)){
			String paymentjson1 = "{" + paymentjson + "}";
		    paymentjsonjsonObj = new JSONObject(paymentjson1);
		}
		
		
		Bill_Status bill_Status = null;
		if(StringUtils.isNotEmpty(status)){
			if(status.equalsIgnoreCase("onhold")){
				bill_Status = Bill_Status.ONHOLD;
			}
			else if(status.equalsIgnoreCase("new")){
				bill_Status = Bill_Status.NEW;
			}
			else if(status.equalsIgnoreCase("paid")){
				bill_Status = Bill_Status.PAID;
			}
			else if(status.equalsIgnoreCase("inprogress")){
				bill_Status = Bill_Status.INPROGRESS;
			}
		}
		else{
			bill_Status = Bill_Status.INPROGRESS;
		}
		billService.addBill(jsonObj,user,bill_Status,paymentjsonjsonObj);
		return "success";

	}
    
}
