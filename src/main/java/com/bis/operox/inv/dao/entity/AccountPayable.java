
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

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "ACCOUNT_PAYABLE", catalog = "operox")
@Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL, region = "accountPayable")
public class AccountPayable {
       
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "ID", nullable = false)
   private Long id;
       
   @ManyToOne(fetch = FetchType.EAGER)
   @JoinColumn(name="SUPPLIER_INVOICE_ID")        
   private SupplierInvoice supplierInvoiceId;
                                                                                         
   @Column(name = "PAYMENT_STATUS")
   private String paymentStatus;
       
   @Column(name = "PAYMENT_MODE")
   private String paymentMode;
       
   @Column(name = "DATE_OF_PAY")
   private Date dateOfPay;
       
   @Column(name = "CREATED_DATE")
   private Date createdDate;
   
   @Column(name = "CREATED_BY")
   private String createdBy;
   
   @Column(name = "UPDATED_DATE")
   private Date updatedDate;
   
   @Column(name = "UPDATED_BY")
   private String updatedBy;

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	
	
	public SupplierInvoice getSupplierInvoiceId() {
		return supplierInvoiceId;
	}

	public void setSupplierInvoiceId(SupplierInvoice supplierInvoiceId) {
		this.supplierInvoiceId = supplierInvoiceId;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}
	
	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	
	public String getPaymentMode() {
		return paymentMode;
	}
	
	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}
	
	public Date getDateOfPay() {
		return dateOfPay;
	}
	
	public void setDateOfPay(Date dateOfPay) {
		this.dateOfPay = dateOfPay;
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
	   
	   
	}
	

