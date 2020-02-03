package com.bis.operox.inv.dao;

import com.bis.operox.inv.dao.entity.PasswordResets;

public interface PasswordResetsDao {

	PasswordResets getPasswordResetsByUserId(Long userId);
	
	public PasswordResets savePasswordResets(PasswordResets passwordResets) throws Exception;
	
	public PasswordResets getPasswordResetsByVerificationCode(String verificationCode) throws Exception;
	
	public PasswordResets deletePasswordResets(PasswordResets passwordResets) throws Exception;
}
