package com.bis.operox.inv.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bis.operox.constants.Constants;
import com.bis.operox.util.CommonUtil;

@Controller
public class SettingsController {

	@Value("${operoxUrl}")
	private String operoxUrl;
	
	@Autowired
	CommonUtil commonUtil;
	

	@RequestMapping(value = "/userSettings")
	public ModelAndView userSettings(HttpServletRequest request){
		commonUtil.highLightMenu(Constants.Dashboard, request);
		ModelAndView model = new ModelAndView();
		model.addObject("operoxUrl", operoxUrl);
		model.setViewName("settings/userSettings");
		return model;

	}
}
