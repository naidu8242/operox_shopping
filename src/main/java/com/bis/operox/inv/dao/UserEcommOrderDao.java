package com.bis.operox.inv.dao;

import java.util.List;

import com.bis.operox.inv.dao.entity.Address;
import com.bis.operox.inv.dao.entity.UserEcommOrder;

public interface UserEcommOrderDao {
	

	UserEcommOrder addUserEcommOrder(UserEcommOrder userEcommOrder);
	
	UserEcommOrder getUserEcommOrderById(Long id);

	List<UserEcommOrder> userEcommOrderList();


}