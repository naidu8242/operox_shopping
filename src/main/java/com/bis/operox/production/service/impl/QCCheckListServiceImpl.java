package com.bis.operox.production.service.impl;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bis.operox.constants.Constants;
import com.bis.operox.inv.dao.ProductDao;
import com.bis.operox.inv.dao.ProductStockDao;
import com.bis.operox.inv.dao.StoreDao;
import com.bis.operox.inv.dao.entity.Category;
import com.bis.operox.inv.dao.entity.Product;
import com.bis.operox.inv.dao.entity.Store;
import com.bis.operox.inv.dao.entity.Ticket;
import com.bis.operox.inv.dao.entity.User;
import com.bis.operox.inv.service.StoreService;
import com.bis.operox.production.dao.QCCheckListDao;
import com.bis.operox.production.dao.QCCheckListReportDao;
import com.bis.operox.production.dao.RawMaterialDao;
import com.bis.operox.production.dao.entity.QCCheckList;
import com.bis.operox.production.dao.entity.QCCheckListReport;
import com.bis.operox.production.dao.entity.RawMaterial;
import com.bis.operox.production.service.QCCheckListService;

@Service
public class QCCheckListServiceImpl implements QCCheckListService {

	@Autowired
	private QCCheckListDao qcCheckListDao;
	
	@Autowired
	private StoreDao storeDao;
	
	@Autowired
	private RawMaterialDao rawMaterialDao;
	
	@Autowired
	QCCheckListReportDao qcCheckListReportDao;
	
	@Autowired
	ProductDao productDao;

	@Override
	public QCCheckList addQcCheckList(QCCheckList qcCheckList) {
		return qcCheckListDao.addQcCheckList(qcCheckList);
	}

	@Override
	public QCCheckList getQcCheckListById(Long id) {
		return qcCheckListDao.getQcCheckListById(id);
	}

	@Override
	public List<QCCheckList> listQcCheckList() {
		return this.qcCheckListDao.listQcCheckList();
	}

