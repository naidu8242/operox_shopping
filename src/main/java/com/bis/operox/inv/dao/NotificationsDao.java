package com.bis.operox.inv.dao;

import java.util.List;

import com.bis.operox.inv.dao.entity.Notifications;

public interface NotificationsDao {

	Notifications saveNotification(Notifications notification) throws Exception;

	 Integer getNotificationsCountByUserId(Long userId, String orgCode, String locationCode) ;

	List<Notifications> getNotificationsListByUserId(Long notificationTo, String orgCode, String locationCode);	
	
	Integer updateNotificationStatusAsRead(Long userId, String orgCode, String locationCode);
	
	Integer updateNotificationDeleteStatus(Long notificationId, String orgCode, String locationCode);

	List<Notifications> getAllNotificationsListByUserId(Long userId, String orgCode, String locationCode);
	
	Notifications getNotificationsById(Long Id) throws Exception;
	
	Notifications getNotificationsByUUID(String UUID) throws Exception;
}
