package com.bis.operox.inv.service;

import java.util.List;

import org.json.JSONObject;

import com.bis.operox.inv.dao.entity.Store;
import com.bis.operox.inv.dao.entity.Tax;
import com.bis.operox.inv.dao.entity.User;

/**
 * 
 * @author Prasad Salina
 *
 */
public interface TaxService {

    public Tax addTax(Tax tax)  throws Exception;
    
    public Tax storeTax(JSONObject  jsonObject,User user,Store store)  throws Exception;
	
	public Tax getTaxById(Long id) throws Exception ;

	public List<Tax> getAll() throws Exception;
	
	Tax getTaxByUserId(Long userID)  throws Exception;

	public Tax getTaxByTaxName( String taxName) throws Exception;
	
	public Tax removeTaxById(Long id, String userCode) throws Exception;
	
	public List<Tax> getTaxListByStoreId(Long storeId) throws Exception;	
}
