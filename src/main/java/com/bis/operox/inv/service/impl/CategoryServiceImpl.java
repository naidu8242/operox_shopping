package com.bis.operox.inv.service.impl;

import java.util.Date;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bis.operox.constants.Constants;
import com.bis.operox.inv.dao.CategoryDao;
import com.bis.operox.inv.dao.entity.Address;
import com.bis.operox.inv.dao.entity.Brand;
import com.bis.operox.inv.dao.entity.Category;
import com.bis.operox.inv.dao.entity.Company;
import com.bis.operox.inv.dao.entity.Counter;
import com.bis.operox.inv.dao.entity.Store;
import com.bis.operox.inv.dao.entity.User;
import com.bis.operox.inv.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{
	
	@Autowired
	private CategoryDao categoryDao;

	@Override
	public Category addCategory(Category category) {
		return categoryDao.addCategory(category);
	}

	@Override
	public Category getCategoryById(Long id) {
		return categoryDao.getCategoryById(id);
	}

	@Override
	public List<Category> listCategories() {
		return categoryDao.listCategories();
	}

	@Override
	public void addCategoryDetails(JSONObject jsonObj) throws Exception {
		Category category = null;
		if(jsonObj.has("id") && jsonObj.getString("id") != null && !"".equals(jsonObj.getString("id"))){
			category = categoryDao.getCategoryById(Long.parseLong(jsonObj.getString("id")));
		}else{
			category = new Category();
		}
		category.setCategoryName(jsonObj.getString("categoryName"));
		category.setStatus(1);
		category.setParentCategory(category);
		category.setCreatedDate(new Date());
		category.setUpdatedDate(new Date());
		categoryDao.addCategory(category);
	}
	
	@Override
	public List<Category> getsubCategoriesList(Long categoryId) {
		return categoryDao.getsubCategoriesList(categoryId);
	}

	@SuppressWarnings("null")
	@Override
	public void addCategoryDetailsandSubCatDetails(JSONObject jsonObj,int subDivLength,User user) throws Exception {
		Category category = null;
		
		if(jsonObj.has("categoryId") && jsonObj.getString("categoryId") != null && !"".equals(jsonObj.getString("categoryId"))){
			category = categoryDao.getCategoryById(Long.parseLong(jsonObj.getString("categoryId")));
			List<Category> subCatList =categoryDao.getsubCategoriesList(Long.parseLong(jsonObj.getString("categoryId")));
			for (Category category2 : subCatList) {
				Category del = categoryDao.getCategoryById(category2.getId());
				categoryDao.deleteCategory(del);
			}
		}else{
			category = new Category();
			category.setCreatedBy(user.getUserCode());
		}
		
		if(jsonObj.has("categoryName") && jsonObj.getString("categoryName") != null && !"".equals(jsonObj.getString("categoryName"))){
			category.setCategoryName(jsonObj.getString("categoryName"));
			if(jsonObj.has("categoryDescription") && jsonObj.getString("categoryDescription") != null && !"".equals(jsonObj.getString("categoryDescription"))){
				category.setDescription(jsonObj.getString("categoryDescription"));
			}
			else{
				category.setDescription("");
			}
			category.setStatus(Constants.RECORD_ACTIVE);
			category.setParentCategory(null);
			category.setCreatedDate(new Date());
			category.setUpdatedDate(new Date());
			category.setCreatedBy(user.getUserCode());
			category.setUpdatedBy(user.getUserCode());
			Category mainCategory = categoryDao.addCategory(category);
			if(mainCategory!=null){
				for (int i = 1; i <= subDivLength; i++) {
					Category subCat = new Category();
					subCat.setId(mainCategory.getId());
					Category subCatSave = new Category();
					String subCatName = "subCategoryName"+i;
					String subCategoryDescription = "subCategoryDescription"+i;
					if(jsonObj.has(subCatName) && jsonObj.getString(subCatName) != null && !"".equals(jsonObj.getString(subCatName))){
						subCatSave.setCategoryName(jsonObj.getString(subCatName));
					}
					else{
						continue;
					}
					if(jsonObj.has(subCategoryDescription) && jsonObj.getString(subCategoryDescription) != null && !"".equals(jsonObj.getString(subCategoryDescription))){
						subCatSave.setDescription(jsonObj.getString(subCategoryDescription));
					}
					else{
						subCatSave.setDescription("");
					}
					subCatSave.setStatus(1);
					subCatSave.setParentCategory(subCat);
					subCatSave.setCreatedBy(user.getUserCode());
					subCatSave.setUpdatedBy(user.getUserCode());
					subCatSave.setCreatedDate(new Date());
					subCatSave.setUpdatedDate(new Date());
					categoryDao.addCategory(subCatSave);
				}
				
			}
		}
		
		
	}

	@Override
	public List<Category> listOfCategories(User user) {
		return categoryDao.listOfCategories(user);
	}

	@Override
	public Boolean getCatgoryByCategoryName(String categoryName) {
		return categoryDao.getCatgoryByCategoryName(categoryName);
	}

	
}
