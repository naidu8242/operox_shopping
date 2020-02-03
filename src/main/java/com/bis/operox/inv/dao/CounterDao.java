package com.bis.operox.inv.dao;

import java.util.List;

import com.bis.operox.inv.dao.entity.Counter;

public interface CounterDao {
	

	public Counter addCounter(Counter counter);
	
	public Counter getCounterById(Long id) throws Exception;;
	
	public List<Counter> listCounters() throws Exception;
	
	public List<Counter> getAllCountersByStoreId(Long storeId) throws Exception;

	public Boolean validateCounterName(String storeId, String counterName) throws Exception;
	
	public Counter getCounterByCounterIdAndStoredIdAndCounterName(String storeId, String counterName, String counterId) throws Exception;
}