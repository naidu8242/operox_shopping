package com.bis.operox.inv.service;

import java.util.List;

import org.json.JSONObject;

import com.bis.operox.inv.dao.entity.Department;
import com.bis.operox.inv.dao.entity.User;

/**
 * @author Ajith Matta
 *
 */
public interface DepartmentService {

	/**
	 * @author Ajith Matta
	 * @param jsonObject
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public  Department addDepartment(JSONObject jsonObject, User user) throws Exception;
	 
	 /**
	  * @author Ajith Matta
	 * @param id
	 * @return
	 */
	public Department getDepartmentById(Long id) throws Exception;
	 
	 /**
	  * @author Ajith Matta
	 * @param id
	 * @return
	 */
	public Department removeDepartmentById(Long id,String userCode) throws Exception;

	 /**
	  * @author Ajith Matta
	 * @return
	 */
	List<Department> listDepartments() throws Exception;
	
	/**
	 * @author Ajith Matta
	 * @param departmentName
	 * @return
	 * @throws Exception
	 */
	public Boolean validateDepartmentNameAndId(String departmentName, String departmentId) throws Exception;
	
	/**
	 * @author Ajith Matta
	 * @param id
	 * @param departmentName
	 * @return
	 * @throws Exception
	 */
	public Department getDepartmentByDeptIdAndDepartmentName(String id, String departmentName, String departmentId) throws Exception;
	
}
