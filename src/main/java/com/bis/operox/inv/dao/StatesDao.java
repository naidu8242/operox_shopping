package com.bis.operox.inv.dao;

import java.util.List;

import com.bis.operox.inv.dao.entity.States;

/**
 * 
 * @author Prasad Salina
 *
 */
public interface StatesDao {

	List<States> getAllStatesByCountryCode(String countryCode) throws Exception;
	
	States getStatesByStateName(String name) throws Exception;
	
	List<String> getStatesCodesByStateName(String name) throws Exception;
}
