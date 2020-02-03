package com.bis.operox.inv.service.impl;

import java.text.ParseException;
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
import com.bis.operox.inv.dao.StockReturnsDao;
import com.bis.operox.inv.dao.StockReturnsItemsDao;
import com.bis.operox.inv.dao.UserDao;
import com.bis.operox.inv.dao.entity.Customer;
import com.bis.operox.inv.dao.entity.EmailAttachment;
import com.bis.operox.inv.dao.entity.EmailMessage;
import com.bis.operox.inv.dao.entity.EmailRecipient;
import com.bis.operox.inv.dao.entity.EmailScenario;
import com.bis.operox.inv.dao.entity.Product;
import com.bis.operox.inv.dao.entity.ProductStock;
import com.bis.operox.inv.dao.entity.PurchaseOrder;
import com.bis.operox.inv.dao.entity.StockReturns;
import com.bis.operox.inv.dao.entity.StockReturnsItems;
import com.bis.operox.inv.dao.entity.Store;
import com.bis.operox.inv.dao.entity.Supplier;
import com.bis.operox.inv.dao.entity.User;
import com.bis.operox.inv.dao.enums.Return_Type;
import com.bis.operox.inv.service.EmailScenarioService;
import com.bis.operox.inv.service.StockReturnsService;
import com.bis.operox.inv.service.UserService;
import com.bis.operox.util.CommonUtil;
import com.bis.operox.util.EmailScenerioUtil;

@Service
public class StockReturnsServiceImpl implements StockReturnsService{ 
	
	
	@Autowired
	private StockReturnsDao stockReturnsDao;
	
	@Autowired
	UserService userService;
	
	@Autowired
	private UserDao userDao;

	@Autowired
	private StockReturnsItemsDao stockReturnsItemsDao;

	@Autowired
	private CommonUtil commonUtil;
	
	@Autowired
	EmailScenarioService emailScenarioService;
	
	@Autowired
	EmailScenerioUtil emailScenerioUtil;
	
	@Value("${operoxUrl}")
    private String operoxUrl;
	
