package com.bis.operox.production.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bis.operox.production.dao.QCExecutionResultsDao;
import com.bis.operox.production.dao.entity.QCExecutionResults;
import com.bis.operox.production.service.QCExecutionResultsService;

@Service
public class QCExecutionResultsServiceImpl implements QCExecutionResultsService {
	
	@Autowired
	QCExecutionResultsDao qcExecutionResultsDao;

	@Override
	public QCExecutionResults addExecutionResults(QCExecutionResults qcExecutionResults) {
		return qcExecutionResultsDao.addExecutionResults(qcExecutionResults);
	}

	@Override
	public List<QCExecutionResults> getQCExecutionResultsByQCExecution(Long qcExecutionId) {
		return qcExecutionResultsDao.getQCExecutionResultsByQCExecution(qcExecutionId);
	}

	@Override
	public QCExecutionResults getQCExecutionResultsReportById(Long id) {
		return qcExecutionResultsDao.getQCExecutionResultsReportById(id);
	}

	@Override
	public QCExecutionResults deleteQCExecutionResultsById(QCExecutionResults qcExecutionResult) {
		return qcExecutionResultsDao.deleteQCExecutionResultsById(qcExecutionResult);
		
	}



}
