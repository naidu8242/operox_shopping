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

@Entity
@Table(name = "QC_EXECUTION", catalog = "operox")
@Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL, region = "qcExecution")
public class QCExecution {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="RAW_MATERIAL_ID")        
    private RawMaterial rawMaterial;
	
	@ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="PRODUCT_ID" )        
    private Product product;
	
	@ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="WORK_ORDER_ID" )        
    private WorkOrder workOrder;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "QC_CHECK_LIST_ID")
    private QCCheckList qcCheckList;
	
	@Column(name = "START_DATE")
    private Date startDate;
	
	@Column(name = "END_DATE")
    private Date endDate;
	
	@Column(name = "TOTAL_QUANTITY")
    private Integer totalQuantity;
	
	@Column(name = "STATUS")
	private Integer status;
	
	@Column(name = "NO_OF_PASSED_UNITS")
	private Integer noOfPassedUnits;
	
	@Column(name = "NO_OF_FAILED_UNITS")
	private Integer noOfFailedUnits;
	
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
	
	@Column(name = "COMMENT",columnDefinition = "TEXT default NULL")
	private String comment;

	@Column(name = "QC_STATUS")
    private String qcStatus;
	
	@Column(name = "QC_EXECUTION_TYPE")
    private String qcExecutionType;
	
	@Transient
	private String rawMaterialName;
	
	@Transient
	private String qcCheckListName;
	
	@Transient
	private String productName;
	
	@Transient
	private String startDateStr;
	
	@Transient
	private String endDateStr;
	
	@Transient
	private Long checkListId;
	
	@Transient
	private Long productId;
	
	public String getQcExecutionType() {
		return qcExecutionType;
	}

	public void setQcExecutionType(String qcExecutionType) {
		this.qcExecutionType = qcExecutionType;
	}

	public WorkOrder getWorkOrder() {
		return workOrder;
	}

	public void setWorkOrder(WorkOrder workOrder) {
		this.workOrder = workOrder;
	}

	public String getQcStatus() {
		return qcStatus;
	}

	public void setQcStatus(String qcStatus) {
		this.qcStatus = qcStatus;
	}

	public QCCheckList getQcCheckList() {
		return qcCheckList;
	}

	public void setQcCheckList(QCCheckList qcCheckList) {
		this.qcCheckList = qcCheckList;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Integer getTotalQuantity() {
		return totalQuantity;
	}

	public void setTotalQuantity(Integer totalQuantity) {
		this.totalQuantity = totalQuantity;
	}

	public Integer getNoOfPassedUnits() {
		return noOfPassedUnits;
	}

	public void setNoOfPassedUnits(Integer noOfPassedUnits) {
		this.noOfPassedUnits = noOfPassedUnits;
	}

	public Integer getNoOfFailedUnits() {
		return noOfFailedUnits;
	}

	public void setNoOfFailedUnits(Integer noOfFailedUnits) {
		this.noOfFailedUnits = noOfFailedUnits;
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

	public String getRawMaterialName() {
		return rawMaterialName;
	}

	public void setRawMaterialName(String rawMaterialName) {
		this.rawMaterialName = rawMaterialName;
	}

	public String getQcCheckListName() {
		return qcCheckListName;
	}

	public void setQcCheckListName(String qcCheckListName) {
		this.qcCheckListName = qcCheckListName;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getStartDateStr() {
		return startDateStr;
	}

	public void setStartDateStr(String startDateStr) {
		this.startDateStr = startDateStr;
	}

	public String getEndDateStr() {
		return endDateStr;
	}

	public void setEndDateStr(String endDateStr) {
		this.endDateStr = endDateStr;
	}

	public Long getCheckListId() {
		return checkListId;
	}

	public void setCheckListId(Long checkListId) {
		this.checkListId = checkListId;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}
}
