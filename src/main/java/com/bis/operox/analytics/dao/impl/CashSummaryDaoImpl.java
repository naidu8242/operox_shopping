package com.bis.operox.analytics.dao.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bis.operox.analytics.dao.CashSummaryDao;
import com.bis.operox.constants.Constants;
import com.bis.operox.inv.dao.entity.BillItems;
import com.bis.operox.inv.dao.entity.ProductStock;
import com.bis.operox.inv.dao.entity.User;

/**
 * @author Prasad Salina
 *
 */
@Repository
public class CashSummaryDaoImpl implements CashSummaryDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	/**
	 * @author Prasad Salina
	 * This method is used for display sales based on daily wise (only dates).
	 */
	
	@Override
	@Transactional
	public List<BillItems> getAnalyticsReportsByDailyWise(JSONObject jsonObj, User user) throws Exception{
		Session session = this.sessionFactory.getCurrentSession();
		Integer status = Constants.RECORD_ACTIVE;
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		Date formDate = null;
		Date toDate = null;
		// only Dates wise
		if(jsonObj.has("fromDate") && jsonObj.getString("fromDate") != null && !"".equals(jsonObj.getString("fromDate")) 
				&& jsonObj.has("toDate") && "".equals(jsonObj.getString("toDate"))){
			formDate = sdf.parse(jsonObj.getString("fromDate"));
			return session.createQuery("from BillItems bi where bi.status = "+status+" and bi.billId.createdDate >= :formDate GROUP BY bi.productStockId.barCode ").setDate("formDate", formDate).setCacheable(true).list();
		}else if(jsonObj.has("toDate") && jsonObj.getString("toDate") != null && !"".equals(jsonObj.getString("toDate")) 
				&& jsonObj.has("fromDate") && "".equals(jsonObj.getString("fromDate"))){
			toDate = sdf.parse(jsonObj.getString("toDate"));
			return session.createQuery("from BillItems bi where bi.status = "+status+" and bi.billId.createdDate <= :toDate GROUP BY bi.productStockId.barCode ").setDate("toDate", toDate).setCacheable(true).list();
		}else if(jsonObj.has("toDate") && jsonObj.getString("toDate") != null && !"".equals(jsonObj.getString("toDate")) && 
				jsonObj.has("fromDate") && jsonObj.getString("fromDate") != null && !"".equals(jsonObj.getString("fromDate"))){
			formDate = sdf.parse(jsonObj.getString("fromDate"));
			toDate = sdf.parse(jsonObj.getString("toDate"));
			return session.createQuery("from BillItems bi where bi.status = "+status+" and bi.billId.createdDate BETWEEN :formDate and :toDate GROUP BY bi.productStockId.barCode ").setDate("formDate", formDate).setDate("toDate", toDate).setCacheable(true).list();
		}
		return session.createQuery("from BillItems bi where bi.status = "+status+" GROUP BY bi.productStockId.barCode").setCacheable(true).list();
	}
	
	/**
	 * @author Prasad Salina
	 * This method is used for display sales based on counter wise (combination of store and counter)and dates wise.
	 */
	
	@Override
	@Transactional
	public List<BillItems> getAnalyticsReportsByCounterWise(JSONObject jsonObj, User user) throws Exception{
		Session session = this.sessionFactory.getCurrentSession();
		Integer status = Constants.RECORD_ACTIVE;
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		Date formDate = null;
		Date toDate = null;
		String subQuery = "";
		String storeId = "";
		String counterId = "";
		
		//only Store and dates based
		if(jsonObj.has("storeDropDown") && jsonObj.getString("storeDropDown") != null && !"".equals(jsonObj.getString("storeDropDown")) 
				&& jsonObj.has("counterSales") && "".equals(jsonObj.getString("counterSales"))){
			storeId = jsonObj.getString("storeDropDown");
			if(jsonObj.has("fromDate") && jsonObj.getString("fromDate") != null && !"".equals(jsonObj.getString("fromDate")) 
					&& jsonObj.has("toDate") && "".equals(jsonObj.getString("toDate"))){
				formDate = sdf.parse(jsonObj.getString("fromDate"));
				return session.createQuery("from BillItems bi where bi.status = "+status+" and bi.billId.createdDate >= :formDate and bi.billId.storeId.id = :storeId GROUP BY bi.productStockId.barCode ").setString("storeId", storeId).setDate("formDate", formDate).setCacheable(true).list();
			}else if(jsonObj.has("toDate") && jsonObj.getString("toDate") != null && !"".equals(jsonObj.getString("toDate")) 
					&& jsonObj.has("fromDate") && "".equals(jsonObj.getString("fromDate"))){
				toDate = sdf.parse(jsonObj.getString("toDate"));
				return session.createQuery("from BillItems bi where bi.status = "+status+" and bi.billId.createdDate <= :toDate and bi.billId.storeId.id = :storeId GROUP BY bi.productStockId.barCode ").setString("storeId", storeId).setDate("toDate", toDate).setCacheable(true).list();
			}else if(jsonObj.has("toDate") && jsonObj.getString("toDate") != null && !"".equals(jsonObj.getString("toDate")) && 
					jsonObj.has("fromDate") && jsonObj.getString("fromDate") != null && !"".equals(jsonObj.getString("fromDate"))){
				formDate = sdf.parse(jsonObj.getString("fromDate"));
				toDate = sdf.parse(jsonObj.getString("toDate"));
				return session.createQuery("from BillItems bi where bi.status = "+status+" and bi.billId.createdDate BETWEEN :formDate and :toDate and bi.billId.storeId.id = :storeId GROUP BY bi.productStockId.barCode ").setString("storeId", storeId).setDate("formDate", formDate).setDate("toDate", toDate).setCacheable(true).list();
			}
			return session.createQuery("from BillItems bi where bi.status = "+status+" and bi.billId.storeId.id = :storeId GROUP BY bi.productStockId.barCode ").setString("storeId", storeId).setCacheable(true).list();
		}
		//Combination of Store and Counter and Dates
		else if(jsonObj.has("counterSales") && jsonObj.getString("counterSales") != null && !"".equals(jsonObj.getString("counterSales")) 
				&& jsonObj.has("storeDropDown") && jsonObj.getString("storeDropDown") != null && !"".equals(jsonObj.getString("storeDropDown"))){
			storeId = jsonObj.getString("storeDropDown");
			counterId = jsonObj.getString("counterSales");
			if(jsonObj.has("fromDate") && jsonObj.getString("fromDate") != null && !"".equals(jsonObj.getString("fromDate")) 
					&& jsonObj.has("toDate") && "".equals(jsonObj.getString("toDate"))){
				formDate = sdf.parse(jsonObj.getString("fromDate"));
				subQuery = " bi.billId.createdDate >= :formDate and bi.billId.storeId.id = :storeId and bi.billId.counterId.id = :counterId ";
				return session.createQuery("from BillItems bi where bi.status = "+status+" and "+subQuery+" GROUP BY bi.productStockId.barCode ").setString("storeId", storeId).setString("counterId", counterId).setDate("formDate", formDate).setCacheable(true).list();
			}else if(jsonObj.has("toDate") && jsonObj.getString("toDate") != null && !"".equals(jsonObj.getString("toDate")) 
					&& jsonObj.has("fromDate") && "".equals(jsonObj.getString("fromDate"))){
				toDate = sdf.parse(jsonObj.getString("toDate"));
				subQuery = " bi.billId.createdDate <= :toDate and bi.billId.storeId.id = :storeId and bi.billId.counterId.id = :counterId ";
				return session.createQuery("from BillItems bi where bi.status = "+status+" and "+subQuery+" GROUP BY bi.productStockId.barCode ").setString("storeId", storeId).setString("counterId", counterId).setDate("toDate", toDate).setCacheable(true).list();
			}else if(jsonObj.has("toDate") && jsonObj.getString("toDate") != null && !"".equals(jsonObj.getString("toDate")) && 
					jsonObj.has("fromDate") && jsonObj.getString("fromDate") != null && !"".equals(jsonObj.getString("fromDate"))){
				formDate = sdf.parse(jsonObj.getString("fromDate"));
				toDate = sdf.parse(jsonObj.getString("toDate"));
				subQuery = "  bi.billId.createdDate BETWEEN :formDate and :toDate and bi.billId.storeId.id = :storeId and bi.billId.counterId.id = :counterId ";
				return session.createQuery("from BillItems bi where bi.status = "+status+" and "+subQuery+" GROUP BY bi.productStockId.barCode ").setString("storeId", storeId).setString("counterId", counterId).setDate("formDate", formDate).setDate("toDate", toDate).setCacheable(true).list();
			}
			return session.createQuery("from BillItems bi where bi.status = "+status+" and bi.billId.storeId.id = :storeId and bi.billId.counterId.id = :counterId GROUP BY bi.productStockId.barCode ").setString("storeId", storeId).setString("counterId", counterId).setCacheable(true).list();
		}
		// only Dates
		else{
			if(jsonObj.has("fromDate") && jsonObj.getString("fromDate") != null && !"".equals(jsonObj.getString("fromDate")) 
					&& jsonObj.has("toDate") && "".equals(jsonObj.getString("toDate"))){
				formDate = sdf.parse(jsonObj.getString("fromDate"));
				return session.createQuery("from BillItems bi where bi.status = "+status+" and bi.billId.createdDate >= :formDate  GROUP BY bi.productStockId.barCode ").setDate("formDate", formDate).setCacheable(true).list();
			}else if(jsonObj.has("toDate") && jsonObj.getString("toDate") != null && !"".equals(jsonObj.getString("toDate")) 
					&& jsonObj.has("fromDate") && "".equals(jsonObj.getString("fromDate"))){
				toDate = sdf.parse(jsonObj.getString("toDate"));
				return session.createQuery("from BillItems bi where bi.status = "+status+" and bi.billId.createdDate <= :toDate GROUP BY bi.productStockId.barCode ").setDate("toDate", toDate).setCacheable(true).list();
			}else if(jsonObj.has("toDate") && jsonObj.getString("toDate") != null && !"".equals(jsonObj.getString("toDate")) && 
					jsonObj.has("fromDate") && jsonObj.getString("fromDate") != null && !"".equals(jsonObj.getString("fromDate"))){
				formDate = sdf.parse(jsonObj.getString("fromDate"));
				toDate = sdf.parse(jsonObj.getString("toDate"));
				return session.createQuery("from BillItems bi where bi.status = "+status+" and bi.billId.createdDate BETWEEN :formDate and :toDate GROUP BY bi.productStockId.barCode ").setDate("formDate", formDate).setDate("toDate", toDate).setCacheable(true).list();
			}
		}
		
		return session.createQuery("from BillItems bi where bi.status = "+status+" GROUP BY bi.productStockId.barCode ").setCacheable(true).list();
	}
	
	/**
	 * @author Prasad Salina
	 * This method is used for display sales based on shift wise and dates wise.
	 */
	
	@Override
	@Transactional
	public List<BillItems> getAnalyticsReportsByShiftWise(JSONObject jsonObj, User user, List<Long> cashierUserIds) throws Exception{
		Session session = this.sessionFactory.getCurrentSession();
		Integer status = Constants.RECORD_ACTIVE;
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		Date formDate = null;
		Date toDate = null;
		String subQuery = "";
		
		//only shift wise and dates
		if(cashierUserIds != null && !cashierUserIds.isEmpty() && cashierUserIds.size() > 0){
			if(jsonObj.has("fromDate") && jsonObj.getString("fromDate") != null && !"".equals(jsonObj.getString("fromDate")) 
					&& jsonObj.has("toDate") && "".equals(jsonObj.getString("toDate"))){
				formDate = sdf.parse(jsonObj.getString("fromDate"));
				subQuery = " bi.billId.createdDate >= :formDate and bi.billId.cashierUserId.id IN (:cashierUserIds)";
				return session.createQuery("from BillItems bi where bi.status = "+status+" and "+subQuery+" GROUP BY bi.productStockId.barCode ")
						.setDate("formDate", formDate).setParameterList("cashierUserIds", cashierUserIds).setCacheable(true).list();
			}else if(jsonObj.has("toDate") && jsonObj.getString("toDate") != null && !"".equals(jsonObj.getString("toDate")) 
					&& jsonObj.has("fromDate") && "".equals(jsonObj.getString("fromDate"))){
				toDate = sdf.parse(jsonObj.getString("toDate"));
				subQuery = " bi.billId.createdDate <= :toDate and bi.billId.cashierUserId.id IN (:cashierUserIds)";
				return session.createQuery("from BillItems bi where bi.status = "+status+" and "+subQuery+" GROUP BY bi.productStockId.barCode ")
						.setDate("toDate", toDate).setParameterList("cashierUserIds", cashierUserIds).setCacheable(true).list();
			}else if(jsonObj.has("toDate") && jsonObj.getString("toDate") != null && !"".equals(jsonObj.getString("toDate")) && 
					jsonObj.has("fromDate") && jsonObj.getString("fromDate") != null && !"".equals(jsonObj.getString("fromDate"))){
				formDate = sdf.parse(jsonObj.getString("fromDate"));
				toDate = sdf.parse(jsonObj.getString("toDate"));
				subQuery = "  bi.billId.createdDate BETWEEN :formDate and :toDate and bi.billId.cashierUserId.id IN (:cashierUserIds)";
				return session.createQuery("from BillItems bi where bi.status = "+status+" and "+subQuery+" GROUP BY bi.productStockId.barCode ")
						.setDate("formDate", formDate).setDate("toDate", toDate).setParameterList("cashierUserIds", cashierUserIds).setCacheable(true).list();
			}
			return session.createQuery("from BillItems bi where bi.status = "+status+" and bi.billId.cashierUserId.id IN (:cashierUserIds) GROUP BY bi.productStockId.barCode ").setParameterList("cashierUserIds", cashierUserIds).setCacheable(true).list();
		}
		// only Dates wise
		else{
			if(jsonObj.has("fromDate") && jsonObj.getString("fromDate") != null && !"".equals(jsonObj.getString("fromDate")) 
					&& jsonObj.has("toDate") && "".equals(jsonObj.getString("toDate"))){
				formDate = sdf.parse(jsonObj.getString("fromDate"));
				return session.createQuery("from BillItems bi where bi.status = "+status+" and bi.billId.createdDate >= :formDate GROUP BY bi.productStockId.barCode ").setDate("formDate", formDate).setCacheable(true).list();
			}else if(jsonObj.has("toDate") && jsonObj.getString("toDate") != null && !"".equals(jsonObj.getString("toDate")) 
					&& jsonObj.has("fromDate") && "".equals(jsonObj.getString("fromDate"))){
				toDate = sdf.parse(jsonObj.getString("toDate"));
				return session.createQuery("from BillItems bi where bi.status = "+status+" and bi.billId.createdDate <= :toDate GROUP BY bi.productStockId.barCode ").setDate("toDate", toDate).setCacheable(true).list();
			}else if(jsonObj.has("toDate") && jsonObj.getString("toDate") != null && !"".equals(jsonObj.getString("toDate")) && 
					jsonObj.has("fromDate") && jsonObj.getString("fromDate") != null && !"".equals(jsonObj.getString("fromDate"))){
				formDate = sdf.parse(jsonObj.getString("fromDate"));
				toDate = sdf.parse(jsonObj.getString("toDate"));
				return session.createQuery("from BillItems bi where bi.status = "+status+" and bi.billId.createdDate BETWEEN :formDate and :toDate GROUP BY bi.productStockId.barCode ").setDate("formDate", formDate).setDate("toDate", toDate).setCacheable(true).list();
			}
		}
		return session.createQuery("from BillItems bi where bi.status = "+status+" GROUP BY bi.productStockId.barCode ").setCacheable(true).list();
	}
	
	/**
	 * @author Prasad Salina
	 * This method is used for display sales based on user wise and dates wise.
	 */
	
	@Override
	@Transactional
	public List<BillItems> getAnalyticsReportsByUserWise(JSONObject jsonObj, User user) throws Exception{
		Session session = this.sessionFactory.getCurrentSession();
		Integer status = Constants.RECORD_ACTIVE;
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		Date formDate = null;
		Date toDate = null;
		String subQuery = "";
		String userId = "";
		
		//only user wise based and dates wise 
		
		if(jsonObj.has("userId") && jsonObj.getString("userId") != null && !"".equals(jsonObj.getString("userId"))){
			userId = jsonObj.getString("userId");
			if(jsonObj.has("fromDate") && jsonObj.getString("fromDate") != null && !"".equals(jsonObj.getString("fromDate")) 
					&& jsonObj.has("toDate") && "".equals(jsonObj.getString("toDate"))){
				formDate = sdf.parse(jsonObj.getString("fromDate"));
				subQuery = " bi.billId.createdDate >= :formDate and bi.billId.cashierUserId.id = :userId ";
				return session.createQuery("from BillItems bi where bi.status = "+status+" and "+subQuery+" GROUP BY bi.productStockId.barCode ").setString("userId", userId).setDate("formDate", formDate).setCacheable(true).list();
			}else if(jsonObj.has("toDate") && jsonObj.getString("toDate") != null && !"".equals(jsonObj.getString("toDate")) 
					&& jsonObj.has("fromDate") && "".equals(jsonObj.getString("fromDate"))){
				toDate = sdf.parse(jsonObj.getString("toDate"));
				subQuery = " bi.billId.createdDate <= :toDate and bi.billId.cashierUserId.id = :userId ";
				return session.createQuery("from BillItems bi where bi.status = "+status+" and "+subQuery+" GROUP BY bi.productStockId.barCode ").setString("userId", userId).setDate("toDate", toDate).setCacheable(true).list();
			}else if(jsonObj.has("toDate") && jsonObj.getString("toDate") != null && !"".equals(jsonObj.getString("toDate")) && 
					jsonObj.has("fromDate") && jsonObj.getString("fromDate") != null && !"".equals(jsonObj.getString("fromDate"))){
				formDate = sdf.parse(jsonObj.getString("fromDate"));
				toDate = sdf.parse(jsonObj.getString("toDate"));
				subQuery = "  bi.billId.createdDate BETWEEN :formDate and :toDate and bi.billId.cashierUserId.id = :userId ";
				return session.createQuery("from BillItems bi where bi.status = "+status+" and "+subQuery+" GROUP BY bi.productStockId.barCode ").setString("userId", userId).setDate("formDate", formDate).setDate("toDate", toDate).setCacheable(true).list();
			}
			return session.createQuery("from BillItems bi where bi.status = "+status+" and bi.billId.cashierUserId.id = :userId GROUP BY bi.productStockId.barCode ").setString("userId", userId).setCacheable(true).list();
		}
		// only Dates wise
		else{
			if(jsonObj.has("fromDate") && jsonObj.getString("fromDate") != null && !"".equals(jsonObj.getString("fromDate")) 
					&& jsonObj.has("toDate") && "".equals(jsonObj.getString("toDate"))){
				formDate = sdf.parse(jsonObj.getString("fromDate"));
				return session.createQuery("from BillItems bi where bi.status = "+status+" and bi.billId.createdDate >= :formDate GROUP BY bi.productStockId.barCode ").setDate("formDate", formDate).setCacheable(true).list();
			}else if(jsonObj.has("toDate") && jsonObj.getString("toDate") != null && !"".equals(jsonObj.getString("toDate")) 
					&& jsonObj.has("fromDate") && "".equals(jsonObj.getString("fromDate"))){
				toDate = sdf.parse(jsonObj.getString("toDate"));
				return session.createQuery("from BillItems bi where bi.status = "+status+" and bi.billId.createdDate <= :toDate GROUP BY bi.productStockId.barCode ").setDate("toDate", toDate).setCacheable(true).list();
			}else if(jsonObj.has("toDate") && jsonObj.getString("toDate") != null && !"".equals(jsonObj.getString("toDate")) && 
					jsonObj.has("fromDate") && jsonObj.getString("fromDate") != null && !"".equals(jsonObj.getString("fromDate"))){
				formDate = sdf.parse(jsonObj.getString("fromDate"));
				toDate = sdf.parse(jsonObj.getString("toDate"));
				return session.createQuery("from BillItems bi where bi.status = "+status+" and bi.billId.createdDate BETWEEN :formDate and :toDate GROUP BY bi.productStockId.barCode ").setDate("formDate", formDate).setDate("toDate", toDate).setCacheable(true).list();
			}
		}
		return session.createQuery("from BillItems bi where bi.status = "+status+" GROUP BY bi.productStockId.barCode ").setCacheable(true).list();
	}
	
	/**
	 * @author Prasad Salina
	 * This method is used for display sales based on store type (combination of store type and store) and dates wise.
	 */
	
	@Override
	@Transactional
	public List<BillItems> getAnalyticsReportsByStoreWise(JSONObject jsonObj, User user) throws Exception{
		Session session = this.sessionFactory.getCurrentSession();
		Integer status = Constants.RECORD_ACTIVE;
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		Date formDate = null;
		Date toDate = null;
		String subQuery = "";
		String storeType = "";
		String storeId = "";
		
		//only Store type based and dates 
		if(jsonObj.has("storeTypeDropDown") && jsonObj.getString("storeTypeDropDown") != null && !"".equals(jsonObj.getString("storeTypeDropDown")) 
				&& jsonObj.has("storeSales") && "".equals(jsonObj.getString("storeSales"))){
			storeType = jsonObj.getString("storeTypeDropDown");
			if(jsonObj.has("fromDate") && jsonObj.getString("fromDate") != null && !"".equals(jsonObj.getString("fromDate")) 
					&& jsonObj.has("toDate") && "".equals(jsonObj.getString("toDate"))){
				formDate = sdf.parse(jsonObj.getString("fromDate"));
				return session.createQuery("from BillItems bi where bi.status = "+status+" and bi.billId.createdDate >= :formDate and bi.billId.storeId.storeType = :storeType GROUP BY bi.productStockId.barCode ").setString("storeType", storeType).setDate("formDate", formDate).setCacheable(true).list();
			}else if(jsonObj.has("toDate") && jsonObj.getString("toDate") != null && !"".equals(jsonObj.getString("toDate")) 
					&& jsonObj.has("fromDate") && "".equals(jsonObj.getString("fromDate"))){
				toDate = sdf.parse(jsonObj.getString("toDate"));
				return session.createQuery("from BillItems bi where bi.status = "+status+" and bi.billId.createdDate <= :toDate and bi.billId.storeId.storeType = :storeType GROUP BY bi.productStockId.barCode ").setString("storeType", storeType).setDate("toDate", toDate).setCacheable(true).list();
			}else if(jsonObj.has("toDate") && jsonObj.getString("toDate") != null && !"".equals(jsonObj.getString("toDate")) && 
					jsonObj.has("fromDate") && jsonObj.getString("fromDate") != null && !"".equals(jsonObj.getString("fromDate"))){
				formDate = sdf.parse(jsonObj.getString("fromDate"));
				toDate = sdf.parse(jsonObj.getString("toDate"));
				return session.createQuery("from BillItems bi where bi.status = "+status+" and bi.billId.createdDate BETWEEN :formDate and :toDate and bi.billId.storeId.storeType = :storeType GROUP BY bi.productStockId.barCode ").setString("storeType", storeType).setDate("formDate", formDate).setDate("toDate", toDate).setCacheable(true).list();
			}
			return session.createQuery("from BillItems bi where bi.status = "+status+" and bi.billId.storeId.storeType = :storeType GROUP BY bi.productStockId.barCode ").setString("storeType", storeType).setCacheable(true).list();
		}
		//Combination of Store type and store and Dates
		else if(jsonObj.has("storeSales") && jsonObj.getString("storeSales") != null && !"".equals(jsonObj.getString("storeSales")) 
				&& jsonObj.has("storeTypeDropDown") && jsonObj.getString("storeTypeDropDown") != null && !"".equals(jsonObj.getString("storeTypeDropDown"))){
			storeType = jsonObj.getString("storeTypeDropDown");
			storeId = jsonObj.getString("storeSales");
			if(jsonObj.has("fromDate") && jsonObj.getString("fromDate") != null && !"".equals(jsonObj.getString("fromDate")) 
					&& jsonObj.has("toDate") && "".equals(jsonObj.getString("toDate"))){
				formDate = sdf.parse(jsonObj.getString("fromDate"));
				subQuery = " bi.billId.createdDate >= :formDate and bi.billId.storeId.storeType = :storeType and bi.billId.storeId.id = :storeId ";
				return session.createQuery("from BillItems bi where bi.status = "+status+" and "+subQuery+" GROUP BY bi.productStockId.barCode ").setString("storeId", storeId).setString("storeType", storeType).setDate("formDate", formDate).setCacheable(true).list();
			}else if(jsonObj.has("toDate") && jsonObj.getString("toDate") != null && !"".equals(jsonObj.getString("toDate")) 
					&& jsonObj.has("fromDate") && "".equals(jsonObj.getString("fromDate"))){
				toDate = sdf.parse(jsonObj.getString("toDate"));
				subQuery = " bi.billId.createdDate <= :toDate and bi.billId.storeId.storeType = :storeType and bi.billId.storeId.id = :storeId ";
				return session.createQuery("from BillItems bi where bi.status = "+status+" and "+subQuery+" GROUP BY bi.productStockId.barCode ").setString("storeId", storeId).setString("storeType", storeType).setDate("toDate", toDate).setCacheable(true).list();
			}else if(jsonObj.has("toDate") && jsonObj.getString("toDate") != null && !"".equals(jsonObj.getString("toDate")) && 
					jsonObj.has("fromDate") && jsonObj.getString("fromDate") != null && !"".equals(jsonObj.getString("fromDate"))){
				formDate = sdf.parse(jsonObj.getString("fromDate"));
				toDate = sdf.parse(jsonObj.getString("toDate"));
				subQuery = "  bi.billId.createdDate BETWEEN :formDate and :toDate and bi.billId.storeId.storeType = :storeType and bi.billId.storeId.id = :storeId ";
				return session.createQuery("from BillItems bi where bi.status = "+status+" and "+subQuery+" GROUP BY bi.productStockId.barCode ").setString("storeId", storeId).setString("storeType", storeType).setDate("formDate", formDate).setDate("toDate", toDate).setCacheable(true).list();
			}
			return session.createQuery("from BillItems bi where bi.status = "+status+" and bi.billId.storeId.storeType = :storeType and bi.billId.storeId.id = :storeId GROUP BY bi.productStockId.barCode ").setString("storeId", storeId).setString("storeType", storeType).setCacheable(true).list();
		}
		// only Dates
		else{
			if(jsonObj.has("fromDate") && jsonObj.getString("fromDate") != null && !"".equals(jsonObj.getString("fromDate")) 
					&& jsonObj.has("toDate") && "".equals(jsonObj.getString("toDate"))){
				formDate = sdf.parse(jsonObj.getString("fromDate"));
				subQuery = " bi.billId.createdDate >= :formDate ";
				return session.createQuery("from BillItems bi where bi.status = "+status+" and "+subQuery+" GROUP BY bi.productStockId.barCode ").setDate("formDate", formDate).setCacheable(true).list();
			}else if(jsonObj.has("toDate") && jsonObj.getString("toDate") != null && !"".equals(jsonObj.getString("toDate")) 
					&& jsonObj.has("fromDate") && "".equals(jsonObj.getString("fromDate"))){
				toDate = sdf.parse(jsonObj.getString("toDate"));
				subQuery = " bi.billId.createdDate <= :toDate ";
				return session.createQuery("from BillItems bi where bi.status = "+status+" and "+subQuery+" GROUP BY bi.productStockId.barCode ").setDate("toDate", toDate).setCacheable(true).list();
			}else if(jsonObj.has("toDate") && jsonObj.getString("toDate") != null && !"".equals(jsonObj.getString("toDate")) && 
					jsonObj.has("fromDate") && jsonObj.getString("fromDate") != null && !"".equals(jsonObj.getString("fromDate"))){
				formDate = sdf.parse(jsonObj.getString("fromDate"));
				toDate = sdf.parse(jsonObj.getString("toDate"));
				subQuery = "  bi.billId.createdDate BETWEEN :formDate and :toDate ";
				return session.createQuery("from BillItems bi where bi.status = "+status+" and "+subQuery+" GROUP BY bi.productStockId.barCode ").setDate("formDate", formDate).setDate("toDate", toDate).setCacheable(true).list();
			}
		}
		
		return session.createQuery("from BillItems bi where bi.status = "+status+" GROUP BY bi.productStockId.barCode ").setCacheable(true).list();
	}
	
	/**
	 * @author Prasad Salina
	 * This method is used for display total result based on Quantity, price.
	 */
	@Override
	@Transactional
	public Map<String, String> getResultantQuantityAndPriceByBarCode(String barCode) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List results = session.createQuery("select SUM(bi.quantity), SUM(bi.price) from BillItems bi where bi.productStockId.barCode = :barCode").setString("barCode", barCode).list();
		Map<String,String> priceList =  new HashMap<String,String>();
		 if(results != null && !results.isEmpty()){
			 for(Object result :results){
				 Object[] list = (Object[])result;
				 String quantity = (String) list[0];
				 String sum = (String) list[1];
				 priceList.put("quantity",quantity);
				 priceList.put("sum",sum);
			 }
		 }
		return priceList;
	}

	@Override
	@Transactional
	public List<ProductStock> getProductReports(JSONObject jsonObj, User user) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		Integer status = Constants.RECORD_ACTIVE;
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		Date formDate = null;
		Date toDate = null;
		String subQuery = "";
		String locationId = "";
		
		//only Store(Location) based and dates 
		if(jsonObj.has("locationId") && jsonObj.getString("locationId") != null && !"".equals(jsonObj.getString("locationId"))){
			locationId = jsonObj.getString("locationId");
			if(jsonObj.has("fromDate") && jsonObj.getString("fromDate") != null && !"".equals(jsonObj.getString("fromDate")) 
					&& jsonObj.has("toDate") && "".equals(jsonObj.getString("toDate"))){
				formDate = sdf.parse(jsonObj.getString("fromDate"));
				subQuery = " pds.ExpireDate >= :formDate and pds.storeId.id = :locationId and pds.currentQuantity > 0 ";
				return session.createQuery("from ProductStock pds where pds.status = "+status+" and "+subQuery+" GROUP BY pds.barCode ").setString("locationId", locationId).setDate("formDate", formDate).setCacheable(true).list();
			}else if(jsonObj.has("toDate") && jsonObj.getString("toDate") != null && !"".equals(jsonObj.getString("toDate")) 
					&& jsonObj.has("fromDate") && "".equals(jsonObj.getString("fromDate"))){
				toDate = sdf.parse(jsonObj.getString("toDate"));
				subQuery = " pds.ExpireDate <= :toDate and pds.storeId.id = :locationId and pds.currentQuantity > 0 ";
				return session.createQuery("from ProductStock pds where pds.status = "+status+" and "+subQuery+" GROUP BY pds.barCode ").setString("locationId", locationId).setDate("toDate", toDate).setCacheable(true).list();
			}else if(jsonObj.has("toDate") && jsonObj.getString("toDate") != null && !"".equals(jsonObj.getString("toDate")) && 
					jsonObj.has("fromDate") && jsonObj.getString("fromDate") != null && !"".equals(jsonObj.getString("fromDate"))){
				formDate = sdf.parse(jsonObj.getString("fromDate"));
				toDate = sdf.parse(jsonObj.getString("toDate"));
				subQuery = "  pds.ExpireDate BETWEEN :formDate and :toDate and pds.storeId.id = :locationId and pds.currentQuantity > 0 ";
				return session.createQuery("from ProductStock pds where pds.status = "+status+" and "+subQuery+" GROUP BY pds.barCode ").setString("locationId", locationId).setDate("formDate", formDate).setDate("toDate", toDate).setCacheable(true).list();
			}
			return session.createQuery("from ProductStock pds where pds.status = "+status+" and pds.storeId.id = :locationId and pds.currentQuantity > 0 GROUP BY pds.barCode ").setString("locationId", locationId).setCacheable(true).list();
		}
		// only Dates wise
		else{
			if(jsonObj.has("fromDate") && jsonObj.getString("fromDate") != null && !"".equals(jsonObj.getString("fromDate")) 
					&& jsonObj.has("toDate") && "".equals(jsonObj.getString("toDate"))){
				formDate = sdf.parse(jsonObj.getString("fromDate"));
				return session.createQuery("from ProductStock pds where pds.status = "+status+" and pds.ExpireDate >= :formDate and pds.currentQuantity > 0 GROUP BY pds.barCode ").setDate("formDate", formDate).setCacheable(true).list();
			}else if(jsonObj.has("toDate") && jsonObj.getString("toDate") != null && !"".equals(jsonObj.getString("toDate")) 
					&& jsonObj.has("fromDate") && "".equals(jsonObj.getString("fromDate"))){
				toDate = sdf.parse(jsonObj.getString("toDate"));
				return session.createQuery("from ProductStock pds where pds.status = "+status+" and pds.ExpireDate <= :toDate and pds.currentQuantity > 0 GROUP BY pds.barCode ").setDate("toDate", toDate).setCacheable(true).list();
			}else if(jsonObj.has("toDate") && jsonObj.getString("toDate") != null && !"".equals(jsonObj.getString("toDate")) && 
					jsonObj.has("fromDate") && jsonObj.getString("fromDate") != null && !"".equals(jsonObj.getString("fromDate"))){
				formDate = sdf.parse(jsonObj.getString("fromDate"));
				toDate = sdf.parse(jsonObj.getString("toDate"));
				return session.createQuery("from ProductStock pds where pds.status = "+status+" and pds.ExpireDate BETWEEN :formDate and :toDate and pds.currentQuantity > 0 GROUP BY pds.barCode ").setDate("formDate", formDate).setDate("toDate", toDate).setCacheable(true).list();
			}
		}
		return session.createQuery("from ProductStock pds where pds.status = "+status+" and pds.currentQuantity > 0 GROUP BY pds.barCode ").setCacheable(true).list();
	}
}
