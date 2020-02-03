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
@Table(name = "PRICE", catalog = "operox")
@Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL, region = "price")
public class Price {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PRODUCT_STOCK_ID")
   	private ProductStock productStockId;
	
	@Column(name = "MRP")
    private String mrp;
	
	@Column(name = "SUPPLIER_PRICE")
    private String supplierPrice;
	
	@Column(name = "WAREHOUSE_PRICE")
    private String warehousePrice;
	
	@Column(name = "STORE_PRICE")
    private String storePrice;
	
	@Column(name = "CURRANCY")
    private String currancy;
	
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ProductStock getProductStockId() {
		return productStockId;
	}

	public void setProductStockId(ProductStock productStockId) {
		this.productStockId = productStockId;
	}

	public String getMrp() {
		return mrp;
	}

	public void setMrp(String mrp) {
		this.mrp = mrp;
	}

	public String getSupplierPrice() {
		return supplierPrice;
	}

	public void setSupplierPrice(String supplierPrice) {
		this.supplierPrice = supplierPrice;
	}

	public String getWarehousePrice() {
		return warehousePrice;
	}

	public void setWarehousePrice(String warehousePrice) {
		this.warehousePrice = warehousePrice;
	}

	public String getStorePrice() {
		return storePrice;
	}

	public void setStorePrice(String storePrice) {
		this.storePrice = storePrice;
	}

	public String getCurrancy() {
		return currancy;
	}

	public void setCurrancy(String currancy) {
		this.currancy = currancy;
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
