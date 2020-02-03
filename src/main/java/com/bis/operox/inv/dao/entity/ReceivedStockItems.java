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

/**
 * 
 * @author Sammeta David Raju
 *
 */
 
@Entity
@Table(name = "RECEIVED_STOCK_ITEMS", catalog = "operox")
@Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL, region = "receivedStockItems")
public class ReceivedStockItems {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false)
    private Long id;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "PRODUCT_ID")
    private Product product;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "RECEIVED_STOCK_ID")
    private ReceivedStock receivedStock;
	
	@Column(name = "QUANTITY")
	private String quantity;

	@Column(name = "PRODUCT_PRICE")
	private String productPrice;
	
	@JoinColumn(name = "DISCOUNT")
	private String discount;
	
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "TAX_ID")
	private Tax taxId;
	
	
	@Column(name = "MRP")
	private String mrp;

	@Column(name = "BAR_CODE")
	private String barCode;
	
	@Column(name = "STATUS")
	private Integer status;
	
	@Column(name = "BATCH_ID")
	private String batchId;
	
	@Column(name = "MANFATUREDATE")
	private Date manuFatureDate;
	
	@Column(name = "EXPIREDATE")
	private Date expiredate;
	
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

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public ReceivedStock getReceivedStock() {
		return receivedStock;
	}

	public void setReceivedStock(ReceivedStock receivedStock) {
		this.receivedStock = receivedStock;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}


	public String getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(String productPrice) {
		this.productPrice = productPrice;
	}


	public String getDiscount() {
		return discount;
	}

	public void setDiscount(String discount) {
		this.discount = discount;
	}

	public Tax getTaxId() {
		return taxId;
	}

	public void setTaxId(Tax taxId) {
		this.taxId = taxId;
	}

	public String getMrp() {
		return mrp;
	}

	public void setMrp(String mrp) {
		this.mrp = mrp;
	}
	public String getBarCode() {
		return barCode;
	}

	public void setBarCode(String barCode) {
		this.barCode = barCode;
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

	public String getBatchId() {
		return batchId;
	}

	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}

	public Date getManuFatureDate() {
		return manuFatureDate;
	}

	public void setManuFatureDate(Date manuFatureDate) {
		this.manuFatureDate = manuFatureDate;
	}

	public Date getExpiredate() {
		return expiredate;
	}

	public void setExpiredate(Date expiredate) {
		this.expiredate = expiredate;
	}
	
}
