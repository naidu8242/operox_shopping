package com.bis.operox.production.dao;

import java.util.List;

import com.bis.operox.production.dao.entity.QCCheckList;

public interface QCCheckListDao {

	QCCheckList addQcCheckList(QCCheckList qcCheckList);
	
	QCCheckList getQcCheckListById(Long id);
	
	List<QCCheckList> listQcCheckList();

	List<QCCheckList> getQcCheckListByRawMaterialId(Long materialId);

	List<QCCheckList> getQcCheckListByProductId(Long productId);
	
}