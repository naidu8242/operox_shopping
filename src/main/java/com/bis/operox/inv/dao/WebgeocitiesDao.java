package com.bis.operox.inv.dao;

import java.util.List;

import com.bis.operox.inv.dao.entity.Webgeocities;

/**
 * 
 * @author Prasad Salina
 *
 */
public interface WebgeocitiesDao {

	List<Webgeocities> getAllCitiesByStateCodeCountryCode(List<String> stateCode, String countryCode) throws Exception;
}
