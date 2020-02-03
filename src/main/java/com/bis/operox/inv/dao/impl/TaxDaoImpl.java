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
import com.bis.operox.inv.dao.TaxDao;
import com.bis.operox.inv.dao.entity.Tax;

/**
 * 
 * @author Prasad Salina
 *
 */
 
@Repository
public class TaxDaoImpl implements TaxDao{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private static final Logger logger = LoggerFactory.getLogger(TaxDaoImpl.class);


	@Override
	@Transactional(rollbackFor = { Exception.class })	
	public Tax addTax(Tax tax)  throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		session.saveOrUpdate(tax);
		logger.info("Tax saved successfully, Tax Details="+tax);
		return tax;
		
	}

	@Override
	@Transactional(rollbackFor = { Exception.class })	
	public Tax getTaxById(Long id) throws Exception  {
		Session session = this.sessionFactory.getCurrentSession();	
		String query = "from Tax tax where tax.id = :id";
		List<Tax> taxList = session.createQuery(query).setLong("id", id).setCacheable(true).list();
		return taxList.get(0);
	}
	
	@Override
	@Transactional(rollbackFor = { Exception.class })	
	public Tax getTaxByUserId(Long userID)  throws Exception  {
		Tax tax = null;
		Session session = this.sessionFactory.getCurrentSession();	
		String query = "from Tax tax  left join fetch tax.category  left join fetch tax.store   where tax.user.id = :userID";
		List<Tax> taxList = session.createQuery(query).setLong("userID", userID).setCacheable(true).list();
		if(taxList!=null && taxList.size() > 0){
			tax = taxList.get(0);
		}
		return tax;
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(rollbackFor = { Exception.class })	
	public List<Tax> getAll()  throws Exception {
		
		Integer status = Constants.RECORD_ACTIVE;
		Session session = this.sessionFactory.getCurrentSession();
		List<Tax> taxList = session.createQuery("from Tax tax left join fetch tax.category  where  tax.status = "+status+"").list();
		return taxList;
	}
	
	
	@Override
	@Transactional(rollbackFor = { Exception.class })	
	public Tax getTaxByTaxName( String taxName) throws Exception {
		logger.debug("In Tax getTaxByTaxAndTaxName method");
		Session session = this.sessionFactory.getCurrentSession();
		Integer status = Constants.RECORD_ACTIVE;
		String query = "from Tax s where s.taxName = :taxName and s.status = "+status+" ";
		List<Tax> taxList = session.createQuery(query).setString("taxName", taxName).setCacheable(true).list();
		Tax tax = null;
		if(taxList != null && !taxList.isEmpty())
		{
			tax = (Tax) taxList.iterator().next();
		}
		return tax;
	}

	@Override
	@Transactional(rollbackFor = { Exception.class })	
	public List<Tax> getTaxListByStoreId(Long storeId) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List<Tax> taxList = session.createQuery(" from Tax tax where tax.store.id = :storeId and tax.status = '1' ").setLong("storeId", storeId).setCacheable(true).list();
		return taxList;
	}
}