package com.bis.operox.production.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bis.operox.constants.Constants;
import com.bis.operox.inv.dao.entity.Product;
import com.bis.operox.inv.dao.entity.Store;
import com.bis.operox.inv.dao.entity.User;
import com.bis.operox.production.dao.QCCheckListDao;
import com.bis.operox.production.dao.QCCheckListReportDao;
import com.bis.operox.production.dao.QCExecutionDao;
import com.bis.operox.production.dao.QCExecutionResultsDao;
import com.bis.operox.production.dao.RawMaterialDao;
import com.bis.operox.production.dao.entity.QCCheckList;
import com.bis.operox.production.dao.entity.QCCheckListReport;
import com.bis.operox.production.dao.entity.QCExecution;
import com.bis.operox.production.dao.entity.QCExecutionResults;
import com.bis.operox.production.dao.entity.RawMaterial;
import com.bis.operox.production.dao.entity.WorkOrder;
import com.bis.operox.production.service.QCCheckListService;
import com.bis.operox.production.service.QCExecutionService;
import com.bis.operox.production.service.RawMaterialService;

@Service
public class QCExecutionServiceImpl implements QCExecutionService {

	
	@Autowired
	private QCExecutionDao qcExecutionDao;
	
	@Autowired
	private QCExecutionResultsDao qcExecutionResultsDao;
	
	@Autowired
	private QCCheckListDao qcCheckListDao;
	
	@Autowired
	RawMaterialDao rawMaterialDao;
	
	@Autowired
	QCCheckListReportDao  qcCheckListReportDao;
	
	@Override
	public QCExecution addQCExecution(QCExecution qcExecution) {
		return qcExecutionDao.addQCExecution(qcExecution);
	}

	@Override
	public QCExecution getQCExecutionById(Long id) {
		return qcExecutionDao.getQCExecutionById(id);
	}

	@Override
	public List<QCExecution> getQcExecutionList(Long workOrderId,String type) {
		return qcExecutionDao.getQcExecutionList(workOrderId,type);
	}

