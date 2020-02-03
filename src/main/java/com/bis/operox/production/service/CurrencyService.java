package com.bis.operox.production.service;

import java.util.List;

import org.json.JSONObject;

import com.bis.operox.inv.dao.entity.User;
import com.bis.operox.production.dao.entity.Currency;

public interface CurrencyService {
	
	Currency addCurrency(Currency currency);
	
	Currency getCurrencyById(Long id);
	
	List<Currency> listCurrency();
	
	List<Currency> listCurrencyByCompanyId(Long companyId);

	void addCurrencyDetails(JSONObject jsonObj, User user) throws Exception;

	Boolean getCurrencyByCurrency(String currencyName);

	Currency getCurrencyByDefault(String defaultVal);
}
