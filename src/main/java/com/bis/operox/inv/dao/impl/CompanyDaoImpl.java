package com.bis.operox.inv.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bis.operox.inv.dao.CompanyDao;
import com.bis.operox.inv.dao.entity.Company;

/**
 * 
 * @author Prasad Salina
 *
 */
 
@Repository
public class CompanyDaoImpl implements CompanyDao{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private static final Logger logger = LoggerFactory.getLogger(CompanyDaoImpl.class);


	@Override
	@Transactional
	public Company addCompany(Company company) {
		Session session = this.sessionFactory.getCurrentSession();
		session.saveOrUpdate(company);
		logger.info("Company saved successfully, Company Details="+company);
		return company;
		
	}

	@Override
	@Transactional
	public Company getCompanyById(Long id) {
		Session session = this.sessionFactory.getCurrentSession();	
		Company company = (Company) session.load(Company.class, new Long(id));
		logger.info("Company loaded successfully, Company details="+company);
		return company;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Company> getAllCompanies() {
		Session session = this.sessionFactory.getCurrentSession();
		List<Company> companiesList = session.createQuery("from Company").list();
		return companiesList;
	}
	
}