package com.bis.operox.production.dao;

import java.util.List;

import com.bis.operox.production.dao.entity.QCExecution;

public interface QCExecutionDao {

	QCExecution addQCExecution(QCExecution qcExecution);
	
	QCExecution getQCExecutionById(Long id);
	
	List<QCExecution> getQcExecutionList(Long workOrderId, String type);
	
}