	//by sarath...for return david in progress...
	@Override
	public StockReturns addStockReturns(JSONObject jsonObj,User user) throws JSONException, ParseException {
		

		StockReturns stockReturns = new StockReturns();
		 
		String pattern = "MM/dd/yyyy";
        SimpleDateFormat format = new SimpleDateFormat(pattern);
	 	
        if((jsonObj.has("productStock")) && !jsonObj.getString("productStock").isEmpty()){
    	String productId =  jsonObj.getString("productStock");
    	ProductStock p = new ProductStock();
    	p.setId(Long.parseLong(productId));
    	stockReturns.setProductStock(p);
        }
        
    	if((jsonObj.has("storeReturnFrom")) && !jsonObj.getString("storeReturnFrom").isEmpty()){
    		Store s1= new Store();
    		s1.setId(Long.parseLong(jsonObj.getString("storeReturnFrom")));
    		stockReturns.setStoreReturnFrom(s1);
    	}
    	if((jsonObj.has("storeReturnTo")) && !jsonObj.getString("storeReturnTo").isEmpty()){
    		Store s2= new Store();
    		s2.setId(Long.parseLong(jsonObj.getString("storeReturnTo")));
    		stockReturns.setStoreReturnTo(s2);
    	}
    	if((jsonObj.has("receivedDate")) && !jsonObj.getString("receivedDate").isEmpty()){
    		Date receivedDate = format.parse(jsonObj.getString("receivedDate"));
    		stockReturns.setReceivedDate(receivedDate);
    	}
    	if((jsonObj.has("customer")) && !jsonObj.getString("customer").isEmpty()){
    		Customer cu = new Customer();
    		cu.setId(Long.parseLong(jsonObj.getString("customer")));
    		stockReturns.setCustomer(cu);
    	}
    	if((jsonObj.has("supplier")) && !jsonObj.getString("supplier").isEmpty()){
    		Supplier  su = new Supplier();
    		su.setId(Long.parseLong(jsonObj.getString("supplier")));
    		stockReturns.setSupplier(su);
    	}
    	if((jsonObj.has("totalAmount")) && !jsonObj.getString("totalAmount").isEmpty()){
    		stockReturns.setTotalAmount(jsonObj.getString("totalAmount"));
    	}
    	if((jsonObj.has("totalNumberOfProducts")) && !jsonObj.getString("totalNumberOfProducts").isEmpty()){
    		stockReturns.setTotalNumberOfProducts(jsonObj.getString("totalNumberOfProducts"));
    	}
    	if((jsonObj.has("comment")) && !jsonObj.getString("comment").isEmpty()){
    		stockReturns.setComment(jsonObj.getString("comment"));
    	}
    	if((jsonObj.has("stockReturnBy")) && !jsonObj.getString("stockReturnBy").isEmpty()){
    		User usr = new User();
    		user.setId(Long.parseLong(jsonObj.getString("stockReturnBy")));
    		stockReturns.setStockReturnBy(user);
    	}
    	
    	stockReturns.setCreatedBy(user.getUserCode());
    	stockReturns.setUpdatedBy(user.getUserCode());
    	stockReturns.setCreatedDate(commonUtil.currentDate());
    	stockReturns.setUpdatedDate(commonUtil.currentDate());
    	stockReturns.setReturnType(Return_Type.RETURN.getValue());
    	stockReturns.setSTATUS(Constants.Active);
    	stockReturns = stockReturnsDao.addStockReturns(stockReturns);
	
		String maxAdminTimeTypeRowNum = jsonObj.getString("maxTsRowNum");
		int maxCount = Integer.parseInt(maxAdminTimeTypeRowNum);
		
		for (long c = 1; c < maxCount; c++) {
			StockReturnsItems  stockReturnsItems   = new StockReturnsItems();
		           if(jsonObj.has(c+"quantity") && !jsonObj.getString(c+"quantity").isEmpty()){
		        	   stockReturnsItems.setQuantity(jsonObj.getString(c+"quantity"));
					}
		           if(jsonObj.has(c+"productId")  && !jsonObj.getString(c+"productId").isEmpty()){
						Product pr  = new Product();
						pr.setId(Long.parseLong(jsonObj.getString(c+"productId")));
						stockReturnsItems.setProduct(pr);
				   }
		           if(jsonObj.has(c+"total")  && !jsonObj.getString(c+"total").isEmpty()){
		        	   stockReturnsItems.setAmount(jsonObj.getString(c+"total"));   
				   }
		           if(jsonObj.has(c+"expiredate")  && !jsonObj.getString(c+"expiredate").isEmpty()){
		        	   
		        	   SimpleDateFormat myFormat = new SimpleDateFormat("dd/MM/yyyy");
		        	   SimpleDateFormat fromUser = new SimpleDateFormat("dd-MM-yyyy");
		        	   try {
		        	       Date expiredate = myFormat.parse((myFormat.format(fromUser.parse(jsonObj.getString(c+"expiredate")))));
		        	       stockReturnsItems.setExpirydate(expiredate);
		        	   } catch (ParseException e) {
		        	       e.printStackTrace();
		        	   }
		        	   
		        	   
		        	   //stockReturnsItems.setExpirydate(expiredate);   
				   }
		           if(jsonObj.has(c+"productPrice")  && !jsonObj.getString(c+"productPrice").isEmpty()){
		        	   stockReturnsItems.setProductPrice(jsonObj.getString(c+"productPrice"));   
				   }
		           if(jsonObj.has(c+"productPrice")  && !jsonObj.getString(c+"productPrice").isEmpty()){
		        	   stockReturnsItems.setProductPrice(jsonObj.getString(c+"productPrice"));   
				   }
		           if(jsonObj.has(c+"batchId")  && !jsonObj.getString(c+"batchId").isEmpty()){
		        	   stockReturnsItems.setBatchId(jsonObj.getString(c+"batchId"));   
				   }
		           
		           if(jsonObj.has(c+"barCode")  && !jsonObj.getString(c+"barCode").isEmpty()){
		        	   stockReturnsItems.setBarCode(jsonObj.getString(c+"barCode"));   
				   }
		           
		            stockReturnsItems.setCreatedBy(user.getUserCode());
			        stockReturnsItems.setUpdatedBy(user.getUserCode());
			        stockReturnsItems.setStatus(Constants.Active);
			        stockReturnsItems.setCreatedDate(commonUtil.currentDate());
			        stockReturnsItems.setUpdatedDate(commonUtil.currentDate());
			        stockReturnsItems.setStockReturns(stockReturns);
			        
			        
				   stockReturnsItemsDao.addStockReturnsItems(stockReturnsItems);
				
			}
		
		return stockReturns;
	}
	
