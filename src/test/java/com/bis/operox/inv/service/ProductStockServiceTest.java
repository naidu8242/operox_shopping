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
import com.bis.operox.inv.dao.entity.ProductStock;

/**
 * This is a JUnit Test class for testing ProductStockService methods
 * 
 * @author: Venkat Kesav
 */

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = WebAppConfig.class, loader = AnnotationConfigWebContextLoader.class)
public class ProductStockServiceTest {

	@Autowired
	ProductStockService productStockService;

	private static final Logger logger = LoggerFactory.getLogger(ProductStockServiceTest.class);

	/*@Test
	public void checkAuthentication() throws Exception {
		ProductStock productStock = new ProductStock();
		productStock = productStockService.addProductStock(productStock);
		Assert.assertNotNull(productStock);
		
	}*/

}

