package com.bis.operox.inv.service;

import java.util.List;

import com.bis.operox.inv.dao.entity.Role;

public interface RoleService {
	/**
	 * to perform to add Role
	 * @param role
	 * @return
	 */
	Role addROle(Role role);
	/**
	 * to get role based on id
	 * @param id
	 * @return
	 */
    Role getRoleById(Long id);

	/**
	 * to get all roles
	 * @return
	 */
    List<Role> getRoles();
}
