package com.bis.operox.production.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bis.operox.production.dao.QCCheckListReportDao;
import com.bis.operox.production.dao.entity.QCCheckListReport;
import com.bis.operox.production.service.QCCheckListReportService;

@Service
public class QCCheckListReportServiceImpl implements QCCheckListReportService {

	@Autowired
	private QCCheckListReportDao qcCheckListReportDao;

	@Override
	public QCCheckListReport addQcCheckListReport(QCCheckListReport qcCheckListReport) {
		return qcCheckListReportDao.addQcCheckListReport(qcCheckListReport);
	}

	@Override
	public QCCheckListReport getQcCheckListReportById(Long id) {
		return qcCheckListReportDao.getQcCheckListReportById(id);
	}

	@Override
	public List<QCCheckListReport> listQcCheckListReport() {
		return qcCheckListReportDao.listQcCheckListReport();
	}
	
	@Override
    public Integer getQcCheckListCountById(Long id) {
        return qcCheckListReportDao.getQcCheckListCountById(id);
    }

	@Override
	public List<QCCheckListReport> getQcCheckReportByQCCheckList(Long id) {
		return qcCheckListReportDao.getQcCheckReportByQCCheckList(id);
	}

}
