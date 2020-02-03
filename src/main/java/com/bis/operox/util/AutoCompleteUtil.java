package com.bis.operox.util;

import java.util.LinkedHashMap;
import java.util.List;

import com.bis.operox.inv.dao.entity.Product;
import com.bis.operox.inv.dao.entity.Bill;
import com.bis.operox.inv.dao.entity.ProductStock;

public class AutoCompleteUtil {

	LinkedHashMap<String, ProductStock> productStockList;
    
    LinkedHashMap<String, AutoCompleteUtil> catAndSubCatAndProducts;
    
    LinkedHashMap<String, List<Product>> subCata;
	
	LinkedHashMap<String, Bill> onHoldBillList;

	public LinkedHashMap<String, ProductStock> getProductStockList() {
		return productStockList;
	}

	public void setProductStockList(LinkedHashMap<String, ProductStock> productStockList) {
		this.productStockList = productStockList;
	}


	public LinkedHashMap<String, List<Product>> getSubCata() {
		return subCata;
	}

	public void setSubCata(LinkedHashMap<String, List<Product>> subCata) {
		this.subCata = subCata;
	}

	public LinkedHashMap<String, AutoCompleteUtil> getCatAndSubCatAndProducts() {
		return catAndSubCatAndProducts;
	}

	public void setCatAndSubCatAndProducts(LinkedHashMap<String, AutoCompleteUtil> catAndSubCatAndProducts) {
		this.catAndSubCatAndProducts = catAndSubCatAndProducts;
	}
	 

	public LinkedHashMap<String, Bill> getOnHoldBillList() {
		return onHoldBillList;
	}

	public void setOnHoldBillList(LinkedHashMap<String, Bill> onHoldBillList) {
		this.onHoldBillList = onHoldBillList;
	}

}
