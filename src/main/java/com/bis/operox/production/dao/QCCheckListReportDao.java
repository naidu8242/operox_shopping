package com.bis.operox.production.dao;

import java.util.List;

import com.bis.operox.production.dao.entity.QCCheckListReport;

public interface QCCheckListReportDao {

	QCCheckListReport addQcCheckListReport(QCCheckListReport qcCheckListReport);
	
	QCCheckListReport getQcCheckListReportById(Long id);
	
	List<QCCheckListReport> listQcCheckListReport();
	
	Integer getQcCheckListCountById(Long id);

	List<QCCheckListReport> getQcCheckReportByQCCheckList(Long id);

	void deleteqcCheckListReportById(QCCheckListReport checkListReport);
	
	
}