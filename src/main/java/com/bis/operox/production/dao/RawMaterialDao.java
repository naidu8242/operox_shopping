package com.bis.operox.production.dao;

import java.util.List;

import com.bis.operox.production.dao.entity.RawMaterial;

public interface RawMaterialDao {
	

	RawMaterial addRawMaterial(RawMaterial rawMaterial);
	
	RawMaterial getRawMaterialById(Long id);

	List<RawMaterial> rawMaterialsList();
	
	public List<RawMaterial> getAllRawMaterialByStatus(Integer status) throws Exception;
	
	public boolean isRawMaterialCodeValid(String rawMaterialCode ,Long id) throws Exception;

}