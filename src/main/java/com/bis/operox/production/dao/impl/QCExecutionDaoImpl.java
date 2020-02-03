package com.bis.operox.production.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bis.operox.production.dao.QCExecutionDao;
import com.bis.operox.production.dao.entity.QCExecution;

@Repository
public class QCExecutionDaoImpl implements QCExecutionDao {

	@Autowired
	private SessionFactory sessionFactory;

	private static final Logger logger = LoggerFactory.getLogger(QCExecutionDaoImpl.class);

	@Override
	@Transactional
	public QCExecution addQCExecution(QCExecution qcExecution) {
		Session session = this.sessionFactory.getCurrentSession();
		session.saveOrUpdate(qcExecution);
		return qcExecution;
	}

	@Override
	@Transactional
	public QCExecution getQCExecutionById(Long id) {
		Session session = this.sessionFactory.getCurrentSession();	
		String query = "from QCExecution qce  where qce.id = :id";  
		List<QCExecution> qcexecutionList = session.createQuery(query).setLong("id", new Long(id)).setCacheable(true).list();
		QCExecution qcExecution = null;
	    if(qcexecutionList != null && !qcexecutionList.isEmpty()){
	    	qcExecution = qcexecutionList.iterator().next();
	    }
	    return qcExecution;
	}

	@Override
	@Transactional
	public List<QCExecution> getQcExecutionList(Long workOrderId,String type) {
		Session session = this.sessionFactory.getCurrentSession();
        Integer status = 1;
        String query = "from QCExecution qce left join fetch qce.rawMaterial left join fetch qce.qcCheckList left join fetch qce.product where qce.workOrder.id =:workOrderId and qce.status = :status and qce.qcExecutionType =:type";
        List<QCExecution> qcexecutionList = session.createQuery(query).setInteger("status", status).setLong("workOrderId", workOrderId).setString("type", type).setCacheable(true).list();
        return qcexecutionList;
		
	}
	
}
