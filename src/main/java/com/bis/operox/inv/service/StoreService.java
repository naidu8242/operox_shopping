package com.bis.operox.inv.service;

import java.util.List;

import org.json.JSONObject;

import com.bis.operox.inv.dao.entity.Store;
import com.bis.operox.inv.dao.entity.User;

public interface StoreService {
	
	public Store addStoreDetails(JSONObject jsonObj, User user) throws Exception;

	public Store getStoreById(Long id) throws Exception;
	
	public List<Store> listStores() throws Exception;
	
	public Store removeStoreById(Long id, String userCode) throws Exception;

	public Store getStoreByStoreIdAndStoreName(String storeId, String storeName) throws Exception;
	
	public Store getStoreByStoreIdAndStoreEmail(String storeId, String email) throws Exception;

	public Boolean validateStoreName(String storeName) throws Exception;
	
	public Boolean validateStoreEmail(String email) throws Exception;
	
	public List<Store> storesListByStoreType(String storeType) throws Exception;
	
	public List<Store> storesListByCompanyId(Long companyId) throws Exception;
	
	public List<Store> storesListByStoreTypeAndCompanyId(String storeType,Long companyId) throws Exception;
	
	public List<Store> getStoreListByCompanyId(Long companyId) throws Exception;

	public List<Store> getStoreListExcludingFromStore(Long fromStore) throws Exception;
	
}
