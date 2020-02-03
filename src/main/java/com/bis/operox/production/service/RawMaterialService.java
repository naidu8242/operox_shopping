package com.bis.operox.production.service;

import java.util.List;

import org.json.JSONObject;

import com.bis.operox.inv.dao.entity.User;
import com.bis.operox.production.dao.entity.RawMaterial;

public interface RawMaterialService {

	
	RawMaterial addRawMaterial(RawMaterial rawMaterial);
	
	RawMaterial getRawMaterialById(Long id);

	List<RawMaterial> rawMaterialsList();
	
	public List<RawMaterial> getAllRawMaterialByStatus(Integer status) throws Exception;
	
	void addRawMaterialDetails(JSONObject jsonObj, User user) throws Exception;
	
	public RawMaterial saveOrUpdateRawMaterial(RawMaterial rawMaterial) throws Exception;
	
	public boolean isRawMaterialCodeValid(String rawMaterialCode ,Long id) throws Exception;

}
