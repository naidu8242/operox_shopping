package com.bis.operox.inv.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bis.operox.constants.Constants;
import com.bis.operox.inv.dao.BrandDao;
import com.bis.operox.inv.dao.entity.Brand;
import com.bis.operox.inv.dao.entity.Customer;

@Repository
public class BrandDaoImpl implements BrandDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	private static final Logger logger = LoggerFactory.getLogger(BrandDaoImpl.class);
	
	@Override
	@Transactional
	public Brand addBrand(Brand brand) {
			Session session = this.sessionFactory.getCurrentSession();
			session.saveOrUpdate(brand);
			logger.info("Brand saved successfully, Brand Details="+brand);
			return brand;
	}

	@Override
	@Transactional
	public Brand getBrandById(Long brandId) {
		Session session = this.sessionFactory.getCurrentSession();	
		 String query = "from Brand brand where brand.id = :brandId";
	     List<Brand> customerList = session.createQuery(query).setLong("brandId", brandId).setCacheable(true).list();
	    return customerList.get(0);
	}
	
	@Override
	@Transactional
	public List<Brand> getBrandByCatagoryId(Long catagoryId) {
		Session session = this.sessionFactory.getCurrentSession();	
		String query = "from Brand b where b.category.id =:catagoryId and b.status = "+Constants.RECORD_ACTIVE+"";
		List<Brand> brandList = session.createQuery(query).setLong("catagoryId", catagoryId).setCacheable(true).list();
		return brandList;
	}


	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Brand> listBrands() {
		Session session = this.sessionFactory.getCurrentSession();
		List<Brand> brandsList = session.createQuery("from Brand brand where brand.status = "+Constants.RECORD_ACTIVE+"").list();
		
		return brandsList;
	}

	@Override
	@Transactional
	public Boolean getBrandByBrandName(String brandName) {
		boolean isValidBrand = true;
		Integer status = 1;
		Session session = this.sessionFactory.getCurrentSession();
		String query = "from Brand brand where brand.brandName = :brandName and brand.status = :status";
		List<Brand> brandList = session.createQuery(query).setString("brandName", brandName).setInteger("status", status).setCacheable(true).list();
		if(brandList != null && brandList.size() > 0){
			isValidBrand = false;
		}
		return isValidBrand;
	}

	
}
