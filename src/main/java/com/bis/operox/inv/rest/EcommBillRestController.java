package com.bis.operox.inv.rest;

import java.util.Date;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bis.operox.inv.dao.BillDao;
import com.bis.operox.inv.dao.BillItemsDao;
import com.bis.operox.inv.dao.ProductStockDao;
import com.bis.operox.inv.dao.entity.Bill;
import com.bis.operox.inv.dao.entity.BillItems;
import com.bis.operox.inv.dao.entity.PaymentDetails;
import com.bis.operox.inv.dao.entity.Product;
import com.bis.operox.inv.dao.entity.ProductStock;
import com.bis.operox.inv.dao.enums.Bill_Status;
import com.bis.operox.inv.dao.enums.Payment_Mode;
import com.bis.operox.inv.service.BillService;
import com.bis.operox.inv.service.PaymentDetailsService;

/**
 * Prasad Salina
 * @author bis113
 *
 */

@RestController
public class EcommBillRestController {

	private static final Logger logger = LoggerFactory.getLogger(EcommBillRestController.class);
	
	@Autowired
	private ApplicationContext applicationContext;
	
	@Autowired
	private BillService billService;
	
	@Autowired
	private BillDao billDao;
	
	@Autowired
	private ProductStockDao productStockDao;
	
	@Autowired
	private BillItemsDao billItemsDao;
	
	@Autowired
	private PaymentDetailsService paymentDetailsService;
	
	
	@PostConstruct
	public void init() {
		logger.debug(" *** AutoCompleteController init with: " + applicationContext);
	}

    
   
    @RequestMapping(value = "public/saveBillingForEcomm",  method = RequestMethod.GET)
	public String saveBillingForEcomm(@RequestParam(value="json", required=false) String json,
			                         @RequestParam(value="paymentJson", required=false) String paymentJson,
									 @RequestParam(value="cartIdIds", required=false) String[] cartIdIds,
									 @RequestParam(value="totalAmount", required=false) String totalAmount,
									 @RequestParam(value="subTotal", required=false) String subTotal,
									 @RequestParam(value="tax", required=false) String tax,
									 @RequestParam(value="discount", required=false) String discount,
									 @RequestParam(value="shippingCharges", required=false) String shippingCharges,
									 @RequestParam(value="userCode", required=false) String userCode,
									 HttpServletRequest request) throws Exception{
    	
		Bill_Status bill_Status = Bill_Status.PAID;;
		addBill(paymentJson,json,userCode, bill_Status, cartIdIds, totalAmount, tax, discount);
		return "success";

	}
    
    
    
