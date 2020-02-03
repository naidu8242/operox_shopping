package com.bis.operox.inv.service;

import java.util.List;

import com.bis.operox.inv.dao.entity.Address;

public interface AddressService {

	
    Address addAddress(Address address);
	
	Address getAddressById(Long id);

	List<Address> addressList();


}
