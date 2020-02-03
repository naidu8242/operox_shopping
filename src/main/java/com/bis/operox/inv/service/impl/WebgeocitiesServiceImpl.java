package com.bis.operox.inv.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bis.operox.inv.dao.CountriesDao;
import com.bis.operox.inv.dao.StatesDao;
import com.bis.operox.inv.dao.WebgeocitiesDao;
import com.bis.operox.inv.dao.entity.Countries;
import com.bis.operox.inv.dao.entity.Webgeocities;
import com.bis.operox.inv.service.WebgeocitiesService;

/**
 * 
 * @author Prasad Salina
 *
 */
@Service
public class WebgeocitiesServiceImpl implements WebgeocitiesService{
	
	@Autowired
	CountriesDao countriesDao;
	
	@Autowired
	StatesDao statesDao;
	
	@Autowired
	WebgeocitiesDao webgeocitiesDao;
	
	private static final Logger logger = LoggerFactory.getLogger(WebgeocitiesServiceImpl.class);

	@Override
	public List<Webgeocities> getAllCitiesByStateAndCountry(String stateName, String countryName) throws Exception {
		Countries countries = countriesDao.getCountriesByCountryName(countryName);
		List<String> stateCodes = statesDao.getStatesCodesByStateName(stateName);
		List<Webgeocities> citiesList = webgeocitiesDao.getAllCitiesByStateCodeCountryCode(stateCodes, countries.getCode2());
		return citiesList;
	}



}
