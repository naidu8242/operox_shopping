package com.bis.operox.inv.dao.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * @author Ajith Matta.
 *
 */
@Entity
@Table(name = "DEPARTMENT", catalog = "operox")
@Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL, region = "department")
public class Department {
	
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name = "ID", nullable = false)
	    private Long id;
 	
		@Column(name = "DEPARTMENT_NAME")
	    private String departmentName;
		
		@Column(name = "DEPARTMENT_ID")
	    private String departmentId;
		
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

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getDepartmentName() {
			return departmentName;
		}

		public void setDepartmentName(String departmentName) {
			this.departmentName = departmentName;
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

		public String getDepartmentId() {
			return departmentId;
		}

		public void setDepartmentId(String departmentId) {
			this.departmentId = departmentId;
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
		
	    
}
