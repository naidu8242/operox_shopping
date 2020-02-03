package com.bis.operox.production.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bis.operox.constants.Constants;
import com.bis.operox.inv.dao.entity.Company;
import com.bis.operox.inv.dao.entity.Customer;
import com.bis.operox.inv.dao.entity.Product;
import com.bis.operox.inv.dao.entity.Store;
import com.bis.operox.inv.dao.entity.Supplier;
import com.bis.operox.inv.dao.entity.User;
import com.bis.operox.inv.service.CustomerService;
import com.bis.operox.inv.service.ProductService;
import com.bis.operox.inv.service.StoreService;
import com.bis.operox.inv.service.SupplierService;
import com.bis.operox.inv.service.UserService;
import com.bis.operox.production.dao.WorkOrderResourceManagementDao;
import com.bis.operox.production.dao.entity.RawMaterial;
import com.bis.operox.production.dao.entity.WorkOrder;
import com.bis.operox.production.dao.entity.WorkOrderItems;
import com.bis.operox.production.dao.entity.WorkOrderMachineryManagement;
import com.bis.operox.production.dao.entity.WorkOrderRawMaterial;
import com.bis.operox.production.dao.entity.WorkOrderResourceManagement;
import com.bis.operox.production.service.RawMaterialService;
import com.bis.operox.production.service.WorkOrderItemsService;
import com.bis.operox.production.service.WorkOrderMachineryManagementService;
import com.bis.operox.production.service.WorkOrderRawMaterialService;
import com.bis.operox.production.service.WorkOrderResourceManagementService;
import com.bis.operox.production.service.WorkOrderService;
import com.bis.operox.util.CommonUtil;
import com.bis.operox.util.OperoxSessionManager;

@Controller
public class WorkOrderController {

	@Value("${operoxUrl}")
	private String operoxUrl;
	
	@Autowired
	CommonUtil commonUtil;
	
	@Autowired
	WorkOrderService workOrderService;
		
	@Autowired
	SupplierService supplierService;

	@Autowired
	CustomerService customerService;
	
	@Autowired
	StoreService storeService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	ProductService productService;
	
	@Autowired
	RawMaterialService rawMaterialService;
	
	@Autowired
	WorkOrderRawMaterialService workOrderRawMaterialService;
	
	@Autowired
	WorkOrderItemsService workOrderItemsService;
	
	@Autowired
	WorkOrderResourceManagementService workOrderResourceManagement;
	
	@Autowired
	WorkOrderMachineryManagementService workOrderMachineryManagementService;
	
	@Autowired
	WorkOrderResourceManagementDao workOrderResourceManagementDao;
	
	 
	@RequestMapping(value = "/workOrder")
	public ModelAndView usersHome(HttpServletRequest request){
		commonUtil.highLightMenu(Constants.Production, request);
		commonUtil.highLightSubMenu(Constants.WorkOrder, request);
		
		ModelAndView model = new ModelAndView();
		model.addObject("operoxUrl", operoxUrl);
		model.setViewName("production/workOrderHome");
		return model;

	}

