package com.bis.operox.inv.service;

import java.util.List;

import org.json.JSONObject;

import com.bis.operox.inv.dao.entity.Counter;
import com.bis.operox.inv.dao.entity.User;

public interface CounterService {
	
	public Counter addCounterDetails(JSONObject jsonObj, User user) throws Exception;

	public Counter getCounterById(Long id) throws Exception;
	
	public List<Counter> listCounters() throws Exception;
	
	public List<Counter> getAllCountersByStoreId(Long storeId) throws Exception;

	public Boolean validateCounterName(String storeId, String counterName) throws Exception;
	
	public Counter getCounterByCounterIdAndStoredIdAndCounterName(String storeId, String counterName, String counterId) throws Exception;

	public Counter removeCounterById(Long counterId, String userCode) throws Exception;

}
