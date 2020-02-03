package com.bis.operox.inv.dao.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "ROLE", catalog = "operox")
@Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL, region = "role")
public class Role {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID", nullable = false)
	private Long id;
	
	@Column(name = "ROLE_NAME")
	private String roleName;
	
	@Column(name = "DISPLAY_NAME")
	private String displayName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	
}

/*@Entity
@Table(name="ORG")
@Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL,region="org")
public class Org {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID", nullable = false)
	private Long id;
	
	@Column(name = "ORG_NAME")
	private String orgName;
	
	@Column(name = "WEBSITE")
	private String webSite;
	
	@Column(name = "ORG_TYPE")
	private String orgType;
	
	@Column(name = "ORG_CODE")
	private String orgCode;
	
	@Column(name = "STATUS")
	@Enumerated(EnumType.STRING)
	private Status status;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "PRIMARY_CONTACT_USER_ID")
    private User user;
	
	@Column(name = "CREATED_DATE")
	private Date createdDate;
	
	@Column(name = "CREATED_BY")
    private String createdBy;
	
	@Column(name = "UPDATED_DATE")
	private Date updatedDate;
	
	@Column(name = "UPDATED_BY")
	private String updatedBy;
	
	@Column(name = "DOC_FILE",columnDefinition="mediumblob")
	@Lob
	private  byte[] docFile;
		
	@Column(name = "FILE_CONTENT_TYPE")
	private String fileContentType;
	
	@Column(name = "ORG_DESCRIPTION")
	@Type(type="text")
	private String orgDescription;
	
	
	@Column(name = "ATS")
	@Enumerated(EnumType.STRING)
	private Status ats;
	
	@Column(name = "VMS")
	@Enumerated(EnumType.STRING)
	private Status vms;
	
	@Column(name = "TIMESHEET")
	@Enumerated(EnumType.STRING)
	private Status timeSheet;
	
	@OneToMany(mappedBy="org",cascade = CascadeType.ALL,fetch=FetchType.LAZY)
    @Fetch(FetchMode.SELECT)
    private Set<OrgLocations> orgLocationsList;*/
	