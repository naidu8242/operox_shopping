package com.bis.operox.production.service;

import java.util.List;

import com.bis.operox.production.dao.entity.QCExecution;
import com.bis.operox.production.dao.entity.QCExecutionResults;

public interface QCExecutionResultsService {

	QCExecutionResults addExecutionResults(QCExecutionResults qcExecutionResults);

	List<QCExecutionResults> getQCExecutionResultsByQCExecution(Long qcExecutionId);

	QCExecutionResults getQCExecutionResultsReportById(Long id);

	QCExecutionResults deleteQCExecutionResultsById(QCExecutionResults qcExecutionResult);
	
}
