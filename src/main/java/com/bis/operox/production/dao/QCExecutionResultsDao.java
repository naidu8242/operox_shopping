package com.bis.operox.production.dao;

import java.util.List;

import com.bis.operox.production.dao.entity.QCExecutionResults;

public interface QCExecutionResultsDao {

	QCExecutionResults addExecutionResults(QCExecutionResults qcExecutionResults);

	List<QCExecutionResults> getQCExecutionResultsByQCExecution(Long qcExecutionId);

	QCExecutionResults getQCExecutionResultsReportById(Long id);

	QCExecutionResults deleteQCExecutionResultsById(QCExecutionResults qcExecutionResult);


}