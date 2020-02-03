package com.bis.operox.inv.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bis.operox.constants.Constants;
import com.bis.operox.inv.dao.entity.Category;
import com.bis.operox.inv.dao.entity.Countries;
import com.bis.operox.inv.dao.entity.Store;
import com.bis.operox.inv.dao.entity.Tax;
import com.bis.operox.inv.dao.entity.User;
import com.bis.operox.inv.service.CategoryService;
import com.bis.operox.inv.service.CountriesService;
import com.bis.operox.inv.service.StoreService;
import com.bis.operox.inv.service.TaxService;
import com.bis.operox.util.CommonUtil;
import com.bis.operox.util.OperoxSessionManager;

@Controller
public class TaxController {
	
	
	@Value("${operoxUrl}")
	private String operoxUrl;
	
	@Autowired
	CommonUtil commonUtil;
	
	@Autowired 
	private CountriesService countriesService;
	
	@Autowired
	private StoreService storeService;
	
	@Autowired
	private CategoryService  categoryService;
	
	@Autowired
	private TaxService  taxService;
	
	
	
	@RequestMapping(value = "/tax")
	public ModelAndView storeHome(HttpServletRequest request){
		commonUtil.highLightMenu(Constants.Setup, request);
		commonUtil.highLightSubMenu(Constants.Tax, request);
		ModelAndView model = new ModelAndView();
		model.addObject("operoxUrl", operoxUrl);
		model.setViewName("tax/taxHome");
		return model;

	}
	
	@RequestMapping(value = "/addTax")
	public ModelAndView addTax(HttpServletRequest request) throws Exception{
		commonUtil.highLightMenu(Constants.Setup, request);
		ModelAndView model = new ModelAndView();
		User user = (User) OperoxSessionManager.getSessionAttribute(request, "user");
		List<Category> categoryList = categoryService.listCategories();
		model.addObject("categoryList", categoryList);
		Countries countries = countriesService.getCountriesByCountryName(user.getStore().getAddress().getCountry());
		if(countries!=null){
			model.addObject("loginCountryName", countries.getName());
		}
		model.setViewName("tax/addTax");
		model.addObject("operoxUrl", operoxUrl);
		return model;

	}
	
	@RequestMapping(value = "/editTax/{taxId}")
	public ModelAndView editTax(@PathVariable Long taxId, HttpServletRequest request) throws Exception{
		commonUtil.highLightMenu(Constants.Setup, request);
		ModelAndView model = new ModelAndView();
		Tax tax = taxService.getTaxById(taxId);
		model.addObject("tax", tax);
		
		List<Category> categoryList = categoryService.listCategories();
		model.addObject("categoryList", categoryList);
		
		model.addObject("operoxUrl", operoxUrl);
		model.setViewName("tax/addTax");
		return model;

	}
	
	@RequestMapping(value = "/taxView/{taxId}")
	public ModelAndView viewTax(@PathVariable Long taxId, HttpServletRequest request) throws Exception{
		commonUtil.highLightMenu(Constants.Setup, request);
		ModelAndView model = new ModelAndView();
		Tax tax = taxService.getTaxById(taxId);
		model.addObject("tax", tax);
		model.addObject("operoxUrl", operoxUrl);
		model.setViewName("tax/taxView");
		return model;

	}

}
