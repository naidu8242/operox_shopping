package com.bis.operox.inv.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bis.operox.constants.Constants;
import com.bis.operox.inv.dao.entity.Customer;
import com.bis.operox.inv.dao.entity.Product;
import com.bis.operox.inv.dao.entity.ProductStock;
import com.bis.operox.inv.dao.entity.PurchaseOrder;
import com.bis.operox.inv.dao.entity.PurchaseOrderItems;
import com.bis.operox.inv.dao.entity.ReceivedStock;
import com.bis.operox.inv.dao.entity.ReceivedStockItems;
import com.bis.operox.inv.dao.entity.StockReturns;
import com.bis.operox.inv.dao.entity.StockReturnsItems;
import com.bis.operox.inv.dao.entity.Store;
import com.bis.operox.inv.dao.entity.Supplier;
import com.bis.operox.inv.dao.entity.SupplierInvoice;
import com.bis.operox.inv.dao.entity.Tax;
import com.bis.operox.inv.dao.entity.User;
import com.bis.operox.inv.service.CustomerService;
import com.bis.operox.inv.service.ProductService;
import com.bis.operox.inv.service.ProductStockService;
import com.bis.operox.inv.service.PurchaseOrderItemsService;
import com.bis.operox.inv.service.PurchaseOrderService;
import com.bis.operox.inv.service.ReceivedStockItemsService;
import com.bis.operox.inv.service.ReceivedStockService;
import com.bis.operox.inv.service.StockReturnsItemsService;
import com.bis.operox.inv.service.StockReturnsService;
import com.bis.operox.inv.service.StoreService;
import com.bis.operox.inv.service.SupplierInvoiceService;
import com.bis.operox.inv.service.SupplierService;
import com.bis.operox.inv.service.TaxService;
import com.bis.operox.inv.service.UserService;
import com.bis.operox.util.CommonUtil;
import com.bis.operox.util.OperoxSessionManager;

@Controller
public class StockController {
	
	
	@Value("${operoxUrl}")
	private String operoxUrl;
	
	@Autowired
	CommonUtil commonUtil;

	@Autowired
	ProductService productService;
	
	@Autowired
	SupplierService supplierService;
	
	@Autowired
	ProductStockService productStockService;
	
	@Autowired
	StoreService storeService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	TaxService taxService;
	
	@Autowired
	CustomerService customerService;
	
	@Autowired
	PurchaseOrderService purchaseOrderService;
	
	@Autowired
	PurchaseOrderItemsService purchaseOrderItemsService;
	
	@Autowired
	StockReturnsService stockReturnsService;
	
	@Autowired
	StockReturnsItemsService stockReturnsItemsService;
	
	@Autowired
	ReceivedStockService receivedStockService;
	
	@Autowired
	ReceivedStockItemsService receivedStockItemsService;
	
	@Autowired
	SupplierInvoiceService supplierInvoiceService;
	
	@RequestMapping(value = "/purchaseorderDashboard")
	public ModelAndView purchaseOrder(HttpServletRequest request){
		commonUtil.highLightMenu(Constants.Stock, request);
		commonUtil.highLightSubMenu(Constants.PurchaseOrder, request);
		ModelAndView model = new ModelAndView();
		
	    model.addObject("operoxUrl", operoxUrl);
		model.setViewName("stock/purchaseorderDashboard");
		return model;
	}
	
