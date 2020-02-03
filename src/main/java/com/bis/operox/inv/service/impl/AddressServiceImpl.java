package com.bis.operox.inv.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.bis.operox.inv.dao.AddressDao;
import com.bis.operox.inv.dao.entity.Address;
import com.bis.operox.inv.service.AddressService;

@Service
@Repository
public class AddressServiceImpl implements AddressService{

	@Autowired
	private AddressDao addressDao;
	
	@Override
	public Address addAddress(Address address) {
		return addressDao.addAddress(address);
	}

	@Override
	public Address getAddressById(Long id) {
		return addressDao.getAddressById(id);
	}

	@Override
	public List<Address> addressList() {
		return this.addressDao.addressList();
	}

}
