package com.bis.operox.production.dao.entity;

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
@Table(name = "QC_EXECUTION_RESULTS", catalog = "operox")
@Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL, region = "qcExecutionResults")
public class QCExecutionResults {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "QC_CHECK_LIST_REPORT_ID")
    private QCCheckListReport qcCheckListReport;
	
	@Column(name = "STATUS")
	private Integer status;
	
	@Column(name = "ACTUAL_VALUE")
	private String actualValue;
	
	@Column(name = "RESULT")
	private String result;
	
	@Column(name = "REMARKS", columnDefinition = "TEXT default NULL")
	private String remarks;
	
	@Column(name = "CREATED_DATE")
    private Date createdDate;
    
    @Column(name = "CREATED_BY")
    private String createdBy;
    
    @Column(name = "UPDATED_DATE")
    private Date updatedDate;
    
    @Column(name = "UPDATED_BY")
    private String updatedBy;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="QC_EXECUTION_ID")        
    private QCExecution qcExecutionid;
    
    @Transient
    private String howToCheck;
    
    @Transient
    private String whatToCheck;
    
    @Transient
    private String testDescription;
    
	public QCExecution getQcExecutionid() {
		return qcExecutionid;
	}

	public void setQcExecutionid(QCExecution qcExecutionid) {
		this.qcExecutionid = qcExecutionid;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public QCCheckListReport getQcCheckListReport() {
		return qcCheckListReport;
	}

	public void setQcCheckListReport(QCCheckListReport qcCheckListReport) {
		this.qcCheckListReport = qcCheckListReport;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getActualValue() {
		return actualValue;
	}

	public void setActualValue(String actualValue) {
		this.actualValue = actualValue;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
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

	public String getTestDescription() {
		return testDescription;
	}

	public void setTestDescription(String testDescription) {
		this.testDescription = testDescription;
	}
}
