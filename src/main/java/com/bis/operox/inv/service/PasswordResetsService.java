package com.bis.operox.inv.service;

import com.bis.operox.inv.dao.entity.PasswordResets;
import com.bis.operox.inv.dao.entity.User;

public interface PasswordResetsService {

	PasswordResets getPasswordResetsByUserId(Long userId);
	
	public String addPasswordResets(User user) throws Exception;
	
	public PasswordResets savePasswordResets(PasswordResets passwordResets) throws Exception;
	
	public PasswordResets getPasswordResetsByVerificationCode(String verificationCode) throws Exception;
	
	public PasswordResets deletePasswordResets(PasswordResets passwordResets) throws Exception;
	
	public void resetPassword(PasswordResets passwordResets,String password) throws Exception;
}
