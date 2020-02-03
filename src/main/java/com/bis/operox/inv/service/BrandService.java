package com.bis.operox.inv.service;

import java.util.List;

import org.json.JSONObject;

import com.bis.operox.inv.dao.entity.Brand;
import com.bis.operox.inv.dao.entity.User;

public interface BrandService {

	 Brand addBrand(Brand brand);
	
	 Brand getBrandById(Long id);
	 
	 List<Brand> getBrandByCatagoryId(Long catagoryId);

	 List<Brand> listBrands();
	 
	 public void addBrandsDetails(JSONObject jsonObj, User user) throws Exception;

	Boolean getBrandByBrandName(String brandName);
	
	
	 
}
