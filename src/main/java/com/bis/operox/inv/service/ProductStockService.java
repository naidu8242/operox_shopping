package com.bis.operox.inv.service;

import java.util.List;

import org.json.JSONObject;

import com.bis.operox.inv.dao.entity.Product;
import com.bis.operox.inv.dao.entity.ProductStock;

public interface ProductStockService {


	    ProductStock getProductStockByProductBarCodeAndStoreId(String barcode,Long storeId);
	    
	    ProductStock getProductStockByProductSearchCodeAndStoreId(String searchcode,Long storeId);
	
	    ProductStock addProductStock(ProductStock s);
		/**
		 * to perform to add Product Stock
		 * @param role
		 * @return
		 */
		ProductStock addProductStock(JSONObject jsonObj) throws Exception;
		/**
		 * to get Product Stock based on id
		 * @param id
		 * @return
		 */
		ProductStock getProductStockById(Long id);
		
		List<ProductStock> getProductStockByProductNameAndStoreId(String productName,Long storeId);
		
		
		List<ProductStock> listProductStock();
		
		List<ProductStock> listProductStockBySearchKeys(Long storeId, Long categoryId, String searchKey,List<Long> brandIds,String minPrice,String maxPrice);
		
		List<ProductStock> recentProductStockList(int count);
		 
		public List<ProductStock> getEcommersProductsStockListByIds(List<Long> projectStockIds);

}
