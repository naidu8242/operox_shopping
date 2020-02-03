package com.bis.operox.inv.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bis.operox.inv.dao.CountriesDao;
import com.bis.operox.inv.dao.StatesDao;
import com.bis.operox.inv.dao.entity.Countries;
import com.bis.operox.inv.dao.entity.States;
import com.bis.operox.inv.service.StatesService;

@Service
public class StatesServiceImpl implements StatesService{
	
	@Autowired
	CountriesDao countriesDao;
	
	@Autowired
	StatesDao statesDao;
	
	private static final Logger logger = LoggerFactory.getLogger(StatesServiceImpl.class);

	@Override
	public List<States> getAllStatesByCountry(String countryName) throws Exception {
		Countries countries = countriesDao.getCountriesByCountryName(countryName);
		List<States> statesList = statesDao.getAllStatesByCountryCode(countries.getCode2());
		return statesList;
	}



}
