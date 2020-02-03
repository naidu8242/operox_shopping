package com.bis.operox.inv.service.impl;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.bis.operox.constants.Constants;
import com.bis.operox.inv.dao.PriceDao;
import com.bis.operox.inv.dao.ProductStockDao;
import com.bis.operox.inv.dao.ReceivedStockDao;
import com.bis.operox.inv.dao.ReceivedStockItemsDao;
import com.bis.operox.inv.dao.SupplierInvoiceDao;
import com.bis.operox.inv.dao.entity.EmailAttachment;
import com.bis.operox.inv.dao.entity.EmailMessage;
import com.bis.operox.inv.dao.entity.EmailRecipient;
import com.bis.operox.inv.dao.entity.EmailScenario;
import com.bis.operox.inv.dao.entity.Price;
import com.bis.operox.inv.dao.entity.Product;
import com.bis.operox.inv.dao.entity.ProductStock;
import com.bis.operox.inv.dao.entity.PurchaseOrder;
import com.bis.operox.inv.dao.entity.ReceivedStock;
import com.bis.operox.inv.dao.entity.ReceivedStockItems;
import com.bis.operox.inv.dao.entity.StockReturns;
import com.bis.operox.inv.dao.entity.Store;
import com.bis.operox.inv.dao.entity.Supplier;
import com.bis.operox.inv.dao.entity.SupplierInvoice;
import com.bis.operox.inv.dao.entity.Tax;
import com.bis.operox.inv.dao.entity.User;
import com.bis.operox.inv.dao.enums.Submission_Status;
import com.bis.operox.inv.service.EmailScenarioService;
import com.bis.operox.inv.service.ReceivedStockService;
import com.bis.operox.inv.service.UserService;
import com.bis.operox.util.CommonUtil;
import com.bis.operox.util.EmailScenerioUtil;
/**
 * provide the implementaion for service methods
 * @author Sammeta David Raju
 *
 */
@Service
public class ReceivedStockServiceImpl implements ReceivedStockService{
	
	@Autowired
	ReceivedStockDao receivedStockDao;
	
	@Autowired
	ReceivedStockItemsDao receivedStockItemsDao;
	
	@Autowired
	SupplierInvoiceDao supplierInvoiceDao;
	
	@Autowired
	UserService userService;
	
	@Autowired
	ProductStockDao productStockDao;
	
	@Autowired
	PriceDao priceDao;
	
	@Autowired
	private CommonUtil commonUtil;
	
	@Autowired
	EmailScenarioService emailScenarioService;
	
	@Autowired
	EmailScenerioUtil emailScenerioUtil;
	
	@Value("${operoxUrl}")
    private String operoxUrl;

