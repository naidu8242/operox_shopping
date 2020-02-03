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
import com.bis.operox.inv.dao.entity.MeasuringUnit;
import com.bis.operox.inv.dao.entity.Product;
import com.bis.operox.inv.dao.entity.Store;
import com.bis.operox.inv.service.MeasuringUnitService;
import com.bis.operox.inv.service.ProductService;
import com.bis.operox.inv.service.StoreService;
import com.bis.operox.production.dao.entity.RawMaterial;
import com.bis.operox.production.service.RawMaterialService;
import com.bis.operox.util.CommonUtil;

@Controller
public class RawMaterialController {
	
	
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
	MeasuringUnitService measuringUnitService;
	
	@RequestMapping(value = "/rawMaterials")
	public ModelAndView rawMaterials(HttpServletRequest request){
		commonUtil.highLightMenu(Constants.Production, request);
		commonUtil.highLightSubMenu(Constants.RawMaterial, request);
		ModelAndView model = new ModelAndView();
	    model.addObject("operoxUrl", operoxUrl);
		model.setViewName("production/rawMaterialHome");
		return model;
	}
	
	@RequestMapping(value = "/newRawMaterial")
	public ModelAndView newRawMaterial(HttpServletRequest request) throws Exception{
		
		ModelAndView model = new ModelAndView();
		commonUtil.highLightMenu(Constants.Production, request);
		commonUtil.highLightSubMenu(Constants.RawMaterial, request);
	    
		List<Store> storeList = storeService.storesListByStoreType(Constants.StoreType);
		model.addObject("storeList", storeList);
		
		List<Product> productsList = productService.getAll();
		model.addObject("productsList", productsList);
		
        List<MeasuringUnit> measuringUnitList =  measuringUnitService.listMeasuringUnit();
		model.addObject("measuringUnitList", measuringUnitList);
		
		model.addObject("operoxUrl", operoxUrl);
		model.setViewName("production/newRawMaterial");
		return model;
	}
	
	/**
	 * @author Prasad K
	 * This method is used for edit the RawMaterial Details
	 * @param supplierId
	 * @param request
	 * @return
	 * @throws Exception
	 */
	
	@RequestMapping(value = "/editRawMaterial/{rawMaterialId}")
	public ModelAndView editRawMaterial(@PathVariable Long rawMaterialId, HttpServletRequest request) throws Exception{
		ModelAndView model = new ModelAndView();
		RawMaterial rawMaterial = rawMaterialService.getRawMaterialById(rawMaterialId);
		model.addObject("rawMaterial", rawMaterial);
		List<Store> storeList = storeService.listStores();
		model.addObject("storeList", storeList);
		model.addObject("operoxUrl", operoxUrl);
		model.setViewName("production/newRawMaterial");
		return model;

	}
	
	
	/**
	 * @author Prasad K
	 * This method is used for view Supplier Details
	 * @param supplierId
	 * @param request
	 * @return
	 * @throws Exception
	 */
	
	@RequestMapping(value = "/viewRawMaterial/{rawMaterialId}")
	public ModelAndView viewRawMaterial(@PathVariable Long rawMaterialId, HttpServletRequest request) throws Exception{
		ModelAndView model = new ModelAndView();
		RawMaterial rawMaterial = rawMaterialService.getRawMaterialById(rawMaterialId);
		model.addObject("rawMaterial", rawMaterial);
		model.addObject("operoxUrl", operoxUrl);
		model.setViewName("production/rawMaterialView");
		return model;

	}
	
	
	
}
