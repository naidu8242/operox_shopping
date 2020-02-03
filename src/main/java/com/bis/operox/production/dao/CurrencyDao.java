package com.bis.operox.production.dao;

import java.util.List;

import com.bis.operox.production.dao.entity.Currency;

public interface CurrencyDao {

	Currency addCurrency(Currency currency);
	
	Currency getCurrencyById(Long id);
	
	List<Currency> listCurrency();
	
	List<Currency> listCurrencyByCompanyId(Long companyId);

	boolean getCurrencyByCurrency(String currencyName);

	Currency getCurrencyByDefault(String defaultVal);

}