    public Bill addBill(String paymentJson,String json,String userCode,Bill_Status billStatus,String[] cartIds,String totalAmount,String tax,String discount) throws JSONException {
		Bill bill = new Bill();
		
		String maxNumber =  billService.getMaxReceivedNumber();
		if(maxNumber != null  && !maxNumber.isEmpty()){
			String increment = maxNumber.substring(0);
			Long  inc = Long.parseLong(increment)+1;
			String with5digits = String.format("%05d", inc); 
	    	maxNumber = "BILL"+with5digits;
		}else{
			maxNumber = "BILL00001";
		}
		bill.setBillNumber(maxNumber);
		
		if(totalAmount != null && totalAmount != "" && totalAmount !="undefined"){
			bill.setTotalAmount(Float.parseFloat(totalAmount));
		}
		if(tax != null && tax != "" && tax !="undefined"){
		bill.setTax(Float.parseFloat(tax));
		}
		if(discount != null && discount != "" && discount !="undefined"){
		bill.setDiscount(Float.parseFloat(discount));
		}
		
		bill.setBillDate(new Date());
		bill.setUpdatedDate(new Date());
		bill.setCreatedDate(new Date());
		bill.setCreatedBy(userCode);
		bill.setUpdatedBy(userCode);
		bill.setBillStatus(billStatus);
		Bill updatedBill= billDao.addBill(bill);
		
		
		String json1 = "{" + json + "}";
		JSONObject jsonObject = new JSONObject(json1);
		
		Integer totalProducts = 0;
		Float totalPrice = 0f;
		if(jsonObject.has("maxCartValue")){
		String maxCartValue = jsonObject.getString("maxCartValue");
		int maxCount = Integer.parseInt(maxCartValue);
		for (int c = 0; c < maxCount; c++) {
				BillItems billItems = new BillItems();
				if(jsonObject.has("quantity"+c)){
					
					Product product = new Product();
					product.setId(Long.parseLong(jsonObject.getString("productId"+c)));
					
					ProductStock productStock = new ProductStock();
					productStock.setId(Long.parseLong(jsonObject.getString("productStockId"+c)));
					
					billItems.setProductId(product); 
					billItems.setProductStockId(productStock);
					billItems.setQuantity(jsonObject.getString("quantity"+c));
					totalProducts = totalProducts + Integer.parseInt(jsonObject.getString("quantity"+c));
					billItems.setPrice(jsonObject.getString("mrp"+c));
					
					if(jsonObject.getString("discount"+c) != null && jsonObject.getString("discount"+c) != "undefined"){
						totalPrice =  (float) ((Integer.parseInt(jsonObject.getString("mrp"+c)) -  0) * Integer.parseInt(jsonObject.getString("quantity"+c)));	
					}else{
						totalPrice =  (float) ((Integer.parseInt(jsonObject.getString("mrp"+c)) -  Integer.parseInt(jsonObject.getString("quantity"+c))) - Integer.parseInt(jsonObject.getString("quantity"+c)));
					}
					
					
					billItems.setProductTotalAmount(totalPrice);	
					billItems.setTax(jsonObject.getString("tax"+c));
					billItems.setDiscount(jsonObject.getString("discount"+c));
					billItems.setStatus(com.bis.operox.constants.Constants.Active);
				    billItems.setBillId(bill);
				    billItems.setCreatedDate(new Date());
				    billItems.setCreatedBy(userCode);
				    BillItems billIt =  billItemsDao.saveBillItems(billItems);
				}
		}
		}
		updatedBill.setNumberOfProducts(totalProducts);
		billDao.addBill(updatedBill);
		
		
		
		
		    paymentJson = "{"+paymentJson+"}";
			JSONObject paymentJsonObject = new JSONObject(paymentJson);
			
			PaymentDetails payment = new PaymentDetails();
			
			//credit Card
			if (paymentJsonObject.has("creditCardNumber") && !paymentJsonObject.getString("creditCardNumber").isEmpty()){
				payment.setCardNumber(paymentJsonObject.getString("creditCardNumber"));
				payment.setPAYMNET_MODE(Payment_Mode.CARD);
			}
			if (paymentJsonObject.has("creditCardHolderName") && !paymentJsonObject.getString("creditCardHolderName").isEmpty()){
				payment.setCardHolderName(paymentJsonObject.getString("creditCardHolderName"));
			}
			
			if (paymentJsonObject.has("creditCardExpireDate") && !paymentJsonObject.getString("creditCardExpireDate").isEmpty()){
				payment.setExpireDate(paymentJsonObject.getString("creditCardExpireDate"));
			}
			if (paymentJsonObject.has("creditCardCvv") && !paymentJsonObject.getString("creditCardCvv").isEmpty()){
				payment.setCvvNumber(Integer.parseInt(paymentJsonObject.getString("creditCardCvv")));
			}
			
			
			//debit Card
			if (paymentJsonObject.has("debitCardNumber") && !paymentJsonObject.getString("debitCardNumber").isEmpty()){
				payment.setCardNumber(paymentJsonObject.getString("debitCardNumber"));
				payment.setPAYMNET_MODE(Payment_Mode.CARD);
			}
			if (paymentJsonObject.has("debitCardHolderName") && !paymentJsonObject.getString("debitCardHolderName").isEmpty()){
				payment.setCardHolderName(paymentJsonObject.getString("debitCardHolderName"));
			}
			
			if (paymentJsonObject.has("debiCardExpireDate") && !paymentJsonObject.getString("debiCardExpireDate").isEmpty()){
				payment.setExpireDate(paymentJsonObject.getString("debiCardExpireDate"));
			}
			if (paymentJsonObject.has("debitCardCvv") && !paymentJsonObject.getString("debitCardCvv").isEmpty()){
				payment.setCvvNumber(Integer.parseInt(paymentJsonObject.getString("debitCardCvv")));
			}
			
			//gift Card
			
			if (paymentJsonObject.has("giftCardNumber") && !paymentJsonObject.getString("giftCardNumber").isEmpty()){
				payment.setGiftCardNumber(paymentJsonObject.getString("giftCardNumber"));
				payment.setPAYMNET_MODE(Payment_Mode.GIFTCARD);
			}
			if (paymentJsonObject.has("giftCardAmount") && !paymentJsonObject.getString("giftCardAmount").isEmpty()){
				payment.setGiftCardAmount(Integer.parseInt(paymentJsonObject.getString("giftCardAmount")));
			}
			
			if (paymentJsonObject.has("giftCardcash") && !paymentJsonObject.getString("giftCardcash").isEmpty()){
				payment.setPaymentCash(Integer.parseInt(paymentJsonObject.getString("giftCardcash")));
			}
			//cash
			if ("".equalsIgnoreCase(paymentJsonObject.getString("cashOnDelivery"))){
				payment.setPAYMNET_MODE(Payment_Mode.CASH);
			}
			
			payment.setCreatedDate(new Date());
			payment.setCreatedBy(userCode);
			paymentDetailsService.addPaymentDetails(payment);
			
	   return bill;
	}
    
    
}
