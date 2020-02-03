package com.bis.operox.inv.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.bis.operox.inv.dao.UserEcommOrderDao;
import com.bis.operox.inv.dao.entity.UserEcommOrder;
import com.bis.operox.inv.service.UserEcommOrderService;

@Service
@Repository
public class UserEcommOrderServiceImpl implements UserEcommOrderService{

	@Autowired
	private UserEcommOrderDao userEcommOrderDao;

	@Override
	public UserEcommOrder addUserEcommOrder(UserEcommOrder userEcommOrder) {
		return userEcommOrderDao.addUserEcommOrder(userEcommOrder);
	}

	@Override
	public UserEcommOrder getUserEcommOrderById(Long id) {
		return userEcommOrderDao.getUserEcommOrderById(id);
	}

	@Override
	public List<UserEcommOrder> userEcommOrderList() {
		return userEcommOrderDao.userEcommOrderList();
	}
}
