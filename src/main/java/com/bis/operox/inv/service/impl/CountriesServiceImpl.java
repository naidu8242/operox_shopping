package com.bis.operox.inv.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bis.operox.inv.dao.CountriesDao;
import com.bis.operox.inv.dao.entity.Countries;
import com.bis.operox.inv.service.CountriesService;

@Service
public class CountriesServiceImpl implements CountriesService{
	
	@Autowired
	CountriesDao countriesDao;
	
	private static final Logger logger = LoggerFactory.getLogger(CountriesServiceImpl.class);

	@Override
	public Countries getCountriesByCountryName(String name) throws Exception{
		logger.debug("In CountriesServiceImpl class...");
		return countriesDao.getCountriesByCountryName(name);
	}

	@Override
	public List<Countries> getAllCountries() throws Exception {
		logger.debug("In CountriesServiceImpl class getAllCountries method...");
		List<Countries> countriesList = countriesDao.getAllCountries();
		return countriesList;
	}
	
	@Override
	public List<Countries> getAllSourceLeadCountries() throws Exception {
		logger.debug("In CountriesServiceImpl class getAllSourceLeadCountries method...");
		List<Countries> countriesList = countriesDao.getAllSourceLeadCountries();
		return countriesList;
	}

	@Override
	public List<String> getAllStatesAndCitiesByCountryName(String countryName) throws Exception {
		logger.debug("In CountriesServiceImpl class getAllLocations method...");
		
		Countries countries = countriesDao.getCountriesByCountryName(countryName);
		
		List<String> countriesList = countriesDao.getAllStatesAndCitiesByCountryCode(countries.getCode2());
		return countriesList;
	}

}
