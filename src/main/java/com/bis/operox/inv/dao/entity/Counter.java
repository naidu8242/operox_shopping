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
@Table(name = "COUNTER", catalog = "operox")
@Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL, region = "counter")
public class Counter {
	
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name = "ID", nullable = false)
	    private Long id;
 	
		@Column(name = "COUNTER_NAME")
	    private String counterName;
		
		@Column(name = "COUNTER_TYPE")
	    private String counterType;
		
		@ManyToOne(fetch = FetchType.EAGER)
		@JoinColumn(name = "STORE_ID")
		private Store store;
		
		@Column(name = "DESCRIPTION", columnDefinition = "TEXT default NULL")
	    private String description;
		
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
	    
	    @Transient
	    private String storeName;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getCounterName() {
			return counterName;
		}

		public void setCounterName(String counterName) {
			this.counterName = counterName;
		}

		public String getCounterType() {
			return counterType;
		}

		public void setCounterType(String counterType) {
			this.counterType = counterType;
		}

		public Store getStore() {
			return store;
		}

		public void setStore(Store store) {
			this.store = store;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
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

		public String getStoreName() {
			return storeName;
		}

		public void setStoreName(String storeName) {
			this.storeName = storeName;
		}
		
}