	@Override
	public StockReturns getStockReturnsById(Long id) {
		
		StockReturns stockReturns =  stockReturnsDao.getStockReturnsById(id);
		if(stockReturns.getTransferDate()!=null){
			stockReturns.setTransferDateStr(Constants.DF_DMY.format(stockReturns.getTransferDate()));
		}
		if(stockReturns.getReceivedDate()!=null){
			stockReturns.setReceivedDateStr(Constants.DF_DMY.format(stockReturns.getReceivedDate()));
		}
		if(stockReturns.getUpdatedDate()!=null){
			stockReturns.setUpdatedDateStr(Constants.DF_DMY.format(stockReturns.getUpdatedDate()));
		}
		return stockReturns;
	}

	//ReturnsStockDashBoardScreen
		@Override
		public List<StockReturns> getAllStockReturns() {
		       List<StockReturns> stockReturnsList  = stockReturnsDao.getAllStockReturns();
					for(StockReturns stockReturns :stockReturnsList){
						
						if(stockReturns.getStoreReturnTo()!=null){
							stockReturns.setToStoreStr(stockReturns.getStoreReturnTo().getStoreName());
						}
						if(stockReturns.getReceivedDate()!=null){
							stockReturns.setReceivedDateStr((Constants.DF_DMY.format(stockReturns.getReceivedDate())));
						}
						if(stockReturns.getStoreReturnFrom()!=null){
							stockReturns.setFromStoreStr(stockReturns.getStoreReturnFrom().getStoreName());
						}
						if(stockReturns.getStockReturnBy()!=null){
							stockReturns.setStockReturnByName(stockReturns.getStockReturnBy().getFirstName() +""+stockReturns.getStockReturnBy().getLastName());
						}
						
						/*stockReturns.setReturnUserStr(stockReturns.getUserReturn().getUserName());*/
					}
			    return stockReturnsList;
		}
	@Override
	public List<StockReturns> getAllTransferStock() {
		
		 List<StockReturns> stockReturnsList  = stockReturnsDao.getAllTransferStock();
			for(StockReturns stockReturns :stockReturnsList){
				
			  if (stockReturns.getReceivedDate() !=null){	
				stockReturns.setReceivedDateStr(Constants.DF_DMY.format(stockReturns.getReceivedDate()));
			  }
			  if (stockReturns.getStoreReturnFrom() !=null){
				stockReturns.setFromStoreStr(stockReturns.getStoreReturnFrom().getStoreName());
			  }
			  if (stockReturns.getStoreReturnTo()!=null){
				stockReturns.setToStoreStr(stockReturns.getStoreReturnTo().getStoreName());
			  }
			  if (stockReturns.getUpdatedBy()!=null){
				  User user =null;
				try {
					user = userDao.getUserByUserCode(stockReturns.getUpdatedBy());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
					stockReturns.setUpdatedBy(user.getUserCode());
			  }
			  
				/*stockReturns.setReturnUserStr(stockReturns.getUserReturn().getUserName());*/
			}
	     return stockReturnsList;
		
	}
	

	//by sarath...for tranfer

		@Override
		public StockReturns addStockTransfer(JSONObject jsonObj,String submissionType,User user) throws Exception {
		

				StockReturns stockReturns = new StockReturns();
				String pattern = "MM/dd/yyyy";
		        SimpleDateFormat format = new SimpleDateFormat(pattern);
			 	
		        if((jsonObj.has("productStock")) && !jsonObj.getString("productStock").isEmpty()){
		    	String productId =  jsonObj.getString("productStock");
		    	ProductStock p = new ProductStock();
		    	p.setId(Long.parseLong(productId));
		    	stockReturns.setProductStock(p);
		        }
		        
		        if((jsonObj.has("transferNumber")) && !jsonObj.getString("transferNumber").isEmpty()){
		    		stockReturns.setTransferNumber(jsonObj.getString("transferNumber"));
		    	}
		        
		        String totalproductsCount = jsonObj.getString("maxTsRowNum");
		        if((jsonObj.has("maxTsRowNum")) && !jsonObj.getString("maxTsRowNum").isEmpty()){
		    		stockReturns.setTotalNumberOfProducts(totalproductsCount);
		    	}
		        if(!submissionType.isEmpty()){
		    		stockReturns.setSubmissionType(submissionType);
		    	}
		        
		    	if((jsonObj.has("storeReturnFrom")) && !jsonObj.getString("storeReturnFrom").isEmpty()){
		    		Store s1= new Store();
		    		s1.setId(Long.parseLong(jsonObj.getString("storeReturnFrom")));
		    		stockReturns.setStoreReturnFrom(s1);
		    	}
		    	if((jsonObj.has("storeReturnTo")) && !jsonObj.getString("storeReturnTo").isEmpty()){
		    		Store s2= new Store();
		    		s2.setId(Long.parseLong(jsonObj.getString("storeReturnTo")));
		    		stockReturns.setStoreReturnTo(s2);
		    	}
		    	if(jsonObj.getString("transferDate") != null &&  !jsonObj.getString("transferDate").isEmpty() && jsonObj.getString("transferDate") != "" && jsonObj.getString("transferDate") != "undefined"){
		    		Date transferDate = format.parse(jsonObj.getString("transferDate"));
		    		stockReturns.setTransferDate(transferDate);
		    	}
		    	if((jsonObj.has("receivedDate")) && !jsonObj.getString("receivedDate").isEmpty()){
		    		Date receivedDate = format.parse(jsonObj.getString("receivedDate"));
		    		stockReturns.setReceivedDate(receivedDate);
		    	}
		    	stockReturns.setCreatedBy(user.getUserCode());
		    	stockReturns.setUpdatedBy(user.getUserCode());
		    	stockReturns.setCreatedDate(commonUtil.currentDate());
		    	stockReturns.setUpdatedDate(commonUtil.currentDate());
		    	stockReturns.setReturnType(Return_Type.TRANSFER.getValue());
		    	stockReturns.setSTATUS(Constants.Active);
		    	stockReturns = stockReturnsDao.addStockTransfer(stockReturns);
			
				String maxAdminTimeTypeRowNum = jsonObj.getString("maxTsRowNum");
				int maxCount = Integer.parseInt(maxAdminTimeTypeRowNum);
				
				for (long c = 1; c < maxCount; c++) {
					StockReturnsItems  stockReturnsItems   = new StockReturnsItems();
					       if(jsonObj.has(c+"quantity") && !jsonObj.getString(c+"quantity").isEmpty()){
				        	   stockReturnsItems.setQuantity(jsonObj.getString(c+"quantity"));
							}
				           if(jsonObj.has(c+"barCode") && !jsonObj.getString(c+"barCode").isEmpty()){
				        	   stockReturnsItems.setBarCode(jsonObj.getString(c+"barCode"));
							}
				           if(jsonObj.has(c+"amount") && !jsonObj.getString(c+"amount").isEmpty()){
				        	   stockReturnsItems.setAmount(jsonObj.getString(c+"amount"));
							}
				           if(jsonObj.has(c+"productId") && !jsonObj.getString(c+"productId").isEmpty()){
								Product pr  = new Product();
								pr.setId(Long.parseLong(jsonObj.getString(c+"productId")));
								stockReturnsItems.setProduct(pr);
						  }
			            stockReturnsItems.setCreatedBy(user.getUserCode());
				        stockReturnsItems.setUpdatedBy(user.getUserCode());
				        stockReturnsItems.setCreatedDate(commonUtil.currentDate());
				        stockReturnsItems.setUpdatedDate(commonUtil.currentDate());
				        stockReturnsItems.setCreatedDate(new Date());
						stockReturnsItems.setUpdatedDate(new Date());
						stockReturnsItems.setStockReturns(stockReturns);
						stockReturnsItemsDao.addStockReturnsItems(stockReturnsItems);
					}
				
				String actionLink = null; 
				Map<String, String> globalVarsMap = new HashMap<String, String>();
				
				StockReturns stockReturnsById = stockReturnsDao.getStockReturnsById(stockReturns.getId());
				
				actionLink=operoxUrl+"/viewPurchaseOrder/"+stockReturnsById.getId();
				globalVarsMap.put("purchaseOrderId", stockReturnsById.getId().toString());
				EmailScenario  notificationScenario = emailScenarioService.getEmailScenarioByUniqueName(Constants.TRANSFER_STOCK,Constants.ENTITY_TYPE_NOTIFICATION);
				String notificationBody = emailScenerioUtil.buildBody(notificationScenario.getBody(),globalVarsMap);
				notificationScenario.setBody(notificationBody);
				User assignUser = new User();
				if(stockReturnsById.getSupplier()!=null){
					assignUser.setEmail(stockReturnsById.getSupplier().getEmail());
				}
				int noOfProducts = maxCount-1;
				sendEmailForTransferStock(stockReturnsById,user, noOfProducts);
				
				
			return stockReturns;
			
		}



	@Override
	public String getMaxStockTransferedNumber() {
		return stockReturnsDao.getMaxStockTransferedNumber();
	}

	
	private void sendEmailForTransferStock(StockReturns stockReturnsById, User user, Integer maxCount) throws Exception {
		
		 EmailMessage emailMessage = new EmailMessage();
		 emailMessage.setFirstName(stockReturnsById.getStoreReturnTo().getStoreName());
		 emailMessage.setToEmail(stockReturnsById.getStoreReturnTo().getEmail());
		 emailMessage.setFromEmail(stockReturnsById.getStoreReturnFrom().getEmail());
		 String fromLastName = "";
		 if(StringUtils.isNotEmpty(user.getLastName())){
			 fromLastName = user.getLastName();
		 }
		 emailMessage.setFromName(user.getFirstName()+" "+fromLastName);
		 
		 //setting the class filed values
		 Map<String, String> globalVarsMap = new HashMap<String, String>();
		 if(stockReturnsById.getStoreReturnFrom().getStoreName()!=null){
			 globalVarsMap.put("fromStoreName", stockReturnsById.getStoreReturnFrom().getStoreName());
		 }
		 if(stockReturnsById.getStoreReturnTo().getStoreName()!=null){
			 globalVarsMap.put("toStoreName", stockReturnsById.getStoreReturnTo().getStoreName());
		 }
		 if(stockReturnsById.getTransferDate()!=null){
			 globalVarsMap.put("transferDate",Constants.DF_DMY.format(stockReturnsById.getTransferDate()));
		 }
		 if(stockReturnsById.getReceivedDate()!=null){
			 globalVarsMap.put("receivedDate",Constants.DF_DMY.format(stockReturnsById.getReceivedDate()));
		 }
		 if(maxCount!=null){
			 globalVarsMap.put("productsCount",maxCount.toString());
		 }
		 globalVarsMap.put("loginUserName",emailMessage.getFromName());
		 globalVarsMap.put("link","<a href='"+operoxUrl+"/"+"' style='text-decoration:none; '><div>click here</div></a>");
		 
		 //setting the Recipient information
		 ArrayList<EmailRecipient> emailRecipientList = new ArrayList<EmailRecipient>();
		 EmailRecipient emailRecipient = new EmailRecipient();
		 emailRecipient.setEmail(emailMessage.getToEmail());
		 emailRecipient.setName(emailMessage.getFirstName());
		 emailRecipientList.add(emailRecipient);
		 
		 User storeManager = userService.getUserByStoreIdAndRole(stockReturnsById.getStoreReturnTo().getId(), Constants.ROLE_STORE_MANAGER);
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
		 EmailScenario  emailScenario = emailScenarioService.getEmailScenarioByUniqueName(Constants.TRANSFER_STOCK,Constants.ENTITY_TYPE_EMAIL);
		
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


