package com.bis.operox.analytics.service;

import java.util.List;

import org.json.JSONObject;

import com.bis.operox.inv.dao.entity.BillItems;
import com.bis.operox.inv.dao.entity.ProductStock;
import com.bis.operox.inv.dao.entity.User;

public interface CashSummaryService {
	
    public List<BillItems> getAnalyticsReports(JSONObject jsonObj, User user, String reportFilterType) throws Exception;
    
    public List<ProductStock> getProductReports(JSONObject jsonObj, User user) throws Exception;

}