	@Override
	public QCCheckList addWorkOrderCheckList(JSONObject jsonObj, User user,MultipartFile multiFile) throws Exception {
		
		QCCheckList qcCheckList = null;
		
		if(jsonObj.has("id") && jsonObj.getString("id") != null && !"".equals(jsonObj.getString("id"))){
			qcCheckList = qcCheckListDao.getQcCheckListById(Long.parseLong(jsonObj.getString("id")));
			List<QCCheckListReport> checklistReport = qcCheckListReportDao.getQcCheckReportByQCCheckList(qcCheckList.getId());
			for (QCCheckListReport checklist : checklistReport) {
				QCCheckListReport cheeckListReport = qcCheckListReportDao.getQcCheckListReportById(checklist.getId());
				qcCheckListReportDao.deleteqcCheckListReportById(cheeckListReport);
			}
		}else{
			qcCheckList = new QCCheckList();
		}
		if(jsonObj.has("rawChecklistName") && jsonObj.getString("rawChecklistName") != null && !"".equals(jsonObj.getString("rawChecklistName"))){
			qcCheckList.setCheckName(jsonObj.getString("rawChecklistName"));
			if(jsonObj.has("rawStore") && jsonObj.getString("rawStore") != null && !"".equals(jsonObj.getString("rawStore"))){
				Store store = storeDao.getStoreById(Long.parseLong(jsonObj.getString("rawStore")));
				qcCheckList.setStore(store);
			}
			if(jsonObj.has("rawMaterial") && jsonObj.getString("rawMaterial") != null && !"".equals(jsonObj.getString("rawMaterial"))){
				RawMaterial rawMaterial = rawMaterialDao.getRawMaterialById(Long.parseLong(jsonObj.getString("rawMaterial")));
				qcCheckList.setRawMaterial(rawMaterial);
			}
			if(jsonObj.has("rawDescription") && jsonObj.getString("rawDescription") != null && !"".equals(jsonObj.getString("rawDescription"))){
				qcCheckList.setDescription(jsonObj.getString("rawDescription"));
			}
			else{
				qcCheckList.setDescription("");
			}
			if(jsonObj.has("checkListType") && jsonObj.getString("checkListType") != null && !"".equals(jsonObj.getString("checkListType"))){
				qcCheckList.setCheckListType(jsonObj.getString("checkListType"));
			}
			else{
				qcCheckList.setCheckListType("");
			}
			if(multiFile != null&& multiFile.getOriginalFilename()!= null && multiFile.getOriginalFilename()!= ""){
				qcCheckList.setDocFile(multiFile.getBytes());
				qcCheckList.setFileName(multiFile.getOriginalFilename());
				qcCheckList.setFileContentType(multiFile.getContentType());
			 }
			qcCheckList.setStatus(Constants.RECORD_ACTIVE);
			qcCheckList.setCreatedDate(new Date());
			qcCheckList.setCreatedBy(user.getUserCode());
			qcCheckList.setUpdatedDate(new Date());
			qcCheckList.setUpdatedBy(user.getUserCode());
			QCCheckList mainQcCheck = qcCheckListDao.addQcCheckList(qcCheckList);
			if(mainQcCheck!=null){
				int maxRows =  Integer.parseInt(jsonObj.getString("maxTestRowNum"));
				for (int i = 1; i < maxRows; i++) {
					QCCheckList subQCCheck = new QCCheckList();
					subQCCheck.setId(mainQcCheck.getId());
					QCCheckListReport subQCCheckReportSave = new QCCheckListReport();
					String subRawTestName = i+"rawTest";
					String subRawProcedure = i+"rawProcedure";
					String subRawValue = i+"rawValue";
					if(jsonObj.has(subRawTestName) && jsonObj.getString(subRawTestName) != null && !"".equals(jsonObj.getString(subRawTestName))){
						subQCCheckReportSave.setWhatToCheck(jsonObj.getString(subRawTestName));
					}
					else{
						continue;
					}
					if(jsonObj.has(subRawProcedure) && jsonObj.getString(subRawProcedure) != null && !"".equals(jsonObj.getString(subRawProcedure))){
						subQCCheckReportSave.setHowToCheck(jsonObj.getString(subRawProcedure));
					}
					else{
						subQCCheckReportSave.setHowToCheck("");
					}
					if(jsonObj.has(subRawValue) && jsonObj.getString(subRawValue) != null && !"".equals(jsonObj.getString(subRawValue))){
						subQCCheckReportSave.setTestDescription(jsonObj.getString(subRawValue));
					}
					else{
						subQCCheckReportSave.setTestDescription("");
					}
					subQCCheckReportSave.setStatus(1);
					subQCCheckReportSave.setQcCheckList(subQCCheck);
					subQCCheckReportSave.setCreatedBy(user.getUserCode());
					subQCCheckReportSave.setUpdatedBy(user.getUserCode());
					subQCCheckReportSave.setCreatedDate(new Date());
					subQCCheckReportSave.setUpdatedDate(new Date());
					qcCheckListReportDao.addQcCheckListReport(subQCCheckReportSave);
				}
				
			}
		}
		return qcCheckList;
		
	}

