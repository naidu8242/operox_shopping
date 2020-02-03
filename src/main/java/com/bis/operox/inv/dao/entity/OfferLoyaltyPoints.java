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
@Table(name = "OFFER_LOYALTY_POINTS", catalog = "operox")
@Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL, region = "offerLoyaltyPoints")
public class OfferLoyaltyPoints {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID",nullable = false)
	private Long id;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="OFFER_ID")        
    private Offer offer;
	
	@Column(name ="INVOICE_AMOUNT")
	private String invoiceAmount;
	
	@Column(name ="LOYALTY_POINTS")
	private String loyalityPoints;
	
	@Column(name ="LOYALTY_POINT_VALUE")
	private String loyalityPointValue;
	
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

	public String getInvoiceAmount() {
		return invoiceAmount;
	}

	public void setInvoiceAmount(String invoiceAmount) {
		this.invoiceAmount = invoiceAmount;
	}

	public String getLoyalityPoints() {
		return loyalityPoints;
	}

	public void setLoyalityPoints(String loyalityPoints) {
		this.loyalityPoints = loyalityPoints;
	}

	public String getLoyalityPointValue() {
		return loyalityPointValue;
	}

	public void setLoyalityPointValue(String loyalityPointValue) {
		this.loyalityPointValue = loyalityPointValue;
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
}
