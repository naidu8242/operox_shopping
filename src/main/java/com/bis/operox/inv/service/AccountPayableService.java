package com.bis.operox.inv.service;

import org.json.JSONObject;

import com.bis.operox.inv.dao.entity.AccountPayable;
import com.bis.operox.inv.dao.entity.User;

public interface AccountPayableService {
	
	public AccountPayable addAccountPayableDetails(JSONObject jsonObj,User user) throws Exception;

}
