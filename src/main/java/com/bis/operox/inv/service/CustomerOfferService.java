package com.bis.operox.inv.service;

import java.util.List;

import com.bis.operox.inv.dao.entity.CustomerOffer;

/**
 * @author Ajith Matta
 *
 */
public interface CustomerOfferService {

	public CustomerOffer addCustomerOffer(CustomerOffer customerOffer) throws Exception;
	
	public CustomerOffer getCustomerOfferById(Long id) throws Exception;
	
	public List<CustomerOffer> getAllCustomerOffer() throws Exception;
	
	
}
