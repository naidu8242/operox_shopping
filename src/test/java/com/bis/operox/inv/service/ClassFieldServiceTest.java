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
import com.bis.operox.inv.dao.entity.ClassField;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = WebAppConfig.class, loader = AnnotationConfigWebContextLoader.class)
public class ClassFieldServiceTest {
	
	@Autowired
	ClassFieldService classFieldService;
	
	private static final Logger logger = LoggerFactory.getLogger(ClassFieldServiceTest.class);
	
	@Test
	public void getClassFieldByDelimiterStr() throws Exception{
		
		String delimiterStr = "{{inviteClient.vendorLastName}}";
		ClassField classField = classFieldService.getClassFieldByDelimiterStr(delimiterStr);
		logger.info("Retrieved class field is..."+classField.getFieldName());
	}
	
}
