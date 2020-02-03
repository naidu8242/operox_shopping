package com.bis.operox.inv.dao.impl;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bis.operox.inv.dao.PasswordResetsDao;
import com.bis.operox.inv.dao.entity.PasswordResets;

@Repository
public class PasswordResetsDaoImpl implements PasswordResetsDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	private static final Logger logger = LoggerFactory.getLogger(PasswordResetsDaoImpl.class);
	

	@Override
	@Transactional
	public PasswordResets getPasswordResetsByUserId(Long userId) {
		
		Session session = this.sessionFactory.getCurrentSession();
		List<PasswordResets> passwordResets = new ArrayList<PasswordResets>();
		logger.debug("PasswordResets by UserId start");
		passwordResets = session.createQuery("from PasswordResets passwordResets where passwordResets.user.id = :userId and passwordResets.resetStatus = 'Open' " ).setLong( "userId", userId ).list();
		logger.debug("PasswordResets by UserId end");
		if (passwordResets.size() > 0) {
			return passwordResets.get(0);
		} else {
			return null;
		}
   	}


	@Override
	@Transactional
	public PasswordResets savePasswordResets(PasswordResets passwordResets) throws Exception {

		logger.debug("Adding PasswordResets");
		Session session = this.sessionFactory.getCurrentSession();
		try {
			session.saveOrUpdate(passwordResets);
			logger.debug("PasswordResets Added Successfully");
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return passwordResets;
	
		
	}


	@Override
	@Transactional
	public PasswordResets getPasswordResetsByVerificationCode(String verificationCode) throws Exception {
		
		Session session = this.sessionFactory.getCurrentSession();
		List<PasswordResets> passwordResets = new ArrayList<PasswordResets>();
		logger.debug("PasswordResets by UserId start");
		passwordResets = session.createQuery("from PasswordResets passwordResets where passwordResets.verificationCode=:verificationCode and passwordResets.resetStatus = 'Open' " ).setString( "verificationCode", verificationCode ).list();
		logger.debug("PasswordResets by UserId end");
		if (passwordResets.size() > 0) {
			return passwordResets.get(0);
		} else {
			return null;
		}
   	}


	@Override
	@Transactional
	public PasswordResets deletePasswordResets(PasswordResets passwordResets) throws Exception {

		logger.debug("Deleting PasswordResets");
		Session session = this.sessionFactory.getCurrentSession();
		try {
			session.delete(passwordResets);
			logger.debug("PasswordResets Deleting Successfully");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return passwordResets;
	
		
	}

}
