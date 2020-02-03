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

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.bis.operox.inv.dao.entity.MeasuringUnit;
import com.bis.operox.inv.dao.entity.Store;

@Entity
@Table(name = "RAW_MATERIAL", catalog = "operox")
@Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL, region = "rawmaterial")
public class RawMaterial {

	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID", nullable = false)
	private Long id;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="STORE_ID")        
    private Store store;
	
	@Column(name = "MATERIAL_NAME")
    private String materialName;
	
	@Column(name = "SEARCH_CODE")
    private String searchCode;
	
	@Column(name = "UNIT_PRICE")
    private String unitPrice;
	
	@Column(name = "DISCOUNT")
    private String discount;
	
	@Column(name = "ANNUAL_ORDER_QTY")
    private Integer annualOrderQuantity;
	
	@Column(name = "AVALIABLE_INVENTORY")
    private String availableInventory;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="MEASURING_UNIT_ID")        
    private MeasuringUnit measuringUnit;
	
	
	@Column(name = "DESCRIPTION",  columnDefinition = "TEXT default NULL")
    private String description;
	
	@Column(name = "STATUS")
	private Integer status;
	
	@Column(name = "IS_PERCENTAGE")
	private String isPercentage;
  
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

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	public String getMaterialName() {
		return materialName;
	}

	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}

	public String getSearchCode() {
		return searchCode;
	}

	public void setSearchCode(String searchCode) {
		this.searchCode = searchCode;
	}

	public String getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(String unitPrice) {
		this.unitPrice = unitPrice;
	}

	public String getDiscount() {
		return discount;
	}

	public void setDiscount(String discount) {
		this.discount = discount;
	}

	public Integer getAnnualOrderQuantity() {
		return annualOrderQuantity;
	}

	public void setAnnualOrderQuantity(Integer annualOrderQuantity) {
		this.annualOrderQuantity = annualOrderQuantity;
	}

	public String getAvailableInventory() {
		return availableInventory;
	}

	public void setAvailableInventory(String availableInventory) {
		this.availableInventory = availableInventory;
	}

	public MeasuringUnit getMeasuringUnit() {
		return measuringUnit;
	}

	public void setMeasuringUnit(MeasuringUnit measuringUnit) {
		this.measuringUnit = measuringUnit;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getIsPercentage() {
		return isPercentage;
	}

	public void setIsPercentage(String isPercentage) {
		this.isPercentage = isPercentage;
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
