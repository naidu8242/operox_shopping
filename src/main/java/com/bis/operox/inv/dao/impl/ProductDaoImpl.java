package com.bis.operox.inv.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bis.operox.inv.dao.ProductDao;
import com.bis.operox.inv.dao.entity.Product;

@Repository
public class ProductDaoImpl implements ProductDao {

	@Autowired
	private SessionFactory sessionFactory;

	private static final Logger logger = LoggerFactory.getLogger(ProductDaoImpl.class);

	
	@Override
	@Transactional
	public Product addProduct(Product product) {
		Session session = this.sessionFactory.getCurrentSession();
		session.saveOrUpdate(product);
		logger.info("Product saved successfully, Product Details="+product);
		return product;
		
	}

	@Override
	@Transactional
	public Product getProductById(Long productID) {
		Session session = this.sessionFactory.getCurrentSession();	
		String query = "from Product prod where prod.id = :productID and prod.status='1'";
		List<Product> productsList = session.createQuery(query).setLong("productID", productID).setCacheable(true).list();
		return productsList.get(0);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Product> getAllProducts(Long companyId) {
		Session session = this.sessionFactory.getCurrentSession();
		 Integer status = 1;
		String query = "from Product prod where prod.companyId = :companyID and prod.status = :status";
		List<Product> productsList = session.createQuery(query).setLong("companyID", companyId).setInteger("status", status).setCacheable(true).list();
		return productsList;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Product> getAll() {
		Session session = this.sessionFactory.getCurrentSession();
		 Integer status = 1;
		String query = "from Product prod where prod.status = :status";
		List<Product> productsList = session.createQuery(query).setInteger("status", status).setCacheable(true).list();
		return productsList;
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Product> getProductsByCategoryId(Long categoryId) {
		Session session = this.sessionFactory.getCurrentSession();
		 Integer status = 1;
		String query = "from Product prod where prod.status = :status and prod.subCategory.id = :categoryId";
		List<Product> productsList = session.createQuery(query).setInteger("status", status).setLong("categoryId", categoryId).setCacheable(true).list();
		return productsList;
	}
	

	@Override
	@Transactional
	public boolean getProductByProductName(String productName) {
		boolean isValidProduct = true;
		Session session = this.sessionFactory.getCurrentSession();
		String query = "from Product prod where prod.productName = :productName and prod.status='1'";
		List<Product> productsList = session.createQuery(query).setString("productName", productName).setCacheable(true).list();
		if(productsList != null && productsList.size() > 0){
			isValidProduct = false;
		}
		return isValidProduct;
	}

	@Override
	@Transactional
	public List<Product> getAllOfferProducts(Long companyId) {
		Session session = this.sessionFactory.getCurrentSession();
		String query = "from Product prod where prod.id NOT IN (select offerBuyxGetY.buyItemProduct.id from OfferBuyxGetY offerBuyxGetY where "
				+ " offerBuyxGetY.offer.company.id = :companyId and offerBuyxGetY.offer.status = '1' ) ";
		List<Product> productsList = session.createQuery(query).setLong("companyId", companyId).setCacheable(true).list();
		return productsList;
	}

	@Override
	@Transactional
	public List<Product> getEcommersProductsListByIds(List<Long> projectIds) {
			
		Session session = this.sessionFactory.getCurrentSession();
		String query = "from Product prod where prod.id IN (:projectIds) ";
		List<Product> productsList = session.createQuery(query).setParameterList("projectIds", projectIds).list();
		return productsList;
		
	}
}