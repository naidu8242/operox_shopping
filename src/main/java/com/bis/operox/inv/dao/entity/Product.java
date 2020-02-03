package com.bis.operox.inv.dao.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "PRODUCT", catalog = "operox")
@Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL, region = "product")
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID",nullable = false)
	private Long id;
	
	@Column(name="PRODUCT_ID")
	private String productId;
	
	@Column(name="PRODUCT_NAME")
	private String productName;
	
	@Column(name="PRODUCT_CODE")
	private String productCode;
	
	@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="COMPANY_ID")        
    private Company companyId;
	
	@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="SUB_CATEGORY_ID" )        
    private Category subCategory;
	
	@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="CATEGORY_ID" )        
    private Category category;
	
	@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="BRAND_ID")        
    private Brand brand;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="MEASURING_UNIT_ID")        
    private MeasuringUnit measuringUnit;
	
	@Column(name = "PRODUCT_IMAGE",columnDefinition="mediumblob")
	@Lob
	private  byte[] docFile;
		
	@Column(name = "FILE_CONTENT_TYPE")
	private String fileContentType;
	
	@Column(name="MANUFACTURED_BY")
	private String manufacturedBy;
	
	@Column(name="MARKETED_BY")
	private String marketedBy;
	
	@Column(name="STATUS")
	private int status;
	
	@Column(name = "CREATED_DATE")
	private Date createdDate;
	
	@Column(name = "CREATED_BY")
	private String createdBy;
	
	@Column(name = "UPDATED_DATE")
	private Date updatedDate;
	
	@Column(name = "UPDATED_BY")
	private String updatedBy;

	@Transient
	private String catagoryName;
	
	@Transient
	private String subCatagoryName;
	
	@Transient
	private String brandName;
	
	@Transient
	private String dateStr;
	
	
	
	public String getSubCatagoryName() {
		return subCatagoryName;
	}

	public void setSubCatagoryName(String subCatagoryName) {
		this.subCatagoryName = subCatagoryName;
	}

	public Category getSubCategory() {
		return subCategory;
	}

	public void setSubCategory(Category subCategory) {
		this.subCategory = subCategory;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	public MeasuringUnit getMeasuringUnit() {
		return measuringUnit;
	}

	public void setMeasuringUnit(MeasuringUnit measuringUnit) {
		this.measuringUnit = measuringUnit;
	}

	public byte[] getDocFile() {
		return docFile;
	}

	public void setDocFile(byte[] docFile) {
		this.docFile = docFile;
	}

	public String getFileContentType() {
		return fileContentType;
	}

	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}

	public String getManufacturedBy() {
		return manufacturedBy;
	}

	public void setManufacturedBy(String manufacturedBy) {
		this.manufacturedBy = manufacturedBy;
	}

	public String getMarketedBy() {
		return marketedBy;
	}

	public void setMarketedBy(String marketedBy) {
		this.marketedBy = marketedBy;
	}
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
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

	public String getCatagoryName() {
		return catagoryName;
	}

	public void setCatagoryName(String catagoryName) {
		this.catagoryName = catagoryName;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public Company getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Company companyId) {
		this.companyId = companyId;
	}

	public String getDateStr() {
		return dateStr;
	}

	public void setDateStr(String dateStr) {
		this.dateStr = dateStr;
	}

}
