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
@Table(name = "OFFER", catalog = "operox")
@Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL, region = "offer")
public class Offer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;
	
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="COMPANY_ID")        
    private Company company;
	
	@Column(name = "OFFER_NAME")
    private String offerName;
	
	
	@Column(name = "OFFER_TYPE")
    private String offerType;
	
	@Column(name = "OFFER_START_DATE")
    private Date offerStartDate;

	@Column(name = "OFFER_END_DATE")
    private Date offerEndDate;
	
	@Column(name = "OFFER_START_TIME")
    private String offerStartTime;
	
	@Column(name = "OFFER_END_TIME")
    private String offerEndTime;
	
	@Column(name = "SUNDAY")
    private String sunday;
	
	@Column(name = "MONDAY")
    private String monday;
	
	@Column(name = "TUESDAY")
    private String tuesday;
	
	@Column(name = "WEDNESDAY")
    private String wednesday;
	
	@Column(name = "THURSDAY")
    private String thursday;
	
	@Column(name = "FRIDAY")
    private String friday;
	
	@Column(name = "SATURDAY")
    private String saturday;
	
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
    private String offerStartDateStr;
    
    @Transient
    private String offerEndDateStr;
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public String getOfferName() {
		return offerName;
	}

	public void setOfferName(String offerName) {
		this.offerName = offerName;
	}

	public String getOfferType() {
		return offerType;
	}

	public void setOfferType(String offerType) {
		this.offerType = offerType;
	}

	public Date getOfferStartDate() {
		return offerStartDate;
	}

	public void setOfferStartDate(Date offerStartDate) {
		this.offerStartDate = offerStartDate;
	}

	public Date getOfferEndDate() {
		return offerEndDate;
	}

	public void setOfferEndDate(Date offerEndDate) {
		this.offerEndDate = offerEndDate;
	}

	public String getOfferStartTime() {
		return offerStartTime;
	}

	public void setOfferStartTime(String offerStartTime) {
		this.offerStartTime = offerStartTime;
	}

	public String getOfferEndTime() {
		return offerEndTime;
	}

	public void setOfferEndTime(String offerEndTime) {
		this.offerEndTime = offerEndTime;
	}

	public String getSunday() {
		return sunday;
	}

	public void setSunday(String sunday) {
		this.sunday = sunday;
	}

	public String getMonday() {
		return monday;
	}

	public void setMonday(String monday) {
		this.monday = monday;
	}

	public String getTuesday() {
		return tuesday;
	}

	public void setTuesday(String tuesday) {
		this.tuesday = tuesday;
	}

	public String getWednesday() {
		return wednesday;
	}

	public void setWednesday(String wednesday) {
		this.wednesday = wednesday;
	}

	public String getThursday() {
		return thursday;
	}

	public void setThursday(String thursday) {
		this.thursday = thursday;
	}

	public String getFriday() {
		return friday;
	}

	public void setFriday(String friday) {
		this.friday = friday;
	}

	public String getSaturday() {
		return saturday;
	}

	public void setSaturday(String saturday) {
		this.saturday = saturday;
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

	public String getOfferStartDateStr() {
		return offerStartDateStr;
	}

	public void setOfferStartDateStr(String offerStartDateStr) {
		this.offerStartDateStr = offerStartDateStr;
	}

	public String getOfferEndDateStr() {
		return offerEndDateStr;
	}

	public void setOfferEndDateStr(String offerEndDateStr) {
		this.offerEndDateStr = offerEndDateStr;
	}

	

	
}
