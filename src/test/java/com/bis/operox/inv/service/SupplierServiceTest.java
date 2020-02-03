package com.bis.operox.inv.service;

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
 * This is a JUnit Test class for testing LoginService methods
 * 
 * @author: Ajith Matta
 */

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = WebAppConfig.class, loader = AnnotationConfigWebContextLoader.class)
public class SupplierServiceTest {

	@Autowired
	SupplierService supplierService;

	private static final Logger logger = LoggerFactory.getLogger(SupplierServiceTest.class);

	/*@Test
	public void checkAuthentication() throws Exception {
		Supplier supplier = new Supplier();
		 supplier = supplierService.addSupplier(supplier);
		Assert.assertNotNull(supplier);
		Assert.assertEquals("operox", supplier.getName());
		
	}*/

}
