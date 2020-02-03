package com.bis.operox.inv.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bis.operox.inv.dao.BillDao;
import com.bis.operox.inv.dao.BillItemsDao;
import com.bis.operox.inv.dao.CustomerDao;
import com.bis.operox.inv.dao.PaymentDetailsDao;
import com.bis.operox.inv.dao.ProductStockDao;
import com.bis.operox.inv.dao.entity.Bill;
import com.bis.operox.inv.dao.entity.BillItems;
import com.bis.operox.inv.dao.entity.Counter;
import com.bis.operox.inv.dao.entity.Customer;
import com.bis.operox.inv.dao.entity.PaymentDetails;
import com.bis.operox.inv.dao.entity.ProductStock;
import com.bis.operox.inv.dao.entity.Store;
import com.bis.operox.inv.dao.entity.User;
import com.bis.operox.inv.dao.enums.Bill_Status;
import com.bis.operox.inv.dao.enums.Payment_Mode;
import com.bis.operox.inv.service.BillService;
import com.bis.operox.production.dao.entity.Currency;

@Service
public class BillServiceImpl implements BillService{
	
	@Autowired
	private BillDao billDao;
	
	@Autowired
	private CustomerDao customerDao;
	
	@Autowired
	private BillItemsDao billItemsDao;
	
	@Autowired
	private ProductStockDao productStockDao;
	
