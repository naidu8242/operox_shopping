package com.bis.operox.production.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bis.operox.production.dao.QCCheckListReportDao;
import com.bis.operox.production.dao.entity.QCCheckListReport;

@Repository
public class QCCheckListReportDaoImpl implements QCCheckListReportDao {

	@Autowired
	private SessionFactory sessionFactory;

	private static final Logger logger = LoggerFactory.getLogger(QCCheckListReportDaoImpl.class);

	@Override
	@Transactional
	public QCCheckListReport addQcCheckListReport(QCCheckListReport qcCheckListReport) {
		Session session = this.sessionFactory.getCurrentSession();
		session.saveOrUpdate(qcCheckListReport);
		logger.info("QcCheckList saved successfully, QcCheckList Details=" + qcCheckListReport);
		return qcCheckListReport;
	}

	@Override
	@Transactional
	public QCCheckListReport getQcCheckListReportById(Long id) {
		Session session = this.sessionFactory.getCurrentSession();
		QCCheckListReport qcCheckListReport = session.load(QCCheckListReport.class, new Long(id));
		logger.info("QcCheckList loaded successfully, QcCheckList details=" + qcCheckListReport);
		return qcCheckListReport;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<QCCheckListReport> listQcCheckListReport() {
		Session session = this.sessionFactory.getCurrentSession();
		List<QCCheckListReport> listQcCheckListReport = session.createQuery("from QcCheckList").list();
		return listQcCheckListReport;
	}
	
	@Override
    @Transactional
    public Integer getQcCheckListCountById(Long id) {
        Session session = this.sessionFactory.getCurrentSession();
        String query = "from QCCheckListReport qcclr where qcclr.qcCheckList.id =:qcCheckListId";
        List<QCCheckListReport> listQcCheckListReport = session.createQuery(query).setLong("qcCheckListId", id).setCacheable(true).list();
        return listQcCheckListReport.size();
    }

	@Override
    @Transactional
    public List<QCCheckListReport> getQcCheckReportByQCCheckList(Long id) {
        Session session = this.sessionFactory.getCurrentSession();
        String query = "from QCCheckListReport qcclr where qcclr.qcCheckList.id =:qcCheckListId";
        List<QCCheckListReport> listQcCheckListReport = session.createQuery(query).setLong("qcCheckListId", id).setCacheable(true).list();
        return listQcCheckListReport;
    }

	@Override
	@Transactional
	public void deleteqcCheckListReportById(QCCheckListReport checkListReport) {
		Session session = this.sessionFactory.getCurrentSession();
		session.delete(checkListReport);
	}
}