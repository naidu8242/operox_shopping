package com.bis.operox.inv.dao;

import java.util.List;

import com.bis.operox.inv.dao.entity.Countries;

public interface CountriesDao {

	public Countries getCountriesByCountryName(String name) throws Exception;

	public List<Countries> getAllCountries() throws Exception;
	
	/**To get Sourcelead Countries(Australia, France, India, Singapore, United Kingdom, United States)
	 * @author Prasad Salina
	 * @return
	 * @throws Exception
	 */
	public List<Countries> getAllSourceLeadCountries() throws Exception;

	public List<String> getAllStatesAndCitiesByCountryCode(String countryCode) throws Exception;
}
