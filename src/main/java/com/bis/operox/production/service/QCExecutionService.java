package com.bis.operox.production.service;

import java.util.List;

import org.json.JSONObject;
import org.springframework.web.multipart.MultipartFile;

import com.bis.operox.inv.dao.entity.User;
import com.bis.operox.production.dao.entity.QCExecution;

public interface QCExecutionService {

	QCExecution addQCExecution(QCExecution qcExecution);
	
	QCExecution getQCExecutionById(Long id);
	
	List<QCExecution> getQcExecutionList(Long workOrderId, String type);

	void addQCExcetion(JSONObject jsonObj, User user, MultipartFile multiFile) throws Exception;
	

	
}
