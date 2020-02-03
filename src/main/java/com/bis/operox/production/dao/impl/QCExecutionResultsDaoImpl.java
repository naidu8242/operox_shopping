package com.bis.operox.production.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bis.operox.production.dao.QCExecutionResultsDao;
import com.bis.operox.production.dao.entity.QCCheckListReport;
import com.bis.operox.production.dao.entity.QCExecutionResults;

@Repository
public class QCExecutionResultsDaoImpl implements QCExecutionResultsDao {

	@Autowired
	private SessionFactory sessionFactory;

	private static final Logger logger = LoggerFactory.getLogger(QCExecutionResultsDaoImpl.class);

	@Override
	@Transactional
	public QCExecutionResults addExecutionResults(QCExecutionResults qcExecutionResults) {
		  Session session = this.sessionFactory.getCurrentSession();
	        session.saveOrUpdate(qcExecutionResults);
	        return qcExecutionResults;
		
	}

	@Override
	@Transactional
	public List<QCExecutionResults> getQCExecutionResultsByQCExecution(Long qcExecutionId) {
		Session session = this.sessionFactory.getCurrentSession();
        String query = "from QCExecutionResults qcclr where qcclr.qcExecutionid.id =:qcExecutionId";
        List<QCExecutionResults> executionResults = session.createQuery(query).setLong("qcExecutionId", qcExecutionId).setCacheable(true).list();
        return executionResults;
	}

	@Override
	@Transactional
	public QCExecutionResults getQCExecutionResultsReportById(Long id) {
		Session session = this.sessionFactory.getCurrentSession();
		QCExecutionResults qcExecutionResults = session.load(QCExecutionResults.class, new Long(id));
		logger.info("QcCheckList loaded successfully, QcCheckList details=" + qcExecutionResults);
		return qcExecutionResults;
	}

	@Override
	@Transactional
	public QCExecutionResults deleteQCExecutionResultsById(QCExecutionResults qcExecutionResult) {
		Session session = this.sessionFactory.getCurrentSession();
		session.delete(qcExecutionResult);
		return qcExecutionResult;
	}



}
