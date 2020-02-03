package com.bis.operox.inv.service;

import com.bis.operox.inv.dao.entity.EmailScenario;

/**
 * 
 * @author Laxman
 *
 */
public interface EmailScenarioService {
	
	EmailScenario getEmailScenarioByUniqueNameAndEntityTypeAndOrgCodeAndLocationsCode(String uniqueScenarioName,String entityType, String orgCode, String locationCode) throws Exception;
	
	EmailScenario save(EmailScenario scenario) throws Exception;
	
	EmailScenario getEmailScenarioByUniqueName(String uniqueScenarioName, String entityType) throws Exception;

}