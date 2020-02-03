package com.bis.operox.analytics.dao;

import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import com.bis.operox.inv.dao.entity.BillItems;
import com.bis.operox.inv.dao.entity.ProductStock;
import com.bis.operox.inv.dao.entity.User;

public interface CashSummaryDao {
	
	public List<BillItems> getAnalyticsReportsByDailyWise(JSONObject jsonObj, User user) throws Exception;
	
	public List<BillItems> getAnalyticsReportsByShiftWise(JSONObject jsonObj, User user, List<Long> cashierUserIds) throws Exception;
	
	public List<BillItems> getAnalyticsReportsByCounterWise(JSONObject jsonObj, User user) throws Exception;
	
	public List<BillItems> getAnalyticsReportsByUserWise(JSONObject jsonObj, User user) throws Exception;
	
	public List<BillItems> getAnalyticsReportsByStoreWise(JSONObject jsonObj, User user) throws Exception;
	
	public Map<String, String> getResultantQuantityAndPriceByBarCode(String barCode) throws Exception;
	
	public List<ProductStock> getProductReports(JSONObject jsonObj, User user) throws Exception;
	
}