	@RequestMapping(value = "/viewWorkOrder/{workOrderId}/{type}")
	public ModelAndView viewWorkOrder(@PathVariable Long workOrderId,@PathVariable String type,HttpServletRequest request) throws Exception{
		commonUtil.highLightMenu(Constants.Production, request);
		ModelAndView model = new ModelAndView();
		
		
		List<User> list = userService.getUserByProductionUnit();
		List<User>  productionUsersList = new ArrayList<>();
		for(User usr : list){
			WorkOrderResourceManagement work = workOrderResourceManagementDao.getWorkOrderMgntByUserId(usr.getId());
			if(work == null){
				 productionUsersList.add(usr);
			}
			System.out.println("shivayogi"+usr.getId());
			}
		
		WorkOrder workOrder = workOrderService.getWorkOrderByWorkOrderId(workOrderId);
		model.addObject("workOrder",workOrder);
		
		User user = (User) OperoxSessionManager.getSessionAttribute(request, "user");
		List<User> userList = userService.getUsersListByStoreId(user.getStore().getId());
		model.addObject("userList",userList);
		
		List<WorkOrderItems> workOrderItemsList = workOrderItemsService.getProductsByworkorderId(workOrderId);
		model.addObject("workOrderItemsList",workOrderItemsList);
		
		List<Supplier> suppliersList = supplierService.getAllSupplier();
		model.addObject("suppliersList",suppliersList);
		
		List<RawMaterial> rawMaterialsList = rawMaterialService.rawMaterialsList();
		model.addObject("rawMaterialsList",rawMaterialsList);
		
		List<WorkOrderRawMaterial> workOrderRawMaterialsList = workOrderRawMaterialService.getAllWorkOrderRawMaterialsByWorkOrderId(workOrderId);
		model.addObject("workOrderRawMaterialsList",workOrderRawMaterialsList);
		
		List<WorkOrderMachineryManagement> workOrderMachineryManagementsList = workOrderMachineryManagementService.getAllWorkOrderMachineryManagementByWorkOrderId(workOrderId);
		model.addObject("workOrderMachineryManagementsList",workOrderMachineryManagementsList); 

		model.addObject("productionUsersList",productionUsersList);
		
		List<Product> productList = productService.getAllProducts(user.getStore().getCompany().getId());
		model.addObject("productList",productList);
		
		
		List<WorkOrderResourceManagement> workOrderResourceManagementList = workOrderResourceManagement.getWorkOrderMngmt(workOrderId);
		for(WorkOrderResourceManagement workOrderl : workOrderResourceManagementList){
			workOrderl.setStartDateStr(commonUtil.getDateString(workOrderl.getStartDate()));
			workOrderl.setEndDateStr(commonUtil.getDateString(workOrderl.getEndDate()));
		}
		model.addObject("workOrderResourceManagementList",workOrderResourceManagementList);
		
		List<WorkOrderResourceManagement> workOrderContractManagementList = workOrderResourceManagement.getWorkOrderContrctMngmt(workOrderId);
		for(WorkOrderResourceManagement contractResorce : workOrderContractManagementList){
			if(null != contractResorce.getStartDate()){
				contractResorce.setStartDateStr(commonUtil.getDateString(contractResorce.getStartDate()));
			}
			if(null != contractResorce.getEndDate()){
				contractResorce.setEndDateStr(commonUtil.getDateString(contractResorce.getEndDate()));
			}
			
		}
		
		model.addObject("workOrderContractManagementList",workOrderContractManagementList);
		
		
		model.addObject("operoxUrl",operoxUrl);
		model.setViewName("production/viewWorkOrder");
		model.addObject("type", type);
		
		return model;
	}

	
	@RequestMapping(value = "/addWorkOrder")
	public ModelAndView addWorkOrder(HttpServletRequest request) throws Exception{
		User user = (User) OperoxSessionManager.getSessionAttribute(request, "user");
		Company company = (Company) OperoxSessionManager.getSessionAttribute(request, "company");
		commonUtil.highLightMenu(Constants.Production, request);
		commonUtil.highLightSubMenu(Constants.WorkOrder, request);
		ModelAndView model = new ModelAndView();
		
		List<Customer> customersList = customerService.listCustomerForWorkOrder(user.getStore().getCompany().getId());
		model.addObject("customersList", customersList);
		
		List<Store> storesList = storeService.storesListByStoreTypeAndCompanyId("Production Unit", company.getId());
		model.addObject("storesList", storesList);
		
		List<User> approversList = userService.getWorkOrderApprovers(user.getStore().getId());
		model.addObject("approversList", approversList);
		
		List<Product> productsList = productService.getAllProducts(user.getStore().getCompany().getId());
		model.addObject("productsList", productsList);
		
		model.addObject("operoxUrl", operoxUrl);
		model.setViewName("production/addWorkOrder");
		return model;

	}
	
}
