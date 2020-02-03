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
@Table(name = "SUPPLIER", catalog = "operox")
@Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL, region = "supplier")
public class Supplier {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false)
	private Long id;
	
	@Column(name= "SUPPLIER_NAME")
	private String name;
	
	@Column(name = "SUPPLIER_UID")
	private String supplieruid;
	
	 @ManyToOne(fetch = FetchType.EAGER)
	 @JoinColumn(name = "ADDRESS_ID")
	 private Address address;
	
	@Column(name = "EMAIL")
	private String email;
	
	@Column(name = "SUPPLIER_CONATCT_NAME")
	private String supplierContactName;
	
	@Column(name = "SUPPLIER_CONATCT_EMAIL")
	private String supplierContactEmail;
	
	@Column(name = "SUPPLIER_CONATCT_PHONE")
	private String supplierContactPhone;
	
	@Column(name = "STATUS")
	private Integer status;
	
	@Column(name = "TIN")
	private String tin;
	
	@Column(name = "CREATED_DATE")
    private Date createdDate;
	
	@Column(name = "CREATED_BY")
	private String createdBy;
	
	@Column(name = "UPDATED_DATE")
	private Date updatedDate;
	
	@Column(name = "UPDATED_BY")
	private String updatedBy;
	
	@Transient
	private String updatedDateStr;
	
	@Transient
	private String createdDateStr;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSupplieruid() {
		return supplieruid;
	}

	public void setSupplieruid(String supplieruid) {
		this.supplieruid = supplieruid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSupplierContactName() {
		return supplierContactName;
	}

	public void setSupplierContactName(String supplierContactName) {
		this.supplierContactName = supplierContactName;
	}

	public String getSupplierContactEmail() {
		return supplierContactEmail;
	}

	public void setSupplierContactEmail(String supplierContactEmail) {
		this.supplierContactEmail = supplierContactEmail;
	}

	public String getSupplierContactPhone() {
		return supplierContactPhone;
	}

	public void setSupplierContactPhone(String supplierContactPhone) {
		this.supplierContactPhone = supplierContactPhone;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getTin() {
		return tin;
	}

	public void setTin(String tin) {
		this.tin = tin;
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

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getUpdatedDateStr() {
		return updatedDateStr;
	}

	public void setUpdatedDateStr(String updatedDateStr) {
		this.updatedDateStr = updatedDateStr;
	}

	public String getCreatedDateStr() {
		return createdDateStr;
	}

	public void setCreatedDateStr(String createdDateStr) {
		this.createdDateStr = createdDateStr;
	}
	
	

}