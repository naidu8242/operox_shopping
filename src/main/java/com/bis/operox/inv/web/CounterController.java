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
import com.bis.operox.inv.dao.entity.Counter;
import com.bis.operox.inv.dao.entity.Store;
import com.bis.operox.inv.service.CounterService;
import com.bis.operox.inv.service.StoreService;
import com.bis.operox.util.CommonUtil;

@Controller
public class CounterController {
	
	
	@Value("${operoxUrl}")
	private String operoxUrl;
	
	@Autowired
	CommonUtil commonUtil;
	
	@Autowired
	private CounterService counterService;
	
	@Autowired
	private StoreService storeService;
	
	
	@RequestMapping(value = "/counterList")
	public ModelAndView counterList(HttpServletRequest request){
		commonUtil.highLightMenu(Constants.Setup, request);
		commonUtil.highLightSubMenu(Constants.Counter, request);
		ModelAndView model = new ModelAndView();
		model.addObject("operoxUrl", operoxUrl);
		model.setViewName("counter/counterList");
		return model;

	}
	
	@RequestMapping(value = "/addCounter")
	public ModelAndView addCounter(HttpServletRequest request) throws Exception{
		commonUtil.highLightMenu(Constants.Setup, request);
		commonUtil.highLightSubMenu(Constants.Counter, request);
		ModelAndView model = new ModelAndView();
		List<Store> storesList = storeService.listStores();
		model.addObject("storesList", storesList);
		model.addObject("operoxUrl", operoxUrl);
		model.setViewName("counter/addCounter");
		return model;

	}
	
	@RequestMapping(value = "/editCounter/{counterId}")
	public ModelAndView editCounter(@PathVariable Long counterId, HttpServletRequest request) throws Exception{
		commonUtil.highLightMenu(Constants.Setup, request);
		ModelAndView model = new ModelAndView();
		Counter counter = counterService.getCounterById(counterId);
		model.addObject("counter", counter);
		List<Store> storesList = storeService.listStores();
		model.addObject("storesList", storesList);
		model.addObject("operoxUrl", operoxUrl);
		model.setViewName("counter/addCounter");
		return model;

	}
	
	@RequestMapping(value = "/viewCounter/{counterId}")
	public ModelAndView viewCounter(@PathVariable Long counterId, HttpServletRequest request) throws Exception{
		commonUtil.highLightMenu(Constants.Setup, request);
		ModelAndView model = new ModelAndView();
		Counter counter = counterService.getCounterById(counterId);
		model.addObject("counter", counter);
		model.addObject("operoxUrl", operoxUrl);
		model.setViewName("counter/counterView");
		return model;

	}

}