	@Autowired
	private PaymentDetailsDao paymentDetailsDao;
	
	
	@Override
	public Bill addBill(JSONObject jsonObject,User user,Bill_Status billStatus,JSONObject paymentjsonjsonObj) throws JSONException {
		String billNumber = jsonObject.getString("billNumber");
		Bill billref = billDao.getBillByBillNumber(billNumber);
	    if(billref == null)	{
	    	billref = new Bill();
	    }
	    if (jsonObject.has("currencyId") && !jsonObject.getString("currencyId").isEmpty()){
	    	Long currencyId = Long.parseLong(jsonObject.getString("currencyId"));
	    	Currency currency = new Currency();
	    	currency.setId(currencyId);
	    	billref.setCurrency(currency);
	    }
		if (jsonObject.has("billNumber") && !jsonObject.getString("billNumber").isEmpty()){
			billref.setBillNumber(jsonObject.getString("billNumber"));
		}
		if (  jsonObject.has("noOfProducts") && !jsonObject.getString("noOfProducts").isEmpty()) {
			billref.setNumberOfProducts(Integer.parseInt(jsonObject.getString("noOfProducts")));
		}
		if (jsonObject.has("totalAmount") && !jsonObject.getString("totalAmount").isEmpty()){
			Float totalAmount = Float.parseFloat(jsonObject.getString("totalAmount"));
			billref.setTotalAmount(totalAmount);
		}
		
		if (  jsonObject.has("billAmount") && !jsonObject.getString("billAmount").isEmpty()){
			Float billAmount = Float.parseFloat(jsonObject.getString("billAmount"));
			billref.setBillAmount(billAmount);
		}
		
		if (jsonObject.has("actualBillAmount") && !jsonObject.getString("actualBillAmount").isEmpty()){
			Float actualBillAmount = Float.parseFloat(jsonObject.getString("actualBillAmount"));
			billref.setActualBillAmount(actualBillAmount);
		}
		
		
		if (  jsonObject.has("discountOnInvoice") && !jsonObject.getString("discountOnInvoice").isEmpty()){
			billref.setDiscountOnInvoice(Float.parseFloat(jsonObject.getString("discountOnInvoice")));
		}
		
		
		if (  jsonObject.has("tax") && !jsonObject.getString("tax").isEmpty()) {
			billref.setTax(Float.parseFloat(jsonObject.getString("tax")));
		}
		if (  jsonObject.has("grossAmount") && !jsonObject.getString("grossAmount").isEmpty()){
			Float grossAmount = Float.parseFloat(jsonObject.getString("grossAmount"));
			billref.setGrossAmount(grossAmount);
		}	
		if (  jsonObject.has("discount") && !jsonObject.getString("discount").isEmpty()) {
			billref.setDiscount(Float.parseFloat(jsonObject.getString("discount")));
		}
		if (  jsonObject.has("customerPhone") && !jsonObject.getString("customerPhone").isEmpty()) {
			Customer cust = customerDao.getCustomerByPhoneNumber(jsonObject.getString("customerPhone"));
			if(cust!=null){
				billref.setCustomerId(cust);
			}else{
				Customer c = new Customer();
				c.setCustomerName(jsonObject.getString("customername"));
				c.setPhone(jsonObject.getString("customerPhone"));
				customerDao.addCustomer(c);
				billref.setCustomerId(c);
			}
		}
		
		//laxman
		if (jsonObject.has("billStoreId") && !jsonObject.getString("billStoreId").isEmpty()){
			Store store = new Store();
			store.setId(Long.parseLong(jsonObject.getString("billStoreId")));
			billref.setStoreId(store);
		}
		if (jsonObject.has("counterId") && !jsonObject.getString("counterId").isEmpty()){
		    Counter counter = new Counter();
			 counter.setId(Long.parseLong(jsonObject.getString("counterId")));
			billref.setCounterId(counter);
		}
		if (jsonObject.has("cashierUserId") && !jsonObject.getString("cashierUserId").isEmpty()){
			User cashierUser = new User();
			cashierUser.setId(Long.parseLong(jsonObject.getString("cashierUserId")));
			billref.setCashierUserId(cashierUser);
		}
		
		billref.setBillDate(new Date());
		billref.setUpdatedDate(user.getUpdatedDate());
		billref.setCreatedDate(new Date());
		billref.setCreatedBy(user.getUserCode());
		billref.setUpdatedBy(user.getUserCode());
		billref.setBillStatus(billStatus);
		Bill bill= billDao.addBill(billref);
		
	 	String maxAdminTimeTypeRowNum = jsonObject.getString("maxProdRowNum");
			int maxCount = Integer.parseInt(maxAdminTimeTypeRowNum);
			@SuppressWarnings("unchecked")
			ArrayList<Long> oldIdList = (ArrayList<Long>) billItemsDao.getBillItemsIdsByBillId(bill.getId());
			ArrayList<Long> currentIdList = new ArrayList<>();
			//
			for (int c = 1; c <= maxCount; c++) {
				BillItems billItems = new BillItems();
					if(jsonObject.has(c+"productStockId")){
						
						ProductStock productStock = productStockDao.getProductStockById(Long.parseLong(jsonObject.getString(c+"productStockId")));
						billItems.setProductStockId(productStock);
						if (jsonObject.has(c+"itemId") && StringUtils.isNotEmpty(jsonObject.getString(c+"itemId"))){
							billItems.setId(Long.parseLong(jsonObject.getString(c+"itemId")));
						}
						billItems.setQuantity(jsonObject.getString(c+"quantity"));
						billItems.setPrice(jsonObject.getString(c+"unitPrice"));
						
						billItems.setTax(jsonObject.getString(c+"taxOnItem"));
						billItems.setDiscount(jsonObject.getString(c+"discountOnItem"));
						
						
						billItems.setProductTotalAmount(Float.parseFloat(jsonObject.getString(c+"total")));
						billItems.setStatus(com.bis.operox.constants.Constants.Active);
					    billItems.setBillId(bill);
					    billItems.setCreatedDate(new Date());
					    billItems.setCreatedBy(user.getUserCode());
					    BillItems billIt =  billItemsDao.saveBillItems(billItems);
					    currentIdList.add(billIt.getId());
					    Long billedQuantity = 0l;
					    if(StringUtils.isNotEmpty(billIt.getQuantity())){
					    	billedQuantity = Long.parseLong(billIt.getQuantity());
					    }
					    productStock.setCurrentQuantity(productStock.getCurrentQuantity()-billedQuantity);
					    productStockDao.addProductStock(productStock);
					}
					
				    oldIdList.removeAll(currentIdList);
				    if(oldIdList != null && !oldIdList.isEmpty()){
				    	for(Long id :oldIdList){
				    		BillItems	billIt = billItemsDao.deleteBillItems(id);
				    		ProductStock productStock = productStockDao.getProductStockById(billIt.getProductStockId().getId());
				    		Long billedQuantity = 0l;
						    if(StringUtils.isNotEmpty(billIt.getQuantity())){
						    	billedQuantity = Long.parseLong(billIt.getQuantity());
						    }
				    		productStock.setCurrentQuantity(productStock.getCurrentQuantity()-billedQuantity);
						    productStockDao.addProductStock(productStock);
				    	}
				    	
				    }
					
			}
			
			
			
//Store ProductDetails
			
			PaymentDetails paymentDetails= new PaymentDetails();
			
			//bill Id 
			paymentDetails.setBillId(bill);
			if (jsonObject.has("noOfProducts") && !jsonObject.getString("noOfProducts").isEmpty()) {
				paymentDetails.setNoOfProduct(Integer.parseInt(jsonObject.getString("noOfProducts")));
			}
			if (jsonObject.has("totalAmount") && !jsonObject.getString("totalAmount").isEmpty()) {
				Float totalAmount = Float.parseFloat(jsonObject.getString("totalAmount"));
				paymentDetails.setTotalAmount(totalAmount);
			}
			
			if(paymentjsonjsonObj != null){
				//Cash
				if (paymentjsonjsonObj.has("paymentCash") && !paymentjsonjsonObj.getString("paymentCash").isEmpty()) {
					paymentDetails.setPaymentCash(Integer.parseInt(paymentjsonjsonObj.getString("paymentCash")));
					paymentDetails.setPAYMNET_MODE(Payment_Mode.CASH);
				}
				if (paymentjsonjsonObj.has("paymentChange") && !paymentjsonjsonObj.getString("paymentChange").isEmpty()) {
					paymentDetails.setPaymentChange(Float.parseFloat(paymentjsonjsonObj.getString("paymentChange")));
				}
				
				//Card
				if (paymentjsonjsonObj.has("cardTypeName") && !paymentjsonjsonObj.getString("cardTypeName").isEmpty()) {
					paymentDetails.setCardType(paymentjsonjsonObj.getString("cardTypeName"));
				}
				if (paymentjsonjsonObj.has("cardNumber") && !paymentjsonjsonObj.getString("cardNumber").isEmpty()) {
					paymentDetails.setCardNumber(paymentjsonjsonObj.getString("cardNumber"));
					paymentDetails.setPAYMNET_MODE(Payment_Mode.CARD);
				}
				if (paymentjsonjsonObj.has("cardCxpdate") && !paymentjsonjsonObj.getString("cardCxpdate").isEmpty()) {
					paymentDetails.setExpireDate(paymentjsonjsonObj.getString("cardNumber"));
				}
				if (paymentjsonjsonObj.has("cardCvv") && !paymentjsonjsonObj.getString("cardCvv").isEmpty()) {
					paymentDetails.setCvvNumber(Integer.parseInt(paymentjsonjsonObj.getString("cardCvv")));
				}
				
				//Gift Card
				if (paymentjsonjsonObj.has("giftCardNumber") && !paymentjsonjsonObj.getString("giftCardNumber").isEmpty()) {
					paymentDetails.setGiftCardNumber(paymentjsonjsonObj.getString("giftCardNumber"));
				}
				if (paymentjsonjsonObj.has("giftCardAmount") && !paymentjsonjsonObj.getString("giftCardAmount").isEmpty()) {
					paymentDetails.setGiftCardAmount(Integer.parseInt(paymentjsonjsonObj.getString("giftCardAmount")));
				}
				if (paymentjsonjsonObj.has("giftcash") && !paymentjsonjsonObj.getString("giftcash").isEmpty()) {
					paymentDetails.setPaymentCash(Integer.parseInt(paymentjsonjsonObj.getString("giftcash")));
					paymentDetails.setPAYMNET_MODE(Payment_Mode.GIFTCARD);
				}
				if (paymentjsonjsonObj.has("giftChange") && !paymentjsonjsonObj.getString("giftChange").isEmpty()) {
					paymentDetails.setPaymentChange(Float.parseFloat(paymentjsonjsonObj.getString("giftChange")));
				}
				
				//coupon 
				if (paymentjsonjsonObj.has("coupon1Amount") && !paymentjsonjsonObj.getString("coupon1Amount").isEmpty()) {
					paymentDetails.setCoupon1Amount(paymentjsonjsonObj.getString("coupon1Amount"));
				}
				if (paymentjsonjsonObj.has("coupon2Amount") && !paymentjsonjsonObj.getString("coupon2Amount").isEmpty()) {
					paymentDetails.setCoupon2Amount(Float.parseFloat(paymentjsonjsonObj.getString("coupon2Amount")));
				}
				if (paymentjsonjsonObj.has("couponCash") && !paymentjsonjsonObj.getString("couponCash").isEmpty()) {
					paymentDetails.setPaymentCash(Integer.parseInt(paymentjsonjsonObj.getString("couponCash")));
					paymentDetails.setPAYMNET_MODE(Payment_Mode.COUPON);
				}
				if (paymentjsonjsonObj.has("couponChange") && !paymentjsonjsonObj.getString("couponChange").isEmpty()) {
					paymentDetails.setPaymentChange(Float.parseFloat(paymentjsonjsonObj.getString("couponChange")));
				}
				paymentDetails.setCreatedDate(new Date());
				paymentDetails.setCreatedBy(user.getUserCode());
				paymentDetailsDao.addPaymentDetails(paymentDetails);
				
				
			}
			
			return billref;
	}

	@Override
	public Long getBillNumberByStoreName(String storeName) {
		return this.billDao.getBillNumberByStoreName(storeName);
	}
	
	@Override
	public List<Bill> listBill() {
		return this.billDao.listBill();
	}
	
	@Override
	public Bill getBillById(Long id) {
		return this.billDao.getBillById(id);
	}

	@Override
	public List<Bill> getOnHoldInvoicesByCounterIdAndCashierUserId(Long counterId,Long cashierUserId) {
		return this.billDao.getOnHoldInvoicesByCounterIdAndCashierUserId(counterId,cashierUserId);
	}

	@Override
	public Bill ecommOrderListByUserId(Long billId,String userCode) {
		return this.billDao.ecommOrderListByUserId(billId, userCode);
	}

	@Override
	public List<Bill> ecommOrderListByUserCode(String userCode) {
		return this.billDao.ecommOrderListByUserCode(userCode);
	}

	@Override
	public String getMaxReceivedNumber() {
		return billDao.getMaxReceivedNumber();
	}

	

}
