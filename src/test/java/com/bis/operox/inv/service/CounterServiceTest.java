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
import com.bis.operox.inv.dao.entity.Counter;
import com.bis.operox.inv.dao.entity.Store;
import com.bis.operox.inv.dao.entity.User;

/**
 * This is a JUnit Test class for testing Counter methods
 * 
 * @author: Ajith Matta.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = WebAppConfig.class, loader = AnnotationConfigWebContextLoader.class)
public class CounterServiceTest {

	@Autowired
	CounterService counterService;
	
	@Autowired
	UserService userService;

	private static final Logger logger = LoggerFactory.getLogger(CounterServiceTest.class);

	@Test
	public void addCounterDetails() throws Exception {

		Counter counter = new Counter();
		Store store = new Store();
		User user = new User();
		store.setId(1l);
		user.setUserCode("Abcd");
		counter.setId(1l);
		user.setId(1l);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("counterName", "reddy");
		jsonObject.put("counterType", "cash counter");
		jsonObject.put("status", "1");
		jsonObject.put("description", "hello");
		jsonObject.put("storeId", "1");
		counter = counterService.addCounterDetails(jsonObject, user);
		Assert.assertNotNull(counter);
		Assert.assertEquals("reddy", counter.getCounterName());
	}
	
	@Test
	public void listCounters() throws Exception{
		List<Counter> counterList = new ArrayList<>();
		counterList = counterService.listCounters();
		Assert.assertNotNull(counterList);
		Assert.assertEquals(1, counterList.size());
	}
	
	@Test
	public void getCounterById() throws Exception{
		Counter counter = new Counter();
		counter.setId(1l);
		counter = counterService.getCounterById(counter.getId());
		Assert.assertNotNull(counter);
		Assert.assertEquals("reddy", counter.getCounterName());
	}
	
	@Test
	public void getCounterByCounterIdAndStoredIdAndCounterName() throws Exception{
		Counter counter = new Counter();
		counter = counterService.getCounterByCounterIdAndStoredIdAndCounterName("1", "reddy", "1");
		Assert.assertNotNull(counter);
		Assert.assertEquals("reddy", counter.getCounterType());
		
	}
	
	@Test
	public void validateCounterName() throws Exception{
		Boolean counter1 = counterService.validateCounterName("1", "1");
		Assert.assertNotNull(counter1);
		Assert.assertTrue(true);
		
	}
	//@Test
	public void removeCounterById() throws Exception{
		Counter counter = new Counter();
		counter = counterService.removeCounterById(1l, "opradmin");
		Assert.assertNotNull(counter);
		Assert.assertTrue(true);
		
		
	}
	
}

