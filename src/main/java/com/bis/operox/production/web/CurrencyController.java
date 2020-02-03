package com.bis.operox.production.web;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.bis.operox.constants.Constants;
import com.bis.operox.inv.dao.entity.User;
import com.bis.operox.inv.service.UserService;
import com.bis.operox.production.dao.entity.Currency;
import com.bis.operox.production.dao.entity.QCCheckList;
import com.bis.operox.production.service.CurrencyService;
import com.bis.operox.production.service.QCCheckListReportService;
import com.bis.operox.production.service.QCCheckListService;
import com.bis.operox.production.service.RawMaterialService;
import com.bis.operox.util.CommonUtil;
import com.bis.operox.util.OperoxSessionManager;


@RestController
@PropertySource("${propertyLocation:operoxapp.properties}")
public class CurrencyController {
	
	private static final Logger logger = LoggerFactory.getLogger(CurrencyController.class);
	
	@Value("${operoxUrl}")
	private String operoxUrl;
	
	@Autowired
	CommonUtil commonUtil;
	
	@Autowired
	CurrencyService currencyService;
	
	@RequestMapping(value = "/currency")
	public ModelAndView currency(HttpServletRequest request){
		commonUtil.highLightMenu(Constants.Setup, request);
		commonUtil.highLightSubMenu(Constants.Currency, request);
		ModelAndView model = new ModelAndView();
		model.addObject("operoxUrl", operoxUrl);
		model.setViewName("production/currencyHome");
		return model;
	
	}
	@RequestMapping(value = "/addCurrency") 
	public ModelAndView addCurrency(HttpServletRequest request){
		commonUtil.highLightMenu(Constants.Setup, request);
		commonUtil.highLightSubMenu(Constants.Currency, request);
		Currency currencyValue = currencyService.getCurrencyByDefault("Y");
		ModelAndView model = new ModelAndView();
		model.addObject("operoxUrl", operoxUrl);
		model.addObject("currencyValue", currencyValue);
		model.setViewName("production/addCurrency");
		return model;
	
	}
	
	@RequestMapping(value = "/editCurrency/{currencyId}")
	public ModelAndView editCurrency(@PathVariable Long currencyId,HttpServletRequest request){
		commonUtil.highLightMenu(Constants.Setup, request);
		commonUtil.highLightSubMenu(Constants.Currency, request);
		ModelAndView model = new ModelAndView();
		Currency currency = currencyService.getCurrencyById(currencyId);
		Currency currencyValue = currencyService.getCurrencyByDefault("Y");
		model.addObject("currency",currency);
		model.addObject("currencyValue", currencyValue);
		model.addObject("operoxUrl",operoxUrl);
		model.setViewName("production/addCurrency");
		return model;
	}
}