	@Override
	public ReceivedStock addReceivedStock(JSONObject jsonObj,String  submissionType, User user)  throws Exception  {
		    
		    ReceivedStock receivedStock  = new ReceivedStock();
		    
		    String pattern = "MM/dd/yyyy";
	        SimpleDateFormat format = new SimpleDateFormat(pattern);
		 	
	        if((jsonObj.has("receivedNumber")) && !jsonObj.getString("receivedNumber").isEmpty()){
	        	receivedStock.setReceivedNumber(jsonObj.getString("receivedNumber"));
	        }
	    	if((jsonObj.has("podId")) && !jsonObj.getString("podId").isEmpty()){
	    		PurchaseOrder purchaseOrder = new PurchaseOrder();
	    		purchaseOrder.setId(Long.parseLong(jsonObj.getString("podId")));
	    		receivedStock.setPodNumber(purchaseOrder);
	    	}
	    	if((jsonObj.has("deliveryDate"))){
	    		Date startDate = format.parse(jsonObj.getString("deliveryDate"));
	    		receivedStock.setDeliveryDate(startDate);
	    	}
	    	if((jsonObj.has("storeId")) && !jsonObj.getString("storeId").isEmpty()){
	    		Store s2= new Store();
	    		s2.setId(Long.parseLong(jsonObj.getString("storeId")));
	    		receivedStock.setStoreId(s2);
	    	}
	    	if((jsonObj.has("supplierId")) && !jsonObj.getString("supplierId").isEmpty()){
	    		Supplier supplier= new Supplier();
	    		supplier.setId(Long.parseLong(jsonObj.getString("supplierId")));
	    		receivedStock.setSupplier(supplier);
	    	}
	    	if((jsonObj.has("warehouse")) && !jsonObj.getString("warehouse").isEmpty()){
	    		Store s= new Store();
	    		s.setId(Long.parseLong(jsonObj.getString("warehouse")));
	    		receivedStock.setWarehouse(s);
	    	}
	    	if((jsonObj.has("supplierInvoiceNumber")) && !jsonObj.getString("supplierInvoiceNumber").isEmpty()){
	    		receivedStock.setSupplierInvoiceNumber((jsonObj.getString("supplierInvoiceNumber"))); 
	    	}
	    	if((jsonObj.has("paymentStatus")) && !jsonObj.getString("paymentStatus").isEmpty()){
	    		receivedStock.setPaymentStatus(jsonObj.getString("paymentStatus")); 
	    	}
	    	if((jsonObj.has("numberOfPackages")) && !jsonObj.getString("numberOfPackages").isEmpty()){
	    		receivedStock.setNumberOfPackages(jsonObj.getString("numberOfPackages")); 
	    	}
	    	if((jsonObj.has("amount")) && !jsonObj.getString("amount").isEmpty()){
	    		receivedStock.setAmount(jsonObj.getString("amount")); 
	    	}
	    	if((jsonObj.has("receivingUsers")) && !jsonObj.getString("receivingUsers").isEmpty()){
	    		User receivingUsers = new User();
	    		receivingUsers.setId(Long.parseLong(jsonObj.getString("receivingUsers")));
	    		receivedStock.setReceivingUsers(receivingUsers); 
	    	}
	    	if((jsonObj.has("tax")) && !jsonObj.getString("tax").isEmpty()){
	    		
	    		String taxIdAndValue = jsonObj.getString("tax");
	    		String[] parts = taxIdAndValue.split("&");
	    			
	    		Tax tax = new Tax();
	    		tax.setId(Long.parseLong(parts[0]));
	    		receivedStock.setTax(tax); 
	    	}
	    	if((jsonObj.has("comment")) && !jsonObj.getString("comment").isEmpty()){
	    		receivedStock.setComment(jsonObj.getString("comment")); 
	    	}
	    	if((jsonObj.has("discount")) && !jsonObj.getString("discount").isEmpty()){
	    		receivedStock.setDiscount(jsonObj.getString("discount")); 
	    	}
	    	if((jsonObj.has("discountType")) && !jsonObj.getString("discountType").isEmpty()){
	    		receivedStock.setDiscountType(jsonObj.getString("discountType")); 
	    	}
	    	if((jsonObj.has("totalAmount")) && !jsonObj.getString("totalAmount").isEmpty()){
	    		receivedStock.setTotalAmount(jsonObj.getString("totalAmount")); 
	    	}
	    	if(submissionType != null && !submissionType.isEmpty()){
	    		if("draft".equalsIgnoreCase(submissionType)){
	    			receivedStock.setSubmissionStatus(Submission_Status.getNameByValue("draft"));
	    		}
	    		if("save".equalsIgnoreCase(submissionType) || "payment".equalsIgnoreCase(submissionType)){
	    			receivedStock.setSubmissionStatus(Submission_Status.getNameByValue("save"));
	    		}
	    	}
	    	receivedStock.setCreatedDate(new Date());
	    	receivedStock.setCreatedBy(user.getUserCode());
	    	receivedStock.setUpdatedDate(new Date());
	    	receivedStock.setUpdatedBy(user.getUserCode());
	    	receivedStock.setStatus(Constants.Active);
	    	
	    	receivedStockDao.addReceivedStock(receivedStock,user);
	    	
	    	SupplierInvoice supplierInvoice = new SupplierInvoice();
	    	if((jsonObj.has("supplierId")) && !jsonObj.getString("supplierId").isEmpty()){
	    		Supplier supplier= new Supplier();
	    		supplier.setId(Long.parseLong(jsonObj.getString("supplierId")));
	    		supplierInvoice.setSupplier(supplier);
	    	}
	    	
	    	if((jsonObj.has("storeId")) && !jsonObj.getString("storeId").isEmpty()){
	    		Store store= new Store();
	    		store.setId(Long.parseLong(jsonObj.getString("storeId")));
	    		supplierInvoice.setStore(store);
	    	}
	    	
	        supplierInvoice = getSupplierInvoiceDetails(supplierInvoice, jsonObj, user);
			supplierInvoice.setStockReceivedId(receivedStock);
			supplierInvoiceDao.addSupplierInvoice(supplierInvoice);
			pushRecievedStockItemsToProductStock(jsonObj,submissionType,user);
	    	
	    	String maxAdminTimeTypeRowNum = jsonObj.getString("maxTsRowNum");
			int maxCount = Integer.parseInt(maxAdminTimeTypeRowNum);
			
			for (long c = 1; c < maxCount; c++) {
				        ReceivedStockItems   receivedStockItems  = new ReceivedStockItems();
				       /*rowNumber  barCode  1   batchId  manuFatureDate  expiredate    quantity   productPrice   discount  tax mrp   totalAmount*/
			           if(jsonObj.has(c+"itemProductName")){
				        	    if(!jsonObj.getString(c+"itemProductName").isEmpty() && jsonObj.has(c+"itemProductName")){
				        		    Long productId =  Long.parseLong(jsonObj.getString(c+"itemProductName")); 
				        	        Product product = new Product();
				        	        product.setId(productId);
				        	        receivedStockItems.setProduct(product);
			        	    	}
			        	        if(!jsonObj.getString(c+"batchId").isEmpty() && jsonObj.has(c+"batchId")){
			        	        	 receivedStockItems.setBatchId(jsonObj.getString(c+"batchId"));
			        	    	}
			        	        if(!jsonObj.getString(c+"manuFatureDate").isEmpty() && jsonObj.has(c+"manuFatureDate")){
			        	    		Date manuFatureDate = format.parse(jsonObj.getString(c+"manuFatureDate"));
			        	    		receivedStockItems.setManuFatureDate(manuFatureDate);
			        	    		
			        	    	}
			        	        if(!jsonObj.getString(c+"expiredate").isEmpty() && jsonObj.has(c+"expiredate")){
			        	    		Date expiredate = format.parse(jsonObj.getString(c+"expiredate"));
			        	    		receivedStockItems.setExpiredate(expiredate);
			        	    	}
			        	        if(!jsonObj.getString(c+"quantity").isEmpty() && jsonObj.has(c+"quantity") && jsonObj.getString(c+"quantity") != "undefined"){
			        	           receivedStockItems.setQuantity(jsonObj.getString(c+"quantity"));
			        	        }	
			        	        if((jsonObj.has(c+"mrp")) && !jsonObj.getString(c+"mrp").isEmpty()  && jsonObj.getString(c+"mrp") != "undefined"){
			        	          receivedStockItems.setMrp(jsonObj.getString(c+"mrp"));
			        	        }
			        	        if((jsonObj.has(c+"productPrice")) && !jsonObj.getString(c+"productPrice").isEmpty()  && jsonObj.getString(c+"productPrice") != "undefined"){
			        	        	
			        	        	DecimalFormat df = new DecimalFormat();
			        	        	df.setMaximumFractionDigits(2);
			        	        	receivedStockItems.setProductPrice(df.format(Float.parseFloat(jsonObj.getString(c+"productPrice"))));
			        	        }
			        	        if((jsonObj.has(c+"barCode")) && !jsonObj.getString(c+"barCode").isEmpty()  && jsonObj.getString(c+"barCode") != "undefined"){
				        	        receivedStockItems.setBarCode(jsonObj.getString(c+"barCode"));
				        	    }
			        	        if((jsonObj.has(c+"tax")) && !jsonObj.getString(c+"tax").isEmpty() && jsonObj.getString(c+"tax") != "undefined"){
			        	        
			        	        	String taxIdAndValue = jsonObj.getString(c+"tax");
			        	    		String[] parts = taxIdAndValue.split("&");
			        	    			
			        	    		Tax tax = new Tax();
			        	    		tax.setId(Long.parseLong(parts[0]));
			        	    		receivedStockItems.setTaxId(tax); 
			        	    		
			        	        	/*Tax tax =new Tax();
			        	        	tax.setId(Long.parseLong(jsonObj.getString(c+"tax")));
			        	        	receivedStockItems.setTaxId(tax);*/
			        	        }
			        	        if((jsonObj.has(c+"discount")) && !jsonObj.getString(c+"discount").isEmpty() && jsonObj.getString(c+"discount") != "undefined"){
			        	        receivedStockItems.setDiscount(jsonObj.getString(c+"discount"));
			        	        }
			        	        
			        	        receivedStockItems.setCreatedDate(new Date());
			        	        receivedStockItems.setCreatedBy(user.getUserCode());
			        	        receivedStockItems.setUpdatedDate(new Date());
			        	        receivedStockItems.setUpdatedBy(user.getUserCode());
			        	        receivedStockItems.setStatus(Constants.Active);
			        	        receivedStockItems.setReceivedStock(receivedStock);
								receivedStockItemsDao.addReceivedStockItems(receivedStockItems);
								
						}
			}
			
			
			
			String actionLink = null; 
			Map<String, String> globalVarsMap = new HashMap<String, String>();
			
			ReceivedStock receivedStockById = receivedStockDao.getReceivedStockById(receivedStock.getId());
			
			actionLink=operoxUrl+"/viewPurchaseOrder/"+receivedStockById.getId();
			globalVarsMap.put("purchaseOrderId", receivedStockById.getId().toString());
			EmailScenario  notificationScenario = emailScenarioService.getEmailScenarioByUniqueName(Constants.RECEIVED_STOCK,Constants.ENTITY_TYPE_NOTIFICATION);
			String notificationBody = emailScenerioUtil.buildBody(notificationScenario.getBody(),globalVarsMap);
			notificationScenario.setBody(notificationBody);
			User assignUser = new User();
			
			if(receivedStockById.getSupplier()!= null){
				
				assignUser.setEmail(receivedStockById.getSupplier().getEmail());
			}
			
			int noOfProducts = maxCount-1;
			sendEmailForReceivedStock(receivedStockById, user, noOfProducts);
			
			
		       
			return receivedStock;
		}

