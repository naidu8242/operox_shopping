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
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="NOTIFICATIONS")
@Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL,region="notifications")
public class Notifications {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="NOTIFICATION_SCENARIO_ID", nullable=false )	
	private EmailScenario notificationScenarioId;
	
	@ManyToOne
	@JoinColumn(name="NOTIFICATION_TO", nullable=false )	
	private User notificationTo;
	
	@ManyToOne
	@JoinColumn(name="NOTIFICATION_FORM", nullable=false )	
	private User notificationFrom;
	
	@Column(name="BODY", columnDefinition = "TEXT default NULL")
	private String body;
	
	@Column(name="MODE")
	private String mode;
	
	@Column(name="ACTION_LINK")
	private String actionLink;
	
	@Column(name="ORG_CODE")
	private String orgCode;
	
	@Column(name="LOCATION_CODE")
	private String locationCode;
	
	@DateTimeFormat(pattern="dd/MM/yyyy  HH:mm:ss")
    @Column(name = "CREATED_DATE", nullable = false)
	private Date createdDate;
	
	@Column(name="CREATED_BY")
	private String createdBy;
	
	@Column(name="NOTIFICATION_STATUS")
	private String notificationStatus;
	
	@Column(name="USER_NOTIFICATION_STATUS")
	private String userNotificationStatus;
	
	@Column(name="UUID")
	private String UUID;
	
	//to get only time from datetime
	@Transient
	private String time;
	
	//to get only date from datetime
	@Transient
	private String dateValue;
	
	//to calculate days
	@Transient
	private Long calDays;
	
	//to notification count
	@Transient
	private Long notificationCount;

	
	
	
	public String getNotificationStatus() {
		return notificationStatus;
	}

	public void setNotificationStatus(String notificationStatus) {
		this.notificationStatus = notificationStatus;
	}

	public Long getCalDays() {
		return calDays;
	}

	public void setCalDays(Long calDays) {
		this.calDays = calDays;
	}

	public String getDateValue() {
		return dateValue;
	}

	public void setDateValue(String dateValue) {
		this.dateValue = dateValue;
	}

	public Long getNotificationCount() {
		return notificationCount;
	}

	public void setNotificationCount(Long notificationCount) {
		this.notificationCount = notificationCount;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public EmailScenario getNotificationScenarioId() {
		return notificationScenarioId;
	}

	public void setNotificationScenarioId(EmailScenario notificationScenarioId) {
		this.notificationScenarioId = notificationScenarioId;
	}

	public User getNotificationTo() {
		return notificationTo;
	}

	public void setNotificationTo(User notificationTo) {
		this.notificationTo = notificationTo;
	}

	public User getNotificationFrom() {
		return notificationFrom;
	}

	public void setNotificationFrom(User notificationFrom) {
		this.notificationFrom = notificationFrom;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public String getActionLink() {
		return actionLink;
	}

	public void setActionLink(String actionLink) {
		this.actionLink = actionLink;
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

	public String getUserNotificationStatus() {
		return userNotificationStatus;
	}

	public void setUserNotificationStatus(String userNotificationStatus) {
		this.userNotificationStatus = userNotificationStatus;
	}

	public String getUUID() {
		return UUID;
	}

	public void setUUID(String uUID) {
		UUID = uUID;
	}

}
