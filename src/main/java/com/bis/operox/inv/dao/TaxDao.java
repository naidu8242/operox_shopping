package com.bis.operox.inv.dao;

import java.util.List;

import com.bis.operox.inv.dao.entity.Tax;
/**
 * 
 * @author Prasad Salina
 *
 */
 
public interface TaxDao {
	
	public Tax addTax(Tax tax) throws Exception ;
	
	public Tax getTaxById(Long id) throws Exception ;

	public List<Tax> getAll() throws Exception ;
	
	Tax getTaxByUserId(Long userID) throws Exception ;

	Tax getTaxByTaxName(String taxName)  throws Exception;
	
	public List<Tax> getTaxListByStoreId(Long storeId) throws Exception;
	
}