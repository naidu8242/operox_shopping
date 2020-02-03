package com.bis.operox.analytics.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bis.operox.analytics.dao.CashSummaryDao;
import com.bis.operox.analytics.service.CashSummaryService;
import com.bis.operox.inv.dao.entity.BillItems;
import com.bis.operox.inv.dao.entity.Price;
import com.bis.operox.inv.dao.entity.ProductStock;
import com.bis.operox.inv.dao.entity.User;
import com.bis.operox.inv.service.PriceService;
import com.bis.operox.inv.service.UserService;
import com.bis.operox.util.CommonUtil;

/**
 * @author Prasad Salina
 *
 */
@Service
public class CashSummaryServiceImpl implements CashSummaryService {
	
	@Autowired
	private CashSummaryDao cashSummaryDao;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PriceService priceService;
	
	@Autowired
	private CommonUtil commonUtil;
	
	/**
	 * @author Prasad Salina.
	 * Display the Analytics report based on Arguments 
	 */
	@Override
	public List<BillItems> getAnalyticsReports(JSONObject jsonObj, User user, String reportFilterType) throws Exception {
		List<BillItems> billItemsList = null;
		
			//By Daily wise
			if(StringUtils.isNotBlank(reportFilterType) && StringUtils.isNotEmpty(reportFilterType) && "daily".equals(reportFilterType)){
				 billItemsList =  cashSummaryDao.getAnalyticsReportsByDailyWise(jsonObj, user);
			}
		 
			//By Shift wise
		   else if(StringUtils.isNotBlank(reportFilterType) && StringUtils.isNotEmpty(reportFilterType) && "shift".equals(reportFilterType)){
				List<Long> cashierUserIds = null;
				if(jsonObj.has("shiftId") && jsonObj.getString("shiftId") != null && !"".equals(jsonObj.getString("shiftId"))){
					cashierUserIds = userService.getUserIdsByShiftId(Long.parseLong(jsonObj.getString("shiftId")));
					if(cashierUserIds != null && !cashierUserIds.isEmpty())
					billItemsList =  cashSummaryDao.getAnalyticsReportsByShiftWise(jsonObj, user, cashierUserIds);
				}else{
					billItemsList =  cashSummaryDao.getAnalyticsReportsByShiftWise(jsonObj, user, cashierUserIds);
				}
		   }
			
			//By Counter Wise
		   else if(StringUtils.isNotBlank(reportFilterType) && StringUtils.isNotEmpty(reportFilterType) && "counter".equals(reportFilterType)){
			   billItemsList =  cashSummaryDao.getAnalyticsReportsByCounterWise(jsonObj, user);
		   }
			
			//By User Wise
		   else if(StringUtils.isNotBlank(reportFilterType) && StringUtils.isNotEmpty(reportFilterType) && "user".equals(reportFilterType)){
			   billItemsList =  cashSummaryDao.getAnalyticsReportsByUserWise(jsonObj, user);
		   }
			
			//By Store Wise
		   else if(StringUtils.isNotBlank(reportFilterType) && StringUtils.isNotEmpty(reportFilterType) && "store".equals(reportFilterType)){
			   billItemsList =  cashSummaryDao.getAnalyticsReportsByStoreWise(jsonObj, user);
		   }
		
		if(billItemsList != null && !billItemsList.isEmpty()){
			for (BillItems billItems : billItemsList) {
				billItems.setBarCode(billItems.getProductStockId().getBarCode());
				billItems.setProductName(billItems.getProductStockId().getProductId().getProductName());
				billItems.setProductCode(billItems.getProductStockId().getProductId().getProductCode());
				Map<String, String> list = cashSummaryDao.getResultantQuantityAndPriceByBarCode(billItems.getProductStockId().getBarCode());
				billItems.setNoOfQuantity(Integer.parseInt(list.get("quantity")));
				billItems.setBillAmount(Float.parseFloat(list.get("sum")));
				
			}
		}
		
		return billItemsList;
	}

	/**
	 * @author Prasad Salina.
	 * Display the Product Stock report based on Arguments 
	 */
	@Override
	public List<ProductStock> getProductReports(JSONObject jsonObj, User user) throws Exception {
		List<ProductStock> productStocksList = null; 
		productStocksList = cashSummaryDao.getProductReports(jsonObj, user);
		if(productStocksList != null && !productStocksList.isEmpty()){
			for (ProductStock productStock : productStocksList) {
				productStock.setProductName(productStock.getProductId().getProductName());
				productStock.setCurrentQuantity(productStock.getCurrentQuantity());
				if(productStock.getExpireDate() != null){
					productStock.setExpireDateStr(commonUtil.getDateString(productStock.getExpireDate()));
				}
				Price price = priceService.getPriceByProductStockId(productStock.getId());
				if(price != null){
					productStock.setPriceStr(price.getMrp());
				}
			}
		}
		return productStocksList;
	}
}