	private SupplierInvoice getSupplierInvoiceDetails(SupplierInvoice supplierInvoice, JSONObject jsonObj, User user) throws JSONException {
		supplierInvoice.setSupplierInvoiceNumber(jsonObj.getString("supplierInvoiceNumber"));
		supplierInvoice.setStatus(Constants.Active);
		supplierInvoice.setCreatedBy(user.getUserCode());
		supplierInvoice.setCreatedDate(new Date());
		supplierInvoice.setUpdatedBy(user.getUserCode());
		supplierInvoice.setUpdatedDate(new Date());
		return supplierInvoice;
	}
	
	@Override
	public ReceivedStock getReceivedStockById(Long id) {
		
		ReceivedStock receivedStock = receivedStockDao.getReceivedStockById(id);
		
				if(receivedStock.getSupplier() != null){
					receivedStock.setSupplierName(receivedStock.getSupplier().getName());
				}
				if(receivedStock.getDeliveryDate() != null){
					receivedStock.setDeliveryDateStr(Constants.DF_DMY.format(receivedStock.getDeliveryDate()));
				}
				if(receivedStock.getStoreId() != null){
					receivedStock.setStoreName(receivedStock.getStoreId().getStoreName());
				}
				if(receivedStock.getWarehouse() != null){
					receivedStock.setWareHouseName(receivedStock.getWarehouse().getStoreName());
				}
		return receivedStock;
	}

