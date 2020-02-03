package com.bis.operox.inv.service;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.AnnotationConfigWebContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;

import com.bis.operox.WebAppConfig;
import com.bis.operox.inv.dao.entity.Department;
import com.bis.operox.inv.dao.entity.User;

/**
 * This is a JUnit Test class for testing Department methods
 * 
 * @author: Ajith Matta.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = WebAppConfig.class, loader = AnnotationConfigWebContextLoader.class)
public class DepartmentServiceTest {

	@Autowired
	DepartmentService departmentService;
	
	@Autowired
	UserService userService;

	private static final Logger logger = LoggerFactory.getLogger(DepartmentServiceTest.class);

	@Test
	public void addDepartment() throws Exception {

		Department department = new Department();
		User user = new User();
		department.setId(1l);
		user.setId(1l);
		user.setUserCode("Abcd");
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("departmentId", "de12");
		jsonObject.put("departmentName", "akhil");
		jsonObject.put("description", "hello");
		jsonObject.put("status", "1");
		jsonObject.put("createdBy", "Ajith");
		jsonObject.put("updatedBy", "Prasad");
		department = departmentService.addDepartment(jsonObject, user);
		Assert.assertNotNull(department);
		Assert.assertEquals("akhil", department.getDepartmentName());
	}
	
	@Test
	public void getDepartmentById() throws Exception{
		Department department = new Department();
		department = departmentService.getDepartmentById(2l);
		Assert.assertNotNull(department);
		Assert.assertEquals("hello", department.getDescription());
		
	}
	
	@Test
	public void listDepartments() throws Exception{
		
		List<Department> departmentList = new ArrayList<Department>();
		departmentList = departmentService.listDepartments();
		Assert.assertNotNull(departmentList);
		Assert.assertEquals(1, departmentList.size());
		
	}
	
	//@Test
	public void removeDepartmentById() throws Exception{
		Department department = new Department();
		department = departmentService.removeDepartmentById(2l,"Abcd");
		Assert.assertNotNull(department);
		Assert.assertTrue(true);
	}

}

