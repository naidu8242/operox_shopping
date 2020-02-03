package com.bis.operox.production.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bis.operox.constants.Constants;
import com.bis.operox.inv.dao.entity.Brand;
import com.bis.operox.inv.dao.entity.Category;
import com.bis.operox.production.dao.CurrencyDao;
import com.bis.operox.production.dao.entity.Currency;

@Repository
public class CurrencyDaoImpl implements CurrencyDao {

	@Autowired
	private SessionFactory sessionFactory;

	private static final Logger logger = LoggerFactory.getLogger(CurrencyDaoImpl.class);


	@Override
	@Transactional
	public Currency addCurrency(Currency currency) {
		Session session = this.sessionFactory.getCurrentSession();
		session.saveOrUpdate(currency);
		return currency;
	}

	@Override
	@Transactional
	public Currency getCurrencyById(Long id) {
		Session session = this.sessionFactory.getCurrentSession();	
		Currency currency = session.load(Currency.class, new Long(id));
		return currency;
	}

	@Override
	@Transactional
	public List<Currency> listCurrency() {
		Session session = this.sessionFactory.getCurrentSession();
		return session.createQuery("from Currency cur where cur.isDefault='N' and cur.status = "+Constants.RECORD_ACTIVE+"").list();
	}

	@Override
	@Transactional
	public boolean getCurrencyByCurrency(String currencyName) {
		boolean isValidCurrency = true;
		Integer status = 1;
		Session session = this.sessionFactory.getCurrentSession();
		String query = "from Currency cur where cur.currency =:currencyName and cur.status = "+Constants.RECORD_ACTIVE+"";
		List<Brand> currencyList = session.createQuery(query).setString("currencyName", currencyName).setCacheable(true).list();
		if(currencyList != null && currencyList.size() > 0){
			isValidCurrency = false;
		}
		return isValidCurrency;
		
	}

	@Override
	@Transactional
	public Currency getCurrencyByDefault(String defaultVal) {
		Session session = this.sessionFactory.getCurrentSession();
		List<Currency> currencyList = session.createQuery("from Currency cur where cur.isDefault =:isDefault and cur.status = "+Constants.RECORD_ACTIVE+"").setString("isDefault", defaultVal).list();
		return currencyList.get(0);
	}

	@Override
	@Transactional
	public List<Currency> listCurrencyByCompanyId(Long companyId) {
		Session session = this.sessionFactory.getCurrentSession();
		return session.createQuery("from Currency cur left join fetch cur.company where  cur.company.id =:companyId and cur.status = "+Constants.RECORD_ACTIVE+"").setLong("companyId", companyId).list();
	}

}