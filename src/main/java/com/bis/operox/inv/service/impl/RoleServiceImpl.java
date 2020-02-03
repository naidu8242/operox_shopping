package com.bis.operox.inv.service.impl;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bis.operox.inv.dao.RoleDao;
import com.bis.operox.inv.dao.entity.Role;
import com.bis.operox.inv.service.RoleService;
@Service
public class RoleServiceImpl implements RoleService{
	
	@Autowired
	RoleDao roleDao;

	@Override
	public Role addROle(Role role) {
		return roleDao.addROle(role);
	}

	@Override
	public Role getRoleById(Long id) {
		return roleDao.getRoleById(id);
	}

	@Override
	public List<Role> getRoles() {
		return roleDao.getRoles();
	}

}
