package com.bis.operox.inv.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bis.operox.constants.Constants;
import com.bis.operox.inv.dao.entity.Supplier;
import com.bis.operox.inv.service.SupplierService;
import com.bis.operox.util.CommonUtil;
import com.bis.operox.util.SessionMaintainanceData;


@Controller
public class SuppliersController {
	
	@Value("${operoxUrl}")
	private String operoxUrl;
	
	@Autowired
	CommonUtil commonUtil;
	
	@Autowired
	SupplierService supplierService;
	
	@RequestMapping(value = "/suppliers")
	public ModelAndView suppliersHome(HttpServletRequest request) throws Exception{
		commonUtil.highLightMenu(Constants.Suppliers, request);
		commonUtil.highLightSubMenu(null, request);
		ModelAndView model = new ModelAndView();
		model.setViewName("suppliers/suppliersHome");
		model.addObject("operoxUrl", operoxUrl);
		
		/*List<Supplier>   supplierActiveList = supplierService.getAllSupplierByStatus(Constants.Active);
		model.addObject("supplierList", supplierActiveList);*/
		
		SessionMaintainanceData sessionData = (SessionMaintainanceData)request.getSession().getAttribute("sessionData");
		if(sessionData == null)
		{
			sessionData = new SessionMaintainanceData();
		}
		sessionData.setNav(Constants.Suppliers);
		
		request.getSession().setAttribute("sessionData",sessionData);
		
		return model;

	}
	
	/**
	 * @author Prasad K
	 * This method is used for create supplier
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/addSupplier")
	public ModelAndView supplier() throws Exception {

		ModelAndView model = new ModelAndView();
		model.setViewName("suppliers/addSupplier");
		model.addObject("operoxUrl", operoxUrl);
		return model;
		
    }
	
	/**
	 * @author Prasad K
	 * This method is used for edit the supplier Details
	 * @param supplierId
	 * @param request
	 * @return
	 * @throws Exception
	 */
	
	@RequestMapping(value = "/editSupplier/{supplierId}")
	public ModelAndView editSupplier(@PathVariable Long supplierId, HttpServletRequest request) throws Exception{
		commonUtil.highLightMenu(Constants.Suppliers, request);
		ModelAndView model = new ModelAndView();
		Supplier supplier = supplierService.getSupplierById(supplierId);
		model.addObject("supplier", supplier);
		model.addObject("operoxUrl", operoxUrl);
		model.setViewName("suppliers/addSupplier");
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
	
	@RequestMapping(value = "/supplierView/{supplierId}")
	public ModelAndView supplierView(@PathVariable Long supplierId, HttpServletRequest request) throws Exception{
		commonUtil.highLightMenu(Constants.Suppliers, request);
		ModelAndView model = new ModelAndView();
		Supplier supplier = supplierService.getSupplierById(supplierId);
		model.addObject("supplier", supplier);
		model.addObject("operoxUrl", operoxUrl);
		model.setViewName("suppliers/supplierView");
		return model;

	}

}