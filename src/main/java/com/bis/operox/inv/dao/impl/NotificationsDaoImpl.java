package com.bis.operox.inv.dao.impl;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bis.operox.inv.dao.NotificationsDao;
import com.bis.operox.inv.dao.entity.Notifications;
import com.bis.operox.constants.Constants;
@Repository
public class NotificationsDaoImpl implements NotificationsDao{

	@Autowired
	private SessionFactory sessionFactory;
	
	private String duplicateValue;
	
	private static final Logger logger = LoggerFactory.getLogger(NotificationsDaoImpl.class);
	
	/**
	 * This method for save Notification
	 */
	@Override
	@Transactional(rollbackFor = { Exception.class })
	public Notifications saveNotification(Notifications notification) throws Exception{
		logger.debug("In NotificationsDaoImpl class save method");
		Session session = this.sessionFactory.getCurrentSession();
		try{
			if(notification != null){
				session.saveOrUpdate(notification);
			}
		}catch(Exception exception){
			StringBuilder message= new StringBuilder(MessageFormat.format(duplicateValue,"Notifications"));
			//throw new SourceLeadConstraintViolationException(message.toString() +exception.getCause().toString());
		}
		
		return notification;
	}

	/**
	 * This method for get notifications count by userId
	 */
	@Override
	@Transactional(rollbackFor = { Exception.class })
	public Integer getNotificationsCountByUserId(Long userId, String orgCode, String locationCode) {
		Session session =  this.sessionFactory.getCurrentSession();
		String mode = Constants.NOTIFICATION_UNREAD;
		String notificationStatus = Constants.NOTIFICATION_NOT_DELETED;
		String query = "select count(*)  from Notifications nf where nf.notificationTo = :userId  and nf.orgCode = :orgCode and nf.locationCode = :locationCode and nf.mode =:mode and nf.notificationStatus =:notificationStatus ";
		List<Long> notificationList = (List<Long>)session.createQuery(query).setLong("userId", userId)
																	.setString("mode", mode)
																	.setString("notificationStatus", notificationStatus)
				   													.setString("orgCode", orgCode)
				   													.setString("locationCode", locationCode).setCacheable(true).list();
		Integer notificationCount = null;
		
			if(notificationList != null && !notificationList.isEmpty()){
				notificationCount = notificationList.get(0).intValue();
			}
		return notificationCount;
	}


	/**
	 * This method for get notifications list by userId
	 */
	@Override
	@Transactional(rollbackFor = { Exception.class })
	public List<Notifications> getNotificationsListByUserId(Long notificationTo, String orgCode, String locationCode) {
		
		Session session =  this.sessionFactory.getCurrentSession();
		String mode = Constants.NOTIFICATION_UNREAD;
		String notificationStatus = Constants.NOTIFICATION_NOT_DELETED;
		String query = "from Notifications nf where nf.notificationTo = :notificationTo  and nf.orgCode = :orgCode and nf.locationCode = :locationCode and nf.mode =:mode and nf.notificationStatus =:notificationStatus";
		List<Notifications> notificationList = (List<Notifications>)session.createQuery(query).setLong("notificationTo", notificationTo)
																	.setString("mode", mode)
																	.setString("notificationStatus", notificationStatus)
				   													.setString("orgCode", orgCode)
				   													.setString("locationCode", locationCode).setCacheable(true).list();
		return notificationList;
	}

	/**
	 * used for change the notifications mode as read
	 */
	@Override
	@Transactional(rollbackFor = { Exception.class })
	public Integer updateNotificationStatusAsRead(Long userId, String orgCode, String locationCode) {
		// TODO Auto-generated method stub
		Session session =  this.sessionFactory.getCurrentSession();
		String mode = Constants.NOTIFICATION_READ;
		String notificationStatus = Constants.NOTIFICATION_NOT_DELETED;
		Query query = session.createQuery("update Notifications nf set nf.mode =:mode where nf.notificationTo = :userId and nf.orgCode = :orgCode and nf.locationCode = :locationCode and nf.notificationStatus =:notificationStatus");
		query.setLong("userId", userId);
		query.setString("mode", mode);
		query.setString("notificationStatus", notificationStatus);
		query.setString("orgCode", orgCode);
		query.setString("locationCode", locationCode);
		int result = query.executeUpdate();
		return result;			  
	}

	@Override
	@Transactional(rollbackFor = { Exception.class })
	public Integer updateNotificationDeleteStatus(Long notificationId, String orgCode, String locationCode) {
		// TODO Auto-generated method stub
		Session session =  this.sessionFactory.getCurrentSession();
		String status = Constants.NOTIFICATION_DELETED;
		Query query = session.createQuery("update Notifications nf set nf.notificationStatus =:status where nf.id = :notificationId and nf.orgCode = :orgCode and nf.locationCode = :locationCode ");
		query.setLong("notificationId", notificationId);
		query.setString("status", status);
		query.setString("orgCode", orgCode);
		query.setString("locationCode", locationCode);
		int result = query.executeUpdate();
		return result;	
	}

	@Override
	@Transactional(rollbackFor = { Exception.class })
	public List<Notifications> getAllNotificationsListByUserId(Long userId, String orgCode, String locationCode) {
		Session session =  this.sessionFactory.getCurrentSession();
		String notificationStatus = Constants.NOTIFICATION_NOT_DELETED;
		String query = "from Notifications nf where nf.notificationTo = :userId  and nf.orgCode = :orgCode and nf.locationCode = :locationCode and nf.notificationStatus =:notificationStatus ORDER BY nf.createdDate DESC";
		List<Notifications> notificationList = (List<Notifications>)session.createQuery(query).setLong("userId", userId)
																	.setString("notificationStatus", notificationStatus)
				   													.setString("orgCode", orgCode)
				   													.setString("locationCode", locationCode).setCacheable(true).list();
		return notificationList;
	}

	@Override
	@Transactional(rollbackFor = { Exception.class })
	public Notifications getNotificationsById(Long Id) throws Exception {
		
		Session session = this.sessionFactory.getCurrentSession();
		List<Notifications> notifications = new ArrayList<Notifications>();
		logger.debug("getNotificationsById start");
		notifications = session.createQuery("from Notifications where id=:id" ).setCacheable(true).setLong( "id", Id ).setCacheable(true).list();
		logger.debug("getNotificationsById end");
		if (notifications.size() > 0) {
			return notifications.get(0);
		} else {
			return null;
		}
   	}

	@Override
	@Transactional(rollbackFor = { Exception.class })
	public Notifications getNotificationsByUUID(String UUID) throws Exception {
		
		Session session = this.sessionFactory.getCurrentSession();
		List<Notifications> notifications = new ArrayList<Notifications>();
		logger.debug("getNotificationsById start");
		notifications = session.createQuery("from Notifications ns where ns.UUID=:UUID and ns.userNotificationStatus <> 'Accepted' " ).setCacheable(true).setString( "UUID", UUID ).setCacheable(true).list();
		logger.debug("getNotificationsById end");
		if (notifications.size() > 0) {
			return notifications.get(0);
		} else {
			return null;
		}
   	}

}
