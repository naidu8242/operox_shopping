package com.bis.operox.inv.dao;

import com.bis.operox.inv.dao.entity.ClassField;

public interface ClassFieldDao {

	ClassField getClassFieldByDelimiterStr(String delimiterStr) throws Exception;
}
