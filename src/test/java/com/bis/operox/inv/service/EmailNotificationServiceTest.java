package com.bis.operox.inv.service;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.AnnotationConfigWebContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;

import com.bis.operox.WebAppConfig;
import com.bis.operox.inv.dao.entity.EmailNotification;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = WebAppConfig.class, loader = AnnotationConfigWebContextLoader.class)
public class EmailNotificationServiceTest {
	
	@Autowired
	EmailNotificationService emailNotificationService;
	private static final Logger logger = LoggerFactory.getLogger(EmailNotificationServiceTest.class); 
	
	@Test
	public void save() throws Exception{
		EmailNotification emailnotification = new EmailNotification();
		emailnotification.setId(2L);
		emailnotification.setEmailTo("raj@gamail.com");
		emailnotification.setEmailFrom("info@hangonn.com");
		emailnotification.setSubject("This is test Message");
		emailnotification.setBody("This is test Message");
		emailnotification.setDeliveryStatus("Y");
		emailnotification.setCreatedDate(new Date());
		emailnotification.setCreatedBy("Source");
		emailnotification = emailNotificationService.save(emailnotification);
		logger.info("save successfully " + emailnotification.getId());
	}
	
	@Test
	public void getById() throws Exception{
		
		EmailNotification emailnotification = new EmailNotification();
		emailnotification.setId(1L);
		emailnotification = emailNotificationService.getById(emailnotification.getId());
		if(emailnotification != null)
		{
		logger.info("getById" + emailnotification.getCreatedBy());
	}
	}
}
