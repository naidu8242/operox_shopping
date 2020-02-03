package com.bis.operox.production.service.impl;

import java.util.Date;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.bis.operox.constants.Constants;
import com.bis.operox.inv.dao.entity.MeasuringUnit;
import com.bis.operox.inv.dao.entity.Store;
import com.bis.operox.inv.dao.entity.User;
import com.bis.operox.inv.service.UserService;
import com.bis.operox.production.dao.RawMaterialDao;
import com.bis.operox.production.dao.entity.RawMaterial;
import com.bis.operox.production.service.RawMaterialService;


@Service
public class RawMaterialServiceImpl implements RawMaterialService{
	
	@Autowired
	private RawMaterialDao rawMaterialDao;
	
	@Value("${operoxUrl}")
    private String operoxUrl;
	
	@Autowired
	UserService userService;
	
	
	@Override
	public RawMaterial addRawMaterial(RawMaterial rawMaterial) {
		return rawMaterialDao.addRawMaterial(rawMaterial);
	}

	@Override
	public RawMaterial getRawMaterialById(Long id) {
		return rawMaterialDao.getRawMaterialById(id);
	}

	@Override
	public List<RawMaterial> rawMaterialsList() {
		return this.rawMaterialDao.rawMaterialsList();
	}

	@Override
	public void addRawMaterialDetails(JSONObject jsonObj,User user) throws Exception {
		RawMaterial rawMaterial = null;
		
		if(jsonObj.has("rawMaterialId") && jsonObj.getString("rawMaterialId") != null && !"".equals(jsonObj.getString("rawMaterialId"))){
			rawMaterial = rawMaterialDao.getRawMaterialById(Long.parseLong(jsonObj.getString("rawMaterialId")));
		}else{
			rawMaterial = new RawMaterial();
		}
		
		if(jsonObj.has("materialName") && jsonObj.getString("materialName") != null && !"".equals(jsonObj.getString("materialName"))){
		rawMaterial.setMaterialName(jsonObj.getString("materialName"));
		}
		
		if(jsonObj.has("availableInventory") && jsonObj.getString("availableInventory") != null && !"".equals(jsonObj.getString("availableInventory"))){
		rawMaterial.setAvailableInventory(jsonObj.getString("availableInventory"));
		}
		
		if(jsonObj.has("annualOrderQuantity") && jsonObj.getString("annualOrderQuantity") != null && !"".equals(jsonObj.getString("annualOrderQuantity"))){
		rawMaterial.setAnnualOrderQuantity(Integer.parseInt(jsonObj.getString("annualOrderQuantity")));
		}
		
		if(jsonObj.has("discount") && jsonObj.getString("discount") != null && !"".equals(jsonObj.getString("discount"))){
		rawMaterial.setDiscount(jsonObj.getString("discount"));
		}
		
		if(jsonObj.has("searchCode") && jsonObj.getString("searchCode") != null && !"".equals(jsonObj.getString("searchCode"))){
		rawMaterial.setSearchCode(jsonObj.getString("searchCode"));
		}
		
		if(jsonObj.has("unitPrice") && jsonObj.getString("unitPrice") != null && !"".equals(jsonObj.getString("unitPrice"))){
		rawMaterial.setUnitPrice(jsonObj.getString("unitPrice"));
		}
		
		if(jsonObj.has("description") && jsonObj.getString("description") != null && !"".equals(jsonObj.getString("description"))){
		rawMaterial.setDescription(jsonObj.getString("description"));
		}
		
		if(jsonObj.has("measuringUnit") && jsonObj.getString("measuringUnit") != null && !"".equals(jsonObj.getString("measuringUnit"))){
			MeasuringUnit mu = new MeasuringUnit();
			mu.setId(Long.parseLong((jsonObj.getString("measuringUnit"))));
		    rawMaterial.setMeasuringUnit(mu);
		}
		
		
	  	
		if(jsonObj.has("isPercentage") && jsonObj.getString("isPercentage") != null && !"".equals(jsonObj.getString("isPercentage"))){
		   rawMaterial.setIsPercentage(jsonObj.getString("isPercentage"));
		}
		
		
		if(jsonObj.has("store") && jsonObj.getString("store") != null && !"".equals(jsonObj.getString("store"))){
			Store store = new Store();
			store.setId(Long.parseLong(jsonObj.getString("store")));
			rawMaterial.setStore(store);
		}
		
		rawMaterial.setStatus(Constants.Active);
		rawMaterial.setCreatedDate(new Date());
		rawMaterial.setUpdatedDate(new Date());
		rawMaterial.setUpdatedBy(user.getUserCode());
		rawMaterial.setCreatedBy(user.getUserCode());
		
		
		rawMaterialDao.addRawMaterial(rawMaterial);
		
		}

	
	
		@Override
		public List<RawMaterial> getAllRawMaterialByStatus(Integer status) throws Exception {
			return rawMaterialDao.getAllRawMaterialByStatus(status);
		}

		@Override
		public RawMaterial saveOrUpdateRawMaterial(RawMaterial rawMaterial) throws Exception {
			return rawMaterialDao.addRawMaterial(rawMaterial);
		}

		@Override
		public boolean isRawMaterialCodeValid(String rawMaterialCode, Long id) throws Exception {
			return rawMaterialDao.isRawMaterialCodeValid(rawMaterialCode, id);
		}

}
