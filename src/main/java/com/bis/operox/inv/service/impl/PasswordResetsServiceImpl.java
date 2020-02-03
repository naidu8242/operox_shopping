package com.bis.operox.inv.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bis.operox.OperoxPasswordEncoder;
import com.bis.operox.inv.dao.PasswordResetsDao;
import com.bis.operox.inv.dao.UserDao;
import com.bis.operox.inv.dao.entity.PasswordResets;
import com.bis.operox.inv.dao.entity.User;
import com.bis.operox.inv.service.PasswordResetsService;
import com.bis.operox.util.OperoxRandomCodeHelper;

@Service
public class PasswordResetsServiceImpl implements PasswordResetsService {
	
	@Autowired
	PasswordResetsDao passwordResetsDao;
	
	@Autowired
	private OperoxPasswordEncoder operoxPasswordEncoder;
	
	@Autowired
	UserDao userDao;

	@Override
	public PasswordResets getPasswordResetsByUserId(Long userId) {
		return passwordResetsDao.getPasswordResetsByUserId(userId);
	}
	
	
	@Override
	public String addPasswordResets(User user) throws Exception {
		
		PasswordResets passwordResets = getPasswordResetsDetails(user);
		passwordResetsDao.savePasswordResets(passwordResets);
		return passwordResets.getVerificationCode(); 
		
		
	}
	
	private PasswordResets getPasswordResetsDetails(User user){
		
		PasswordResets passwordResets = new PasswordResets();
		passwordResets.setUser(user);
		passwordResets.setEmail(user.getEmail());
		passwordResets.setResetStatus("Open");
		passwordResets.setVerificationCode(OperoxRandomCodeHelper.generateRandomVerificationCode());
		passwordResets.setCreatedDate(new Date());
		passwordResets.setCreatedBy(user.getUserCode());
		return passwordResets;
		
	}


	@Override
	public PasswordResets savePasswordResets(PasswordResets passwordResets) throws Exception {
		return passwordResetsDao.savePasswordResets(passwordResets);
	}


	@Override
	public PasswordResets getPasswordResetsByVerificationCode(String verificationCode) throws Exception {
		return passwordResetsDao.getPasswordResetsByVerificationCode(verificationCode);
	}


	@Override
	public PasswordResets deletePasswordResets(PasswordResets passwordResets) throws Exception {
		return passwordResetsDao.deletePasswordResets(passwordResets);
	}
	
	@Override
	public void resetPassword(PasswordResets passwordResets,String password) throws Exception {
		
		//Here user will loaded automatically without any DB hit. beacuse user fetchtype is declared as Eager in PasswordResets.
		User user = passwordResets.getUser();
		user.setPassword(operoxPasswordEncoder.encode(password));
		userDao.addUser(user);
		
	}


}
