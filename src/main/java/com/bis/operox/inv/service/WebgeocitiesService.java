package com.bis.operox.inv.service;

import java.util.List;

import com.bis.operox.inv.dao.entity.Webgeocities;
/**
 * 
 * @author Prasad Salina
 *
 */

public interface WebgeocitiesService {

	
	List<Webgeocities> getAllCitiesByStateAndCountry(String stateName, String countryName) throws Exception;
	
}