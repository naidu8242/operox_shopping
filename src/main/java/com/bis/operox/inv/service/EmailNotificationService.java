package com.bis.operox.inv.service;

import com.bis.operox.inv.dao.entity.EmailNotification;

/**
 * 
 * @author Laxman
 *
 */
public interface EmailNotificationService {

	EmailNotification save(EmailNotification emailNotification) throws Exception;

	EmailNotification getById(Long emailNotificationd) throws Exception;

}
