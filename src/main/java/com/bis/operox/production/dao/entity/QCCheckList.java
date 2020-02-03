package com.bis.operox.production.dao.entity;

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

import com.bis.operox.inv.dao.entity.Product;
import com.bis.operox.inv.dao.entity.Store;
import com.bis.operox.production.dao.entity.RawMaterial;

@Entity
@Table(name = "QC_CHECK_LIST", catalog = "operox")
@Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL, region = "qcCheckList")
public class QCCheckList {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="RAW_MATERIAL_ID")        
    private RawMaterial rawMaterial;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="PRODUCT_ID")        
    private Product productid;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "WORK_ORDER_ID")
    private WorkOrder workOrder;
	
	@Column(name = "HOW_TO_CHECK", columnDefinition = "TEXT")
	private String howToCheck;
		
	@Column(name = "WHAT_TO_CHECK", columnDefinition = "TEXT")
	private String whatToCheck;
	
	@Column(name = "IS_CHECKED")
    private String isChecked;
	
	@Column(name = "CHECK_NAME")
    private String checkName;
	
	@Column(name = "CHECK_LIST_TYPE")
    private String checkListType;
	
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

    @Column(name = "FILE_CONTENT_TYPE")
    private String fileContentType;
	
	@Column(name = "FILE_NAME", columnDefinition = "TEXT default NULL")
	private String fileName;
	
	@Column(name = "DOC_FILE",columnDefinition="mediumblob")
	@Lob
	private  byte[] docFile;
	
	@Column(name = "DESCRIPTION")
	private String description;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "STORE_ID")
    private Store store;
	
	@Transient
    Integer noOfTests;
	
	@Transient
	String nameOfMaterial;
    
	
	
	public String getNameOfMaterial() {
		return nameOfMaterial;
	}

	public void setNameOfMaterial(String nameOfMaterial) {
		this.nameOfMaterial = nameOfMaterial;
	}

	public Product getProductid() {
		return productid;
	}

	public void setProductid(Product productid) {
		this.productid = productid;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	public String getCheckName() {
		return checkName;
	}

	public void setCheckName(String checkName) {
		this.checkName = checkName;
	}

	public String getFileContentType() {
		return fileContentType;
	}

	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public byte[] getDocFile() {
		return docFile;
	}

	public void setDocFile(byte[] docFile) {
		this.docFile = docFile;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public RawMaterial getRawMaterial() {
		return rawMaterial;
	}

	public void setRawMaterial(RawMaterial rawMaterial) {
		this.rawMaterial = rawMaterial;
	}

	public WorkOrder getWorkOrder() {
		return workOrder;
	}

	public void setWorkOrder(WorkOrder workOrder) {
		this.workOrder = workOrder;
	}

	public String getHowToCheck() {
		return howToCheck;
	}

	public void setHowToCheck(String howToCheck) {
		this.howToCheck = howToCheck;
	}

	public String getWhatToCheck() {
		return whatToCheck;
	}

	public void setWhatToCheck(String whatToCheck) {
		this.whatToCheck = whatToCheck;
	}

	public String getIsChecked() {
		return isChecked;
	}

	public void setIsChecked(String isChecked) {
		this.isChecked = isChecked;
	}

	public String getCheckListType() {
		return checkListType;
	}

	public void setCheckListType(String checkListType) {
		this.checkListType = checkListType;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getNoOfTests() {
		return noOfTests;
	}

	public void setNoOfTests(Integer noOfTests) {
		this.noOfTests = noOfTests;
	}
}
