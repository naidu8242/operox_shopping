package com.bis.operox.production.service.impl;

import java.util.Date;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bis.operox.constants.Constants;
import com.bis.operox.inv.dao.PurchaseOrderDao;
import com.bis.operox.inv.dao.PurchaseOrderItemsDao;
import com.bis.operox.inv.dao.entity.PurchaseOrder;
import com.bis.operox.inv.dao.entity.PurchaseOrderItems;
import com.bis.operox.inv.dao.entity.Supplier;
import com.bis.operox.inv.dao.entity.User;
import com.bis.operox.inv.dao.enums.Stock_Status;
import com.bis.operox.inv.dao.enums.Submission_Status;
import com.bis.operox.production.dao.WorkOrderDao;
import com.bis.operox.production.dao.WorkOrderRawMaterialDao;
import com.bis.operox.production.dao.entity.RawMaterial;
import com.bis.operox.production.dao.entity.WorkOrder;
import com.bis.operox.production.dao.entity.WorkOrderItems;
import com.bis.operox.production.dao.entity.WorkOrderRawMaterial;
import com.bis.operox.production.service.WorkOrderRawMaterialService;

@Service
public class WorkOrderRawMaterialServiceImpl implements WorkOrderRawMaterialService {

	@Autowired
	private WorkOrderRawMaterialDao workOrderRawMaterialDao;
	
	@Autowired
	private WorkOrderDao workOrderDao;
	
	@Autowired
	PurchaseOrderDao purchaseOrderDao;
	
	@Autowired
	PurchaseOrderItemsDao purchaseOrderItemsDao;
	
