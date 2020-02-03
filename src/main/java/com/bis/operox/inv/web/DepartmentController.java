package com.bis.operox.inv.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bis.operox.constants.Constants;
import com.bis.operox.inv.dao.entity.Department;
import com.bis.operox.inv.service.DepartmentService;
import com.bis.operox.util.CommonUtil;

/**
 * @author Ajith Matta
 *
 */
@Controller
public class DepartmentController {
	
	@Value("${operoxUrl}")
	private String operoxUrl;
	
	@Autowired
	CommonUtil commonUtil;
	
	@Autowired
	private DepartmentService departmentService;
	
	/**
	 * @author Ajith Matta
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/departmentList")
	public ModelAndView departmentList(HttpServletRequest request){
		commonUtil.highLightMenu(Constants.Setup, request);
		commonUtil.highLightSubMenu(Constants.Depatment, request);
		ModelAndView model = new ModelAndView();
		model.addObject("operoxUrl", operoxUrl);
		model.setViewName("department/departmentList");
		return model;

	}
	
	/**
     * @author Ajith Matta
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/addDepartment")
	public ModelAndView storeDepartment(HttpServletRequest request){
		commonUtil.highLightMenu(Constants.Setup, request);
		ModelAndView model = new ModelAndView();
		model.addObject("operoxUrl", operoxUrl);
		model.setViewName("department/addDepartment");
		return model;

	}
	
	/**
	 * @author Ajith Matta
	 * @param departmentId
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/editDepartment/{departmentId}")
	public ModelAndView editDepartment(@PathVariable Long departmentId, HttpServletRequest request) throws Exception{
		commonUtil.highLightMenu(Constants.Setup, request);
		ModelAndView model = new ModelAndView();
		Department department = departmentService.getDepartmentById(departmentId);
		model.addObject("department", department);
		model.addObject("operoxUrl", operoxUrl);
		model.setViewName("department/addDepartment");
		return model;

	}
	
	/**
	 * @author Ajith Matta
	 * @param departmentId
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/viewDepartment/{departmentId}")
	public ModelAndView viewDepartment(@PathVariable Long departmentId, HttpServletRequest request) throws Exception{
		commonUtil.highLightMenu(Constants.Setup, request);
		ModelAndView model = new ModelAndView();
		Department department = departmentService.getDepartmentById(departmentId);
		model.addObject("department", department);
		model.addObject("operoxUrl", operoxUrl);
		model.setViewName("department/departmentview");
		return model;

	}

}
