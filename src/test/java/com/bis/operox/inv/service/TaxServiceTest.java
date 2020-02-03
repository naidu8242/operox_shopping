package com.bis.operox.inv.service;

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

/**
 * This is a JUnit Test class for testing TaxService methods
 * 
 * @author: Prasad Salina
 */
 
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = WebAppConfig.class, loader = AnnotationConfigWebContextLoader.class)
public class TaxServiceTest {

	@Autowired
	TaxService taxService;

	private static final Logger logger = LoggerFactory.getLogger(TaxServiceTest.class);

	@Test
	public void addTax() throws Exception {

		
	}
	
}
