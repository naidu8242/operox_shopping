package com.bis.operox.inv.service;

import java.util.List;

import com.bis.operox.inv.dao.entity.Countries;

public interface CountriesService {

	public Countries getCountriesByCountryName(String name) throws Exception;
	
	List<Countries> getAllCountries() throws Exception;
	
	/**To get Sourcelead Countries(Australia, France, India, Singapore, United Kingdom, United States)
	 * @author Prasad Salina
	 * @return
	 * @throws Exception
	 */
	public List<Countries> getAllSourceLeadCountries() throws Exception;
	
	List<String>  getAllStatesAndCitiesByCountryName(String countryName) throws Exception;
	
}
