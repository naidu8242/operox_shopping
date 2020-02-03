package com.bis.operox.inv.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bis.operox.inv.dao.CustomerOfferDao;
import com.bis.operox.inv.dao.entity.CustomerOffer;
import com.bis.operox.inv.service.CustomerOfferService;

/**
 * @author Ajith Matta
 * @Date 23/09/2016
 *
 */
@Service
public class CustomerOfferServiceImpl implements CustomerOfferService{
	
	@Autowired
	private CustomerOfferDao customerOfferDao;
	

	@Override
	public CustomerOffer addCustomerOffer(CustomerOffer customerOffer) throws Exception{
		return customerOfferDao.addCustomerOffer(customerOffer);
	}


	@Override
	public CustomerOffer getCustomerOfferById(Long id) throws Exception {
		return this.customerOfferDao.getCustomerOfferById(id);
	}


	@Override
	public List<CustomerOffer> getAllCustomerOffer() throws Exception {
		return customerOfferDao.getAllCustomerOffer();
	}

	
	
}
