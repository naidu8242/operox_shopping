package com.bis.operox.inv.service;

import java.util.List;

import com.bis.operox.inv.dao.entity.Company;

/**
 * 
 * @author Prasad Salina
 *
 */
public interface CompanyService {

    public Company addCompany(Company company);
	
	public Company getCompanyById(Long id);

	public List<Company> getAllCompanies();

}
