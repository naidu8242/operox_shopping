package com.bis.operox.inv.service;

import java.util.List;

import org.json.JSONObject;

import com.bis.operox.inv.dao.entity.Customer;
import com.bis.operox.inv.dao.entity.User;

public interface CustomerService {

	public Customer addCustomer(JSONObject jsonObj, User user) throws Exception;
	
	public Customer getCustomerById(Long customerId);
	
	public Customer getCustomerByPhoneNumber(String phone);
	
	public List<Customer> listCustomer(Long companyId);

	public Customer deleteCustomerById(Customer customer);

	public Boolean getCustomerByPhone(String phone);
	
	public List<Customer> listCustomerForWorkOrder(Long companyId);

}
