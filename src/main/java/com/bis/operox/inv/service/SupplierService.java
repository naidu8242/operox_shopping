package com.bis.operox.inv.service;

import java.util.List;

import org.json.JSONObject;

import com.bis.operox.inv.dao.entity.Supplier;
import com.bis.operox.inv.dao.entity.User;

/**
 * @author Ajith Matta
 *
 */
public interface SupplierService {

	public Supplier addSupplier(JSONObject jsonObj,User user) throws Exception;

	public Supplier getSupplierById(Long id) throws Exception;

	public List<Supplier> getAllSupplier() throws Exception;

	public List<Supplier> getAllSupplierByStatus(Integer status) throws Exception;
	
	public Supplier saveOrUpdateSupplier(Supplier supplier) throws Exception;
	
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