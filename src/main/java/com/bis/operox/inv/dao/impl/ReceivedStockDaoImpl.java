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

import com.bis.operox.inv.dao.ReceivedStockDao;
import com.bis.operox.inv.dao.entity.ReceivedStock;
import com.bis.operox.inv.dao.entity.User;
/**
 * 
 * @author Sammeta David Raju
 * @date 26rd Sep 2016
 *
 */
@Repository
public class ReceivedStockDaoImpl implements ReceivedStockDao{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private static final Logger logger = LoggerFactory.getLogger(ReceivedStockDaoImpl.class);

	@Override
	@Transactional
	public ReceivedStock addReceivedStock(ReceivedStock receivedStock,User user) {
		Session session = this.sessionFactory.getCurrentSession();
		session.saveOrUpdate(receivedStock);
		logger.info("receivedStock added successfully..!!");
		return receivedStock;
	}

	@Override
	@Transactional
	public ReceivedStock getReceivedStockById(Long id) {
		Session session = this.sessionFactory.getCurrentSession();	
		String query = "from ReceivedStock receivedStock  where receivedStock.id = :id";  
	    List<ReceivedStock> receivedStockList = session.createQuery(query).setLong("id", id).setCacheable(true).list();
	    ReceivedStock receivedStock = null;
	    if(receivedStockList != null && !receivedStockList.isEmpty()){
	    	receivedStock = receivedStockList.iterator().next();
	    }
	    return receivedStock;
	}

	@Override
	@Transactional
	public List<ReceivedStock> getAllReceivedStocks() {
		Session session = this.sessionFactory.getCurrentSession();
		List<ReceivedStock> receivedStock = session.createQuery("from ReceivedStock").list();
		return receivedStock;
	}

	@Override
	@Transactional
	public String getMaxReceivedNumber() {
		
		Session session = this.sessionFactory.getCurrentSession();
		String query = "SELECT  MAX(CAST(SUBSTR(TRIM(RECEIVED_NUMBER),2) AS UNSIGNED)) FROM operox.RECEIVED_STOCK";
		List<BigInteger> maxCountList = session.createSQLQuery(query).list();
        Long count = null;
        if(maxCountList != null && !maxCountList.isEmpty() && maxCountList.get(0) != null ){
            count = (Long) maxCountList.iterator().next().longValue();
            return count.toString();
        }else{
        	return "";
        }
	}
	
	/* ReceivedStock receivedStock = null;
	if(receivedStockList != null && !receivedStockList.isEmpty())
	{
		receivedStock = (ReceivedStock) receivedStockList.iterator().next();
	}
	System.out.println(""+receivedStock.getReceivedNumber());
	if(receivedStock != null){
		return receivedStock.getReceivedNumber();
	}else{
		return "";
	}*/
	/*List receivedStockList =  session.createQuery("SELECT MAX(st.receivedNumber) from ReceivedStock st").list();*/
	

}
