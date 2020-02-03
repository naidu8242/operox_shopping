package com.bis.operox.inv.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.AnnotationConfigWebContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;

import com.bis.operox.WebAppConfig;
import com.bis.operox.inv.dao.entity.Countries;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = WebAppConfig.class, loader = AnnotationConfigWebContextLoader.class)
public class CountriesServiceTest {
	
	@Autowired
	CountriesService countriesService;
	private static final Logger logger = LoggerFactory.getLogger(CountriesServiceTest.class);
	
	@Test
	public void getCountriesByCountryName() throws Exception{
		String name = "India";
		Countries countries = countriesService.getCountriesByCountryName(name);
		logger.info("getCountriesByCountryName " + countries.getId());
	}
	
	@Test
	public void getAllCountries() throws Exception{
		List<Countries> countries = countriesService.getAllCountries();
		logger.info("getAllCountries " + countries.size());
	}
	
	@Test
	public void getAllStatesAndCitiesByCountryName() throws Exception{
		List<String> countries = countriesService.getAllStatesAndCitiesByCountryName("India");
		logger.info("getAllStatesAndCitiesByCountryName " + countries);
	}
}
