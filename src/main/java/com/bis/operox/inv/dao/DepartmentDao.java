package com.bis.operox.inv.dao;

import java.util.List;

import com.bis.operox.inv.dao.entity.Department;

/**
 * @author Ajith Matta
 *
 */
public interface DepartmentDao {

	 Department addDepartment(Department dept);
	 
	 Department getDepartmentById(Long id) throws Exception;

	 List<Department> listDepartments() throws Exception;
	 
	 public Boolean validateDepartmentNameAndId(String departmentName,String departmentId) throws Exception;
	 
	 public Department getDepartmentByDeptIdAndDepartmentName(String id, String departmentName, String departmentId) throws Exception;

}
