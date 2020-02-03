package com.bis.operox.inv.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.bis.operox.constants.Constants;
import com.bis.operox.inv.dao.PurchaseOrderDao;
import com.bis.operox.inv.dao.PurchaseOrderItemsDao;
import com.bis.operox.inv.dao.entity.Customer;
import com.bis.operox.inv.dao.entity.EmailAttachment;
import com.bis.operox.inv.dao.entity.EmailMessage;
import com.bis.operox.inv.dao.entity.EmailRecipient;
import com.bis.operox.inv.dao.entity.EmailScenario;
import com.bis.operox.inv.dao.entity.Product;
import com.bis.operox.inv.dao.entity.PurchaseOrder;
import com.bis.operox.inv.dao.entity.PurchaseOrderItems;
import com.bis.operox.inv.dao.entity.Store;
import com.bis.operox.inv.dao.entity.Supplier;
import com.bis.operox.inv.dao.entity.Ticket;
import com.bis.operox.inv.dao.entity.User;
import com.bis.operox.inv.dao.enums.Stock_Status;
import com.bis.operox.inv.dao.enums.Submission_Status;
import com.bis.operox.inv.service.EmailScenarioService;
import com.bis.operox.inv.service.PurchaseOrderService;
import com.bis.operox.inv.service.UserService;
import com.bis.operox.util.EmailScenerioUtil;
/**
 * provide the implementaion for service methods
 * @author root
 *
 */
@Service
public class PurchaseOrderServiceImpl implements PurchaseOrderService{
	
	@Autowired
	PurchaseOrderDao purchaseOrderDao;
	
	@Autowired
	PurchaseOrderItemsDao purchaseOrderItemsDao;
	
	@Autowired
	UserService userService;
	
	@Autowired
	EmailScenarioService emailScenarioService;
	
	@Autowired
	EmailScenerioUtil emailScenerioUtil;
	
	@Value("${operoxUrl}")
    private String operoxUrl;