	@RequestMapping(value = "/purchaseorder")
	public ModelAndView newPurchaseOrder(HttpServletRequest request) throws Exception{
		commonUtil.highLightMenu(Constants.Stock, request);
		commonUtil.highLightSubMenu(Constants.PurchaseOrder, request);
		ModelAndView model = new ModelAndView();

		String maxNumber = purchaseOrderService.getMaxPurchaseOrderId();
		if(maxNumber != null  && !maxNumber.isEmpty()){
			String increment = maxNumber.substring(0);
			Long  inc = Long.parseLong(increment)+1;
			String with5digits = String.format("%05d", inc); 
	    	maxNumber = "P"+with5digits;
		}else{
			maxNumber = "P00001";
		}
		model.addObject("purchaseNumber", maxNumber);
		
		//List<Product> productList = productService.getAllProducts();

		User user = (User) OperoxSessionManager.getSessionAttribute(request, "user");
		if(user.getStore()!=null){
			List<Product> productList = productService.getAllProducts(user.getStore().getCompany().getId());
			model.addObject("productList", productList);
		}
		
		
		List<Supplier> supplierList;
		try {
			supplierList = supplierService.getAllSupplierByStatus(Constants.Active);
			model.addObject("supplierList", supplierList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<Store> storeList = storeService.listStores();
		model.addObject("storeList", storeList);
		
		
		model.addObject("operoxUrl", operoxUrl);
		model.setViewName("stock/purchaseorder");
		return model;
	}
	
	@RequestMapping(value = "/purchaseorderView")
	public ModelAndView purchaseorderView(String productId, HttpServletRequest request) throws Exception{
		commonUtil.highLightMenu(Constants.Stock, request);
		ModelAndView model = new ModelAndView();
		
		PurchaseOrder purchaseOrder = null;
		if(StringUtils.isNotEmpty(productId)){
			purchaseOrder = purchaseOrderService.getPurchaseOrderById(Long.parseLong(productId));
			model.addObject("purchaseOrder", purchaseOrder);
		}
		
		if(purchaseOrder != null && StringUtils.isNotEmpty(purchaseOrder.getUpdatedBy())){
			User updatedUser = userService.getUserByUserCode(purchaseOrder.getUpdatedBy());
			model.addObject("updatedUser", updatedUser);
		}
		
		if(purchaseOrder != null && purchaseOrder.getId() != null){
			List<PurchaseOrderItems> purchaseOrderItems = purchaseOrderItemsService.getAllPurchaseOrderItemsByPurchaseOrderId(purchaseOrder.getId());
			model.addObject("purchaseOrderItems", purchaseOrderItems);
		}
			
		model.addObject("operoxUrl", operoxUrl);
		model.setViewName("stock/purchaseorderView");
		return model;
	}
	
	@RequestMapping(value = "/receivedItemDashboard")
	public ModelAndView receivedItemDashboard(HttpServletRequest request) {
		commonUtil.highLightMenu(Constants.Stock, request);
		commonUtil.highLightSubMenu(Constants.ReceivedItem, request);
		ModelAndView model = new ModelAndView();
		model.setViewName("stock/receivedItemDashboard");
		return model;

	}
	
	
	

    @RequestMapping(value = "/receivedItem" ,method = RequestMethod.GET)
	public ModelAndView receivedItem(HttpServletRequest request,@RequestParam(value="receivedItemId", required=false) Long receivedItemId) throws Exception{

		commonUtil.highLightMenu(Constants.Stock, request);
		commonUtil.highLightSubMenu(Constants.ReceivedItem, request);
		
		Store store = (Store) OperoxSessionManager.getSessionAttribute(request, "store");
		
		ModelAndView model = new ModelAndView();
		ReceivedStock receivedStock;
		if(receivedItemId != null){
			
			receivedStock = receivedStockService.getReceivedStockById(receivedItemId);
			model.addObject("receivedStock",receivedStock);
			
			List<ReceivedStockItems> receivedStockItemsList = receivedStockItemsService.getAllReceivedStockItemsByReceivedStockId(receivedStock.getId());
			model.addObject("receivedStockItemsList",receivedStockItemsList);
			
		}else{
			receivedStock = new ReceivedStock();
			model.addObject("receivedStock",receivedStock);
			
			String maxNumber =  receivedStockService.getMaxReceivedNumber();
			if(maxNumber != null  && !maxNumber.isEmpty()){
				String increment = maxNumber.substring(0);
				Long  inc = Long.parseLong(increment)+1;
				String with5digits = String.format("%05d", inc); 
		    	maxNumber = "R"+with5digits;
			}else{
				maxNumber = "R00001";
			}
			model.addObject("receivedNumber",maxNumber);
		}
		
		
		
		/*model objects*/
		List<PurchaseOrder> purchaseOrderList = purchaseOrderService.getAllPurchaseOrders();
		model.addObject("purchaseOrderList", purchaseOrderList);
		
		List<Store> storeList= storeService.storesListByStoreType(Constants.Store);
		model.addObject("storeList", storeList);
		
		List<Store> wareHouseList= storeService.storesListByStoreType(Constants.WareHouse);
		model.addObject("wareHouseList", wareHouseList);
		
		List<Supplier> supplierList = supplierService.getAllSupplier();
		model.addObject("supplierList", supplierList);
		
		List<ProductStock> productStockList = productStockService.listProductStock();
		model.addObject("productStockList",productStockList);

		User user = (User) OperoxSessionManager.getSessionAttribute(request, "user");
		if(user.getStore()!=null){
			List<Product> productList = productService.getAllProducts(user.getStore().getCompany().getId());
			model.addObject("productList", productList);
		}
		
		List<User> userList = userService.listUser();
		model.addObject("userList", userList);
		
		if(store.getId()!=null){
			List<Tax> taxList = taxService.getTaxListByStoreId(store.getId());
				if(taxList!=null){
					model.addObject("taxList", taxList);
				}
		}
		model.addObject("operoxUrl", operoxUrl);
		model.setViewName("stock/receivedItem");
		return model;
	
	}



	
	@RequestMapping(value = "/returnStockDashboard")
	public ModelAndView returnStock(HttpServletRequest request){
		commonUtil.highLightMenu(Constants.Stock, request);
		commonUtil.highLightSubMenu(Constants.ReturnStock, request);
		ModelAndView model = new ModelAndView();
		model.addObject("operoxUrl", operoxUrl);
		model.setViewName("stock/returnStockDashboard");
		return model;

	}
	
	@RequestMapping(value = "/openingStockDashboard")
	public ModelAndView openingStock(HttpServletRequest request){
		commonUtil.highLightMenu(Constants.Stock, request);
		commonUtil.highLightSubMenu(Constants.OpeningStock, request);
		ModelAndView model = new ModelAndView();
		model.setViewName("stock/openingStockDashboard");
		return model;

	}
	
	
	@RequestMapping(value = "/transferStockDashboard")
	public ModelAndView newTransferStock(HttpServletRequest request){
		commonUtil.highLightMenu(Constants.Stock, request);
		commonUtil.highLightSubMenu(Constants.TransferStock, request);
		ModelAndView model = new ModelAndView();
		model.addObject("operoxUrl", operoxUrl);
		model.setViewName("stock/transferStockDashboard");
		return model;
	}
	
	
	@RequestMapping(value = "/transferstockAcceptReject")
	public ModelAndView transferstockAcceptReject(String transferStockId, HttpServletRequest request) throws Exception{
		commonUtil.highLightMenu(Constants.Stock, request);
		ModelAndView model = new ModelAndView();
		
		StockReturns stockReturns = null;
		if(StringUtils.isNotEmpty(transferStockId)){
			stockReturns = stockReturnsService.getStockReturnsById(Long.parseLong(transferStockId));
			model.addObject("stockReturns", stockReturns);
		}
		
		if(stockReturns != null && StringUtils.isNotEmpty(stockReturns.getUpdatedBy())){
			User updatedUser = userService.getUserByUserCode(stockReturns.getUpdatedBy());
			model.addObject("updatedUser", updatedUser);
		}
		
		if(stockReturns != null && stockReturns.getId() != null){
			List<StockReturnsItems> stockReturnsItems = stockReturnsItemsService.getAllStockReturnsItemsByStockReturnsId(stockReturns.getId());
			model.addObject("itemsCount", stockReturnsItems.size());
			model.addObject("stockReturnsItems", stockReturnsItems);
		}
		return model;
	}
	/*@RequestMapping(value = "/transferStock")
	public ModelAndView usersHome(HttpServletRequest request){
		commonUtil.highLightMenu(Constants.Stock, request);
		ModelAndView model = new ModelAndView();
		model.setViewName("stock/transferStock");
		return model;

	}*/
	
	
	@RequestMapping(value = "/transferStock")
	public ModelAndView transferStock(HttpServletRequest request,@RequestParam(value="receivedItemId", required=false) Long receivedItemId) throws Exception{
		commonUtil.highLightMenu(Constants.Stock, request);
		commonUtil.highLightSubMenu(Constants.TRANSFER_STOCK, request);
		ModelAndView model = new ModelAndView();
		
		List<ProductStock> productStockList = productStockService.listProductStock();
		model.addObject("productStockList",productStockList);
		
		List<ProductStock> productStockSearchList = productStockService.listProductStock();
		model.addObject("productStockSearchList",productStockSearchList);
		
		
		
		List<Store> storeList = storeService.listStores();
		model.addObject("storeList",storeList);
		
		StockReturns stockReturns;
		if(receivedItemId != null){
			stockReturns = stockReturnsService.getStockReturnsById(receivedItemId);
			model.addObject("stockReturns",stockReturns);
			
			List<StockReturnsItems> receivedStockItemsList = stockReturnsItemsService.getAllStockReturnsItemsByStockReturnsId(receivedItemId);
			model.addObject("receivedStockItemsList",receivedStockItemsList);
			
		}else{
			stockReturns = new StockReturns();
			model.addObject("stockReturns",stockReturns);
			
			String maxNumber =  stockReturnsService.getMaxStockTransferedNumber();
			if(maxNumber != null  && !maxNumber.isEmpty()){
				String increment = maxNumber.substring(0);
				Long  inc = Long.parseLong(increment)+1;
				String with5digits = String.format("%05d", inc); 
		    	maxNumber = "T"+with5digits;
			}else{
				maxNumber = "T00001";
			}
			model.addObject("transferNumber",maxNumber);
		}
		
		
		/*List<Product> productList = productService.getAllProducts();
		model.addObject("productList", productList);*/

		User user = (User) OperoxSessionManager.getSessionAttribute(request, "user");
		if(user.getStore()!=null){
			List<Product> productList = productService.getAllProducts(user.getStore().getCompany().getId());
			model.addObject("productList", productList);
		}
	
		
		model.addObject("operoxUrl", operoxUrl);
		model.setViewName("stock/transferStock");
		return model;

	}
	
	@RequestMapping(value = "/transferStockView")
	public ModelAndView transferStockView( String stockReturnsId, HttpServletRequest request) throws Exception{
		commonUtil.highLightMenu(Constants.Stock, request);
		commonUtil.highLightSubMenu(Constants.TRANSFER_STOCK, request);
		ModelAndView model = new ModelAndView();
		
		StockReturns stockReturns = null;
		if(StringUtils.isNotEmpty(stockReturnsId)){
			stockReturns = stockReturnsService.getStockReturnsById(Long.parseLong(stockReturnsId));
			model.addObject("stockReturns", stockReturns);
		}
		
		if(stockReturns != null && StringUtils.isNotEmpty(stockReturns.getUpdatedBy())){
			User updatedUser = userService.getUserByUserCode(stockReturns.getUpdatedBy());
			model.addObject("updatedUser", updatedUser);
		}
		
		if(stockReturns != null && stockReturns.getId() != null){
			List<StockReturnsItems> stockReturnsItems = stockReturnsItemsService.getAllStockReturnsItemsByStockReturnsId(stockReturns.getId());
			model.addObject("stockReturnsItems", stockReturnsItems);
		}
			
		model.addObject("operoxUrl", operoxUrl);
		model.setViewName("stock/transferStockView");
		return model;
	}
	
	@RequestMapping(value = "/openingStockView")
	public ModelAndView openingStockView(String productStockId, HttpServletRequest request) throws Exception{
		commonUtil.highLightMenu(Constants.Stock, request);
		ModelAndView model = new ModelAndView();
		
		ProductStock productStock = null;
		if(StringUtils.isNotEmpty(productStockId)){
			productStock = productStockService.getProductStockById(Long.parseLong(productStockId));
			model.addObject("productStock", productStock);
		}
		
		if(productStock != null && StringUtils.isNotEmpty(productStock.getUpdatedBy())){
			User updatedUser = userService.getUserByUserCode(productStock.getUpdatedBy());
			model.addObject("updatedUser", updatedUser);
		}
			
		model.addObject("operoxUrl", operoxUrl);
		model.setViewName("stock/openingStockView");
		return model;
	}
	
	
	@RequestMapping(value = "/returnStock")
	public ModelAndView returnStocks(HttpServletRequest request) throws Exception{
		commonUtil.highLightMenu(Constants.Stock, request);
		commonUtil.highLightSubMenu(Constants.ReturnStock, request);
		ModelAndView model = new ModelAndView();
		
		
		User user = (User) OperoxSessionManager.getSessionAttribute(request, "user");
		
		List<Store> storeList = storeService.listStores();
		model.addObject("storeList", storeList);

		List<Supplier> supplierList = supplierService.getAllSupplier();
		model.addObject("supplierList", supplierList);
		
		List<Customer> cusromerList  = customerService.listCustomer(user.getStore().getCompany().getId());
		model.addObject("cusromerList", cusromerList);
		
		/*List<Product> productList = productService.getAll();
		model.addObject("productList", productList);*/
		
		List<ProductStock>  productStockList =  productStockService.listProductStock();
		model.addObject("productList", productStockList);
		
		
		List<User> userList = userService.listUser();
		model.addObject("userList", userList);
		
		model.addObject("operoxUrl", operoxUrl);
		model.setViewName("stock/returnStock");
		return model;
	
	}
	
	
	@RequestMapping(value = "/receivedStockView")
	public ModelAndView receivedStockView(String receivedStockId, HttpServletRequest request) throws Exception{
		commonUtil.highLightMenu(Constants.Stock, request);
		ModelAndView model = new ModelAndView();
		
		ReceivedStock receivedStock = null;
		if(StringUtils.isNotEmpty(receivedStockId)){
			receivedStock = receivedStockService.getReceivedStockById(Long.parseLong(receivedStockId));
			model.addObject("receivedStock", receivedStock);
		}
		
		if(receivedStock != null && StringUtils.isNotEmpty(receivedStock.getUpdatedBy())){
			User updatedUser = userService.getUserByUserCode(receivedStock.getUpdatedBy());
			model.addObject("updatedUser", updatedUser);
		}
		if(receivedStock != null && receivedStock.getId() != null){
			List<ReceivedStockItems> receivedStockItems = receivedStockItemsService.getAllReceivedStockItemsByReceivedStockId(receivedStock.getId());
			model.addObject("receivedStockItems", receivedStockItems);
		}
			
		model.addObject("operoxUrl", operoxUrl);
		model.setViewName("stock/receivedStockView");
		return model;
	}
	
	
	@RequestMapping(value = "/returnStockView")
	public ModelAndView returnStockView(String stockReturnsId, HttpServletRequest request) throws Exception{
		commonUtil.highLightMenu(Constants.Stock, request);
		ModelAndView model = new ModelAndView();
		StockReturns stockReturns = null;
		if(StringUtils.isNotEmpty(stockReturnsId)){
			stockReturns = stockReturnsService.getStockReturnsById(Long.parseLong(stockReturnsId));
			model.addObject("stockReturns", stockReturns);
		}
		
		if(stockReturns != null && StringUtils.isNotEmpty(stockReturns.getUpdatedBy())){
			User updatedUser = userService.getUserByUserCode(stockReturns.getUpdatedBy());
			model.addObject("updatedUser", updatedUser);
		}
		if(stockReturns != null && stockReturns.getId() != null){
			List<StockReturnsItems> stockReturnsItems = stockReturnsItemsService.getAllStockReturnsItemsByStockReturnsId(stockReturns.getId());
			model.addObject("stockReturnsItems", stockReturnsItems);
		}
			
		model.addObject("operoxUrl", operoxUrl);
		model.setViewName("stock/returnStockView");
		return model;
	}
	
	@RequestMapping(value = "/accountPayable")
	public ModelAndView addAccountPayable(HttpServletRequest request,@RequestParam(value="stockReceivedId", required=false) Long stockReceivedId) throws Exception{
		commonUtil.highLightMenu(Constants.Stock, request);
		ModelAndView model = new ModelAndView();
		
		ReceivedStock receivedStock = receivedStockService.getReceivedStockById(stockReceivedId);
		model.addObject("receivedStock",receivedStock);
		
		SupplierInvoice supplierInvoice = supplierInvoiceService.getSupplierInvoiceByStoreReceivedId(receivedStock.getId());
		model.addObject("supplierInvoiceNumber",supplierInvoice.getSupplierInvoiceNumber());	
		
		model.addObject("operoxUrl", operoxUrl);
		model.setViewName("stock/accountPayable");
		return model;
	}
	
}
