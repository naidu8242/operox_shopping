package com.bis.operox.inv.dao;

import java.util.List;

import com.bis.operox.inv.dao.entity.Store;

public interface StoreDao {
	

	public Store addStore(Store s);
	
	public Store getStoreById(Long id) throws Exception;;
	
	public List<Store> listStores() throws Exception;

	public Boolean validateStoreName(String storeName) throws Exception;
	
	public Boolean validateStoreEmail(String email) throws Exception;

	public Store getStoreByStoreIdAndStoreName(String storeId, String storeName) throws Exception;
	
	public Store getStoreByStoreIdAndStoreEmail(String storeId, String email) throws Exception;
	
	public List<Store> storesListByStoreType(String storeType) throws Exception;
	
	public List<Store> storesListByCompanyId(Long companyId) throws Exception;
	
	public List<Store> storesListByStoreTypeAndCompanyId(String storeType,Long companyId) throws Exception;
	
	public List<Store> getStoreListByCompanyId(Long companyId) throws Exception;

	public List<Store> getStoreListExcludingFromStore(Long fromStore) throws Exception;
	

}