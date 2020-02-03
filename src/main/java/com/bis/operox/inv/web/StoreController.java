package com.bis.operox.inv.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bis.operox.constants.Constants;
import com.bis.operox.inv.dao.entity.Countries;
import com.bis.operox.inv.dao.entity.Store;
import com.bis.operox.inv.dao.entity.User;
import com.bis.operox.inv.service.CountriesService;
import com.bis.operox.inv.service.StoreService;
import com.bis.operox.util.CommonUtil;
import com.bis.operox.util.OperoxSessionManager;

@Controller
public class StoreController {
	
	
	@Value("${operoxUrl}")
	private String operoxUrl;
	
	@Autowired
	CommonUtil commonUtil;
	
	@Autowired 
	private CountriesService countriesService;
	
	@Autowired
	private StoreService storeService;
	
	
	@RequestMapping(value = "/store")
	public ModelAndView storeHome(HttpServletRequest request){
		commonUtil.highLightMenu(Constants.Setup, request);
		commonUtil.highLightSubMenu(Constants.Store, request);
		ModelAndView model = new ModelAndView();
		model.addObject("operoxUrl", operoxUrl);
		model.setViewName("store/storeHome");
		return model;

	}
	
	@RequestMapping(value = "/addStore")
	public ModelAndView addStore(HttpServletRequest request) throws Exception{
		commonUtil.highLightMenu(Constants.Setup, request);
		ModelAndView model = new ModelAndView();
		User user = (User) OperoxSessionManager.getSessionAttribute(request, "user");
		Countries countries = countriesService.getCountriesByCountryName(user.getStore().getAddress().getCountry());
		if(countries!=null){
			model.addObject("loginCountryName", countries.getName());
		}
		model.setViewName("store/addStore");
		model.addObject("operoxUrl", operoxUrl);
		return model;

	}
	
	@RequestMapping(value = "/editStore/{storeId}")
	public ModelAndView editStore(@PathVariable Long storeId, HttpServletRequest request) throws Exception{
		commonUtil.highLightMenu(Constants.Setup, request);
		ModelAndView model = new ModelAndView();
		Store store = storeService.getStoreById(storeId);
		model.addObject("store", store);
		model.addObject("operoxUrl", operoxUrl);
		model.setViewName("store/addStore");
		return model;

	}
	
	@RequestMapping(value = "/viewStore/{storeId}")
	public ModelAndView viewStore(@PathVariable Long storeId, HttpServletRequest request) throws Exception{
		commonUtil.highLightMenu(Constants.Setup, request);
		ModelAndView model = new ModelAndView();
		Store store = storeService.getStoreById(storeId);
		model.addObject("store", store);
		model.addObject("operoxUrl", operoxUrl);
		model.setViewName("store/storeView");
		return model;

	}

}
