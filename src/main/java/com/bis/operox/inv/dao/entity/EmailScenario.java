package com.bis.operox.inv.dao.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name="EMAIL_SCENARIO")
@Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL ,region="emailscenario")
public class EmailScenario {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID")
	private Long id;
	
	@Column(name="SCENARIO_NAME")
	private String scenarioName;
	
	@Column(name = "ENTITY_TYPE", nullable = false)
	private String entityType;
	
	@Column(name="UNIQUE_SCENARIO_NAME")
	private String uniqueScenarioName;
	
	@Column(name="DESCRIPTION", columnDefinition = "TEXT default NULL")
	private String description;
	
	@Column(name="SUBJECT", columnDefinition = "TEXT default NULL")
	private String subject;
	
	@Column(name="TEMPLATE_TYPE")
	private String templateType;
	
	@Column(name="SIGNATURE", columnDefinition = "TEXT default NULL")
	private String signature;
	
	@Column(name="BODY", columnDefinition = "TEXT default NULL")
	private String body;
	
	@Column(name="CALL_TO_ACTION_TEXT", columnDefinition = "TEXT default NULL")
	private String callToActionText;
	
	@Column(name="BUTTON_TEXT", columnDefinition = "TEXT default NULL")
	private String buttonText;
	
	@Column(name="IS_TEMPLATE_MODIFIED")
	private String isTemplateModified;
	
	@Column(name="CREATED_DATE")
	private Date createdDate;
	
	@Column(name="CREATED_BY")
	private String createdBy;
	
	@Column(name="USE_LOGO")
	private String useLogo;
	
	@Column(name="ORG_CODE")
	private String orgCode;
	
	@Column(name="LOCATION_CODE")
	private String locationCode;
	
	@Column(name="DISCLAIMER_TEXT", columnDefinition = "TEXT default NULL")
	private String disclaimerText;
	
	@Transient
	private String UUID;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getScenarioName() {
		return scenarioName;
	}

	public void setScenarioName(String scenarioName) {
		this.scenarioName = scenarioName;
	}

	public String getEntityType() {
		return entityType;
	}

	public void setEntityType(String entityType) {
		this.entityType = entityType;
	}

	public String getUniqueScenarioName() {
		return uniqueScenarioName;
	}

	public void setUniqueScenarioName(String uniqueScenarioName) {
		this.uniqueScenarioName = uniqueScenarioName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getTemplateType() {
		return templateType;
	}

	public void setTemplateType(String templateType) {
		this.templateType = templateType;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getCallToActionText() {
		return callToActionText;
	}

	public void setCallToActionText(String callToActionText) {
		this.callToActionText = callToActionText;
	}

	public String getButtonText() {
		return buttonText;
	}

	public void setButtonText(String buttonText) {
		this.buttonText = buttonText;
	}

	public String getIsTemplateModified() {
		return isTemplateModified;
	}

	public void setIsTemplateModified(String isTemplateModified) {
		this.isTemplateModified = isTemplateModified;
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

	public String getUseLogo() {
		return useLogo;
	}

	public void setUseLogo(String useLogo) {
		this.useLogo = useLogo;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getLocationCode() {
		return locationCode;
	}

	public void setLocationCode(String locationCode) {
		this.locationCode = locationCode;
	}

	public String getDisclaimerText() {
		return disclaimerText;
	}

	public void setDisclaimerText(String disclaimerText) {
		this.disclaimerText = disclaimerText;
	}

	public String getUUID() {
		return UUID;
	}

	public void setUUID(String uUID) {
		UUID = uUID;
	}
}
