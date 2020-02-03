package com.bis.operox.production.service.impl;

import java.util.Date;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bis.operox.inv.dao.entity.Company;
import com.bis.operox.inv.dao.entity.User;
import com.bis.operox.production.dao.CurrencyDao;
import com.bis.operox.production.dao.entity.Currency;
import com.bis.operox.production.service.CurrencyService;

@Service
public class CurrencyServiceImpl implements CurrencyService {

	
	@Autowired
	CurrencyDao currencyDao;
	
	@Override
	public Currency addCurrency(Currency currency) {
		return currencyDao.addCurrency(currency);
	}

	@Override
	public Currency getCurrencyById(Long id) {
		return currencyDao.getCurrencyById(id);
	}

	@Override
	public List<Currency> listCurrency() {
		return currencyDao.listCurrency();
	}

	@Override
	public void addCurrencyDetails(JSONObject jsonObj, User user)throws Exception {
		Currency currency = null;
		if(jsonObj.has("id") && jsonObj.getString("id") != null && !"".equals(jsonObj.getString("id"))){
			currency = currencyDao.getCurrencyById(Long.parseLong(jsonObj.getString("id")));
		}else{
			currency = new Currency();
		}
		if(jsonObj.has("currencyName") && jsonObj.getString("currencyName") != null && !"".equals(jsonObj.getString("currencyName"))){
			currency.setCurrency(jsonObj.getString("currencyName"));
		}
		if(jsonObj.has("symbol") && jsonObj.getString("symbol") != null && !"".equals(jsonObj.getString("symbol"))){
			currency.setSymbol(jsonObj.getString("symbol"));
		}
		if(jsonObj.has("exchangeValue") && jsonObj.getString("exchangeValue") != null && !"".equals(jsonObj.getString("exchangeValue"))){
			currency.setExchangeValue(Float.parseFloat(jsonObj.getString("exchangeValue")));
		}
		if(jsonObj.has("currencyDescription") && jsonObj.getString("currencyDescription") != null && !"".equals(jsonObj.getString("currencyDescription"))){
			currency.setDescription(jsonObj.getString("currencyDescription"));
		}
		currency.setIsDefault("N");
		Company company = new Company();
		company.setId(user.getStore().getCompany().getId());
		currency.setCompany(company);
		currency.setStatus(1);
		currency.setCreatedDate(new Date());
		currency.setUpdatedDate(new Date());
		currency.setCreatedBy(user.getUserCode());
		currency.setUpdatedBy(user.getUserCode());
		currencyDao.addCurrency(currency);
	}

	@Override
	public Boolean getCurrencyByCurrency(String currencyName) {
		return currencyDao.getCurrencyByCurrency(currencyName);
	}

	@Override
	public Currency getCurrencyByDefault(String defaultVal) {
		return  currencyDao.getCurrencyByDefault(defaultVal);
	}

	@Override
	public List<Currency> listCurrencyByCompanyId(Long companyId) {
		return  currencyDao.listCurrencyByCompanyId(companyId);
	}



}
