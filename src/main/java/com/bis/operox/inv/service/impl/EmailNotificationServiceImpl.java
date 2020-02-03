package com.bis.operox.inv.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bis.operox.inv.dao.EmailNotificationDao;
import com.bis.operox.inv.dao.entity.EmailNotification;
import com.bis.operox.inv.service.EmailNotificationService;

/**
 * 
 * @author Laxman
 *
 */

@Service
public class EmailNotificationServiceImpl implements EmailNotificationService{

	
private static final Logger logger = LoggerFactory.getLogger(EmailNotificationServiceImpl.class);
	
	@Autowired
	private EmailNotificationDao emailNotificationDao;
	
	@Override
	public EmailNotification save(EmailNotification emailNotification) throws Exception {
		logger.debug("In EmailNotificationServiceImpl class save method");
		return emailNotificationDao.save(emailNotification);
	}

	@Override
	public EmailNotification getById(Long emailNotificationd) throws Exception {
		logger.debug("In EmailNotificationServiceImpl class getById method...");
		return emailNotificationDao.getById(emailNotificationd);
	}

}
