package com.bis.operox.inv.dao;

import java.util.List;

import com.bis.operox.inv.dao.entity.Address;

public interface AddressDao {
	

	Address addAddress(Address address);
	
	Address getAddressById(Long id);

	List<Address> addressList();


}