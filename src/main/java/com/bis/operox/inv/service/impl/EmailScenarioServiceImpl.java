package com.bis.operox.inv.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bis.operox.inv.dao.EmailScenarioDao;
import com.bis.operox.inv.dao.entity.EmailScenario;
import com.bis.operox.inv.service.EmailScenarioService;
/**
 * 
 * @author Prasad salina
 *
 */
@Service
public class EmailScenarioServiceImpl implements EmailScenarioService {
	
private static final Logger logger = LoggerFactory.getLogger(EmailScenarioServiceImpl.class);
	
	@Autowired
	private EmailScenarioDao emailScenarioDao;

	@Override
	public EmailScenario getEmailScenarioByUniqueNameAndEntityTypeAndOrgCodeAndLocationsCode(String uniqueScenarioName,String entityType,
			String orgCode, String locationsCode) throws Exception{
		logger.debug("In EmailScenarioServiceImpl class getEmailScenarioByUniqueNameAndEntityTypeAndOrgCodeAndLocationsCode method");
		EmailScenario emailScenario = emailScenarioDao.getEmailScenarioByUniqueNameAndEntityTypeAndOrgCodeAndLocationsCode(uniqueScenarioName, entityType, orgCode, locationsCode);
		return emailScenario;
	}

	@Override
	public EmailScenario save(EmailScenario scenario) throws Exception {
		logger.debug("In EmailScenarioServiceImpl class save method");
		return emailScenarioDao.save(scenario);
	}

	@Override
	public EmailScenario getEmailScenarioByUniqueName(String uniqueScenarioName, String entityType) throws Exception {
		logger.debug("In EmailScenarioServiceImpl class getEmailScenarioByUniqueName method");
		return emailScenarioDao.getEmailScenarioByUniqueName(uniqueScenarioName, entityType);
	}

}
