package com.bis.operox.inv.dao;

import java.util.List;

import com.bis.operox.inv.dao.entity.Supplier;

/**
 * @author Ajith Matta
 *
 */
public interface SupplierDao {

	public Supplier addSupplier(Supplier supplier) throws Exception;
	
	public Supplier getSupplierById(Long id) throws Exception;
	
	public List<Supplier> getAllSupplier() throws Exception;
	
	public List<Supplier> getAllSupplierByStatus(Integer status) throws Exception;
	
	/**
	 * @author Prasad K
	 * Handles whether the given Supplier is duplicate or not
	 * @param supplierName 
	 * @param id
	 * @return boolean
	 * @throws Exception
	 */
	public boolean isSupplierNameValid(String supplierName ,Long id) throws Exception;

}