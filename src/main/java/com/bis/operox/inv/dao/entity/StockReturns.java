package com.bis.operox.inv.dao.entity;

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

@Entity
@Table(name = "STOCK_RETURNS", catalog = "operox")
@Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL, region = "stockReturns")
public class StockReturns {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
	private Long id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "PRODUCT_STOCK_ID")
	private ProductStock ProductStock;
	
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "RETURN_TO")
	private Store storeReturnTo;
	
	@Column(name = "TRANSFER_NUMBER")
	private String transferNumber;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "RETURN_FROM")
	private Store storeReturnFrom;
	
	@Column(name = "RETURN_TYPE")
	private String returnType;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "CUSTOMER_ID")
	private Customer customer;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "SUPPLIER_ID")
	private Supplier supplier;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "STOCK_RETURN_BY")
	private User stockReturnBy;
	
	
	@Column(name = "RETURN_REASON",  columnDefinition = "TEXT default NULL")
	private String returnReason;
	
	@Column(name = "SUBMISSION_STATUS")
	private String submissionType;
	
	@Column(name = "TOTAL_AMOUNT")
	private String totalAmount;
	
	@Column(name = "TOTAL_NUMBEROF_PRODUCTS")
	private String totalNumberOfProducts;
	
	@Column(name = "COMMENT",columnDefinition = "TEXT default NULL")
	private String comment;
	
	@Column(name = "STATUS")
	private Integer STATUS;
	
	@Column(name = "TRANSFER_DATE")
	private Date transferDate;
	
	@Column(name = "RECEVED_DATE")
	private Date receivedDate;

	@Column(name = "CREATED_DATE")
	private Date createdDate;
	
	@Column(name = "CREATED_BY")
	private String createdBy;
	
	@Column(name = "UPDATED_DATE")
	private Date updatedDate;
	
	@Column(name = "UPDATED_BY")
	private String updatedBy;
	
    @Transient
	private String transferDateStr;
    
    @Transient
	private String receivedDateStr;
    
    @Transient
	private String updatedDateStr;
    
    @Transient
   	private String returnStockStr;
    
    @Transient
   	private String returnDateStr;
    
    @Transient
   	private String fromStoreStr;
    
    @Transient
   	private String toStoreStr;
    
    @Transient
   	private String returnUserStr;
    
    @Transient
   	private String receviedDateStr;
    
    @Transient
   	private String stockReturnByName;
    
    

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ProductStock getProductStock() {
		return ProductStock;
	}

	public void setProductStock(ProductStock productStock) {
		ProductStock = productStock;
	}

	public Store getStoreReturnTo() {
		return storeReturnTo;
	}

	public void setStoreReturnTo(Store storeReturnTo) {
		this.storeReturnTo = storeReturnTo;
	}

	public String getTransferNumber() {
		return transferNumber;
	}

	public void setTransferNumber(String transferNumber) {
		this.transferNumber = transferNumber;
	}

	public Store getStoreReturnFrom() {
		return storeReturnFrom;
	}

	public void setStoreReturnFrom(Store storeReturnFrom) {
		this.storeReturnFrom = storeReturnFrom;
	}

	public String getReturnType() {
		return returnType;
	}

	public void setReturnType(String returnType) {
		this.returnType = returnType;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public String getSubmissionType() {
		return submissionType;
	}

	public void setSubmissionType(String submissionType) {
		this.submissionType = submissionType;
	}

	public String getReturnReason() {
		return returnReason;
	}

	public void setReturnReason(String returnReason) {
		this.returnReason = returnReason;
	}

	public Integer getSTATUS() {
		return STATUS;
	}

	public void setSTATUS(Integer sTATUS) {
		STATUS = sTATUS;
	}

	public Date getTransferDate() {
		return transferDate;
	}

	public User getStockReturnBy() {
		return stockReturnBy;
	}

	public void setStockReturnBy(User stockReturnBy) {
		this.stockReturnBy = stockReturnBy;
	}

	public String getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getTotalNumberOfProducts() {
		return totalNumberOfProducts;
	}

	public void setTotalNumberOfProducts(String totalNumberOfProducts) {
		this.totalNumberOfProducts = totalNumberOfProducts;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public void setTransferDate(Date transferDate) {
		this.transferDate = transferDate;
	}

	public Date getReceivedDate() {
		return receivedDate;
	}

	public void setReceivedDate(Date receivedDate) {
		this.receivedDate = receivedDate;
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

	public String getTransferDateStr() {
		return transferDateStr;
	}

	public void setTransferDateStr(String transferDateStr) {
		this.transferDateStr = transferDateStr;
	}

	public String getReceivedDateStr() {
		return receivedDateStr;
	}

	public void setReceivedDateStr(String receivedDateStr) {
		this.receivedDateStr = receivedDateStr;
	}

	public String getUpdatedDateStr() {
		return updatedDateStr;
	}

	public void setUpdatedDateStr(String updatedDateStr) {
		this.updatedDateStr = updatedDateStr;
	}

	public String getReturnStockStr() {
		return returnStockStr;
	}

	public void setReturnStockStr(String returnStockStr) {
		this.returnStockStr = returnStockStr;
	}

	public String getReturnDateStr() {
		return returnDateStr;
	}

	public void setReturnDateStr(String returnDateStr) {
		this.returnDateStr = returnDateStr;
	}

	public String getFromStoreStr() {
		return fromStoreStr;
	}

	public void setFromStoreStr(String fromStoreStr) {
		this.fromStoreStr = fromStoreStr;
	}

	public String getToStoreStr() {
		return toStoreStr;
	}

	public void setToStoreStr(String toStoreStr) {
		this.toStoreStr = toStoreStr;
	}

	public String getReturnUserStr() {
		return returnUserStr;
	}

	public void setReturnUserStr(String returnUserStr) {
		this.returnUserStr = returnUserStr;
	}

	public String getReceviedDateStr() {
		return receviedDateStr;
	}

	public void setReceviedDateStr(String receviedDateStr) {
		this.receviedDateStr = receviedDateStr;
	}

	public String getStockReturnByName() {
		return stockReturnByName;
	}

	public void setStockReturnByName(String stockReturnByName) {
		this.stockReturnByName = stockReturnByName;
	}
	

}
