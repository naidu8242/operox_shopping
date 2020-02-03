package com.bis.operox.production.service;

import java.util.List;

import com.bis.operox.production.dao.entity.QCCheckListReport;

public interface QCCheckListReportService {
	
	QCCheckListReport addQcCheckListReport(QCCheckListReport qcCheckListReport);
	
	QCCheckListReport getQcCheckListReportById(Long id);
	
	List<QCCheckListReport> listQcCheckListReport();
	
	Integer getQcCheckListCountById(Long id);
	
	List<QCCheckListReport> getQcCheckReportByQCCheckList(Long id);
}
