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

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "QC_CHECK_LIST_REPORT", catalog = "operox")
@Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL, region = "qcCheckListReport")
public class QCCheckListReport {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "QC_CHECK_LIST_ID")
    private QCCheckList qcCheckList;
	
	@Column(name = "IS_CHECKED")
    private String isChecked;
	
	@Column(name = "HOW_TO_CHECK", columnDefinition = "TEXT")
	private String howToCheck;
		
	@Column(name = "WHAT_TO_CHECK", columnDefinition = "TEXT")
	private String whatToCheck;
	
	@Column(name = "TOTAL_UNITS")
	private Integer totalUnits;
	
	@Column(name = "TOTAL_UNITS_PASSED")
	private Integer totalUnitsPassed;
	
	@Column(name = "TOTAL_UNITS_FAILED")
	private Integer toalUnitsFailed;
	
	@Column(name = "CHECKED_BY")
    private String checkedBy;
	
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

    @Column(name = "TEST_DESCRIPTION", columnDefinition = "TEXT")
    private String testDescription;
	
	
	
    
	public String getTestDescription() {
		return testDescription;
	}

	public void setTestDescription(String testDescription) {
		this.testDescription = testDescription;
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

	public QCCheckList getQcCheckList() {
		return qcCheckList;
	}

	public void setQcCheckList(QCCheckList qcCheckList) {
		this.qcCheckList = qcCheckList;
	}

	public String getIsChecked() {
		return isChecked;
	}

	public void setIsChecked(String isChecked) {
		this.isChecked = isChecked;
	}

	public Integer getTotalUnits() {
		return totalUnits;
	}

	public void setTotalUnits(Integer totalUnits) {
		this.totalUnits = totalUnits;
	}

	public Integer getTotalUnitsPassed() {
		return totalUnitsPassed;
	}

	public void setTotalUnitsPassed(Integer totalUnitsPassed) {
		this.totalUnitsPassed = totalUnitsPassed;
	}

	public Integer getToalUnitsFailed() {
		return toalUnitsFailed;
	}

	public void setToalUnitsFailed(Integer toalUnitsFailed) {
		this.toalUnitsFailed = toalUnitsFailed;
	}

	public String getCheckedBy() {
		return checkedBy;
	}

	public void setCheckedBy(String checkedBy) {
		this.checkedBy = checkedBy;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}
