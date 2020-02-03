package com.bis.operox.inv.dao.entity;

import java.util.Date;
import java.util.LinkedHashMap;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.StringUtils;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.bis.operox.inv.dao.enums.Status;

@Entity
@Table(name = "USER", catalog = "operox")
@Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL, region = "user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID",nullable = false)
	private Long id;
	
	@Column(name ="EMPLOYEE_ID")
	private String employeeId;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="STORE_ID")        
    private Store store;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="ADDRESS_ID")        
    private Address address;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="ROLE_ID")        
    private Role role;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="DEPARTMENT_ID")        
    private Department department;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="SHIFT_ID")        
    private Shift shift;
	
	@Column(name ="FIRST_NAME")
	private String firstName;
    
    @Column(name ="LAST_NAME")
	private String lastName;
    
    @Column(name ="EMAIL")
	private String email;
    
    @Column(name ="DOJ")
	private Date doj;
    
    
    @Column(name = "USER_IMAGE",columnDefinition="mediumblob")
  	@Lob
  	private  byte[] userImage;
  		
  	@Column(name = "FILE_CONTENT_TYPE")
  	private String fileContentType;
    
  	@Column(name = "MAIL_SENT")
	@Enumerated(EnumType.STRING)
	private Status mailSent;
  	
  	@Column(name ="LEAVES")
   	private Integer leaves;
    
  	@Column(name = "USER_STATE")
	@Enumerated(EnumType.STRING)
	private Status userState;
  	
    @Column(name ="USERNAME")
	private String username;
    
    @Column(name = "PASSWORD", nullable = false)
	private String password;
    
    @Column(name ="PHONE")
   	private String phone;
    
    @Column(name ="STATUS")
   	private Integer status;
    
    @Column(name ="USER_CODE")
   	private String userCode;
    
    @Column(name ="CREATED_DATE")
   	private Date createdDate;
    
    @Column(name ="CREATED_BY")
	private String createdBy;
    
    @Column(name ="UPDATED_DATE")
   	private Date updatedDate;
    
    @Column(name ="UPDATED_BY")
	private String updatedBy;
    
    @Column(name ="COMPENSATION")
   	private String compensation;
    
    @Column(name ="COMPENSATATION_TYPE")
   	private String compensatationType;
    
    @Column(name ="PAYSLIP_FROM_DATE")
   	private Date payslipFromDate;
    
    @Column(name ="PAYSLIP_TO_DATE")
   	private Date payslipToDate;
    
    @Column(name ="GENDER")
   	private String gender;
    
    @Column(name ="VERIFICATION_CODE")
   	private String verificationCode;
    
    @Transient
    private String location;
    
    @Transient
    private String roleName;
    
    @Transient
    private String storeName;
    
    @Transient
    private String billNumber;
    
    @Transient
    private String billingDate;
    
    @Transient
    private String fullName;
    
    @Transient
    private String storeAddress;
    
    @Transient
    private String taxValue;
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
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

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public Date getDoj() {
		return doj;
	}

	public void setDoj(Date doj) {
		this.doj = doj;
	}

	public String getCompensation() {
		return compensation;
	}

	public void setCompensation(String compensation) {
		this.compensation = compensation;
	}

	public Shift getShift() {
		return shift;
	}

	public void setShift(Shift shift) {
		this.shift = shift;
	}

	public byte[] getUserImage() {
		return userImage;
	}

	public void setUserImage(byte[] userImage) {
		this.userImage = userImage;
	}

	public String getFileContentType() {
		return fileContentType;
	}

	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}


	public String getBillNumber() {
		return billNumber;
	}

	public void setBillNumber(String billNumber) {
		this.billNumber = billNumber;
	}

	public String getBillingDate() {
		return billingDate;
	}

	public void setBillingDate(String billingDate) {
		this.billingDate = billingDate;
	}

	 public String getFullName() {
        StringBuilder sb = new StringBuilder();
        sb.append(firstName);
        if(lastName != null && !lastName.isEmpty()){
          sb.append(" ").append(lastName);
        }
        return sb.toString();
    }

	public String getStoreAddress() {
		return storeAddress;
	}

	public void setStoreAddress(String storeAddress) {
		this.storeAddress = storeAddress;
	}

	public Status getUserState() {
		return userState;
	}

	public void setUserState(Status userState) {
		this.userState = userState;
	}

	public Status getMailSent() {
		return mailSent;
	}

	public void setMailSent(Status mailSent) {
		this.mailSent = mailSent;
	}


	public String getTaxValue() {
		return taxValue;
	}

	public void setTaxValue(String taxValue) {
		this.taxValue = taxValue;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	
	public String getCompensatationType() {
		return compensatationType;
	}

	public void setCompensatationType(String compensatationType) {
		this.compensatationType = compensatationType;
	}

	public Integer getLeaves() {
		return leaves;
	}

	public void setLeaves(Integer leaves) {
		this.leaves = leaves;
	}

	public Date getPayslipFromDate() {
		return payslipFromDate;
	}

	public void setPayslipFromDate(Date payslipFromDate) {
		this.payslipFromDate = payslipFromDate;
	}

	public Date getPayslipToDate() {
		return payslipToDate;
	}

	public void setPayslipToDate(Date payslipToDate) {
		this.payslipToDate = payslipToDate;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getVerificationCode() {
		return verificationCode;
	}

	public void setVerificationCode(String verificationCode) {
		this.verificationCode = verificationCode;
	}
	
}
