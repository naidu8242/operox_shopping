package com.bis.operox.production.dao.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.bis.operox.inv.dao.entity.Customer;
import com.bis.operox.inv.dao.entity.Store;
import com.bis.operox.inv.dao.entity.User;

@Entity
@Table(name = "WORK_ORDER", catalog = "operox")
@Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL, region = "workorder")
public class WorkOrder {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID",nullable = false)
	private Long id;
	
	@Column(name ="WORK_ORDER_NUMBER")
	private String workOrderNumber;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="CUSTOMER_ID")        
    private Customer customer;
	
	@Column(name ="PURCHASE_ORDER_ID")
	private String purchaseOrderId;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="STORE_ID")        
    private Store store;
	
	@Column(name ="ORDER_DATE")
	private Date orderDate;
	
	@Column(name ="DUE_DATE")
	private Date dueDate;
	
	@Column(name ="DESPATCHED_ON_DATE")
	private Date despatchedOnDates;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="APPROVED_USER_ID")        
    private User approvedUser;
	
	@Column(name ="WORK_ORDER_STATUS")
	private String workOrderStatus;
	
	@Column(name = "COMMANTS",  columnDefinition = "TEXT default NULL")
	private String commants;
	
	@Column(name ="STATUS")
   	private Integer status;
    
    @Column(name ="CREATED_DATE")
   	private Date createdDate;
    
    @Column(name ="CREATED_BY")
	private String createdBy;
    
    @Column(name ="UPDATED_DATE")
   	private Date updatedDate;
    
    @Column(name ="UPDATED_BY")
	private String updatedBy;
    
    @Transient
    private String customerName;
    
    @Transient
    private String productName;
    
    @Transient
    private String orderDateStr;
    
    @Transient
    private String dueDateStr;
    
    @Transient
	private String despatchedOnDatesStr;
   
    
	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getWorkOrderNumber() {
		return workOrderNumber;
	}

	public void setWorkOrderNumber(String workOrderNumber) {
		this.workOrderNumber = workOrderNumber;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public Date getDespatchedOnDates() {
		return despatchedOnDates;
	}

	public void setDespatchedOnDates(Date despatchedOnDates) {
		this.despatchedOnDates = despatchedOnDates;
	}

	public User getApprovedUser() {
		return approvedUser;
	}

	public void setApprovedUser(User approvedUser) {
		this.approvedUser = approvedUser;
	}

	public String getWorkOrderStatus() {
		return workOrderStatus;
	}

	public void setWorkOrderStatus(String workOrderStatus) {
		this.workOrderStatus = workOrderStatus;
	}

	public String getCommants() {
		return commants;
	}

	public void setCommants(String commants) {
		this.commants = commants;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}


	public String getOrderDateStr() {
		return orderDateStr;
	}

	public void setOrderDateStr(String orderDateStr) {
		this.orderDateStr = orderDateStr;
	}

	public String getDueDateStr() {
		return dueDateStr;
	}

	public void setDueDateStr(String dueDateStr) {
		this.dueDateStr = dueDateStr;
	}

	public String getDespatchedOnDatesStr() {
		return despatchedOnDatesStr;
	}

	public void setDespatchedOnDatesStr(String despatchedOnDatesStr) {
		this.despatchedOnDatesStr = despatchedOnDatesStr;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getPurchaseOrderId() {
		return purchaseOrderId;
	}

	public void setPurchaseOrderId(String purchaseOrderId) {
		this.purchaseOrderId = purchaseOrderId;
	}
	
	

}
