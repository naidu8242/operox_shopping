package com.bis.operox.inv.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bis.operox.inv.dao.RoleDao;
import com.bis.operox.inv.dao.entity.Role;
/**
 * 
 * @author Rajashekar
 * @date 23rd Sep 2016
 * Handles role activities
 */

@Repository
public class RoleDaoImpl implements RoleDao{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private static final Logger logger = LoggerFactory.getLogger(RoleDaoImpl.class);

	@Override
	@Transactional
	public Role addROle(Role role) {
		Session session = this.sessionFactory.getCurrentSession();
		session.saveOrUpdate(role);
		logger.info("Role added successfully..!!");
		return role;
	}

	@Override
	@Transactional
	public Role getRoleById(Long id) {
       Session session = this.sessionFactory.getCurrentSession();	
		Role role = (Role) session.load(Role.class, new Long(id));
		logger.info("Role loaded successfully");
		return role;
	}

	@Override
	@Transactional
	public List<Role> getRoles() {
		Session session = this.sessionFactory.getCurrentSession();
		List<Role> roleList = session.createQuery("from Role").list();
		return roleList;
	}

}
