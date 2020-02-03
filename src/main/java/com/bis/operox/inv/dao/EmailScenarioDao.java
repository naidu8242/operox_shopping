package com.bis.operox.inv.dao;

import com.bis.operox.inv.dao.entity.EmailScenario;

public interface EmailScenarioDao {
	
	EmailScenario getEmailScenarioByUniqueNameAndEntityTypeAndOrgCodeAndLocationsCode(String uniqueScenarioName, String entityType, String orgCode, String locationsCode) throws Exception;
 
	EmailScenario save(EmailScenario scenario) throws Exception;

	EmailScenario getEmailScenarioByUniqueName(String uniqueScenarioName, String entityType) throws Exception;
}
