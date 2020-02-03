package com.bis.operox.inv.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.RandomStringUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bis.operox.inv.dao.CustomerDao;
import com.bis.operox.inv.dao.entity.Address;
import com.bis.operox.inv.dao.entity.Customer;
import com.bis.operox.inv.dao.entity.Store;
import com.bis.operox.inv.dao.entity.User;
import com.bis.operox.inv.service.AddressService;
import com.bis.operox.inv.service.CustomerService;
import com.bis.operox.util.CommonUtil;

@Service class CustomerServiceImpl implements CustomerService{

	@Autowired
	private CustomerDao customerDao;

	@Autowired
	private CommonUtil commonUtil;

	@Autowired
	private AddressService addressService;

	@Override
	public Customer addCustomer(JSONObject jsonObj, User user) throws Exception {
		// setting Address data
		Address address = null;
		Address address1 = null;
		Customer customer = null;
		if(jsonObj.has("customerId") && jsonObj.getString("customerId") != null && !"".equals(jsonObj.getString("customerId"))){
			customer = customerDao.getCustomerById(Long.parseLong(jsonObj.getString("customerId")));
			if(customer.getAddressId() != null){
				address = addressService.getAddressById(customer.getAddressId().getId());
				address.setUpdatedBy(user.getUserCode());
				address.setUpdatedDate(new Date());
			}else{
				address = new Address();
				address.setCreatedDate(new Date());
				address.setCreatedBy(user.getUserCode());
			}
			if (user.getUserCode() != null) {
				customer.setUpdatedBy(user.getUserCode());
			}
			customer.setUpdatedDate(new Date());
		}else{
			customer = new Customer();
			address = new Address();
			address.setCreatedDate(new Date());
			address.setCreatedBy(user.getUserCode());
			customer.setUpdatedBy(user.getUserCode());
			customer.setUpdatedDate(new Date());
			customer.setCreatedDate(new Date());
			customer.setCustomeruid(RandomStringUtils.randomNumeric(6));
		}
		
		if (jsonObj.has("country") && jsonObj.getString("country") != null && !"".equals(jsonObj.getString("country"))) {
			address.setCountry(jsonObj.getString("country"));
		}else{
			address.setCountry("");
		}
		
		if (jsonObj.has("state") && jsonObj.getString("state") != null  && !"".equals(jsonObj.getString("state"))) {
			address.setState(jsonObj.getString("state"));
		}else{
			address.setState("");
		}
		
		if (jsonObj.has("city") && jsonObj.getString("city") != null && !"".equals(jsonObj.getString("city"))) {
			address.setCity(jsonObj.getString("city"));
		}else{
			address.setCity("");
		}
		
		if (!"".equals(jsonObj.getString("address"))
				&& jsonObj.getString("address") != null) {
			address.setAddress(jsonObj.getString("address"));
		}else{
			address.setAddress("");
		}
		
		if (!"".equals(jsonObj.getString("zipcode"))
				&& jsonObj.getString("zipcode") != null) {
			address.setZipcode(jsonObj.getString("zipcode"));
		}else{
			address.setZipcode("");
		}
		
		if (address.getCountry() != null || address.getState() != null
				|| address.getCity() != null || address.getZipcode() != null) {
			address1 = addressService.addAddress(address);
		}
		// setting customer data
		if (!"".equals(jsonObj.getString("customerName"))
				&& jsonObj.getString("customerName") != null) {
			customer.setCustomerName(jsonObj.getString("customerName"));
		}else{
			customer.setCustomerName("");
		}
		
		if (!"".equals(jsonObj.getString("email"))
				&& jsonObj.getString("email") != null) {
			customer.setEmail(jsonObj.getString("email"));
		}else{
			customer.setEmail("");
		}
		
		if (!"".equals(jsonObj.getString("phone"))
				&& jsonObj.getString("phone") != null) {
			customer.setPhone(jsonObj.getString("phone"));
		}
		
		if (!"".equals(jsonObj.getString("datebirth"))
				&& jsonObj.getString("datebirth") != null) {
			customer.setDob(commonUtil.getDateFormat(jsonObj
					.getString("datebirth")));
		}else{
			customer.setDob(null);
		}
		if (jsonObj.has("customerType")
				&& jsonObj.getString("customerType") != null) {
			customer.setCustomerType(jsonObj.getString("customerType"));
		}else{
			customer.setCustomerType("");
		}
		
		if (!"".equals(jsonObj.getString("tinNumber"))
				&& jsonObj.getString("tinNumber") != null) {
			customer.setCustomerTinNumber(jsonObj.getString("tinNumber"));
		}else{
			customer.setCustomerTinNumber("");
		}
		
		if (user.getUserCode() != null) {
			customer.setCreatedBy(user.getUserCode());
		}
		if (address1 != null) {
			customer.setAddressId(address);
		}
		customer.setCompanyId(user.getStore().getCompany());
		customer.setStatus(1);
		return customerDao.addCustomer(customer);
	}

	@Override
	public List<Customer> listCustomer(Long companyId) {
		return this.customerDao.listCustomer(companyId);
	}

	@Override
	public Customer getCustomerById(Long customerId) {
		return this.customerDao.getCustomerById(customerId);
	}

	@Override
	public Customer deleteCustomerById(Customer customer) {
		return this.customerDao.deleteCustomerById(customer);
	}

	@Override
	public Boolean getCustomerByPhone(String phone) {
		return customerDao.getCustomerByPhone(phone);
	}

	@Override
	public Customer getCustomerByPhoneNumber(String phone) {
		return this.customerDao.getCustomerByPhoneNumber(phone);
	}

	@Override
	public List<Customer> listCustomerForWorkOrder(Long companyId) {
		return customerDao.listCustomerForWorkOrder(companyId);
	}
	

}
