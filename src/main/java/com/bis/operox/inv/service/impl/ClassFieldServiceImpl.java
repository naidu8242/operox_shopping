package com.bis.operox.inv.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bis.operox.inv.dao.ClassFieldDao;
import com.bis.operox.inv.dao.entity.ClassField;
import com.bis.operox.inv.service.ClassFieldService;

/**
 * 
 * @author Laxman
 *
 */
@Service
public class ClassFieldServiceImpl implements ClassFieldService{
	
	private static final Logger logger = LoggerFactory.getLogger(ClassFieldServiceImpl.class);
	
	@Autowired
	private ClassFieldDao classFieldDao;
	
	
	
	@Override
	public ClassField getClassFieldByDelimiterStr(String delimiterStr) throws Exception{
		logger.debug("In ClassFieldServiceImpl class getClassFieldByDelimiterStr method");
		ClassField classField = classFieldDao.getClassFieldByDelimiterStr(delimiterStr);
		return classField;
	}

}
