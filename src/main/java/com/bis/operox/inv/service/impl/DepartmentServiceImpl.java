package com.bis.operox.inv.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bis.operox.constants.Constants;
import com.bis.operox.inv.dao.DepartmentDao;
import com.bis.operox.inv.dao.entity.Department;
import com.bis.operox.inv.dao.entity.Store;
import com.bis.operox.inv.dao.entity.User;
import com.bis.operox.inv.service.DepartmentService;
import com.bis.operox.inv.service.UserService;

/**
 * @author Ajith Matta
 *
 */

@Service
public class DepartmentServiceImpl implements DepartmentService {
	
	@Autowired
	private DepartmentDao departmentDao;
	
	@Autowired
	private UserService userService;

	/**
	 * @author Ajith Matta
	 * This method is used for save/ update the Department
	 * @param jsonObject
	 * @param user
	 * @return
	 * @throws NumberFormatException 
	 * @throws Exception
	 */
	@Override
	public Department addDepartment(JSONObject jsonObject, User user) throws NumberFormatException, Exception {
		Department department = null;
		
		if(jsonObject.has("deptId") && jsonObject.getString("deptId") != null && !"".equals(jsonObject.getString("deptId"))){
			department = departmentDao.getDepartmentById(Long.parseLong(jsonObject.getString("deptId")));
		}else{
			department = new Department();
			department.setCreatedBy(user.getUserCode());
		}
		
		if (jsonObject.has("departmentName")) {
			department.setDepartmentName(jsonObject.getString("departmentName"));
		}
		if (jsonObject.has("departmentId")) {
			department.setDepartmentId(jsonObject.getString("departmentId"));
		}
		if (jsonObject.has("description")) {
			department.setDescription(jsonObject.getString("description"));
		}
		
		department.setCreatedDate(new Date());
		department.setUpdatedBy(user.getUserCode());
		department.setUpdatedDate(new Date());
		department.setStatus(Constants.RECORD_ACTIVE);
		
		return departmentDao.addDepartment(department);
	}

	/**
	  * @author Ajith Matta
	  * This method is used for to get the department based on id.
	 * @param id
	 * @return
	 * @throws Exception 
	 */
	
	@Override
	public Department getDepartmentById(Long id) throws Exception {
		Department department= departmentDao.getDepartmentById(id);
		User user = userService.getUserByUserCode(department.getUpdatedBy());
		if(user != null){
		  String createdByName = "";
		   if(StringUtils.isNotEmpty(user.getFirstName())){
			   createdByName = user.getFirstName();
		   }
		   if(StringUtils.isNotEmpty(user.getLastName()) && StringUtils.isNotBlank(user.getLastName())){
			   createdByName = createdByName+" "+user.getLastName();
		   }
		   department.setUpdatedBy(createdByName);
		}
		return department;
	}

	/**
	  * @author Ajith Matta
	  * This method is used for to get the department list.
	 * @return
	 * @throws Exception 
	 */
	
	@Override
	public List<Department> listDepartments() throws Exception {
		List<Department> departmentsList = departmentDao.listDepartments();
		for (Department department : departmentsList) {
			User user = userService.getUserByUserCode(department.getUpdatedBy());
			if(user != null){
			  String updatedByName = "";
    		   if(StringUtils.isNotEmpty(user.getFirstName())){
    			   updatedByName = user.getFirstName();
    		   }
    		   if(StringUtils.isNotEmpty(user.getLastName()) && StringUtils.isNotBlank(user.getLastName())){
    			   updatedByName = updatedByName+" "+user.getLastName();
    		   }
    		   department.setUpdatedBy(updatedByName);
			}
		}
		return departmentsList;
	}
	
	/**
	  * @author Ajith Matta
	  * This method is used for de-activate the department based on status. 
	 * @param id
	 * @return
	 * @throws Exception 
	 */

	@Override
	public Department removeDepartmentById(Long id,String userCode) throws Exception {
		
		Department department = departmentDao.getDepartmentById(id);
		department.setUpdatedBy(userCode);
		department.setStatus(Constants.RECORD_IN_ACTIVE);
	     return departmentDao.addDepartment(department);
	}
	
	/**
	 * @author Ajith Matta
	 * This method is used for validate the department (dont allow remove duplicates)
	 * @param departmentName
	 * @return
	 * @throws Exception
	 */
	
	@Override
	public Boolean validateDepartmentNameAndId(String departmentName,String departmentId) throws Exception {
		return departmentDao.validateDepartmentNameAndId(departmentName,departmentId);
	}
	
	/**
	 * @author Ajith Matta
	 * This method is used for get the department based on Id and Department name
	 * @param id
	 * @param departmentName
	 * @return
	 * @throws Exception
	 */
	@Override
	public Department getDepartmentByDeptIdAndDepartmentName(String id, String departmentName, String departmentId) throws Exception {
		return departmentDao.getDepartmentByDeptIdAndDepartmentName(id,departmentName,departmentId);
	}
}