	@Override
	public WorkOrderRawMaterial addWorkOrderRawMaterialDetails(JSONObject jsonObj, User user) throws Exception {
		
		String maxAdminTimeTypeRowNum = jsonObj.getString("maxTsRowNum");
		int maxCount = Integer.parseInt(maxAdminTimeTypeRowNum);
		WorkOrderRawMaterial workOrderRawMaterial  = null;
		
		
		if(jsonObj.has("id") && !jsonObj.getString("id").isEmpty()){
			String ids = jsonObj.getString("id");
			ids = ids.replaceAll("\\[", "").replaceAll("\\]","");
			String[] workOrderRawMaterialIds = ids.split(",");
			for(int i=0 ;i<workOrderRawMaterialIds.length;i++ ){
				WorkOrderRawMaterial oldWorkOrderRawMaterial = workOrderRawMaterialDao.getWorkOrderRawMaterialById(Long.parseLong(workOrderRawMaterialIds[i].replace("\"", "")));
				workOrderRawMaterialDao.deleteWorkOrderRawMaterial(oldWorkOrderRawMaterial);
			}
		}
		
			for (long c = 1; c < maxCount; c++) {
				if(jsonObj.has(c+"workOrderItemsId") && !jsonObj.getString(c+"workOrderItemsId").isEmpty()){
					if(jsonObj.has(c+"quantity") && !jsonObj.getString(c+"quantity").isEmpty()){
						workOrderRawMaterial  = new WorkOrderRawMaterial();
						
						   if(jsonObj.has(c+"productName") && !jsonObj.getString(c+"productName").isEmpty()){
				        	   workOrderRawMaterial.setProductName(jsonObj.getString(c+"productName"));
						    }
						   
				           if(jsonObj.has(c+"workOrderItemsId") && !jsonObj.getString(c+"workOrderItemsId").isEmpty()){
				        	   WorkOrderItems   workOrderItems  = new WorkOrderItems();
				        	   workOrderItems.setId(Long.parseLong(jsonObj.getString(c+"workOrderItemsId")));
				        	   workOrderRawMaterial.setWorkOrderItems(workOrderItems);
							}
				           
				           if(jsonObj.has(c+"MaterialId") && !jsonObj.getString(c+"MaterialId").isEmpty()){
				        	   String[] data = jsonObj.getString(c+"MaterialId").split("&");
				        	   RawMaterial rawMaterial = new RawMaterial();
				        	   rawMaterial.setId(Long.parseLong(data[0]));
				        	   workOrderRawMaterial.setRawMaterial(rawMaterial);
						    }
				           
				           if(jsonObj.has(c+"supplierId") && !jsonObj.getString(c+"supplierId").isEmpty()){
				        	   Supplier supplier = new Supplier();
				        	   supplier.setId(Long.parseLong(jsonObj.getString(c+"supplierId")));
				        	   workOrderRawMaterial.setSupplier(supplier);
						    }
				           
				           if(jsonObj.has(c+"quantity") && !jsonObj.getString(c+"quantity").isEmpty()){
				        	   workOrderRawMaterial.setQuantity(Integer.parseInt(jsonObj.getString(c+"quantity")));
						    }
				           
				           if(jsonObj.has(c+"price") && !jsonObj.getString(c+"price").isEmpty()){
								workOrderRawMaterial.setPrice(Float.parseFloat(jsonObj.getString(c+"price")));
						    }
				           
				           if(jsonObj.has(c+"totalAmount") && !jsonObj.getString(c+"totalAmount").isEmpty()){
								workOrderRawMaterial.setTotalAmount(Float.parseFloat(jsonObj.getString(c+"totalAmount")));
						    }
				           workOrderRawMaterial.setStatus(Constants.RECORD_ACTIVE);
				           workOrderRawMaterial.setCreatedDate(new Date());
				           workOrderRawMaterial.setCreatedBy(user.getUserCode());
				           workOrderRawMaterial.setUpdatedDate(new Date());
				           workOrderRawMaterial.setUpdatedBy(user.getUserCode());
				           workOrderRawMaterialDao.saveworkOrderRawMaterial(workOrderRawMaterial);
				           
				           //to store PurchaseOrder and PurchaseOrderItems
				           if(jsonObj.has(c+"supplierId") && !jsonObj.getString(c+"supplierId").isEmpty()){
				        	   
				        	   PurchaseOrder purchaseOrder = purchaseOrderDao.getPurchaseOrderBySupplierIdAndWorkOrderId(jsonObj.getString(c+"supplierId"), jsonObj.getString("workOrderId"));
				        	   if(purchaseOrder == null){
				        		   purchaseOrder = new PurchaseOrder();
				        		   purchaseOrder.setCreatedDate(new Date());
				        		   purchaseOrder.setCreatedBy(user.getUserCode());
				        		   String maxNumber = purchaseOrderDao.getMaxPurchaseOrderId();
				        			if(maxNumber != null  && !maxNumber.isEmpty()){
				        				String increment = maxNumber.substring(0);
				        				Long  inc = Long.parseLong(increment)+1;
				        				String with5digits = String.format("%05d", inc); 
				        		    	purchaseOrder.setPurchaseNumber("P"+with5digits);
				        			}else{
				        				purchaseOrder.setPurchaseNumber("P00001");
				        			}
				        	   }
					   			WorkOrder workOrder = workOrderDao.getWorkOrderById(Long.parseLong(jsonObj.getString("workOrderId")));
					   			purchaseOrder.setWorkOrder(workOrder);
					   			purchaseOrder.setStoreId(workOrder.getStore());
					   			
					   			purchaseOrder.setSupplier(workOrderRawMaterial.getSupplier());
					   			
					   			purchaseOrder.setOrderDate(new Date());
					            purchaseOrder.setOrderStatus(Stock_Status.getNameByValue("pending"));
					            purchaseOrder.setSubmissionStatus(Submission_Status.getNameByValue("save"));
					   	        purchaseOrder.setStatus(Constants.Active);           
					   	        purchaseOrder.setUpdatedDate(new Date());
					   	        purchaseOrder.setUpdatedBy(user.getUserCode());
					   	        purchaseOrder =  purchaseOrderDao.addPurchaseOrder(purchaseOrder);
					   	        
					   	        
					   	        PurchaseOrderItems purchaseOrderItems  = purchaseOrderItemsDao.getPurchaseOrderItemsByPurchaseOrderIdAndRawMaterialId(purchaseOrder.getId(), jsonObj.getString(c+"MaterialId").split("&")[0]);
					   	        if(purchaseOrderItems == null){
					   	           purchaseOrderItems  = new PurchaseOrderItems();
					   	           purchaseOrderItems.setCreatedDate(new Date());
					   	           purchaseOrderItems.setCreatedBy(user.getUserCode());
					   	        }
					   	        
					   	        if(jsonObj.has(c+"quantity") && !jsonObj.getString(c+"quantity").isEmpty()){
					   	        	purchaseOrderItems.setQuantity(jsonObj.getString(c+"quantity"));
							    }
					   	        
					   	        purchaseOrderItems.setRawMaterial(workOrderRawMaterial.getRawMaterial());
					   	        purchaseOrderItems.setStatus(Constants.Active);           
								purchaseOrderItems.setUpdatedDate(new Date());
								purchaseOrderItems.setUpdatedBy(user.getUserCode());
								purchaseOrderItems.setPurchaseOrder(purchaseOrder);
							    purchaseOrderItemsDao.addPurchaseOrderItems(purchaseOrderItems);
				           }
				           
					}
					continue;
					
				}
			}
			
			
		
		return workOrderRawMaterial;
	}

	@Override
	public List<WorkOrderRawMaterial> getAllWorkOrderRawMaterials() throws Exception {
		return workOrderRawMaterialDao.getAllWorkOrderRawMaterials();
	}

	@Override
	public List<WorkOrderRawMaterial> getAllWorkOrderRawMaterialsByWorkOrderId(Long workoderId) throws Exception {
		return workOrderRawMaterialDao.getAllWorkOrderRawMaterialsByWorkOrderId(workoderId);
	}

}
