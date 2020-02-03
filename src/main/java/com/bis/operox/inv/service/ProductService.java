package com.bis.operox.inv.service;

import java.util.List;

import org.json.JSONObject;
import org.springframework.web.multipart.MultipartFile;

import com.bis.operox.inv.dao.entity.Company;
import com.bis.operox.inv.dao.entity.Product;
import com.bis.operox.inv.dao.entity.User;
/**
 * 
 * @author Prasad K
 *
 */
public interface ProductService  {

public Product addProduct(Product product);
	
	public Product getProductById(Long id);

	public List<Product> getAllProducts(Long companyId);
	
	public List<Product> getAll();
	
	public List<Product> getProductsByCategoryId(Long categoryId);
	
	public void addProductDetails(JSONObject jsonObj, User user, MultipartFile multiFile) throws Exception;

	public boolean getProductByProductName(String productName);
	
	public List<Product> getAllOfferProducts(Long companyId);
	
	public List<Product> getEcommersProductsListByIds(List<Long> projectIds);

}	
		
	
