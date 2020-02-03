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
@Table(name = "OFFER_FREE_ITEM_ON_INVOICE", catalog = "operox")
@Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL, region = "offerFreeItemOnInvoice")
public class OfferFreeItemOnInvoice {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID",nullable = false)
	private Long id;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="OFFER_ID")        
    private Offer offer;
	
	@Column(name ="TOTAL_INVOICE_AMOUNT")
	private String totalInvoiceAmount;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="FREE_ITEM_PRODUCT_ID")        
    private Product freeItemProduct;
	
	@Column(name ="FREE_ITEM")
	private String freeItem;
	
	@Column(name ="FREE_ITEM_QUANTITY")
	private String freeItemQuantity;
	
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
    
    

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Offer getOffer() {
		return offer;
	}

	public void setOffer(Offer offer) {
		this.offer = offer;
	}

	public String getTotalInvoiceAmount() {
		return totalInvoiceAmount;
	}

	public void setTotalInvoiceAmount(String totalInvoiceAmount) {
		this.totalInvoiceAmount = totalInvoiceAmount;
	}

	public String getFreeItem() {
		return freeItem;
	}

	public void setFreeItem(String freeItem) {
		this.freeItem = freeItem;
	}

	public String getFreeItemQuantity() {
		return freeItemQuantity;
	}

	public void setFreeItemQuantity(String freeItemQuantity) {
		this.freeItemQuantity = freeItemQuantity;
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

	public Product getFreeItemProduct() {
		return freeItemProduct;
	}

	public void setFreeItemProduct(Product freeItemProduct) {
		this.freeItemProduct = freeItemProduct;
	}

}
