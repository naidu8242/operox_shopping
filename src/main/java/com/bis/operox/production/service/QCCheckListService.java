package com.bis.operox.production.service;

import java.io.IOException;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.multipart.MultipartFile;

import com.bis.operox.inv.dao.entity.User;
import com.bis.operox.production.dao.entity.QCCheckList;

public interface QCCheckListService {

	QCCheckList addQcCheckList(QCCheckList qcCheckList);
	
	QCCheckList getQcCheckListById(Long id);
	
	List<QCCheckList> listQcCheckList();

	QCCheckList addWorkOrderCheckList(JSONObject jsonObj, User user, MultipartFile multiFile) throws  Exception;

	void addWorkOrderCheckListForProducts(JSONObject jsonObj, User user, MultipartFile multiFile) throws Exception;

	List<QCCheckList> getQcCheckListByRawMaterialId(Long materialId);

	List<QCCheckList> getQcCheckListByProductId(Long productId);
	
	
}
