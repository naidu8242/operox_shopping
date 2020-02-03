package com.bis.operox.inv.dao;

import java.util.List;

import com.bis.operox.inv.dao.entity.Product;

public interface ProductDao {

    public Product addProduct(Product product);
	
	public Product getProductById(Long id);

	public List<Product> getAllProducts(Long companyId);
	
	public List<Product> getAll();
	
	public List<Product> getProductsByCategoryId(Long categoryId);

	public boolean getProductByProductName(String productName);
	
	public List<Product> getAllOfferProducts(Long companyId);
	
	public List<Product> getEcommersProductsListByIds(List<Long> projectIds);
	
	
	
}