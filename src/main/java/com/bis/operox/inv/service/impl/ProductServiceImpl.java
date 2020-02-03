package com.bis.operox.inv.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bis.operox.inv.dao.ProductDao;
import com.bis.operox.inv.dao.entity.Address;
import com.bis.operox.inv.dao.entity.Brand;
import com.bis.operox.inv.dao.entity.Category;
import com.bis.operox.inv.dao.entity.Company;
import com.bis.operox.inv.dao.entity.MeasuringUnit;
import com.bis.operox.inv.dao.entity.Product;
import com.bis.operox.inv.dao.entity.Store;
import com.bis.operox.inv.dao.entity.User;
import com.bis.operox.inv.service.ProductService;

/**
 * This Service Class is used to get Product Object 
 * 
 * @author: Prasad K
 * @date: 22-sept-2016
 */

@Service
public class ProductServiceImpl implements ProductService {

	private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

	@Autowired
	private ProductDao productDao;
	

	@Override
	public Product addProduct(Product product) {
		return productDao.addProduct(product);
	}
	
	@Override
	public Product getProductById(Long id) {
		return this.productDao.getProductById(id);
	}

	@Override
	public List<Product> getAllProducts(Long companyId) {
		return this.productDao.getAllProducts(companyId);
	}
	
	@Override
	public List<Product> getAll() {
		return this.productDao.getAll();
	}
	
	@Override
	public List<Product> getProductsByCategoryId(Long categoryId) {
		return this.productDao.getProductsByCategoryId(categoryId);
	}

	@Override
	public void addProductDetails(JSONObject jsonObj,User user,MultipartFile multiFile) throws Exception {
		Product product = null;
		if(jsonObj.has("pid") && jsonObj.getString("pid") != null && !"".equals(jsonObj.getString("pid"))){
			product = productDao.getProductById(Long.parseLong(jsonObj.getString("pid")));
		}else{
			product = new Product();
		}
		/*product.setProductId(jsonObj.getString("productId"));*/
		product.setProductName(jsonObj.getString("productName"));
		if(jsonObj.has("searchCode") && StringUtils.isNotBlank(jsonObj.getString("searchCode")) && StringUtils.isNotEmpty(jsonObj.getString("searchCode"))){
			product.setProductCode(jsonObj.getString("searchCode"));
		}
		else{
			product.setProductCode("");
		}
		if(jsonObj.has("category") && StringUtils.isNotBlank(jsonObj.getString("category")) && StringUtils.isNotEmpty(jsonObj.getString("category"))){
			Category category = new Category();
			category.setId(Long.parseLong(jsonObj.getString("category")));
			product.setCategory(category);
		}
		if(jsonObj.has("subCategory") && StringUtils.isNotBlank(jsonObj.getString("subCategory")) && StringUtils.isNotEmpty(jsonObj.getString("subCategory"))){
			Category subCategory = new Category();
			subCategory.setId(Long.parseLong(jsonObj.getString("subCategory")));
			product.setSubCategory(subCategory);
		}
		if(jsonObj.has("measuringUnit") && StringUtils.isNotBlank(jsonObj.getString("measuringUnit")) && StringUtils.isNotEmpty(jsonObj.getString("measuringUnit"))){
			MeasuringUnit measuringUnit = new MeasuringUnit();
			measuringUnit.setId(Long.parseLong(jsonObj.getString("measuringUnit")));
			product.setMeasuringUnit(measuringUnit);
		}
		if(jsonObj.has("brand") && StringUtils.isNotBlank(jsonObj.getString("brand")) && StringUtils.isNotEmpty(jsonObj.getString("brand"))){
			Brand brand = new Brand();
			brand.setId(Long.parseLong(jsonObj.getString("brand")));
			product.setBrand(brand);
		}
		if(jsonObj.has("manufaturedBy") && StringUtils.isNotBlank(jsonObj.getString("manufaturedBy")) && StringUtils.isNotEmpty(jsonObj.getString("manufaturedBy"))){
			product.setManufacturedBy(jsonObj.getString("manufaturedBy"));
		}
		else{
			product.setManufacturedBy("");
		}
		if(jsonObj.has("marketedBy") && StringUtils.isNotBlank(jsonObj.getString("marketedBy")) && StringUtils.isNotEmpty(jsonObj.getString("marketedBy"))){
			product.setMarketedBy(jsonObj.getString("marketedBy"));
		}
		else{
			product.setMarketedBy("");
		}
		product.setCompanyId(user.getStore().getCompany());
		product.setStatus(1);
		product.setCreatedDate(new Date());
		product.setUpdatedDate(new Date());
		
		if(null != multiFile.getContentType() && !multiFile.getContentType().equalsIgnoreCase("application/octet-stream")){
	    	  try {
	    		  product.setFileContentType(multiFile.getContentType());
	    		  product.setDocFile(multiFile.getBytes());
	    	   
		       }catch (Exception e) {
	    	      	 e.printStackTrace();
				    }
	        }
		
		productDao.addProduct(product);
	}

	public boolean getProductByProductName(String productName){
		return productDao.getProductByProductName(productName);
		
	}

	@Override
	public List<Product> getAllOfferProducts(Long companyId) {
		return productDao.getAllOfferProducts(companyId);
	}

	@Override
	public List<Product> getEcommersProductsListByIds(List<Long> projectIds) {
		return productDao.getEcommersProductsListByIds(projectIds);
	}
}
