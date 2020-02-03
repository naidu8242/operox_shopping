package com.bis.operox.inv.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bis.operox.inv.dao.AddressDao;
import com.bis.operox.inv.dao.entity.Address;
import com.bis.operox.inv.dao.entity.Customer;

@Repository
public class AddressDaoImpl implements AddressDao{

	@Autowired
	private SessionFactory sessionFactory;
	
	private static final Logger logger = LoggerFactory.getLogger(AddressDaoImpl.class);
	
	/**
	 * This method is using for save or update 
	 */
	@Override
	@Transactional(rollbackFor = { Exception.class })	
	public Address addAddress(Address address) {
		Session session = this.sessionFactory.getCurrentSession();
		session.saveOrUpdate(address);
		logger.info("Address saved successfully, Address Details="+address);
		return address;
	}

	/**
	 * This method is using for list by id 
	 */
	@Override
	@Transactional(rollbackFor = { Exception.class })	
	public Address getAddressById(Long id) {
        Session session = this.sessionFactory.getCurrentSession();	
        String query = "from Address addr where addr.id = :addressId";
	     List<Address> addressList = session.createQuery(query).setLong("addressId", id).setCacheable(true).list();
	    return addressList.get(0);
	}

	/**
	 * This method is using for Address list 
	 */
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(rollbackFor = { Exception.class })	
	public List<Address> addressList() {
		Session session = this.sessionFactory.getCurrentSession();
		List<Address> addressList = session.createQuery("from Address").list();
		return addressList;
	}
}