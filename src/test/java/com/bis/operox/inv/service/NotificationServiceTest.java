package com.bis.operox.inv.service;

import java.util.Date;
import java.util.List;

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
import com.bis.operox.inv.dao.entity.EmailScenario;
import com.bis.operox.inv.dao.entity.Notifications;
import com.bis.operox.inv.dao.entity.User;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = WebAppConfig.class, loader = AnnotationConfigWebContextLoader.class)
public class NotificationServiceTest {
	
	@Autowired
	NotificationsService notificationsService;

	private static final Logger logger = LoggerFactory.getLogger(NotificationServiceTest.class);

	
	@Test
	public void saveNotification() throws Exception{
		Notifications notifications = new Notifications();
		EmailScenario emailScenario = new EmailScenario();
		emailScenario.setId(1L);
		notifications.setNotificationScenarioId(emailScenario);
		User user = new User();

		user.setId(1L);

		notifications.setNotificationTo(user);
		notifications.setNotificationFrom(user);
		notifications.setBody("this is used for testing service");
		notifications.setMode("unread");
		notifications.setActionLink("link");
		notifications.setOrgCode("hangonn");
		notifications.setLocationCode("in.sourcelead.com");
		notifications.setCreatedDate(new Date());
		notifications.setCreatedBy("Source");
		notifications.setNotificationStatus("1");
		notifications = notificationsService.saveNotification(notifications);
		logger.info("saveNotification " + notifications.getId());
	}
	
	@Test
	public void getNotificationsCountByUserId() throws Exception{
		Notifications notifications = new Notifications();
		notifications.setOrgCode("hangonn");
		notifications.setLocationCode("in.sourcelead.com");
		User user = new User();

		user.setId(1L);
		Integer notificationscount = notificationsService.getNotificationsCountByUserId(user.getId(), notifications.getOrgCode(), notifications.getLocationCode());
		logger.info("getNotificationsCountByUserId " + notificationscount);
	}
	
	@Test
	public void getNotificationsListByUserId() throws Exception{
		Notifications notifications = new Notifications();
		notifications.setOrgCode("QAeD");
		notifications.setLocationCode("QAeDIN");
		User user = new User();

		user.setId(1L);

		notifications.setNotificationTo(user);
		List<Notifications> list = notificationsService.getNotificationsListByUserId(notifications.getNotificationTo().getId(), notifications.getOrgCode(), notifications.getLocationCode());
		logger.info("getNotificationsListByUserId " + list.size());
	}
	
	@Test
	public void updateNotificationStatusAsRead() throws Exception{
		Notifications notifications = new Notifications();
		notifications.setOrgCode("QAeD");
		notifications.setLocationCode("QAeDIN");
		User user = new User();

		user.setId(1L);

		Integer notificationscount = notificationsService.updateNotificationStatusAsRead(user.getId(), notifications.getOrgCode(), notifications.getLocationCode());
		logger.info("updateNotificationStatusAsRead " + notificationscount);
	}
	
	@Test
	public void updateNotificationDeleteStatus() throws Exception{
		Notifications notifications = new Notifications();
		notifications.setOrgCode("hangonn");
		notifications.setId(1L);
		notifications.setLocationCode("in.sourcelead.com");
		User user = new User();

		user.setId(1L);

		notifications.setNotificationTo(user);
		Integer notificationscount = notificationsService.updateNotificationDeleteStatus(notifications.getId(), notifications.getOrgCode(), notifications.getLocationCode());
		logger.info("updateNotificationDeleteStatus " + notificationscount);
	}
	
	@Test
	public void getAllNotificationsListByUserId() throws Exception{
		Notifications notifications = new Notifications();
		notifications.setOrgCode("QAeD");
		notifications.setLocationCode("QAeDIN");
		User user = new User();

		user.setId(1L);

		List<Notifications> list = notificationsService.getAllNotificationsListByUserId(user.getId(), notifications.getOrgCode(), notifications.getLocationCode());
		logger.info("getAllNotificationsListByUserId " + list.size());
	}
	
	@Test
	public void getNotificationsById() throws Exception{
		Notifications notifications = notificationsService.getNotificationsById(6L);
		logger.info("retrieved notification Mode is... " +notifications.getMode());
	}
}