	@Override
	public PurchaseOrder addPurchaseOrder(JSONObject jsonObj,User user,String submissionType)  throws Exception  {
		

			PurchaseOrder purchaseOrder = new PurchaseOrder();
			
			String pattern = "MM/dd/yyyy";
			
	        SimpleDateFormat format = new SimpleDateFormat(pattern);
			
	        if(jsonObj.has("purchaseId") && !jsonObj.getString("purchaseId").isEmpty()){
        		purchaseOrder.setPurchaseNumber(jsonObj.getString("purchaseId"));
	        }
	        if(jsonObj.has("orderDate") && !jsonObj.getString("orderDate").isEmpty()){
	        		Date orderDate = format.parse(jsonObj.getString("orderDate"));
	        		purchaseOrder.setOrderDate(orderDate);
			}
	        if(jsonObj.has("deliveryDate") && !jsonObj.getString("deliveryDate").isEmpty()){
	    		Date deliveryDate = format.parse(jsonObj.getString("deliveryDate"));
	    		purchaseOrder.setDeliveryDate(deliveryDate);
		    }
	        if(jsonObj.has("deliveryDate") && !jsonObj.getString("deliveryDate").isEmpty()){
	    		Date deliveryDate = format.parse(jsonObj.getString("deliveryDate"));
	    		purchaseOrder.setDeliveryDate(deliveryDate);
		    }
	        if(jsonObj.has("Supplier") && !jsonObj.getString("Supplier").isEmpty() ){
	        	Supplier supplier = new Supplier();
	        	supplier.setId(Long.parseLong(jsonObj.getString("Supplier")));
	        	purchaseOrder.setSupplier(supplier);
		    }
	        if(jsonObj.has("storeId") && !jsonObj.getString("storeId").isEmpty() ){
	        	Store store = new Store();
	        	store.setId(Long.parseLong(jsonObj.getString("storeId")));
	        	purchaseOrder.setStoreId(store);
		    }
	        
	        purchaseOrder.setOrderStatus(Stock_Status.getNameByValue("pending"));
	        
	        purchaseOrder.setStatus(Constants.Active);           
	        purchaseOrder.setCreatedDate(new Date());
	        purchaseOrder.setUpdatedDate(new Date());
	        purchaseOrder.setCreatedBy(user.getUserCode());
	        purchaseOrder.setUpdatedBy(user.getUserCode());
	        
	        if(submissionType != null && !submissionType.isEmpty()){
	    		if("draft".equalsIgnoreCase(submissionType)){
	    			purchaseOrder.setSubmissionStatus(Submission_Status.getNameByValue("draft"));
	    		}
	    		if("save".equalsIgnoreCase(submissionType)){
	    			purchaseOrder.setSubmissionStatus(Submission_Status.getNameByValue("save"));
	    		}
	    	}
	        
	        purchaseOrder = purchaseOrderDao.addPurchaseOrder(purchaseOrder);
	        

	    	String maxAdminTimeTypeRowNum = jsonObj.getString("maxTsRowNum");
			int maxCount = Integer.parseInt(maxAdminTimeTypeRowNum);
			
				for (long c = 1; c < maxCount; c++) {
					if(jsonObj.has(c+"quantity") && !jsonObj.getString(c+"quantity").isEmpty()){
					PurchaseOrderItems   purchaseOrderItems  = new PurchaseOrderItems();
				           if(jsonObj.has(c+"quantity") && !jsonObj.getString(c+"quantity").isEmpty()){
									purchaseOrderItems.setQuantity(jsonObj.getString(c+"quantity"));
							}
				           if(jsonObj.has(c+"productId") && !jsonObj.getString(c+"productId").isEmpty()){
								Product pr  = new Product();
								pr.setId(Long.parseLong(jsonObj.getString(c+"productId")));
								purchaseOrderItems.setProduct(pr);
						    }
						purchaseOrderItems.setCreatedDate(new Date());
						purchaseOrderItems.setUpdatedDate(new Date());
						purchaseOrderItems.setCreatedBy(user.getUserCode());
						purchaseOrderItems.setUpdatedBy(user.getUserCode());
						purchaseOrderItems.setPurchaseOrder(purchaseOrder);
					    purchaseOrderItemsDao.addPurchaseOrderItems(purchaseOrderItems);
					}
				}
				
				
				
				
				
				
				String actionLink = null; 
				Map<String, String> globalVarsMap = new HashMap<String, String>();
				
				PurchaseOrder purchaseOrderById = purchaseOrderDao.getPurchaseOrderById(purchaseOrder.getId());
				
				actionLink=operoxUrl+"/viewPurchaseOrder/"+purchaseOrderById.getId();
				globalVarsMap.put("purchaseOrderId", purchaseOrderById.getId().toString());
				EmailScenario  notificationScenario = emailScenarioService.getEmailScenarioByUniqueName(Constants.PURCHASE_ORDER_SUPPLIER,Constants.ENTITY_TYPE_NOTIFICATION);
				String notificationBody = emailScenerioUtil.buildBody(notificationScenario.getBody(),globalVarsMap);
				notificationScenario.setBody(notificationBody);
				User assignUser = new User();
				assignUser.setEmail(purchaseOrderById.getSupplier().getEmail());
				//emailScenerioUtil.sendNotification(actionLink, user, assignUser, notificationScenario);
				
				int noOfProducts = maxCount-1;
				sendEmailToSupplierPurchaseOrder(purchaseOrderById,user, noOfProducts);
				
				
				
				
				
				
				
				
		 return purchaseOrder;
		}

	@Override
	public PurchaseOrder getPurchaseOrderById(Long id) {
		PurchaseOrder purchaseOrder = purchaseOrderDao.getPurchaseOrderById(id);
		if(purchaseOrder.getSupplier() != null ){
			purchaseOrder.setSupplierName(purchaseOrder.getSupplier().getName());
		}
		if(purchaseOrder.getDeliveryDate() != null ){
		purchaseOrder.setDeliveryDateStr(Constants.DF_DMY.format(purchaseOrder.getDeliveryDate()));
		}
		if(purchaseOrder.getOrderDate() != null ){
		purchaseOrder.setOrderDateStr(Constants.DF_DMY.format(purchaseOrder.getOrderDate()));
		}
		if(purchaseOrder.getUpdatedDate() != null  ){
		purchaseOrder.setUpdatedDateStr(Constants.DF_DMY.format(purchaseOrder.getUpdatedDate()));
		}
		return purchaseOrder;
	}

