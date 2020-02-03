package com.bis.operox.inv.service;

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
import com.bis.operox.inv.dao.entity.Bill;

/**
 * This is a JUnit Test class for testing BillService methods
 * 
 * @author: Venkat Kesav
 */

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = WebAppConfig.class, loader = AnnotationConfigWebContextLoader.class)
public class BillServiceTest {

	@Autowired
	BillService billService;

	private static final Logger logger = LoggerFactory.getLogger(BillServiceTest.class);

	@Test
	public void checkAuthentication() throws Exception {
		Bill bill = new Bill();
		/*bill = billService.addBill(bill);
		Assert.assertNotNull(bill);*/
		
	}

}

