package com.bis.operox.inv.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bis.operox.inv.dao.CompanyDao;
import com.bis.operox.inv.dao.entity.Company;
import com.bis.operox.inv.service.CompanyService;

/**
 * 
 * @author Prasad Salina
 *
 */

@Service
public class CompanyServiceImpl implements CompanyService{
	
	@Autowired
	private CompanyDao companyDao;
	

	@Override
	public Company addCompany(Company company) {
		return companyDao.addCompany(company);
	}
	
	@Override
	public Company getCompanyById(Long id) {
		return this.companyDao.getCompanyById(id);
	}

	@Override
	public List<Company> getAllCompanies() {
		return this.companyDao.getAllCompanies();
	}
	
}