	@Override
	public void addQCExcetion(JSONObject jsonObj, User user, MultipartFile multiFile) throws Exception  {

		QCExecution qcExecution = null;
		String pattern = "MM/dd/yyyy";
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		if(jsonObj.has("executionId") && jsonObj.getString("executionId") != null && !"".equals(jsonObj.getString("executionId"))){
			qcExecution = qcExecutionDao.getQCExecutionById(Long.parseLong(jsonObj.getString("executionId")));
			List<QCExecutionResults> executionResults = qcExecutionResultsDao.getQCExecutionResultsByQCExecution(qcExecution.getId());
			for (QCExecutionResults executionResult : executionResults) {
				QCExecutionResults qcExecutionResult = qcExecutionResultsDao.getQCExecutionResultsReportById(executionResult.getId());
				qcExecutionResultsDao.deleteQCExecutionResultsById(qcExecutionResult);
			}
		}else{
			qcExecution = new QCExecution();
		}
		if(jsonObj.has("checkList") && jsonObj.getString("checkList") != null && !"".equals(jsonObj.getString("checkList"))){
			QCCheckList qcCheckList = qcCheckListDao.getQcCheckListById(Long.parseLong(jsonObj.getString("checkList")));
			qcExecution.setQcCheckList(qcCheckList);
			if(jsonObj.has("executionType") && jsonObj.getString("executionType") != null && !"".equals(jsonObj.getString("executionType"))){
				if(jsonObj.getString("executionType").equalsIgnoreCase("RawMaterial")){
					if(jsonObj.has("materialId") && jsonObj.getString("materialId") != null && !"".equals(jsonObj.getString("materialId"))){
						RawMaterial rawMaterial = new RawMaterial();
						rawMaterial.setId(Long.parseLong(jsonObj.getString("materialId")));
						qcExecution.setRawMaterial(rawMaterial);
					}
				}
				if(jsonObj.getString("executionType").equalsIgnoreCase("Products")){
					if(jsonObj.has("productId") && jsonObj.getString("productId") != null && !"".equals(jsonObj.getString("productId"))){
						Product product = new Product();
						product.setId(Long.parseLong(jsonObj.getString("productId")));
						qcExecution.setProduct(product);
					}
				}
			}
			if(jsonObj.has("startDate") && jsonObj.getString("startDate") != null && !"".equals(jsonObj.getString("startDate"))){
				Date startDate = format.parse(jsonObj.getString("startDate"));
				qcExecution.setStartDate(startDate);
			}
			if(jsonObj.has("endDate") && jsonObj.getString("endDate") != null && !"".equals(jsonObj.getString("endDate"))){
				Date endDate = format.parse(jsonObj.getString("endDate"));
				qcExecution.setEndDate(endDate);
			}
			
			if(jsonObj.has("totalQuantity") && jsonObj.getString("totalQuantity") != null && !"".equals(jsonObj.getString("totalQuantity"))){
				qcExecution.setTotalQuantity(Integer.parseInt(jsonObj.getString("totalQuantity")));
			}
			if(jsonObj.has("noOfPassedUnits") && jsonObj.getString("noOfPassedUnits") != null && !"".equals(jsonObj.getString("noOfPassedUnits"))){
				qcExecution.setNoOfPassedUnits(Integer.parseInt(jsonObj.getString("noOfPassedUnits")));
			}
			else{
				qcExecution.setNoOfPassedUnits(null);
			}
			if(jsonObj.has("noOfFailedUnits") && jsonObj.getString("noOfFailedUnits") != null && !"".equals(jsonObj.getString("noOfFailedUnits"))){
				qcExecution.setNoOfFailedUnits(Integer.parseInt(jsonObj.getString("noOfFailedUnits")));
			}
			else{
				qcExecution.setNoOfFailedUnits(null);
			}
			if(jsonObj.has("status") && jsonObj.getString("status") != null && !"".equals(jsonObj.getString("status"))){
				qcExecution.setQcStatus(jsonObj.getString("status"));
			}
			else{
				qcExecution.setQcStatus("");
			}
			if(jsonObj.has("workOrderId") && jsonObj.getString("workOrderId") != null && !"".equals(jsonObj.getString("workOrderId"))){
				WorkOrder workOrder = new WorkOrder();
				workOrder.setId(Long.parseLong(jsonObj.getString("workOrderId")));
				qcExecution.setWorkOrder(workOrder);
			}
			if(jsonObj.has("executionType") && jsonObj.getString("executionType") != null && !"".equals(jsonObj.getString("executionType"))){
				qcExecution.setQcExecutionType(jsonObj.getString("executionType"));
			}
			else{
				qcExecution.setQcExecutionType("");
			}
			
			if(jsonObj.has("description") && jsonObj.getString("description") != null && !"".equals(jsonObj.getString("description"))){
				qcExecution.setComment(jsonObj.getString("description"));
			}
			else{
				qcExecution.setComment("");
			}
			
			if(multiFile != null&& multiFile.getOriginalFilename()!= null && multiFile.getOriginalFilename()!= ""){
				qcExecution.setDocFile(multiFile.getBytes());
				qcExecution.setFileName(multiFile.getOriginalFilename());
				qcExecution.setFileContentType(multiFile.getContentType());
			 }
			qcExecution.setStatus(Constants.RECORD_ACTIVE);
			qcExecution.setCreatedDate(new Date());
			qcExecution.setCreatedBy(user.getUserCode());
			qcExecution.setUpdatedDate(new Date());
			qcExecution.setUpdatedBy(user.getUserCode());
			QCExecution mainQCExecution = qcExecutionDao.addQCExecution(qcExecution);
			if(mainQCExecution!=null){
				int maxValue =  Integer.parseInt(jsonObj.getString("maxValue"));
				for (int i = 1; i <= maxValue; i++) {
					QCExecutionResults subQCExecutionResultsSave = new QCExecutionResults();
					String checkReportIdVal = "checkReportId"+i;
					String actualValue = "actualValue"+i;
					String result = "result"+i;
					String remarks = "remarks"+i;
					if(jsonObj.has(checkReportIdVal) && jsonObj.getString(checkReportIdVal) != null && !"".equals(jsonObj.getString(checkReportIdVal))){
						QCCheckListReport qcCheckListReport = qcCheckListReportDao.getQcCheckListReportById(Long.parseLong(jsonObj.getString(checkReportIdVal)));
						subQCExecutionResultsSave.setQcCheckListReport(qcCheckListReport);
					}
					else{
						continue;
					}
					if(jsonObj.has(result) && jsonObj.getString(result) != null && !"".equals(jsonObj.getString(result))){
						subQCExecutionResultsSave.setResult(jsonObj.getString(result));
					}else{
						subQCExecutionResultsSave.setResult("");
					}
					
					if(jsonObj.has(actualValue) && jsonObj.getString(actualValue) != null && !"".equals(jsonObj.getString(actualValue))){
						subQCExecutionResultsSave.setActualValue(jsonObj.getString(actualValue));
					}else{
						subQCExecutionResultsSave.setActualValue("");
					}
					
					if(jsonObj.has(remarks) && jsonObj.getString(remarks) != null && !"".equals(jsonObj.getString(remarks))){
						subQCExecutionResultsSave.setRemarks(jsonObj.getString(remarks));
					}else{
						subQCExecutionResultsSave.setRemarks("");
					}
					
					subQCExecutionResultsSave.setStatus(1);
					subQCExecutionResultsSave.setQcExecutionid(mainQCExecution);
					subQCExecutionResultsSave.setCreatedBy(user.getUserCode());
					subQCExecutionResultsSave.setUpdatedBy(user.getUserCode());
					subQCExecutionResultsSave.setCreatedDate(new Date());
					subQCExecutionResultsSave.setUpdatedDate(new Date());
					qcExecutionResultsDao.addExecutionResults(subQCExecutionResultsSave);
				}
				
			}
		}
	}

}
