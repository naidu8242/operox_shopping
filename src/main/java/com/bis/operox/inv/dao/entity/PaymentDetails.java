package com.bis.operox.inv.dao.entity;

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

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.bis.operox.inv.dao.enums.Payment_Mode;

/**
 * @author root
 *
 */
@Entity
@Table(name = "PAYMENT_DETAILS", catalog = "operox")
@Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL, region = "paymentDetails")
public class PaymentDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;
	
	@ManyToOne
    @JoinColumn(name="BILL_ID" )        
    private Bill billId;
	
	@Column(name = "CARD_TYPE")
	private String cardType;
	
	@Column(name="CARD_NUMBER")
	private String cardNumber;
	
	@Column(name="CARD_HOLDER")
	private String cardHolderName;
	
	@Column(name="NO_OF_PRODUCT")
	private Integer noOfProduct;
	
	@Column(name="TOTAL_AMOUNT")
	private Float totalAmount;
	
	@Column(name = "PAYMNET_MODE")
	@Enumerated(EnumType.STRING)
	private Payment_Mode PAYMNET_MODE;
	
	@Column(name="PAYMENT_CASH")
	private Integer paymentCash;
	
	@Column(name="PAYMENT_CHANGE")
	private Float paymentChange;
	
	@Column(name="GIFT_CARD_NUMBER")
	private String giftCardNumber;
	
	@Column(name="GIFT_CARD_AMOUNT")
	private Integer giftCardAmount;
	
	@Column(name="EXPIRE_DATE")
	private String expireDate;
	
	@Column(name="CVV_NUMBER")
	private Integer cvvNumber;
	
	@Column(name="COUPON1_AMOUNT")
	private String coupon1Amount;
	
	@Column(name="COUPON2_AMOUNT")
	private Float coupon2Amount;
	
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

	public Bill getBillId() {
		return billId;
	}

	public void setBillId(Bill billId) {
		this.billId = billId;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public Integer getNoOfProduct() {
		return noOfProduct;
	}

	public void setNoOfProduct(Integer noOfProduct) {
		this.noOfProduct = noOfProduct;
	}

	public Float getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Float totalAmount) {
		this.totalAmount = totalAmount;
	}


	public Payment_Mode getPAYMNET_MODE() {
		return PAYMNET_MODE;
	}

	public void setPAYMNET_MODE(Payment_Mode pAYMNET_MODE) {
		PAYMNET_MODE = pAYMNET_MODE;
	}

	public Integer getPaymentCash() {
		return paymentCash;
	}

	public void setPaymentCash(Integer paymentCash) {
		this.paymentCash = paymentCash;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	
	public String getCardHolderName() {
		return cardHolderName;
	}

	public void setCardHolderName(String cardHolderName) {
		this.cardHolderName = cardHolderName;
	}

	public String getGiftCardNumber() {
		return giftCardNumber;
	}

	public void setGiftCardNumber(String giftCardNumber) {
		this.giftCardNumber = giftCardNumber;
	}

	public String getCoupon1Amount() {
		return coupon1Amount;
	}

	public void setCoupon1Amount(String coupon1Amount) {
		this.coupon1Amount = coupon1Amount;
	}

	public Float getPaymentChange() {
		return paymentChange;
	}

	public void setPaymentChange(Float paymentChange) {
		this.paymentChange = paymentChange;
	}

	public Integer getGiftCardAmount() {
		return giftCardAmount;
	}

	public void setGiftCardAmount(Integer giftCardAmount) {
		this.giftCardAmount = giftCardAmount;
	}

	public String getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(String expireDate) {
		this.expireDate = expireDate;
	}

	public Integer getCvvNumber() {
		return cvvNumber;
	}

	public void setCvvNumber(Integer cvvNumber) {
		this.cvvNumber = cvvNumber;
	}

	public Float getCoupon2Amount() {
		return coupon2Amount;
	}

	public void setCoupon2Amount(Float coupon2Amount) {
		this.coupon2Amount = coupon2Amount;
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
