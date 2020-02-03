package com.bis.operox.inv.service;

import java.util.List;

import org.json.JSONObject;

import com.bis.operox.inv.dao.entity.Category;
import com.bis.operox.inv.dao.entity.User;

public interface CategoryService {

	public Category addCategory(Category category);
	
	public Category getCategoryById(Long id);
	
	public List<Category> listCategories();

	public void addCategoryDetails(JSONObject jsonObj) throws Exception;
	
	public List<Category> getsubCategoriesList(Long categoryId);

	public void addCategoryDetailsandSubCatDetails(JSONObject jsonObj, int subDivLength, User user) throws Exception;

	public List<Category> listOfCategories(User user);

	public Boolean getCatgoryByCategoryName(String categoryName);

}
