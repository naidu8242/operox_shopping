package com.bis.operox.inv.service;

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
import com.bis.operox.inv.dao.entity.Customer;
import com.bis.operox.inv.dao.entity.User;

/**
 * This is a JUnit Test class for testing CustomerService methods
 * 
 * @author: Venkat Kesav
 */

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = WebAppConfig.class, loader = AnnotationConfigWebContextLoader.class)
public class CustomerServiceTest {

	@Autowired
	CustomerService customerService;

	private static final Logger logger = LoggerFactory.getLogger(CustomerServiceTest.class);

	@Test
	public void checkAuthentication() throws Exception {
		Customer customer = new Customer();
		JSONObject jsonObj = new JSONObject();
		User user = new User();
		customer = customerService.addCustomer( jsonObj,user);
		Assert.assertNotNull(customer);
		
	}

}

