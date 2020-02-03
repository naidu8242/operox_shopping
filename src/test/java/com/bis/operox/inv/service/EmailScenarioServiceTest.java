package com.bis.operox.inv.service;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.AnnotationConfigWebContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;

import com.bis.operox.WebAppConfig;
import com.bis.operox.inv.dao.entity.EmailScenario;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = WebAppConfig.class, loader = AnnotationConfigWebContextLoader.class)
public class EmailScenarioServiceTest {
	
	@Autowired
	EmailScenarioService emailScenarioService;
	
	
	
	@Test
	public void save() throws Exception{
		EmailScenario emailScenario = new EmailScenario();
		emailScenario.setScenarioName("Testing service");
		emailScenario.setEntityType("Email");
		emailScenario.setUniqueScenarioName("testService");
		emailScenario.setDescription("this is used for testing service");
		emailScenario.setSubject("Testing Service");
		emailScenario.setTemplateType("org");
		emailScenario.setSignature("SourceLead");
		emailScenario.setBody("this is used for testing service");
		emailScenario.setCallToActionText("Sourcelead");
		emailScenario.setButtonText("Sourcelead");
		emailScenario.setIsTemplateModified("N");
		emailScenario.setCreatedDate(new Date());
		emailScenario.setCreatedBy("Source");
		emailScenario.setOrgCode("hangon.com");
		emailScenario.setLocationCode("in.sourceLead.com");
		emailScenario.setUseLogo("LOG");
		emailScenario.setDisclaimerText("Disclaimer");
		emailScenario = emailScenarioService.save(emailScenario);
		System.out.println("save successfully " +emailScenario.getId());
	}
	
	@Test
	public void getEmailScenarioByUniqueNameAndEntityTypeAndOrgCodeAndLocationsCode() throws Exception{
		EmailScenario emailScenario = new EmailScenario();
		emailScenario.setUniqueScenarioName("Forgot Password");
		emailScenario.setEntityType("Email");
		emailScenario.setOrgCode("slead");
		emailScenario.setLocationCode("sleadIN");
		emailScenario = emailScenarioService.getEmailScenarioByUniqueNameAndEntityTypeAndOrgCodeAndLocationsCode(emailScenario.getUniqueScenarioName(), emailScenario.getEntityType(), emailScenario.getOrgCode(), emailScenario.getLocationCode());
		System.out.println("EmailScenario by given particulars is...>>"+emailScenario.getScenarioName());
	}
	
	@Test
	public void getEmailScenarioByUniqueName() throws Exception{
		EmailScenario emailScenario = new EmailScenario();
		emailScenario.setUniqueScenarioName("Forgot Password");
		emailScenario.setEntityType("Email");
		emailScenario = emailScenarioService.getEmailScenarioByUniqueName(emailScenario.getUniqueScenarioName(), emailScenario.getEntityType());
		System.out.println("EmailScenario by given particulars is...>>"+emailScenario.getId());
	}
}
