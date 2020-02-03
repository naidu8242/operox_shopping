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
import com.bis.operox.inv.dao.StoreDao;
import com.bis.operox.inv.dao.entity.Store;

@Repository
public class StoreDaoImpl implements StoreDao{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private static final Logger logger = LoggerFactory.getLogger(StoreDaoImpl.class);

	

	@Override
	@Transactional
	public Store addStore(Store s) {
		Session session = this.sessionFactory.getCurrentSession();
		session.saveOrUpdate(s);
		logger.info("Store saved successfully, Store Details="+s);
		return s;
		
	}


	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Store> listStores() throws Exception{
		Integer status = Constants.RECORD_ACTIVE;
		Session session = this.sessionFactory.getCurrentSession();
		return session.createQuery("from Store s where s.status = "+status+" ").setCacheable(true).list();
	}

	@Override
	@Transactional
	public Store getStoreById(Long id) throws Exception{
		Session session = this.sessionFactory.getCurrentSession();	
		Store store = session.load(Store.class, new Long(id));
		logger.info("Store loaded successfully, Store details="+store);
		return store;
	}
	
	@Override
	@Transactional(rollbackFor = { Exception.class })	
	public Boolean validateStoreName(String storeName) throws Exception {
		 logger.debug("In Store validateStoreName method");
		 Session session = this.sessionFactory.getCurrentSession();
		 Integer status = Constants.RECORD_ACTIVE;
		 boolean flag = false;
		 if(storeName != null){
				String query = "from Store s where s.storeName = :storeName and s.status = "+status+" ";
				List<Store> storesList = session.createQuery(query).setString("storeName", storeName).setCacheable(true).list();
				if (storesList != null && storesList.size() > 0) {
					flag = true;
				}
		 }
		return flag;
	}
	
	@Override
	@Transactional(rollbackFor = { Exception.class })	
	public Boolean validateStoreEmail(String email) throws Exception {
		 logger.debug("In Store validateStoreName method");
		 Session session = this.sessionFactory.getCurrentSession();
		 Integer status = Constants.RECORD_ACTIVE;
		 boolean flag = false;
		 if(email != null){
				String query = "from Store s where s.email = :email and s.status = "+status+" ";
				List<Store> storesList = session.createQuery(query).setString("email", email).setCacheable(true).list();
				if (storesList != null && storesList.size() > 0) {
					flag = true;
				}
		 }
		return flag;
	}
	
	@Override
	@Transactional(rollbackFor = { Exception.class })	
	public Store getStoreByStoreIdAndStoreName(String storeId, String storeName) throws Exception {
		logger.debug("In Store getStoreByStoreIdAndStoreName method");
		Session session = this.sessionFactory.getCurrentSession();
		Integer status = Constants.RECORD_ACTIVE;
		String query = "from Store s where s.storeName = :storeName and s.id = :storeId and s.status = "+status+" ";
		List<Store> storesList = session.createQuery(query).setString("storeId", storeId).setString("storeName", storeName).setCacheable(true).list();
		Store store = null;
		if(storesList != null && !storesList.isEmpty())
		{
			store = (Store) storesList.iterator().next();
		}
		return store;
	}
	
	@Override
	@Transactional(rollbackFor = { Exception.class })	
	public Store getStoreByStoreIdAndStoreEmail(String storeId, String email) throws Exception {
		logger.debug("In Store getStoreByStoreIdAndStoreName method");
		Session session = this.sessionFactory.getCurrentSession();
		Integer status = Constants.RECORD_ACTIVE;
		String query = "from Store s where s.email = :email and s.id = :storeId and s.status = "+status+" ";
		List<Store> storesList = session.createQuery(query).setString("storeId", storeId).setString("email", email).setCacheable(true).list();
		Store store = null;
		if(storesList != null && !storesList.isEmpty())
		{
			store = (Store) storesList.iterator().next();
		}
		return store;
	}
	
	@Override
	@Transactional
	public List<Store> storesListByStoreType(String storeType) throws Exception {
		Integer status = Constants.RECORD_ACTIVE;
		Session session = this.sessionFactory.getCurrentSession();
		return session.createQuery("from Store s where s.storeType = :storeType and s.status = "+status+" ").setString("storeType", storeType).setCacheable(true).list();
	}
	
	@Override
	@Transactional
	public List<Store> storesListByCompanyId(Long companyId) throws Exception {
		Integer status = Constants.RECORD_ACTIVE;
		Session session = this.sessionFactory.getCurrentSession();
		return session.createQuery("from Store s where s.company.id = :companyId and s.storeType = 'Store' and s.status = "+status+" ").setLong("companyId", companyId).setCacheable(true).list();
	}


	@Override
	@Transactional
	public List<Store> storesListByStoreTypeAndCompanyId(String storeType, Long companyId) throws Exception {
		Integer status = Constants.RECORD_ACTIVE;
		Session session = this.sessionFactory.getCurrentSession();
		return session.createQuery("from Store s where s.storeType = :storeType and s.company.id = :companyId and s.status = "+status+" ").setString("storeType", storeType).setLong("companyId", companyId).setCacheable(true).list();
	}


	@Override
	@Transactional
	public List<Store> getStoreListExcludingFromStore(Long id) throws Exception {
	
		Integer status = Constants.RECORD_ACTIVE;
		Session session = this.sessionFactory.getCurrentSession();
		return session.createQuery("from Store s where s.id <> :myId").setParameter("myId", id).list();
	}
	
	@Override
	@Transactional
	public List<Store> getStoreListByCompanyId(Long companyId) throws Exception {
		Integer status = Constants.RECORD_ACTIVE;
		Session session = this.sessionFactory.getCurrentSession();
		return session.createQuery("from Store s where s.company.id = :companyId and s.status = "+status+" ").setLong("companyId", companyId).setCacheable(true).list();

	}
	
}