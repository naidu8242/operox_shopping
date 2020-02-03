package com.bis.operox.inv.dao.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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

import com.bis.operox.inv.dao.enums.Submission_Status;

@Entity
@Table(name = "RECEIVED_STOCK", catalog = "operox")
@Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL, region = "receivedStock")
/**
 * Entity related to received Stock
 * @author Sammeta David Raju
 * 
 *
 */
public class ReceivedStock {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID", nullable = false)
	private Long id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="SUPPLIER_ID")       
	private Supplier Supplier;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="STORE_ID")  
	private Store storeId;
	
	@Column(name = "ORDER_DATE")
	private Date orderDate;
	
	@Column(name = "DELIVERY_DATE")
	private Date deliveryDate;
	
	@Column(name = "ORDER_STATUS")
	private String orderStatus;
	
	@Column(name = "STATUS")
	private Integer status;
	
	@Column(name = "CREATED_DATE")
    private Date createdDate;
	
	@Column(name = "CREATED_BY")
	private String createdBy;
	
	@Column(name = "UPDATED_DATE")
	private Date updatedDate;
	
	@Column(name = "UPDATED_BY")
	private String updatedBy;
	
	
	@Column(name = "RECEIVED_NUMBER")
	private String receivedNumber;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "POD_NUMBER")
	private PurchaseOrder podNumber;
	
	@Column(name = "SUBMISSION_STATUS")
	@Enumerated(EnumType.STRING)
	private Submission_Status submissionStatus;
	
	@Column(name = "SUPPLIER_INVOICE_NUMBER")
	private String supplierInvoiceNumber;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="WAREHOUSE")  
	private Store warehouse;
	
	@Column(name = "PAYMENT_STATUS")
	private String paymentStatus;
	
	@Column(name = "NUMBER_OF_PACKAGES")
	private String numberOfPackages;
	
	@Column(name = "AMOUNT")
	private String amount;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="RECEIVING_USER_ID")  
	private User receivingUsers;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="TAX_ID")  
	private Tax tax;
	
	@Column(name = "COMMENT")
	private String comment;
	
	@Column(name = "DISCOUNT")
	private String discount;
	
	@Column(name = "DISCOUNT_TYPE")
	private String discountType;
	
	@Column(name = "TOTAL_AMOUNT")
	private String totalAmount;
	
	@Transient
	private String supplierName;
	
	@Transient
	private String deliveryDateStr;
	
	@Transient
	private String updatedDateStr;
	
	@Transient
	private String storeName;
	
	@Transient
	private String wareHouseName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Supplier getSupplier() {
		return Supplier;
	}

	public void setSupplier(Supplier supplier) {
		Supplier = supplier;
	}

	public Store getStoreId() {
		return storeId;
	}

	public void setStoreId(Store storeId) {
		this.storeId = storeId;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
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

	public String getReceivedNumber() {
		return receivedNumber;
	}

	public void setReceivedNumber(String receivedNumber) {
		this.receivedNumber = receivedNumber;
	}

	public PurchaseOrder getPodNumber() {
		return podNumber;
	}

	public void setPodNumber(PurchaseOrder podNumber) {
		this.podNumber = podNumber;
	}

	public Submission_Status getSubmissionStatus() {
		return submissionStatus;
	}

	public void setSubmissionStatus(Submission_Status submissionStatus) {
		this.submissionStatus = submissionStatus;
	}

	public String getSupplierInvoiceNumber() {
		return supplierInvoiceNumber;
	}

	public void setSupplierInvoiceNumber(String supplierInvoiceNumber) {
		this.supplierInvoiceNumber = supplierInvoiceNumber;
	}

	public Store getWarehouse() {
		return warehouse;
	}

	public void setWarehouse(Store warehouse) {
		this.warehouse = warehouse;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public String getNumberOfPackages() {
		return numberOfPackages;
	}

	public void setNumberOfPackages(String numberOfPackages) {
		this.numberOfPackages = numberOfPackages;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public User getReceivingUsers() {
		return receivingUsers;
	}

	public void setReceivingUsers(User receivingUsers) {
		this.receivingUsers = receivingUsers;
	}

	public Tax getTax() {
		return tax;
	}

	public void setTax(Tax tax) {
		this.tax = tax;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getDiscount() {
		return discount;
	}

	public void setDiscount(String discount) {
		this.discount = discount;
	}

	public String getDiscountType() {
		return discountType;
	}

	public void setDiscountType(String discountType) {
		this.discountType = discountType;
	}

	public String getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getDeliveryDateStr() {
		return deliveryDateStr;
	}

	public void setDeliveryDateStr(String deliveryDateStr) {
		this.deliveryDateStr = deliveryDateStr;
	}

	public String getUpdatedDateStr() {
		return updatedDateStr;
	}

	public void setUpdatedDateStr(String updatedDateStr) {
		this.updatedDateStr = updatedDateStr;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getWareHouseName() {
		return wareHouseName;
	}

	public void setWareHouseName(String wareHouseName) {
		this.wareHouseName = wareHouseName;
	}
	
}
