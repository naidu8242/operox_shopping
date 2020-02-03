package com.bis.operox.inv.dao;

import com.bis.operox.inv.dao.entity.EmailNotification;

public interface EmailNotificationDao {
	
	EmailNotification save(EmailNotification emailNotification) throws Exception;

	EmailNotification getById(Long emailNotificationd) throws Exception;

}
