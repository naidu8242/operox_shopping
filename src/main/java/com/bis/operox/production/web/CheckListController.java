package com.bis.operox.production.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bis.operox.constants.Constants;
import com.bis.operox.inv.dao.entity.Brand;
import com.bis.operox.inv.dao.entity.Category;
import com.bis.operox.inv.dao.entity.Product;
import com.bis.operox.inv.dao.entity.Store;
import com.bis.operox.inv.dao.entity.User;
import com.bis.operox.inv.service.ProductService;
import com.bis.operox.inv.service.StoreService;
import com.bis.operox.production.dao.entity.QCCheckList;
import com.bis.operox.production.dao.entity.QCCheckListReport;
import com.bis.operox.production.dao.entity.RawMaterial;
import com.bis.operox.production.service.QCCheckListReportService;
import com.bis.operox.production.service.QCCheckListService;
import com.bis.operox.production.service.RawMaterialService;
import com.bis.operox.util.CommonUtil;
import com.bis.operox.util.OperoxSessionManager;

@Controller
public class CheckListController {
	
	
	@Value("${operoxUrl}")
	private String operoxUrl;
	
	@Autowired
	CommonUtil commonUtil;

	@Autowired
	RawMaterialService rawMaterialService;
	
	@Autowired
	StoreService storeService;
	
	@Autowired
	ProductService productService;
	
	@Autowired
	QCCheckListService qcCheckListService;
	
	@Autowired
	QCCheckListReportService qcCheckListReportService;
	
	@RequestMapping(value = "/addCheckList")
	public ModelAndView addCheckList(HttpServletRequest request) throws Exception{
		commonUtil.highLightMenu(Constants.Production, request);
		commonUtil.highLightSubMenu(Constants.CheckList, request);
		ModelAndView model = new ModelAndView();
		User user = (User) OperoxSessionManager.getSessionAttribute(request, "user");
		List<Store> storesList = storeService.listStores();
		List<RawMaterial> rawMaterialList = rawMaterialService.rawMaterialsList();
		List<Product> products = productService.getAllProducts(user.getStore().getCompany().getId());
		model.addObject("storesList", storesList);
		model.addObject("products", products);
		model.addObject("rawMaterialList", rawMaterialList);
	    model.addObject("operoxUrl", operoxUrl);
		model.setViewName("production/addCheckList");
		return model;
	}
	

	@RequestMapping(value = "/qaCheckList")
	public ModelAndView qaCheckList(HttpServletRequest request){
		commonUtil.highLightMenu(Constants.Production, request);
		commonUtil.highLightSubMenu(Constants.CheckList, request);
		ModelAndView model = new ModelAndView();
		model.addObject("operoxUrl", operoxUrl);
		model.setViewName("production/checkListHome");
		return model;

	}
	@RequestMapping(value = "/editCheckList/{checkId}")
	public ModelAndView editCheckList(@PathVariable Long checkId, HttpServletRequest request) throws Exception{
		commonUtil.highLightMenu(Constants.Production, request);
		ModelAndView model = new ModelAndView();
		User user = (User) OperoxSessionManager.getSessionAttribute(request, "user");
		QCCheckList qcCheckList = qcCheckListService.getQcCheckListById(checkId);
		model.addObject("qcCheckList", qcCheckList);
		List<Store> storesList = storeService.listStores();
		List<RawMaterial> rawMaterialList = rawMaterialService.rawMaterialsList();
		List<Product> products = productService.getAllProducts(user.getStore().getCompany().getId());
		List<QCCheckListReport> checklistReport = qcCheckListReportService.getQcCheckReportByQCCheckList(checkId);
		model.addObject("storesList", storesList);
		model.addObject("products", products);
		model.addObject("checklistReport", checklistReport);
		model.addObject("rawMaterialList", rawMaterialList);
		model.addObject("operoxUrl", operoxUrl);
		model.setViewName("production/editCheckList");
		return model;

	}
	
	
	@RequestMapping(value = "/viewChecklist/{checkListId}")
	public ModelAndView viewChecklist(@PathVariable Long checkListId,HttpServletRequest request){
		commonUtil.highLightMenu(Constants.Production, request);
		ModelAndView model = new ModelAndView();
		QCCheckList checkList = qcCheckListService.getQcCheckListById(checkListId);
        List<QCCheckListReport> checklistReport = qcCheckListReportService.getQcCheckReportByQCCheckList(checkListId);
		model.addObject("checkList", checkList);
		model.addObject("checklistReport", checklistReport);
		model.addObject("operoxUrl",operoxUrl);
		model.setViewName("production/viewCheckList");
		return model;
	}
	
}
