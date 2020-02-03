package com.bis.operox.inv.dao.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.bis.operox.inv.dao.enums.Bill_Status;
import com.bis.operox.production.dao.entity.Currency;

@Entity
@Table(name = "BILL", catalog = "operox")
@Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL, region = "bill")
public class Bill {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
	private Long id;
	
	@ManyToOne
    @JoinColumn(name="CUSTOMER_ID")        
    private Customer customerId;
	
	@ManyToOne
    @JoinColumn(name="STORE_ID" )        
    private Store storeId;
	
	@ManyToOne
    @JoinColumn(name="COUNTER_ID" )        
    private Counter counterId;
	
	
	@ManyToOne
    @JoinColumn(name="CASHIER_USER_ID" )        
    private User cashierUserId;
	
	@Column(name="BILL_NUMBER")
	private String billNumber;
	
	@Column(name="NUMBER_OF_PRODUCTS")
	private Integer numberOfProducts;
	
	@Column(name="TOTAL_AMOUNT")
	private Float totalAmount;
	
	@ManyToOne
    @JoinColumn(name="CURRENCY_ID" ) 
	private Currency currency;
	
	@Column(name="TAX")
	private Float tax;
	
	@Column(name="GROSS_AMOUNT")
	private Float grossAmount;	
	
	@Column(name="DISCOUNT")
	private Float discount;	
	
	@Column(name="DISCOUNT_ON_INVOICE")
	private Float discountOnInvoice;
	
	
	@Column(name="BILL_DATE")
	private Date billDate;
	
	@Column(name="BILL_AMOUNT")
	private Float billAmount;
	
	@Column(name="ACTUAL_BILL_AMOUNT")
	private Float actualBillAmount;
	
	@Column(name = "BILL_STATUS")
	@Enumerated(EnumType.STRING)
	private Bill_Status billStatus;
	
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
	private String displayBillDate;
	
	@Transient
	private String orderDate;
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Customer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Customer customerId) {
		this.customerId = customerId;
	}

	public Store getStoreId() {
		return storeId;
	}

	public void setStoreId(Store storeId) {
		this.storeId = storeId;
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

	public Counter getCounterId() {
		return counterId;
	}

	public void setCounterId(Counter counterId) {
		this.counterId = counterId;
	}

	public User getCashierUserId() {
		return cashierUserId;
	}

	public void setCashierUserId(User cashierUserId) {
		this.cashierUserId = cashierUserId;
	}

	public String getBillNumber() {
		return billNumber;
	}

	public void setBillNumber(String billNumber) {
		this.billNumber = billNumber;
	}

	public Integer getNumberOfProducts() {
		return numberOfProducts;
	}

	public void setNumberOfProducts(Integer numberOfProducts) {
		this.numberOfProducts = numberOfProducts;
	}

	public Float getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Float totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Float getTax() {
		return tax;
	}

	public void setTax(Float tax) {
		this.tax = tax;
	}

	public Float getGrossAmount() {
		return grossAmount;
	}

	public void setGrossAmount(Float grossAmount) {
		this.grossAmount = grossAmount;
	}

	public Float getDiscount() {
		return discount;
	}

	public void setDiscount(Float discount) {
		this.discount = discount;
	}

	public Float getBillAmount() {
		return billAmount;
	}

	public void setBillAmount(Float billAmount) {
		this.billAmount = billAmount;
	}

	public Bill_Status getBillStatus() {
		return billStatus;
	}

	public void setBillStatus(Bill_Status billStatus) {
		this.billStatus = billStatus;
	}

	public Float getDiscountOnInvoice() {
		return discountOnInvoice;
	}

	public void setDiscountOnInvoice(Float discountOnInvoice) {
		this.discountOnInvoice = discountOnInvoice;
	}

	public Date getBillDate() {
		return billDate;
	}

	public void setBillDate(Date billDate) {
		this.billDate = billDate;
	}
	
   public String getDisplayBillDate() {
		
        if(billDate != null) {
			//HH converts hour in 24 hours format (0-23), day calculation
			SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
			
			Date d1 = null;
			Date d2 =  null;
	
			try {
				d1 = dateFormat.parse(dateFormat.format(billDate));
				d2 = dateFormat.parse(dateFormat.format(new Date()));
	
				//in milliseconds
				long diff = d2.getTime() - d1.getTime();
	
				long diffMinutes = diff / (60 * 1000) % 60;
				long diffHours = diff / (60 * 60 * 1000) % 24;
				long diffDays = diff / (24 * 60 * 60 * 1000);
				if(diffDays >= 1){
					SimpleDateFormat dayFormat = new SimpleDateFormat("EEE, MMM d yyyy");
					displayBillDate = dayFormat.format(createdDate);
					
				}
				else{
					if(diffHours >= 1){
						displayBillDate = Long.toString(diffHours)+" Hours Ago";
					}
					else{
						displayBillDate = Long.toString(diffMinutes)+" Minutes Ago";
					}
				}
	
	
			} catch (Exception e) {
				e.printStackTrace();
			}
        }
		return displayBillDate;
	}

	public Currency getCurrency() {
		return currency;
	}
	
	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	public Float getActualBillAmount() {
		return actualBillAmount;
	}

	public void setActualBillAmount(Float actualBillAmount) {
		this.actualBillAmount = actualBillAmount;
	}
	
	public String getOrderDate() {
		return orderDate;
	}
	
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
		
	
}
