package com.bis.operox.inv.service;

import static org.junit.Assert.assertNotNull;

import java.util.List;

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
import com.bis.operox.inv.dao.entity.Webgeocities;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = WebAppConfig.class, loader = AnnotationConfigWebContextLoader.class)
public class WebgeocitiesServiceTest {

	@Autowired
	WebgeocitiesService webgeocitiesService;

	private static final Logger logger = LoggerFactory.getLogger(UserServiceTest.class);

	@Test
	public void getAllCitiesByStateAndCountry() throws Exception {
		List<Webgeocities> list= webgeocitiesService.getAllCitiesByStateAndCountry("Telangana", "India");
		assertNotNull(list);
	}
}
