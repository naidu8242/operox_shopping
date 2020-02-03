package com.bis.operox.inv.dao;

import java.util.List;

import com.bis.operox.inv.dao.entity.Customer;

public interface CustomerDao {
	
	public Customer addCustomer(Customer customer);
	

	public Customer getCustomerById(int id);
	
	public Customer getCustomerByPhoneNumber(String phone);

	public Customer getCustomerById(Long customerId);


	public List<Customer> listCustomer(Long companyId);

	public Customer deleteCustomerById(Customer customer);

	public Boolean getCustomerByPhone(String phone);
	
	public List<Customer> listCustomerForWorkOrder(Long companyId);
}