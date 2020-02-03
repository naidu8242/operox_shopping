package com.bis.operox.inv.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bis.operox.constants.Constants;
import com.bis.operox.inv.dao.CounterDao;
import com.bis.operox.inv.dao.entity.Counter;
import com.bis.operox.inv.dao.entity.Store;

@Repository
public class CounterDaoImpl implements CounterDao{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private static final Logger logger = LoggerFactory.getLogger(CounterDaoImpl.class);

	

	@Override
	@Transactional
	public Counter addCounter(Counter counter) {
		Session session = this.sessionFactory.getCurrentSession();
		session.saveOrUpdate(counter);
		logger.info("Counter saved successfully, Counter Details="+counter);
		return counter;
		
	}


	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Counter> listCounters() throws Exception{
		Integer status = Constants.RECORD_ACTIVE;
		Session session = this.sessionFactory.getCurrentSession();
		return session.createQuery("from Counter c where c.store.id IN (select s.id from Store s where s.status = "+status+") and c.status = "+status+" ").setCacheable(true).list();
	}
	
	@Override
	@Transactional
	public List<Counter> getAllCountersByStoreId(Long storeId) throws Exception {
		Integer status = Constants.RECORD_ACTIVE;
		Session session = this.sessionFactory.getCurrentSession();
		return session.createQuery("from Counter c where c.store.id = :storeId and c.status = "+status+" ").setLong("storeId", storeId).setCacheable(true).list();
	}

	@Override
	@Transactional
	public Counter getCounterById(Long id) throws Exception{
		Session session = this.sessionFactory.getCurrentSession();	
		Counter counter = session.load(Counter.class, new Long(id));
		logger.info("Counter loaded successfully, Counter details="+counter);
		return counter;
	}
	
	
	@Override
	@Transactional(rollbackFor = { Exception.class })	
	public Boolean validateCounterName(String storeId, String counterName) throws Exception {
		 logger.debug("In Employee getEmployeeByEmployeeId method");
		 Session session = this.sessionFactory.getCurrentSession();
		 Integer status = Constants.RECORD_ACTIVE;
		 boolean flag = false;
		 if(storeId != null && counterName != null){
				String query = "from Counter c where c.store = :storeId and c.counterName = :counterName and c.status = "+status+" ";
				List<Counter> countersList = session.createQuery(query).setString("storeId", storeId).setString("counterName", counterName).setCacheable(true).list();
				if (countersList != null && countersList.size() > 0) {
					flag = true;
				}
		 }
		return flag;
	}
	
	@Override
	@Transactional(rollbackFor = { Exception.class })	
	public Counter getCounterByCounterIdAndStoredIdAndCounterName(String storeId, String counterName, String counterId) throws Exception {
		logger.debug("In Employee getEmployeeByEmployeeId method");
		Session session = this.sessionFactory.getCurrentSession();
		Integer status = Constants.RECORD_ACTIVE;
		String query = "from Counter c where c.store = :storeId and c.counterName = :counterName and c.id = :counterId and c.status = "+status+" ";
		List<Counter> countersList = session.createQuery(query).setString("storeId", storeId).setString("counterName", counterName).setString("counterId", counterId).setCacheable(true).list();
		Counter counter = null;
		if(countersList != null && !countersList.isEmpty())
		{
			counter = (Counter) countersList.iterator().next();
		}
		return counter;
	}
	
}