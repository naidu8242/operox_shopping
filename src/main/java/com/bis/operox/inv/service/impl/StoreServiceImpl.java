package com.bis.operox.inv.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bis.operox.constants.Constants;
import com.bis.operox.inv.dao.AddressDao;
import com.bis.operox.inv.dao.CounterDao;
import com.bis.operox.inv.dao.StoreDao;
import com.bis.operox.inv.dao.entity.Address;
import com.bis.operox.inv.dao.entity.Company;
import com.bis.operox.inv.dao.entity.Counter;
import com.bis.operox.inv.dao.ShiftDao;
import com.bis.operox.inv.dao.StoreDao;
import com.bis.operox.inv.dao.entity.Address;
import com.bis.operox.inv.dao.entity.Company;
import com.bis.operox.inv.dao.entity.Shift;
import com.bis.operox.inv.dao.entity.Store;
import com.bis.operox.inv.dao.entity.User;
import com.bis.operox.inv.service.AddressService;
import com.bis.operox.inv.service.ShiftService;
import com.bis.operox.inv.service.StoreService;
import com.bis.operox.inv.service.UserService;

@Service
public class StoreServiceImpl implements StoreService{
	
	@Autowired
	private StoreDao storeDao;

	@Autowired
	private AddressDao addressDao;
	
	@Autowired
	private AddressService addressService;
	
	@Autowired
	private UserService userService;
	

	@Autowired
	private CounterDao counterDao;


	@Override
	public List<Store> listStores() throws Exception {
		
		List<Store> storesList = storeDao.listStores();
		for (Store store : storesList) {
			if(store.getAddress() != null && store.getAddress().getId() != null){
				Address address = addressService.getAddressById(store.getAddress().getId());
				store.setCountry(address.getCountry());
				store.setState(address.getState());
				store.setCity(address.getCity());
			}
			User user = userService.getUserByUserCode(store.getUpdatedBy());
			if(user != null){
			  String updatedByName = "";
    		   if(StringUtils.isNotEmpty(user.getFirstName())){
    			   updatedByName = user.getFirstName();
    		   }
    		   if(StringUtils.isNotEmpty(user.getLastName()) && StringUtils.isNotBlank(user.getLastName())){
    			   updatedByName = updatedByName+" "+user.getLastName();
    		   }
    		   store.setUpdatedBy(updatedByName);
			}
		}
		return storesList;
	}
	
	@Override
	public Store getStoreById(Long id) throws Exception {
		Store store = storeDao.getStoreById(id);
		User user = userService.getUserByUserCode(store.getUpdatedBy());
		if(user != null){
		  String updatedByName = "";
		   if(StringUtils.isNotEmpty(user.getFirstName())){
			   updatedByName = user.getFirstName();
		   }
		   if(StringUtils.isNotEmpty(user.getLastName()) && StringUtils.isNotBlank(user.getLastName())){
			   updatedByName = updatedByName+" "+user.getLastName();
		   }
		   store.setUpdatedBy(updatedByName);
		}
		return store;
	}

	@Override
	public Store addStoreDetails(JSONObject jsonObj, User user) throws NumberFormatException, Exception {
		Store store = null;
		Address address = null;
		
		if(jsonObj.has("storeId") && jsonObj.getString("storeId") != null && !"".equals(jsonObj.getString("storeId"))){
			store = storeDao.getStoreById(Long.parseLong(jsonObj.getString("storeId")));
		}else{
			store = new Store();
			store.setCreatedBy(user.getUserCode());
		}
		store.setStoreName(jsonObj.getString("storeName"));
		store.setStoreType(jsonObj.getString("storeType"));
		store.setEmail(jsonObj.getString("email"));
		store.setPhoneNumber(jsonObj.getString("phone"));
		store.setStatus(Constants.RECORD_ACTIVE);
		
		if(user.getStore() != null && user.getStore().getCompany() != null){
            store.setCompany(user.getStore().getCompany());
        }else{
            Company company = new Company();
            company.setId(1L);
            store.setCompany(company);
        }
		
		if(store != null && store.getAddress() != null && store.getAddress().getId() != null){
			address = addressDao.getAddressById(store.getAddress().getId());
		}else{
			address = new Address();
			address.setCreatedBy(user.getUserCode());
		}
		
		address = getAddressDetails(address,jsonObj,user);
		address = addressDao.addAddress(address);
		store.setAddress(address);
		store.setCreatedDate(new Date());
		store.setUpdatedBy(user.getUserCode());
		store.setUpdatedDate(new Date());

		store = storeDao.addStore(store);
		if("".equals(jsonObj.getString("storeId"))){
		Counter counter = new Counter();
		counter.setCounterName(store.getStoreName()+" Counter");
		counter.setCounterType("Cash & Card Counter");
		counter.setStatus(Constants.RECORD_ACTIVE);
		counter.setCreatedDate(new Date());
		counter.setUpdatedBy(user.getUserCode());
		counter.setUpdatedDate(new Date());
		counter.setStore(store);
		counterDao.addCounter(counter);
	}
		return store;

	}

	private Address getAddressDetails(Address address, JSONObject jsonObj, User user) throws JSONException {
		address.setCountry(jsonObj.getString("country"));
		address.setState(jsonObj.getString("state"));
		address.setCity(jsonObj.getString("city"));
		address.setAddress(jsonObj.getString("address"));
		address.setZipcode(jsonObj.getString("zipCode"));
		address.setCountry(jsonObj.getString("country"));
		address.setStatus(Constants.RECORD_ACTIVE);
		address.setCreatedDate(new Date());
		address.setUpdatedBy(user.getUserCode());
		address.setUpdatedDate(new Date());
		return address;
	}

	@Override
	public Store removeStoreById(Long id, String userCode) throws Exception {
		Store store = storeDao.getStoreById(id);
		store.setUpdatedBy(userCode);
		store.setStatus(Constants.RECORD_IN_ACTIVE);
	    return storeDao.addStore(store);
	}
	
	@Override
	public Boolean validateStoreName(String storeName) throws Exception {
		Boolean flag = storeDao.validateStoreName(storeName);
		return flag;
	}
	
	@Override
	public Boolean validateStoreEmail(String email) throws Exception {
		Boolean flag = storeDao.validateStoreEmail(email);
		return flag;
	}
	
	@Override
	public Store getStoreByStoreIdAndStoreName(String storeId, String storeName) throws Exception {
		Store store = storeDao.getStoreByStoreIdAndStoreName(storeId,storeName);
		return store;
	}
	
	@Override
	public Store getStoreByStoreIdAndStoreEmail(String storeId, String email) throws Exception {
		Store store = storeDao.getStoreByStoreIdAndStoreEmail(storeId,email);
		return store;
	}
	
	@Override
	public List<Store> storesListByStoreType(String storeType) throws Exception {
		return storeDao.storesListByStoreType(storeType);
	}
	
	@Override
	public List<Store> storesListByCompanyId(Long companyId) throws Exception {
		return storeDao.storesListByCompanyId(companyId);
	}

	@Override
	public List<Store> storesListByStoreTypeAndCompanyId(String storeType, Long companyId) throws Exception {
		return storeDao.storesListByStoreTypeAndCompanyId(storeType, companyId);
	}

	@Override
	public List<Store> getStoreListExcludingFromStore(Long fromStore) throws Exception {
		return storeDao.getStoreListExcludingFromStore(fromStore);
	}
	public List<Store> getStoreListByCompanyId(Long companyId) throws Exception {
		return storeDao.getStoreListByCompanyId(companyId);

	}
	
}
