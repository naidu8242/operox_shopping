package com.bis.operox.inv.service;

import java.util.List;

import com.bis.operox.inv.dao.entity.States;

public interface StatesService {

	
	List<States> getAllStatesByCountry(String countryName) throws Exception;
	
}