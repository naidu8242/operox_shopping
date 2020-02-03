package com.bis.operox.inv.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bis.operox.constants.Constants;
import com.bis.operox.inv.dao.entity.Company;
import com.bis.operox.inv.dao.entity.Counter;
import com.bis.operox.inv.dao.entity.Store;
import com.bis.operox.inv.dao.entity.User;
import com.bis.operox.inv.service.CounterService;
import com.bis.operox.production.dao.entity.Currency;
import com.bis.operox.production.service.CurrencyService;
import com.bis.operox.util.CommonUtil;
import com.bis.operox.util.OperoxSessionManager;

/**
 * @author shivayogiKadagad
 *
 */
@Controller
public class BillingController {
	
	@Value("${operoxUrl}")
	private String operoxUrl;
	
	@Autowired
	CommonUtil commonUtil;
	
	@Autowired
	CounterService counterService;
	
	@Autowired
	CurrencyService currencyService;
	
	@RequestMapping(value = "/billing")
	public ModelAndView storeHome(HttpServletRequest request){
		commonUtil.highLightMenu(Constants.Billing, request);
		@SuppressWarnings("unused")
		User user = (User) OperoxSessionManager.getSessionAttribute(request, "user");
		Store store = (Store) OperoxSessionManager.getSessionAttribute(request, "store");
		Company company = (Company) OperoxSessionManager.getSessionAttribute(request, "company");
		ModelAndView model = new ModelAndView();
		List<Counter> countersList = null;
		List<Currency> currencyList = null;
		try {
			countersList = counterService.getAllCountersByStoreId(store.getId());
			currencyList = currencyService.listCurrencyByCompanyId(company.getId());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addObject("countersList", countersList);
		model.addObject("currencyList", currencyList);
		model.addObject("operoxUrl", operoxUrl);
		model.setViewName("billing/billing");
		return model;
	}
	
	
}
