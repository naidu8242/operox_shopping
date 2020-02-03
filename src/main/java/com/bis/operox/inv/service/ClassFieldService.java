package com.bis.operox.inv.service;

import com.bis.operox.inv.dao.entity.ClassField;

/**
 * 
 * @author Laxman
 *
 */
public interface ClassFieldService {
	
	ClassField getClassFieldByDelimiterStr(String delimiterStr) throws Exception;

}