package com.bis.operox.inv.dao;

import java.util.List;

import com.bis.operox.inv.dao.entity.Brand;

public interface BrandDao {

	 Brand addBrand(Brand brand);
	
	 Brand getBrandById(Long id);
	 
	 public List<Brand> getBrandByCatagoryId(Long catagoryId);

	 List<Brand> listBrands();

	Boolean getBrandByBrandName(String brandName);
}
