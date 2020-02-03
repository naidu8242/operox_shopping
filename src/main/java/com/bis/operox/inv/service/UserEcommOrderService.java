package com.bis.operox.inv.service;

import java.util.List;

import com.bis.operox.inv.dao.entity.UserEcommOrder;

public interface UserEcommOrderService {

    UserEcommOrder addUserEcommOrder(UserEcommOrder userEcommOrder);
	
	UserEcommOrder getUserEcommOrderById(Long id);

	List<UserEcommOrder> userEcommOrderList();

}