	@Override
	public List<PurchaseOrder> getAllPurchaseOrders() throws Exception {
           List<PurchaseOrder> purchaseOrderList  = purchaseOrderDao.getAllPurchaseOrders();
				for(PurchaseOrder purchaseOrder :purchaseOrderList){
					
					User updatedUser = userService.getUserByUserCode(purchaseOrder.getUpdatedBy());
					if(updatedUser != null){
						String updatedByName = "";
			    		   if(StringUtils.isNotEmpty(updatedUser.getFirstName())){
			    			   updatedByName = updatedUser.getFirstName();
			    		   }
			    		   if(StringUtils.isNotEmpty(updatedUser.getLastName()) && StringUtils.isNotBlank(updatedUser.getLastName())){
			    			   updatedByName = updatedByName+" "+updatedUser.getLastName();
			    		   }
						purchaseOrder.setUpdatedBy(updatedByName);
					}
					
					if(purchaseOrder.getSupplier() != null){
						purchaseOrder.setSupplierName(purchaseOrder.getSupplier().getName());
					}
					if(purchaseOrder.getDeliveryDate() != null){
					purchaseOrder.setDeliveryDateStr(Constants.DF_DMY.format(purchaseOrder.getDeliveryDate()));
					}
					if(purchaseOrder.getOrderDate() != null){
					purchaseOrder.setOrderDateStr(Constants.DF_DMY.format(purchaseOrder.getOrderDate()));
					}
				}
		return purchaseOrderList;
	}
	
	@Override
	public String getMaxPurchaseOrderId() {
		return purchaseOrderDao.getMaxPurchaseOrderId();
	}
	
	@Override
	public PurchaseOrder saveOrUpdatePurchaseOrder(PurchaseOrder purchaseOrder) {
		return purchaseOrderDao.addPurchaseOrder(purchaseOrder);
	}
	
	
	private void sendEmailToSupplierPurchaseOrder(PurchaseOrder purchaseOrderById, User user, Integer maxCount) throws Exception {
		
		 EmailMessage emailMessage = new EmailMessage();
		 emailMessage.setFirstName(purchaseOrderById.getSupplier().getName());
		 emailMessage.setToEmail(purchaseOrderById.getSupplier().getSupplierContactEmail());
		 emailMessage.setFromEmail(user.getEmail());
		 String fromLastName = "";
		 if(StringUtils.isNotEmpty(user.getLastName())){
			 fromLastName = user.getLastName();
		 }
		 emailMessage.setFromName(user.getFirstName()+" "+fromLastName);
		 
		 //setting the class filed values
		 Map<String, String> globalVarsMap = new HashMap<String, String>();
		 globalVarsMap.put("supplierName", emailMessage.getFirstName());
		 globalVarsMap.put("storeName", purchaseOrderById.getStoreId().getStoreName());
		 globalVarsMap.put("location",purchaseOrderById.getStoreId().getAddress().getAddress());
		 
		 globalVarsMap.put("productsCount",maxCount.toString());
		 globalVarsMap.put("orderDate",Constants.DF_DMY.format(purchaseOrderById.getDeliveryDate()));
		 globalVarsMap.put("loginUserName",emailMessage.getFromName());
		 globalVarsMap.put("link","<a href='"+operoxUrl+"/"+"' style='text-decoration:none; '><div>click here</div></a>");
		 
		 //setting the Recipient information
		 ArrayList<EmailRecipient> emailRecipientList = new ArrayList<EmailRecipient>();
		 EmailRecipient emailRecipient = new EmailRecipient();
		 emailRecipient.setEmail(emailMessage.getToEmail());
		 emailRecipient.setName(emailMessage.getFirstName());
		 emailRecipientList.add(emailRecipient);
		
		 //getting the current EmailScenario by EmailScenario unique name and entity type
		 EmailScenario  emailScenario = emailScenarioService.getEmailScenarioByUniqueName(Constants.PURCHASE_ORDER_SUPPLIER,Constants.ENTITY_TYPE_EMAIL);
		
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
