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
import com.bis.operox.inv.dao.entity.User;

/**
 * This is a JUnit Test class for testing ProductService methods
 * 
 * @author: Prasad K
 */

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = WebAppConfig.class, loader = AnnotationConfigWebContextLoader.class)
public class ProductServiceTest {

	@Autowired
	ProductService productService;

	private static final Logger logger = LoggerFactory.getLogger(ProductServiceTest.class);

	/*@Test
	public void checkAuthentication() throws Exception {

		User user = userService.findByUserName("operox");
		Assert.assertNotNull(user);
		Assert.assertEquals("operox", user.getUsername());
		
		//TODO: The following logger statements are to verify the Object paramaters. Don't use instead of Assert equals 
		logger.info("User Name: "+user.getUsername());
		logger.info("User Id: "+user.getId());
	}
*/
}