	@Override
	public void addWorkOrderCheckListForProducts(JSONObject jsonObj, User user, MultipartFile multiFile) throws Exception {
		
		QCCheckList qcCheckList = null;
		
		if(jsonObj.has("id") && jsonObj.getString("id") != null && !"".equals(jsonObj.getString("id"))){
			qcCheckList = qcCheckListDao.getQcCheckListById(Long.parseLong(jsonObj.getString("id")));
			List<QCCheckListReport> checklistReport = qcCheckListReportDao.getQcCheckReportByQCCheckList(qcCheckList.getId());
			for (QCCheckListReport checklist : checklistReport) {
				QCCheckListReport cheeckListReport = qcCheckListReportDao.getQcCheckListReportById(checklist.getId());
				qcCheckListReportDao.deleteqcCheckListReportById(cheeckListReport);
			}
			
		}else{
			qcCheckList = new QCCheckList();
		}
		if(jsonObj.has("productChecklistName") && jsonObj.getString("productChecklistName") != null && !"".equals(jsonObj.getString("productChecklistName"))){
			qcCheckList.setCheckName(jsonObj.getString("productChecklistName"));
			if(jsonObj.has("productStore") && jsonObj.getString("productStore") != null && !"".equals(jsonObj.getString("productStore"))){
				Store store = storeDao.getStoreById(Long.parseLong(jsonObj.getString("productStore")));
				qcCheckList.setStore(store);
			}
			if(jsonObj.has("products") && jsonObj.getString("products") != null && !"".equals(jsonObj.getString("products"))){
				Product product = productDao.getProductById(Long.parseLong(jsonObj.getString("products")));
				qcCheckList.setProductid(product);
			}
			if(jsonObj.has("productDescription") && jsonObj.getString("productDescription") != null && !"".equals(jsonObj.getString("productDescription"))){
				qcCheckList.setDescription(jsonObj.getString("productDescription"));
			}
			if(jsonObj.has("checkListType") && jsonObj.getString("checkListType") != null && !"".equals(jsonObj.getString("checkListType"))){
				qcCheckList.setCheckListType(jsonObj.getString("checkListType"));
			}
			
			if(multiFile != null&& multiFile.getOriginalFilename()!= null && multiFile.getOriginalFilename()!= ""){
				qcCheckList.setDocFile(multiFile.getBytes());
				qcCheckList.setFileName(multiFile.getOriginalFilename());
				qcCheckList.setFileContentType(multiFile.getContentType());
			 }
			qcCheckList.setStatus(Constants.RECORD_ACTIVE);
			qcCheckList.setCreatedDate(new Date());
			qcCheckList.setCreatedBy(user.getUserCode());
			qcCheckList.setUpdatedDate(new Date());
			qcCheckList.setUpdatedBy(user.getUserCode());
			QCCheckList mainQcCheck = qcCheckListDao.addQcCheckList(qcCheckList);
			if(mainQcCheck!=null){
				int maxRows =  Integer.parseInt(jsonObj.getString("maxTestRowNumForProducts"));
				for (int i = 1; i < maxRows; i++) {
					QCCheckList subQCCheck = new QCCheckList();
					subQCCheck.setId(mainQcCheck.getId());
					QCCheckListReport subQCCheckReportSave = new QCCheckListReport();
					String subRawTestName = i+"ProductsTest";
					String subRawProcedure = i+"ProductsProcedure";
					String subRawValue = i+"ProductsValue";
					if(jsonObj.has(subRawTestName) && jsonObj.getString(subRawTestName) != null && !"".equals(jsonObj.getString(subRawTestName))){
						subQCCheckReportSave.setWhatToCheck(jsonObj.getString(subRawTestName));
					}
					else{
						continue;
					}
					if(jsonObj.has(subRawProcedure) && jsonObj.getString(subRawProcedure) != null && !"".equals(jsonObj.getString(subRawProcedure))){
						subQCCheckReportSave.setHowToCheck(jsonObj.getString(subRawProcedure));
					}
					if(jsonObj.has(subRawValue) && jsonObj.getString(subRawValue) != null && !"".equals(jsonObj.getString(subRawValue))){
						subQCCheckReportSave.setTestDescription(jsonObj.getString(subRawValue));
					}
					subQCCheckReportSave.setStatus(1);
					subQCCheckReportSave.setQcCheckList(subQCCheck);
					subQCCheckReportSave.setCreatedBy(user.getUserCode());
					subQCCheckReportSave.setUpdatedBy(user.getUserCode());
					subQCCheckReportSave.setCreatedDate(new Date());
					subQCCheckReportSave.setUpdatedDate(new Date());
					qcCheckListReportDao.addQcCheckListReport(subQCCheckReportSave);
				}
			}
		}
	}

	@Override
	public List<QCCheckList> getQcCheckListByRawMaterialId(Long materialId) {
		return qcCheckListDao.getQcCheckListByRawMaterialId(materialId);
	}

	@Override
	public List<QCCheckList> getQcCheckListByProductId(Long productId) {
		return qcCheckListDao.getQcCheckListByProductId(productId);
	}
}
