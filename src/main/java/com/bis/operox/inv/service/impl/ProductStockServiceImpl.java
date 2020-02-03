package com.bis.operox.inv.service.impl;

import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bis.operox.constants.Constants;
import com.bis.operox.inv.dao.PriceDao;
import com.bis.operox.inv.dao.ProductStockDao;
import com.bis.operox.inv.dao.entity.Price;
import com.bis.operox.inv.dao.entity.ProductStock;
import com.bis.operox.inv.service.ProductStockService;

@Service
public class ProductStockServiceImpl implements ProductStockService{
	


	@Autowired
	private ProductStockDao productStockDao;
	
	@Autowired
	private PriceDao priceDao;

	@Override
	public ProductStock addProductStock(JSONObject jsonObj) {
		ProductStock productStock = new ProductStock();
		
		return  productStockDao.addProductStock(productStock);
	}

	@Override
	public ProductStock getProductStockById(Long id) {
		
		return productStockDao.getProductStockById(id);
	}
	
	

	@Override
	public List<ProductStock> listProductStock() {
		
		 List<ProductStock> productStockList  = productStockDao.listProductStock();
			for(ProductStock productStock :productStockList){
				
				productStock.setProductName(productStock.getProductId().getProductName());
				productStock.setSearchCodeStr(productStock.getProductId().getProductCode());
				if(productStock.getCurrentQuantity() != null ){
					productStock.setQtyStr(productStock.getCurrentQuantity().toString());
				}
			    /*	productStock.setTaxStr(productStock.getProductId().getProductName());*/
				if(productStock.getTaxId() != null ){
					productStock.setTaxStr(productStock.getTaxId().getTaxName());
				}
				if(productStock.getExpireDate() != null ){
					productStock.setExpireDateStr(Constants.DF_DMY.format(productStock.getExpireDate()));
				}
				if(productStock.getId() != null ){
					Price pr =  priceDao.getPriceByProductId(productStock.getId());
					if(pr != null && pr.getMrp() != null){
						productStock.setPriceStr(pr.getMrp());
					}
					
					
					
				}
				/*productStock.setDescriptionStr(productStock.getDescription().getDescription());*/
				
			}
	       return productStockList;
	 }
	
	@Override
	public List<ProductStock> listProductStockBySearchKeys(Long storeId, Long categoryId, String searchKey,List<Long> brandIds,String minPrice,String maxPrice) {
		
		 List<ProductStock> productStockList  = productStockDao.listProductStockBySearchKeys(storeId, categoryId,searchKey,brandIds,minPrice,maxPrice);
			for(ProductStock productStock :productStockList){
				
				productStock.setProductName(productStock.getProductId().getProductName());
				productStock.setSearchCodeStr(productStock.getProductId().getProductCode());
				if(productStock.getCurrentQuantity() != null ){
					productStock.setQtyStr(productStock.getCurrentQuantity().toString());
				}
			    /*	productStock.setTaxStr(productStock.getProductId().getProductName());*/
				if(productStock.getTaxId() != null ){
					productStock.setTaxStr(productStock.getTaxId().getTaxName());
				}
				if(productStock.getId() != null ){
					Price pr =  priceDao.getPriceByProductId(productStock.getId());
					if(pr != null && pr.getMrp() != null){
						productStock.setPriceStr(pr.getMrp());
					}
					
				}
				/*productStock.setDescriptionStr(productStock.getDescription().getDescription());*/
				
			}
	       return productStockList;
	 }
	
	@Override
	public List<ProductStock> recentProductStockList(int count) {
		
		 List<ProductStock> productStockList  = productStockDao.recentProductStockList(count);
			for(ProductStock productStock :productStockList){
				
				productStock.setProductName(productStock.getProductId().getProductName());
				productStock.setSearchCodeStr(productStock.getProductId().getProductCode());
				if(productStock.getCurrentQuantity() != null ){
					productStock.setQtyStr(productStock.getCurrentQuantity().toString());
				}
				if(productStock.getTaxId() != null ){
					productStock.setTaxStr(productStock.getTaxId().getTaxName());
				}
				if(productStock.getId() != null ){
					Price pr =  priceDao.getPriceByProductId(productStock.getId());
					if(pr != null && pr.getMrp() != null){
						productStock.setPriceStr(pr.getMrp());
					}
					
				}
			}
	       return productStockList;
	 }
	

	@Override
	public List<ProductStock> getProductStockByProductNameAndStoreId(String productName, Long storeId) {
		return productStockDao.getProductStockByProductNameAndStoreId(productName, storeId);
	}

	@Override
	public ProductStock addProductStock(ProductStock s) {
		return productStockDao.addProductStock(s);
	}

	@Override
	public ProductStock getProductStockByProductBarCodeAndStoreId(String barcode, Long storeId) {
		// TODO Auto-generated method stub
		return productStockDao.getProductStockByProductBarCodeAndStoreId(barcode, storeId);
	}

	@Override
	public ProductStock getProductStockByProductSearchCodeAndStoreId(String searchcode, Long storeId) {
		// TODO Auto-generated method stub
		return productStockDao.getProductStockByProductSearchCodeAndStoreId(searchcode, storeId);
	}

	@Override
	public List<ProductStock> getEcommersProductsStockListByIds(List<Long> projectStockIds) {
		 
		return productStockDao.getEcommersProductsStockListByIds(projectStockIds);
	}

}



	
