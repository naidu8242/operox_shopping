package com.bis.operox.inv.dao.impl;

import java.text.MessageFormat;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bis.operox.inv.dao.EmailNotificationDao;
import com.bis.operox.inv.dao.entity.EmailNotification;

/**
 * 
 * @author Laxman
 *
 */
@Repository
public class EmailNotificationDaoImpl implements EmailNotificationDao{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private String duplicateValue;
	
	private static final Logger logger = LoggerFactory.getLogger(EmailScenarioDaoImpl.class);

	/**
	 * This method for save the EmailNotification
	 */
	@Override
	@Transactional(rollbackFor = { Exception.class })
	public EmailNotification save(EmailNotification emailNotification) throws Exception {
		logger.debug("In EmailNotificationDaoImpl class save method");
		Session session = this.sessionFactory.getCurrentSession();
		try {
			if(emailNotification != null){
				session.saveOrUpdate(emailNotification);
			}
		}catch(Exception exception)
		 {
			StringBuilder message= new StringBuilder(MessageFormat.format(duplicateValue,"EmailNotification"));
			//throw new SourceLeadConstraintViolationException(message.toString() +exception.getCause().toString());
		 }
		
		return emailNotification;	
	}

	/**
	 * This method for get the EmailNotification by ID
	 */
	@Override
	@Transactional(rollbackFor = { Exception.class })
	public EmailNotification getById(Long emailNotificationd) throws Exception {
		logger.debug("In EmailNotificationDaoImpl class getById method");
		Session session = this.sessionFactory.getCurrentSession();
		EmailNotification emailNotification = session.get(EmailNotification.class, emailNotificationd);
        return session.get(EmailNotification.class, emailNotificationd);
	}

}
