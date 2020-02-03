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
import com.bis.operox.inv.dao.entity.Address;
import com.bis.operox.inv.dao.entity.Store;
import com.bis.operox.inv.dao.entity.User;

/**
 * This is a JUnit Test class for testing Store methods
 * 
 * @author: Ajith Matta.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = WebAppConfig.class, loader = AnnotationConfigWebContextLoader.class)
public class StoreServiceTest {

	@Autowired
	StoreService storeService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	AddressService addressService;

	private static final Logger logger = LoggerFactory.getLogger(StoreServiceTest.class);

	@Test
	public void addStoreDetails() throws Exception {

		Store store = new Store();
		User user = new User();
		Address address = new Address();
		address.setId(1l);
		store.setId(1l);
		user.setId(1l);
		user.setUserCode("Prasad");
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("storeName", "kphb");
		jsonObject.put("storeType", "Store");
		jsonObject.put("email", "kphb@gmail.com");
		jsonObject.put("status", "1");
		jsonObject.put("phone", "90876542");
		jsonObject.put("country", "India");
		jsonObject.put("state", "Telangana");
		jsonObject.put("city", "hyd");
		jsonObject.put("address", "kukatpally");
		jsonObject.put("zipCode", "23456");
		store = storeService.addStoreDetails(jsonObject,user);
		Assert.assertNotNull(store);
		Assert.assertEquals("kphb@gmail.com", store.getEmail());
		
	}
	
	@Test
	public void listStores() throws Exception{
		List<Store> storeLlst = new ArrayList<>();
		storeLlst = storeService.listStores();
		Assert.assertNotNull(storeLlst);
		Assert.assertEquals(2, storeLlst.size());
	}
	
	@Test
	public void getStoreById() throws Exception{
		Store store = new Store();
		store = storeService.getStoreById(2l);
		Assert.assertNotNull(store);
		Assert.assertEquals("kphb@gmail.com", store.getEmail());
		
	}
	
	@Test
	public void getStoreByStoreIdAndStoreName() throws Exception{
		Store store = new Store();
		store = storeService.getStoreByStoreIdAndStoreName("2", "kphb");
		Assert.assertNotNull(store);
		Assert.assertEquals("kphb@gmail.com", store.getEmail());
	}
	@Test
	public void validateStoreName() throws Exception{
		Boolean store1 = storeService.validateStoreName("kphb");
		Assert.assertNotNull(store1);
		Assert.assertTrue(true);
	}
	
	//@Test
	public void removeStoreById() throws Exception{
		Store store = new Store();
		store = storeService.removeStoreById(2l, "Prasad");
		Assert.assertNotNull(store);
		Assert.assertTrue(true);
	}
	
	
}

