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
@Table(name = "STOCK_RETURNS_ITEMS", catalog = "operox")
@Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL, region = "stockReturnsItems")
public class StockReturnsItems {

	  
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false)
    private Long id;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "PRODUCT_ID")
    private Product product;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "STOCK_RETURNS_ID")
    private StockReturns stockReturns;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "RECEIVED_STOCK_ID")
    private ReceivedStock receivedStock;
	
	@Column(name = "QUANTITY")
	private String quantity;
	
	@Column(name = "PRODUCT_PRICE")
	private String productPrice;
	
	@Column(name = "BARCODE")
	private String barCode;

	@Column(name = "BATCH_ID")
	private String batchId;
	
	@Column(name = "AMOUNT")
	private String amount;
	
	@Column(name = "MANUFACTUREDATE")
	private Date manufactureDate;
	
	@Column(name = "EXPERIEDATE")
	private Date expirydate;
	
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

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public StockReturns getStockReturns() {
		return stockReturns;
	}

	public void setStockReturns(StockReturns stockReturns) {
		this.stockReturns = stockReturns;
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

	public String getBarCode() {
		return barCode;
	}

	public String getBatchId() {
		return batchId;
	}

	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}

	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public Date getManufactureDate() {
		return manufactureDate;
	}

	public void setManufactureDate(Date manufactureDate) {
		this.manufactureDate = manufactureDate;
	}

	public Date getExpirydate() {
		return expirydate;
	}

	public void setExpirydate(Date expirydate) {
		this.expirydate = expirydate;
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
