package com.bis.operox.inv.dao;

import java.util.List;

import com.bis.operox.inv.dao.entity.CustomerOffer;

public interface CustomerOfferDao {

	public CustomerOffer addCustomerOffer(CustomerOffer customerOffer) throws Exception;
	
	public CustomerOffer getCustomerOfferById(Long id) throws Exception;
	
	public List<CustomerOffer> getAllCustomerOffer() throws Exception;

}