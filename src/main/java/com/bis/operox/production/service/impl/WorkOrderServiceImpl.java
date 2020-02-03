package com.bis.operox.production.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bis.operox.inv.dao.CustomerDao;
import com.bis.operox.inv.dao.ProductDao;
import com.bis.operox.inv.dao.StoreDao;
import com.bis.operox.inv.dao.UserDao;
import com.bis.operox.inv.dao.entity.Customer;
import com.bis.operox.inv.dao.entity.Product;
import com.bis.operox.inv.dao.entity.PurchaseOrderItems;
import com.bis.operox.inv.dao.entity.Store;
import com.bis.operox.inv.dao.entity.User;
import com.bis.operox.production.dao.WorkOrderDao;
import com.bis.operox.production.dao.WorkOrderItemsDao;
import com.bis.operox.production.dao.entity.WorkOrder;
import com.bis.operox.production.dao.entity.WorkOrderItems;
import com.bis.operox.production.service.WorkOrderService;

@Service
public class WorkOrderServiceImpl implements WorkOrderService{

	@Autowired
	WorkOrderDao workOrderDao;
	
	@Autowired
	CustomerDao customerDao;
	
	@Autowired
	StoreDao storeDao;
	
	@Autowired
	ProductDao productDao;
	
	@Autowired
	UserDao userDao;
	
	@Autowired
	WorkOrderItemsDao workOrderItemsDao;
	
	@Override
	public List<WorkOrder> getWorkOrderByCompanyId(Long company) throws Exception {
		return workOrderDao.getWorkOrderByCompanyId(company);
	}
	
	@Override
	public WorkOrder saveWorkOrder(WorkOrder workOrder) {
		return workOrderDao.saveWorkOrder(workOrder);
	}

	@Override
	public WorkOrder addWorkOrderDetails(JSONObject jsonObj, User user) throws Exception {
		
		WorkOrder workOrder = new WorkOrder();
		Customer customer = null;
		Store store = null;
		User approver = null;
		
		String pattern = "MM/dd/yyyy";
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		
		if(jsonObj.has("workOrderId") && jsonObj.getString("workOrderId") != null && !"".equals(jsonObj.getString("workOrderId"))){
			workOrder.setWorkOrderNumber(jsonObj.getString("workOrderId"));
		}
		
		if(jsonObj.has("customer") && jsonObj.getString("customer") != null && !"".equals(jsonObj.getString("customer"))){
			customer = customerDao.getCustomerById(Long.parseLong(jsonObj.getString("customer")));
			workOrder.setCustomer(customer);
		}
		
		if(jsonObj.has("purchaseOrderId") && jsonObj.getString("purchaseOrderId") != null && !"".equals(jsonObj.getString("purchaseOrderId"))){
			workOrder.setPurchaseOrderId(jsonObj.getString("purchaseOrderId"));
		}
		
		if(jsonObj.has("store") && jsonObj.getString("store") != null && !"".equals(jsonObj.getString("store"))){
			store = storeDao.getStoreById(Long.parseLong(jsonObj.getString("store")));
			workOrder.setStore(store);
		}
		
		if(jsonObj.has("orderDate") && jsonObj.getString("orderDate") != null && !"".equals(jsonObj.getString("orderDate"))){
			Date orderDate = format.parse(jsonObj.getString("orderDate"));
			workOrder.setOrderDate(orderDate);
		}
		
		if(jsonObj.has("dueDate") && jsonObj.getString("dueDate") != null && !"".equals(jsonObj.getString("dueDate"))){
			Date dueDate = format.parse(jsonObj.getString("dueDate"));
			workOrder.setDueDate(dueDate);
		}
		
		if(jsonObj.has("dispatchedDate") && jsonObj.getString("dispatchedDate") != null && !"".equals(jsonObj.getString("dispatchedDate"))){
			Date dispatchedDate = format.parse(jsonObj.getString("dispatchedDate"));
			workOrder.setDespatchedOnDates(dispatchedDate);
		}
		
		if(jsonObj.has("approver") && jsonObj.getString("approver") != null && !"".equals(jsonObj.getString("approver"))){
			approver = userDao.getUserById(Long.parseLong(jsonObj.getString("approver")));
			workOrder.setApprovedUser(approver);
		}
		
		if(jsonObj.has("status") && jsonObj.getString("status") != null && !"".equals(jsonObj.getString("status"))){
			workOrder.setWorkOrderStatus(jsonObj.getString("status"));
		}
		
		if(jsonObj.has("v") && jsonObj.getString("commants") != null && !"".equals(jsonObj.getString("commants"))){
			workOrder.setCommants(jsonObj.getString("commants"));
		}
		
		workOrder.setStatus(1);
		workOrder.setCreatedBy(user.getUserCode());
		workOrder.setCreatedDate(new Date());
		workOrder = workOrderDao.saveWorkOrder(workOrder);
		
		
		String maxAdminTimeTypeRowNum = jsonObj.getString("maxTsRowNum");
		int maxCount = Integer.parseInt(maxAdminTimeTypeRowNum);
		
			for (long c = 1; c < maxCount; c++) {
				if(jsonObj.has(c+"totalqty") && !jsonObj.getString(c+"totalqty").isEmpty()){
				WorkOrderItems   workOrderItems  = new WorkOrderItems();
			           if(jsonObj.has(c+"totalqty") && !jsonObj.getString(c+"totalqty").isEmpty()){
			        	   workOrderItems.setTotalQty(Integer.parseInt(jsonObj.getString(c+"totalqty")));
						}
			           
			           if(jsonObj.has(c+"batchId") && !jsonObj.getString(c+"batchId").isEmpty()){
			        	   workOrderItems.setBatchId(jsonObj.getString(c+"batchId"));
					    }
			           
			           if(jsonObj.has(c+"waitingforqc") && !jsonObj.getString(c+"waitingforqc").isEmpty()){
			        	   workOrderItems.setTotalQtyForQC(Integer.parseInt(jsonObj.getString(c+"waitingforqc")));
					    }
			           
			           if(jsonObj.has(c+"status") && !jsonObj.getString(c+"status").isEmpty()){
			        	   workOrderItems.setWorkOrderItemStatus(jsonObj.getString(c+"status"));
					    }
			           
			           
			           if(jsonObj.has(c+"productId") && !jsonObj.getString(c+"productId").isEmpty()){
							Product pr  = new Product();
							pr.setId(Long.parseLong(jsonObj.getString(c+"productId")));
							workOrderItems.setProduct(pr);
					    }
			           workOrderItems.setCreatedDate(new Date());
			           workOrderItems.setStatus(1);
			           workOrderItems.setCreatedBy(user.getUserCode());
			           workOrderItems.setWorkOrder(workOrder);
			           workOrderItemsDao.saveWorkOrderItems(workOrderItems);
				}
			}
			
			return workOrder;
		
	}

	@Override
	public WorkOrder getWorkOrderById(Long id) {
		return workOrderDao.getWorkOrderById(id);
	}
	@Override
	public List<WorkOrder> listWorkOrders() {
		return workOrderDao.listWorkOrders();
	}

	@Override
	public WorkOrder getWorkOrderByWorkOrderId(Long workOrderId) {
		return workOrderDao.getWorkOrderByWorkOrderId(workOrderId);
	}
	@Override
	public WorkOrder getWorkOrderByWorkOrderNumber(String workOrderNumber,Long companyId) throws Exception {
		return workOrderDao.getWorkOrderByWorkOrderNumber(workOrderNumber,companyId);

	}

}
