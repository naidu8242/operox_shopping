package com.bis.operox.inv.dao;

import java.util.List;

import com.bis.operox.inv.dao.entity.ProductStock;

public interface ProductStockDao {

	 ProductStock getProductStockByProductBarCodeAndStoreId(String barcode,Long storeId);
	    
	 ProductStock getProductStockByProductSearchCodeAndStoreId(String searchcode,Long storeId);
	/**
	 * to perform to add Product Stock
	 * @param role
	 * @return
	 */
	ProductStock addProductStock(ProductStock productStock);
	/**
	 * to get Product Stock based on id
	 * @param id
	 * @return
	 */
	ProductStock getProductStockById(Long id);
	
	/**
	 * to get all Product Stock
	 * @return
	 */
	List<ProductStock> listProductStock();
	
	List<ProductStock> listProductStockBySearchKeys(Long storeId, Long categoryId, String searchKey,List<Long> brandIds,String minPrice,String maxPrice);
	
	List<ProductStock> recentProductStockList(int count);

	/**
	 * to get all ProductStock By Product Name and StoreID
	 * @return
	 */
    List<ProductStock> getProductStockByProductNameAndStoreId(String productName, Long storeId);
    
    List<ProductStock> getEcommersProductsStockListByIds(List<Long> projectStockIds);
		


}