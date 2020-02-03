package com.bis.operox.inv.service.impl;

import java.util.Date;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bis.operox.inv.dao.BrandDao;
import com.bis.operox.inv.dao.entity.Brand;
import com.bis.operox.inv.dao.entity.Category;
import com.bis.operox.inv.dao.entity.User;
import com.bis.operox.inv.service.BrandService;

@Service
public class BrandServiceImpl implements BrandService{

	@Autowired
	private BrandDao brandDao;
	
	@Override
	public Brand addBrand(Brand brand) {
		return brandDao.addBrand(brand);
	}

	@Override
	public Brand getBrandById(Long id) {
		return brandDao.getBrandById(id);
	}
	
	@Override
	public List<Brand> getBrandByCatagoryId(Long catagoryId) {
		return brandDao.getBrandByCatagoryId(catagoryId);
	}

	@Override
	public List<Brand> listBrands() {
		return brandDao.listBrands();
	}

	@Override
	public void addBrandsDetails(JSONObject jsonObj, User user) throws Exception {
		Brand brand = null;
		if(jsonObj.has("id") && jsonObj.getString("id") != null && !"".equals(jsonObj.getString("id"))){
			brand = brandDao.getBrandById(Long.parseLong(jsonObj.getString("id")));
		}else{
			brand = new Brand();
		}
		if(jsonObj.has("brandName") && jsonObj.getString("brandName") != null && !"".equals(jsonObj.getString("brandName"))){
			brand.setBrandName(jsonObj.getString("brandName"));
		}
		else{
			brand.setBrandName("");
		}
		if(jsonObj.has("brandDescription") && jsonObj.getString("brandDescription") != null && !"".equals(jsonObj.getString("brandDescription"))){
			brand.setDescription(jsonObj.getString("brandDescription"));
		}
		else{
			brand.setDescription("");
		}
		if(jsonObj.has("manufacturedBy") && jsonObj.getString("manufacturedBy") != null && !"".equals(jsonObj.getString("manufacturedBy"))){
			brand.setManufacturedBy(jsonObj.getString("manufacturedBy"));
		}
		else{
			brand.setManufacturedBy("");
		}
		if(jsonObj.has("marketedBy") && jsonObj.getString("marketedBy") != null && !"".equals(jsonObj.getString("marketedBy"))){
			brand.setMarketedBy(jsonObj.getString("marketedBy"));
		}
		else{
			brand.setMarketedBy("");
		}
		/*if(jsonObj.has("category") && jsonObj.getString("category") != null && !"".equals(jsonObj.getString("category"))){
			Category category = new Category();
			category.setId(Long.parseLong(jsonObj.getString("category")));
			brand.setCategory(category);
		}*/
		
		brand.setStatus(1);
		brand.setCreatedDate(new Date());
		brand.setUpdatedDate(new Date());
		brand.setCreatedBy(user.getUserCode());
		brand.setUpdatedBy(user.getUserCode());
		brandDao.addBrand(brand);	
	}

	@Override
	public Boolean getBrandByBrandName(String brandName) {
		return brandDao.getBrandByBrandName(brandName);
	}

	
	
}
