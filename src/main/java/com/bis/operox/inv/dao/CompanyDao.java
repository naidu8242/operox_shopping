package com.bis.operox.inv.dao;

import java.util.List;

import com.bis.operox.inv.dao.entity.Company;

/**
 * 
 * @author Prasad Salina
 *
 */
 
public interface CompanyDao {
	

	public Company addCompany(Company company);
	
	public Company getCompanyById(Long id);

	public List<Company> getAllCompanies();


}