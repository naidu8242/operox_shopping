package com.bis.operox.inv.dao.impl;

import java.text.MessageFormat;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bis.operox.inv.dao.EmailScenarioDao;
import com.bis.operox.inv.dao.entity.EmailScenario;

/**
 * 
 * @author Laxman
 *
 */
@Repository
public class EmailScenarioDaoImpl implements EmailScenarioDao{
	
	@Autowired
	private SessionFactory sessionFactory;
	private String duplicateValue;
	
	private static final Logger logger = LoggerFactory.getLogger(EmailScenarioDaoImpl.class);

	/**
	 * This method for get the EmailScenario
	 */
	@Override
	@Transactional(rollbackFor = { Exception.class })
	public EmailScenario getEmailScenarioByUniqueNameAndEntityTypeAndOrgCodeAndLocationsCode(String uniqueScenarioName,String entityType,
			String orgCode, String locationCode) throws Exception {
logger.debug("In EmailScenarioDaoImpl class getEmailScenarioByUniqueNameAndOrgCodeAndLocationsCode method");
		
		Session session = this.sessionFactory.getCurrentSession();
		
		String query = "from EmailScenario es where es.uniqueScenarioName = :uniqueScenarioName and es.entityType = :entityType and es.locationCode = :locationCode and es.orgCode =:orgCode";
		 
		List<EmailScenario> emailScenariosList =  session.createQuery(query.toString()) .setString("uniqueScenarioName", uniqueScenarioName)
				                                                                        .setString("entityType", entityType)
																	                    .setString("locationCode", locationCode)
																	                    .setString("orgCode", orgCode).list();
		EmailScenario emailScenario = null;
		if(emailScenariosList != null && !emailScenariosList.isEmpty())
		{
			emailScenario = (EmailScenario) emailScenariosList.iterator().next();
		}
		return emailScenario;
	}

	/**
	 * This method for save the EmailScenario
	 */
	@Override
	@Transactional(rollbackFor = { Exception.class })
	public EmailScenario save(EmailScenario scenario) throws Exception {
		logger.debug("In EmailScenarioDaoImpl class save method");
		Session session = this.sessionFactory.getCurrentSession();
		try {
			if(scenario != null){
				session.save(scenario);
				logger.debug("EmailScenario Added Successfully");
			}
		}catch(Exception exception)
		{
			StringBuilder message= new StringBuilder(MessageFormat.format(duplicateValue,"EmailScenario"));
			//throw new SourceLeadConstraintViolationException(message.toString() +exception.getCause().toString());
		}
		
		return scenario;	
	}

	/**
	 * This method for get the EmailScenario by using uniqueScenarioName
	 */
	@Override
	@Transactional(rollbackFor = { Exception.class })
	public EmailScenario getEmailScenarioByUniqueName(String uniqueScenarioName, String entityType) throws Exception{
		logger.debug("In EmailScenarioDaoImpl class getEmailScenarioByUniqueName method");
		Session session = this.sessionFactory.getCurrentSession();
		String query = "from EmailScenario es where es.uniqueScenarioName = :uniqueScenarioName and es.entityType = :entityType";
		List<EmailScenario> emailScenariosList =  session.createQuery(query.toString())
				                                         .setString("uniqueScenarioName", uniqueScenarioName)
				                                         .setString("entityType", entityType).list();
		EmailScenario emailScenario = null;
		if(emailScenariosList != null && !emailScenariosList.isEmpty())
		{
			emailScenario = (EmailScenario) emailScenariosList.iterator().next();
		}
		return emailScenario;
	
	}

}
