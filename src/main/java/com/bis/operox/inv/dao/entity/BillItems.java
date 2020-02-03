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
@Table(name = "BILL_ITEMS", catalog = "operox")
@Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL, region = "billItems")
public class BillItems {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "BILL_ID")
	private Bill billId;

	@ManyToOne
	@JoinColumn(name = "PRODUCT_ID")
	private Product productId;
	
	@ManyToOne
	@JoinColumn(name = "PRODUCT_STOCK_ID")
	private ProductStock productStockId;

	@ManyToOne
	@JoinColumn(name = "PRODUCT_STOCK_OFFER_ID")
	private ProductStockOffer productStockOfferdId;

	@Column(name = "QUANTITY")
	private String quantity;

	@Column(name = "PRICE")
	private String price;

	@Column(name="TOTAL_AMOUNT")
	private Float productTotalAmount;
	
	@Column(name="TAX")
	private String tax;
	
	@Column(name="DISCOUNT")
	private String discount;	
	
	@Column(name = "CURRENCY")
	private String currency;

	@Column(name = "STATUS")
	private int status;
	
	@Column(name = "CREATED_DATE")
    private Date createdDate;

	@Column(name = "CREATED_BY")
	private String createdBy;

	@Column(name = "UPDATED_DATE")
	private Date updatedDate;

	@Column(name = "UPDATED_BY")
	private String updateBy;
	
	
	//Analytics fields
	@Transient
    private String barCode;
	
	@Transient
	private String productName;
	
	@Transient
	private String productCode;
	
	@Transient
	private Integer noOfQuantity;
	
	@Transient
	private Float billAmount;
	
	@Transient
	private Float totalAmount;
	
	@Transient
	private Long maxQuantity;
	
	@Transient
	private String orderDate;

	@Transient
	private String orderNumber;
	
	@Transient
	private Integer numberOfProducts;
	
	@Transient
	private Float orderTotalAmmount;
	
	@Transient
	private String orderDiscount;
	
	@Transient
	private String orderBillAmount;
	
	
	//Analytics fields
	
	public String getBarCode() {
		return barCode;
	}

	public void setBarCode(String barCode) {
		this.barCode = barCode;
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

	public Integer getNoOfQuantity() {
		return noOfQuantity;
	}

	public void setNoOfQuantity(Integer noOfQuantity) {
		this.noOfQuantity = noOfQuantity;
	}

	public Float getBillAmount() {
		return billAmount;
	}

	public void setBillAmount(Float billAmount) {
		this.billAmount = billAmount;
	}

	public Float getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Float totalAmount) {
		this.totalAmount = totalAmount;
	}
	
	//Analytics fields

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Bill getBillId() {
		return billId;
	}

	public void setBillId(Bill billId) {
		this.billId = billId;
	}

	public Product getProductId() {
		return productId;
	}

	public void setProductId(Product productId) {
		this.productId = productId;
	}

	public ProductStockOffer getProductStockOfferdId() {
		return productStockOfferdId;
	}

	public void setProductStockOfferdId(ProductStockOffer productStockOfferdId) {
		this.productStockOfferdId = productStockOfferdId;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getTax() {
		return tax;
	}

	public void setTax(String tax) {
		this.tax = tax;
	}

	public String getDiscount() {
		return discount;
	}

	public void setDiscount(String discount) {
		this.discount = discount;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
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

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public ProductStock getProductStockId() {
		return productStockId;
	}

	public void setProductStockId(ProductStock productStockId) {
		this.productStockId = productStockId;
	}

	public Float getProductTotalAmount() {
		return productTotalAmount;
	}

	public void setProductTotalAmount(Float productTotalAmount) {
		this.productTotalAmount = productTotalAmount;
	}

	public Long getMaxQuantity() {
		return maxQuantity;
	}

	public void setMaxQuantity(Long maxQuantity) {
		this.maxQuantity = maxQuantity;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}


	public Integer getNumberOfProducts() {
		return numberOfProducts;
	}

	public void setNumberOfProducts(Integer numberOfProducts) {
		this.numberOfProducts = numberOfProducts;
	}

	public Float getOrderTotalAmmount() {
		return orderTotalAmmount;
	}

	public void setOrderTotalAmmount(Float orderTotalAmmount) {
		this.orderTotalAmmount = orderTotalAmmount;
	}

	public String getOrderDiscount() {
		return orderDiscount;
	}

	public void setOrderDiscount(String orderDiscount) {
		this.orderDiscount = orderDiscount;
	}

	public String getOrderBillAmount() {
		return orderBillAmount;
	}

	public void setOrderBillAmount(String orderBillAmount) {
		this.orderBillAmount = orderBillAmount;
	}
	
}
