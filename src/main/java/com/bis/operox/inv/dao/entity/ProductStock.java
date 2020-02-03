package com.bis.operox.inv.dao.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "PRODUCT_STOCK", catalog = "operox")
@Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL, region = "productStock")
public class ProductStock {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
	private Long id;
	
	@ManyToOne
    @JoinColumn(name="PRODUCT_ID")        
    private Product productId;
	
	@Column(name = "DISCOUNT")
	private Float discount;
	
	
	@ManyToOne
    @JoinColumn(name="TAX_ID")        
    private Tax taxId;
	
	@ManyToOne
    @JoinColumn(name="DISCOUNT_ID")        
    private Tax discountId;
	
	@ManyToOne
    @JoinColumn(name="STORE_ID")        
    private Store storeId;
	
	@Column(name = "MANUFACTURING_DATE")
	private Date manufacturingDate;
	
	@Column(name = "EXPIRE_DATE")
	private Date ExpireDate;
    
	@Column(name = "BATCH_NUMBER")
	private String batchNumber;
	
	@Column(name = "BAR_CODE")
	private String barCode;
	
	@Column(name = "STOCK_IN")
	private Long stockIn;
	
	@Column(name = "STOCK_OUT")
	private Long stockOut;
	
	@Column(name = "CURRENT_QUANTITY")
	private Long currentQuantity;
	
	@Column(name="STATUS")
	private Integer status;
	
	@Column(name = "CREATED_DATE")
	private Date createdDate;
	
	@Column(name = "CREATED_BY")
	private String createdBy;
	
	@Column(name = "UPDATED_DATE")
	private Date updatedDate;
	
	@Column(name = "UPDATED_BY")
	private String updatedBy;
	
	@Transient
	private String productName;
	
	@Transient
	private String searchCodeStr;
	
	@Transient
	private String expireDateStr;
	
	@Transient
	private String qtyStr;
	
	@Transient
	private String mrpStr;
	
	@Transient
	private String priceStr;
	
	@Transient
	private String taxStr;
	
	@Transient
	private String descriptionStr;
	
	@Transient
	private String catagoryName;
	
	@Transient
	private String parentCatagoryName;
	
	@Transient
	private String brandName;
	
	@Transient
	private String productCode;
	
	@Transient
	private String productIdStr;
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Product getProductId() {
		return productId;
	}

	public void setProductId(Product productId) {
		this.productId = productId;
	}
	
	public Float getDiscount() {
		return discount;
	}

	public void setDiscount(Float discount) {
		this.discount = discount;
	}

	public Tax getTaxId() {
		return taxId;
	}

	public void setTaxId(Tax taxId) {
		this.taxId = taxId;
	}

	public Tax getDiscountId() {
		return discountId;
	}

	public void setDiscountId(Tax discountId) {
		this.discountId = discountId;
	}

	public Date getManufacturingDate() {
		return manufacturingDate;
	}

	public void setManufacturingDate(Date manufacturingDate) {
		this.manufacturingDate = manufacturingDate;
	}

	public Date getExpireDate() {
		return ExpireDate;
	}

	public void setExpireDate(Date expireDate) {
		ExpireDate = expireDate;
	}

	public String getBatchNumber() {
		return batchNumber;
	}

	public void setBatchNumber(String batchNumber) {
		this.batchNumber = batchNumber;
	}

	public String getBarCode() {
		return barCode;
	}

	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}

	public Long getStockIn() {
		return stockIn;
	}

	public void setStockIn(Long stockIn) {
		this.stockIn = stockIn;
	}

	public Long getStockOut() {
		return stockOut;
	}

	public void setStockOut(Long stockOut) {
		this.stockOut = stockOut;
	}

	public Long getCurrentQuantity() {
		return currentQuantity;
	}

	public void setCurrentQuantity(Long currentQuantity) {
		this.currentQuantity = currentQuantity;
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

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getSearchCodeStr() {
		return searchCodeStr;
	}

	public void setSearchCodeStr(String searchCodeStr) {
		this.searchCodeStr = searchCodeStr;
	}

	public String getQtyStr() {
		return qtyStr;
	}

	public void setQtyStr(String qtyStr) {
		this.qtyStr = qtyStr;
	}

	public String getPriceStr() {
		return priceStr;
	}

	public void setPriceStr(String priceStr) {
		this.priceStr = priceStr;
	}

	public Store getStoreId() {
		return storeId;
	}

	public void setStoreId(Store storeId) {
		this.storeId = storeId;
	}


	public String getMrpStr() {
		return mrpStr;
	}

	public void setMrpStr(String mrpStr) {
		this.mrpStr = mrpStr;
	}

	public String getTaxStr() {
		return taxStr;
	}

	public String getExpireDateStr() {
		return expireDateStr;
	}

	public void setExpireDateStr(String expireDateStr) {
		this.expireDateStr = expireDateStr;
	}

	public void setTaxStr(String taxStr) {
		this.taxStr = taxStr;
	}

	public String getDescriptionStr() {
		return descriptionStr;
	}

	public void setDescriptionStr(String descriptionStr) {
		this.descriptionStr = descriptionStr;
	}

	public String getCatagoryName() {
		return catagoryName;
	}

	public void setCatagoryName(String catagoryName) {
		this.catagoryName = catagoryName;
	}

	public String getParentCatagoryName() {
		return parentCatagoryName;
	}

	public void setParentCatagoryName(String parentCatagoryName) {
		this.parentCatagoryName = parentCatagoryName;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getProductIdStr() {
		return productIdStr;
	}

	public void setProductIdStr(String productIdStr) {
		this.productIdStr = productIdStr;
	}

}
