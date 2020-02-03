package com.bis.operox.inv.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bis.operox.constants.Constants;
import com.bis.operox.inv.dao.CounterDao;
import com.bis.operox.inv.dao.entity.Counter;
import com.bis.operox.inv.dao.entity.Store;
import com.bis.operox.inv.dao.entity.User;
import com.bis.operox.inv.service.CounterService;
import com.bis.operox.inv.service.StoreService;
import com.bis.operox.inv.service.UserService;

@Service
public class CounterServiceImpl implements CounterService{
	
	@Autowired
	private CounterDao counterDao;
	
	@Autowired
	private StoreService storeService;
	
	@Autowired
	private UserService userService;

	@Override
	public List<Counter> listCounters() throws Exception {
		List<Counter> countersList = counterDao.listCounters();
		for (Counter counter : countersList) {
			if(counter.getStore() != null && counter.getStore().getId() != null){
				Store store = storeService.getStoreById(counter.getStore().getId());
				counter.setStoreName(store.getStoreName());
			}
			User user = userService.getUserByUserCode(counter.getUpdatedBy());
			if(user != null){
			  String updatedByName = "";
    		   if(StringUtils.isNotEmpty(user.getFirstName())){
    			   updatedByName = user.getFirstName();
    		   }
    		   if(StringUtils.isNotEmpty(user.getLastName()) && StringUtils.isNotBlank(user.getLastName())){
    			   updatedByName = updatedByName+" "+user.getLastName();
    		   }
    		   counter.setUpdatedBy(updatedByName);
			}
		}
		return countersList;
	}
	
	@Override
	public List<Counter> getAllCountersByStoreId(Long storeId) throws Exception {
		return this.counterDao.getAllCountersByStoreId(storeId);
	}
	
	@Override
	public Counter getCounterById(Long id) throws Exception {
		Counter counter = counterDao.getCounterById(id);
		User user = userService.getUserByUserCode(counter.getUpdatedBy());
		if(user != null){
		  String updatedByName = "";
		   if(StringUtils.isNotEmpty(user.getFirstName())){
			   updatedByName = user.getFirstName();
		   }
		   if(StringUtils.isNotEmpty(user.getLastName()) && StringUtils.isNotBlank(user.getLastName())){
			   updatedByName = updatedByName+" "+user.getLastName();
		   }
		   counter.setUpdatedBy(updatedByName);
		}
		return counter;
	}

	@Override
	public Counter addCounterDetails(JSONObject jsonObj, User user) throws NumberFormatException, Exception {
		Counter counter = null;
		
		if(jsonObj.has("counterId") && jsonObj.getString("counterId") != null && !"".equals(jsonObj.getString("counterId"))){
			counter = counterDao.getCounterById(Long.parseLong(jsonObj.getString("counterId")));
		}else{
			counter = new Counter();
			counter.setCreatedBy(user.getUserCode());
		}
		counter.setCounterName(jsonObj.getString("counterName"));
		counter.setCounterType(jsonObj.getString("counterType"));
		counter.setDescription(jsonObj.getString("description"));
		counter.setStatus(Constants.RECORD_ACTIVE);
		
		if(jsonObj.has("storeId") && jsonObj.getString("storeId") != null && !"".equals(jsonObj.getString("storeId"))){
			Store store = new Store();
			store.setId(Long.parseLong(jsonObj.getString("storeId")));
			counter.setStore(store);
		}
		
		counter.setCreatedDate(new Date());
		counter.setUpdatedBy(user.getUserCode());
		counter.setUpdatedDate(new Date());
		return counterDao.addCounter(counter);
	}
	
	@Override
	public Boolean validateCounterName(String storeId, String counterName) throws Exception {
		Boolean flag = counterDao.validateCounterName(storeId,counterName);
		return flag;
	}
	
	@Override
	public Counter getCounterByCounterIdAndStoredIdAndCounterName(String storeId, String counterName, String counterId) throws Exception {
		Counter counter = counterDao.getCounterByCounterIdAndStoredIdAndCounterName(storeId,counterName,counterId);
		return counter;
	}
	
	@Override
	public Counter removeCounterById(Long id, String userCode) throws Exception {
		Counter counter = counterDao.getCounterById(id);
		counter.setUpdatedBy(userCode);
		counter.setStatus(Constants.RECORD_IN_ACTIVE);
	    return counterDao.addCounter(counter);
	}
}
