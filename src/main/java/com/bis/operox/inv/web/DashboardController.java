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
import com.bis.operox.inv.dao.entity.Shift;
import com.bis.operox.inv.dao.entity.Store;
import com.bis.operox.inv.dao.entity.User;
import com.bis.operox.inv.service.ShiftService;
import com.bis.operox.inv.service.StoreService;
import com.bis.operox.inv.service.UserService;
import com.bis.operox.util.CommonUtil;

@Controller
public class DashboardController {
	
	@Value("${operoxUrl}")
	private String operoxUrl;
	
	@Autowired
	CommonUtil commonUtil;
	
	@Autowired
	private StoreService storeService;
	
	@Autowired
	private ShiftService shiftService;
	
	@Autowired
	private UserService userService;
	
	
	@RequestMapping(value = "/analyticsReports/{type}")
	public ModelAndView dailySalesReports(@PathVariable String type,HttpServletRequest request) throws Exception{
		commonUtil.highLightMenu(Constants.Dashboard, request);
		ModelAndView model = new ModelAndView();
		
		List<Store> storesList = storeService.listStores();
		List<Shift> shiftsList = shiftService.getAllShifts();
		List<User>  usersList = userService.listUser();
		
		model.addObject("storesList", storesList);
		model.addObject("shiftsList", shiftsList);
		model.addObject("usersList", usersList);
		model.addObject("operoxUrl", operoxUrl);
		model.addObject("type", type);
		model.setViewName("dashboard/analyticsReports");
		return model;

	}
	
	
	@RequestMapping(value = "/productReports")
	public ModelAndView productReports(HttpServletRequest request) throws Exception{
		commonUtil.highLightMenu(Constants.Dashboard, request);
		ModelAndView model = new ModelAndView();
		List<Store> storesList = storeService.listStores();
		model.addObject("storesList", storesList);
		model.addObject("operoxUrl", operoxUrl);
		model.setViewName("dashboard/productReports");
		return model;

	}

}
