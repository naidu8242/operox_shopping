package com.bis.operox.analytics.rest;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bis.operox.analytics.service.CashSummaryService;
import com.bis.operox.inv.dao.entity.BillItems;
import com.bis.operox.inv.dao.entity.Counter;
import com.bis.operox.inv.dao.entity.ProductStock;
import com.bis.operox.inv.dao.entity.Store;
import com.bis.operox.inv.dao.entity.User;
import com.bis.operox.inv.service.CounterService;
import com.bis.operox.inv.service.StoreService;
import com.bis.operox.util.CommonUtil;
import com.bis.operox.util.OperoxSessionManager;

@RestController
public class CashSummaryRestService {
	
	@Autowired
	private CashSummaryService cashSummaryService;
	
	@Autowired 
	private CommonUtil commonUtil;
	
	@Autowired
	private CounterService counterService;
	
	@Autowired
	private StoreService storeService;

	/**
	 * @author Prasad Salina.
	 * Display the Analytics report based on Arguments 
	 */
	@RequestMapping(value = "/getAnalyticsReports")
	@ResponseBody
	public String getAnalyticsReports(@RequestParam(value="json", required=false) String json,@RequestParam(value="reportFilterType", required=false) String reportFilterType, HttpServletRequest request) throws Exception {
		 User user = (User) OperoxSessionManager.getSessionAttribute(request, "user");
         json = "{"+json+"}";
		 JSONObject jsonObj = new JSONObject(json);
		 
		List<BillItems> reportsList = cashSummaryService.getAnalyticsReports(jsonObj, user, reportFilterType);
		Float total = 0f;
		if(reportsList != null && !reportsList.isEmpty()){
			for (BillItems billItems : reportsList) {
				 total = total + billItems.getBillAmount();
				 billItems.setTotalAmount(total);
			}
		}
		
		String resultJson = commonUtil.toJSON(reportsList);
		return resultJson;
	}
	
	@RequestMapping(value = "/getCountersListByStoreId")
	@ResponseBody
	public String getCountersListByStoreId(@RequestParam(value="storeId", required=false) Long storeId,HttpServletRequest request) throws Exception {
		List<Counter> countersList = counterService.getAllCountersByStoreId(storeId);
		String resultJson = commonUtil.toJSON(countersList);
		return resultJson;
	}
	
	@RequestMapping(value = "/getStoresListByStoreType")
	@ResponseBody
	public String getStoresListByStoreType(@RequestParam(value="storeType", required=false) String storeType,HttpServletRequest request) throws Exception {
		List<Store> storesList = storeService.storesListByStoreType(storeType);
		String resultJson = commonUtil.toJSON(storesList);
		return resultJson;
	}
	
	/**
	 * @author Prasad Salina.
	 * Display the Product Stock report based on Arguments 
	 */
	@RequestMapping(value = "/getProductReportsList")
	@ResponseBody
	public String getProductReportsList(@RequestParam(value="json", required=false) String json, HttpServletRequest request) throws Exception {
		 User user = (User) OperoxSessionManager.getSessionAttribute(request, "user");
         json = "{"+json+"}";
		 JSONObject jsonObj = new JSONObject(json);
		 List<ProductStock> reportsList = cashSummaryService.getProductReports(jsonObj, user);
		 String resultJson = commonUtil.toJSON(reportsList);
		 return resultJson;
	}
}
