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
import com.bis.operox.inv.dao.CategoryDao;
import com.bis.operox.inv.dao.entity.Brand;
import com.bis.operox.inv.dao.entity.Category;
import com.bis.operox.inv.dao.entity.User;

@Repository
public class CategoryDaoImpl implements CategoryDao{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private static final Logger logger = LoggerFactory.getLogger(CategoryDaoImpl.class);

	@Override
	@Transactional
	public Category addCategory(Category category) {
		Session session = this.sessionFactory.getCurrentSession();
		session.saveOrUpdate(category);
		logger.info("category saved successfully, category Details="+category);
		return category;
	}


	@Override
	@Transactional
	public Category getCategoryById(Long id) {
		Session session = this.sessionFactory.getCurrentSession();	
		Category category = session.load(Category.class, new Long(id));
		logger.info("Store loaded successfully, Store details="+category);
		return category;
	}


	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Category> listCategories() {
		Session session = this.sessionFactory.getCurrentSession();
		return session.createQuery("from Category cat where cat.parentCategory.id IS NULL and cat.status = "+Constants.RECORD_ACTIVE+"").list();
	}
	
	@Override
	@Transactional
	public List<Category> getsubCategoriesList(Long categoryId) {
		Session session = this.sessionFactory.getCurrentSession();
		String query = "from Category cat where cat.parentCategory.id=:categoryId";
		List<Category> subCategoriesList = session.createQuery(query.toString()).setLong("categoryId", categoryId).setCacheable(true).list();
		return subCategoriesList;
	}


		@Override
		@Transactional
		public List<Category> listOfCategories(User user) {
			Session session = this.sessionFactory.getCurrentSession();
			String query = "from Category cat where cat.parentCategory.id IS NULL and cat.updatedBy=:userCode and cat.status = "+Constants.RECORD_ACTIVE+"";
			List<Category> subCategoriesList = session.createQuery(query.toString()).setString("userCode", user.getUserCode()).setCacheable(true).list();
			return subCategoriesList;
		}


		@Override
		@Transactional
		public void deleteCategory(Category category) {
			Session session = this.sessionFactory.getCurrentSession();
			session.delete(category);
		}


		@Override
		@Transactional
		public Boolean getCatgoryByCategoryName(String categoryName) {
			boolean isValidBrand = true;
			Integer status = 1;
			Session session = this.sessionFactory.getCurrentSession();
			String query = "from Category cat where cat.parentCategory.id IS NULL and cat.categoryName = :categoryName and cat.status = :status";
			@SuppressWarnings("unchecked")
			List<Category> categoryList = session.createQuery(query).setString("categoryName", categoryName).setInteger("status", status).setCacheable(true).list();
			if(categoryList != null && categoryList.size() > 0){
				isValidBrand = false;
			}
			return isValidBrand;
		}
}