	@Override
	public List<ReceivedStock> getAllReceivedStocks() {
		
	       List<ReceivedStock> receivedStockList  = receivedStockDao.getAllReceivedStocks();
				for(ReceivedStock receivedStock :receivedStockList){
					if(receivedStock.getSupplier() != null){
						receivedStock.setSupplierName(receivedStock.getSupplier().getName());
					}
					if(receivedStock.getDeliveryDate() != null){
						receivedStock.setDeliveryDateStr(Constants.DF_DMY.format(receivedStock.getDeliveryDate()));
					}
					if(receivedStock.getStoreId() != null){
						receivedStock.setStoreName(receivedStock.getStoreId().getStoreName());
					}
					if(receivedStock.getWarehouse() != null){
						receivedStock.setWareHouseName(receivedStock.getWarehouse().getStoreName());
					}
				}
		return receivedStockList;
	 }
	
	@Override
	public String getMaxReceivedNumber() {
		return receivedStockDao.getMaxReceivedNumber();
	}


void pushRecievedStockItemsToProductStock(JSONObject jsonObj,String submissionType,User user) throws Exception{
		
	
		    String pattern = "MM/dd/yyyy";
	        SimpleDateFormat format = new SimpleDateFormat(pattern);
			String maxAdminTimeTypeRowNum = jsonObj.getString("maxTsRowNum");
			int maxCount = Integer.parseInt(maxAdminTimeTypeRowNum);
		
			for (long c = 1; c < maxCount; c++) {
				        ProductStock   productStock  = new ProductStock();
				        Price pr = new Price();
				        
				       /*rowNumber  barCode  1   batchId  manuFatureDate  expiredate    quantity   productPrice   discount  tax mrp   totalAmount*/
			           if(jsonObj.has(c+"itemProductName")){
				        	    if(!jsonObj.getString(c+"itemProductName").isEmpty() && jsonObj.has(c+"itemProductName")){
				        		    Long productId =  Long.parseLong(jsonObj.getString(c+"itemProductName")); 
				        	        Product product = new Product();
				        	        product.setId(productId);
				        	        productStock.setProductId(product);
			        	    	}
			        	        if(!jsonObj.getString(c+"batchId").isEmpty() && jsonObj.has(c+"batchId")){
			        	        	 productStock.setBatchNumber(jsonObj.getString(c+"batchId"));
			        	    	}
			        	        if(!jsonObj.getString(c+"mrp").isEmpty() && jsonObj.has(c+"mrp")){
			        	        	 productStock.setMrpStr(jsonObj.getString(c+"mrp"));
			        	    	}
			        	        if(!jsonObj.getString(c+"manuFatureDate").isEmpty() && jsonObj.has(c+"manuFatureDate")){
			        	    		Date manuFatureDate = format.parse(jsonObj.getString(c+"manuFatureDate"));
			        	    		productStock.setManufacturingDate(manuFatureDate);
			        	    		
			        	    	}
			        	        if(!jsonObj.getString(c+"expiredate").isEmpty() && jsonObj.has(c+"expiredate")){
			        	    		Date expiredate = format.parse(jsonObj.getString(c+"expiredate"));
			        	    		productStock.setExpireDate(expiredate);
			        	    	}
			        	        if(!jsonObj.getString(c+"quantity").isEmpty() && jsonObj.has(c+"quantity") && jsonObj.getString(c+"quantity") != "undefined"){
			        	            productStock.setCurrentQuantity(Long.parseLong(jsonObj.getString(c+"quantity")));
			        	        }	
			        	        if((jsonObj.has(c+"barCode")) && !jsonObj.getString(c+"barCode").isEmpty()  && jsonObj.getString(c+"barCode") != "undefined"){
				        	        productStock.setBarCode(jsonObj.getString(c+"barCode"));
				        	    }
			        	        if((jsonObj.has(c+"discount")) && !jsonObj.getString(c+"discount").isEmpty() && jsonObj.getString(c+"discount") != "undefined"){
			        	        	productStock.setDiscount(Float.parseFloat(jsonObj.getString(c+"discount")));;
				        	        }
			        	        if((jsonObj.has(c+"tax")) && !jsonObj.getString(c+"tax").isEmpty() && jsonObj.getString(c+"tax") != "undefined"){
			        	        
			        	        	String taxIdAndValue = jsonObj.getString(c+"tax");
			        	    		String[] parts = taxIdAndValue.split("&");
			        	    			
			        	    		Tax tax = new Tax();
			        	    		tax.setId(Long.parseLong(parts[0]));
			        	    		productStock.setTaxId(tax); 
			        	    		
			        	        }
			        	        
			        	        
			        	        
			        	        if((jsonObj.has("storeId")) && !jsonObj.getString("storeId").isEmpty()){
			        	    		Store s2= new Store();
			        	    		s2.setId(Long.parseLong(jsonObj.getString("storeId")));
			        	    		productStock.setStoreId(s2);
			        	    	}
			        	        productStock.setCreatedDate(new Date());
			        	        productStock.setCreatedBy(user.getUserCode());
			        	        productStock.setUpdatedDate(new Date());
			        	        productStock.setUpdatedBy(user.getUserCode());
			        	        productStock.setStatus(Constants.Active);
			        	        productStockDao.addProductStock(productStock);
			        	        
			        	        
			        	        
			        	        // Storing  of  Mrp and Product Stock Id in Price Table
			        	        
			        	        if(!jsonObj.getString(c+"mrp").isEmpty() && jsonObj.has(c+"mrp")){
			        	        	 pr.setMrp(jsonObj.getString(c+"mrp"));
			        	    	}
			        	        pr.setProductStockId(productStock);
			        	        pr.setCreatedDate(new Date());
			        	        pr.setCreatedBy(user.getUserCode());
			        	        pr.setUpdatedDate(new Date());
			        	        pr.setUpdatedBy(user.getUserCode());
			        	        pr.setStatus(Constants.Active);
			        	        priceDao.addPrice(pr);
			
			           }
			}

	}


private void sendEmailForReceivedStock(ReceivedStock receivedStockById, User user, Integer maxCount) throws Exception {
	
	 EmailMessage emailMessage = new EmailMessage();
	 emailMessage.setFirstName(receivedStockById.getSupplier().getSupplierContactName());
	 emailMessage.setToEmail(receivedStockById.getStoreId().getEmail());
	 emailMessage.setFromEmail(receivedStockById.getSupplier().getEmail());
	 String fromLastName = "";
	 if(StringUtils.isNotEmpty(user.getLastName())){
		 fromLastName = user.getLastName();
	 }
	 emailMessage.setFromName(user.getFirstName()+" "+fromLastName);
	 
	 //setting the class filed values
	 Map<String, String> globalVarsMap = new HashMap<String, String>();
	 globalVarsMap.put("fromStoreName", receivedStockById.getSupplier().getSupplierContactName());
	 globalVarsMap.put("receivedDate",Constants.DF_DMY.format(receivedStockById.getDeliveryDate()));
	 globalVarsMap.put("toStoreName", receivedStockById.getStoreId().getStoreName());
	 globalVarsMap.put("productsCount",maxCount.toString());
	 globalVarsMap.put("totalAmount",receivedStockById.getTotalAmount().toString());
	 globalVarsMap.put("loginUserName",emailMessage.getFromName());
	 globalVarsMap.put("link","<a href='"+operoxUrl+"/"+"' style='text-decoration:none; '><div>click here</div></a>");
	 
	 //setting the Recipient information
	 ArrayList<EmailRecipient> emailRecipientList = new ArrayList<EmailRecipient>();
	 EmailRecipient emailRecipient = new EmailRecipient();
	 emailRecipient.setEmail(emailMessage.getToEmail());
	 emailRecipient.setName(emailMessage.getFirstName());
	 emailRecipientList.add(emailRecipient);
	 
	 User storeManager = userService.getUserByStoreIdAndRole(receivedStockById.getStoreId().getId(), Constants.ROLE_STORE_MANAGER);
	 if(storeManager != null){
		 emailRecipient.setEmail(storeManager.getEmail());
		 String managerLastName = "";
		 if(StringUtils.isNotEmpty(storeManager.getLastName())){
			 managerLastName = storeManager.getLastName();
		 }
		 emailRecipient.setName(storeManager.getFirstName()+" "+managerLastName);
		 emailRecipientList.add(emailRecipient);
	 }
	 
	 //getting the current EmailScenario by EmailScenario unique name and entity type
	 EmailScenario  emailScenario = emailScenarioService.getEmailScenarioByUniqueName(Constants.RECEIVED_STOCK,Constants.ENTITY_TYPE_EMAIL);
	
	 //covert subject,body and signature
	 String subject = emailScenerioUtil.bulidSubject(emailScenario.getSubject(),globalVarsMap);
	 String body = emailScenerioUtil.buildTempBody(emailScenario.getBody(),globalVarsMap);
	 String signature = emailScenerioUtil.buildSignature(emailScenario.getSignature(),globalVarsMap);
	 String url = operoxUrl;
	 
	 //construct HTML template using required data
	 String emailTemplate = emailScenerioUtil.AssembleEmailMessage(emailScenario,emailMessage, subject, body, signature, url);
	 emailMessage.setSubject(subject);
	 emailMessage.setMessageBody(emailTemplate);
	 
	 //calling the sendEmail method of emailScenerioUtil
	 emailScenerioUtil.sendEmail(emailMessage,emailRecipientList,new ArrayList<EmailAttachment>());
	
}

	
	
}	
	
