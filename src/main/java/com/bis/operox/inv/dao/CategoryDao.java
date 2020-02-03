package com.bis.operox.inv.dao;

import java.util.List;

import com.bis.operox.inv.dao.entity.Category;
import com.bis.operox.inv.dao.entity.User;

public interface CategoryDao {
	

	public Category addCategory(Category category);
	
	public Category getCategoryById(Long id);
	
	public List<Category> listCategories();

	public List<Category> getsubCategoriesList(Long categoryId);

	public List<Category> listOfCategories(User user);

	public void deleteCategory(Category category);

	public Boolean getCatgoryByCategoryName(String categoryName);


}