package com.bis.operox.inv.dao.impl;

import java.math.BigInteger;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bis.operox.inv.dao.StockReturnsDao;
import com.bis.operox.inv.dao.entity.StockReturns;

@Repository
public class StockReturnsDaoImpl implements StockReturnsDao{
	

@Autowired
private SessionFactory sessionFactory;

private static final Logger logger = LoggerFactory.getLogger(PurchaseOrderDaoImpl.class);

@Override
@Transactional
public StockReturns addStockReturns(StockReturns stockReturns) {
	Session session = this.sessionFactory.getCurrentSession();
	session.saveOrUpdate(stockReturns);
	logger.info("stockReturns added successfully..!!");
	return stockReturns;
}

@Override
@Transactional
public StockReturns getStockReturnsById(Long id) {
	Session session = this.sessionFactory.getCurrentSession();
    String query = "from StockReturns stockReturns  where stockReturns.id = :id";  
    List<StockReturns> stockReturnsList = session.createQuery(query).setLong("id", id).setCacheable(true).list();
    StockReturns stockReturns = null;
    if(stockReturnsList != null && !stockReturnsList.isEmpty()){
    	stockReturns = stockReturnsList.iterator().next();
    }
    return stockReturns;
}

@Override
@Transactional
public List<StockReturns> getAllStockReturns() {
	Session session = this.sessionFactory.getCurrentSession();
	List<StockReturns> stockReturns = session.createQuery("from StockReturns str where str.returnType ='return' and str.returnType.STATUS = 1 ").list();
	return stockReturns;
}

@Override
@Transactional
public List<StockReturns> getAllTransferStock() {
	Session session = this.sessionFactory.getCurrentSession();
	List<StockReturns> stockReturns = session.createQuery("from StockReturns str where str.returnType ='transfer' and str.returnType.STATUS = 1 ").list();
	return stockReturns;
	
  }

@Override
@Transactional
public StockReturns addStockTransfer(StockReturns stockReturns) {

	    Session session = this.sessionFactory.getCurrentSession();
		session.saveOrUpdate(stockReturns);
		logger.info("stockReturns added successfully..!!");
		return stockReturns;
	}


@Override
@Transactional
public String getMaxStockTransferedNumber() {
    
    
	Session session = this.sessionFactory.getCurrentSession();
	String query = "SELECT  MAX(CAST(SUBSTR(TRIM(TRANSFER_NUMBER),2) AS UNSIGNED)) FROM operox.STOCK_RETURNS";
	List<BigInteger> maxCountList = session.createSQLQuery(query).list();
    Long count = null;
    if(maxCountList != null && !maxCountList.isEmpty() && maxCountList.get(0) != null ){
        count = (Long) maxCountList.iterator().next().longValue();
        return count.toString();
    }else{
    	return "";
    }
    
}

}