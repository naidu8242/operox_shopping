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

import com.bis.operox.inv.dao.BillDao;
import com.bis.operox.inv.dao.entity.Bill;
import com.bis.operox.inv.dao.entity.User;

@Repository
public class BillDaoImpl implements BillDao{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private static final Logger logger = LoggerFactory.getLogger(BillDaoImpl.class);

	@Transactional
	public Bill addBill(Bill bill) {
		Session session = this.sessionFactory.getCurrentSession();
		session.saveOrUpdate(bill);
		logger.info("Bill saved successfully, Bill Details=");
		return bill;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public Bill getBillByBillNumber(String billNumber) {
		Session session = this.sessionFactory.getCurrentSession();
		String query = "from Bill bill left join fetch bill.storeId  left join fetch bill.counterId left join fetch bill.cashierUserId where  bill.billNumber = :billNumber";
	    List<Bill> billList = session.createQuery(query).setParameter("billNumber", billNumber).list();         
	    Bill bill = null;
	    if(billList != null && !billList.isEmpty()){
	    	bill = billList.iterator().next();
	    }
	    return bill;
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<Bill> listBill() {
		Session session = this.sessionFactory.getCurrentSession();
		List<Bill> billList = session.createQuery("from Bill").list();
		return billList;
	}

	@Transactional
	public Bill getBillById(Long id) {
		Session session = this.sessionFactory.getCurrentSession();	
		
		Bill bill = (Bill) session.load(Bill.class, new Long(id));
		logger.info("Bill loaded successfully, Bill details="+bill);
		return bill;
	}

	@Override
	@Transactional
	public Long getBillNumberByStoreName(String storeName) {
		Session session = this.sessionFactory.getCurrentSession();
		String query = "SELECT  MAX(CAST(SUBSTR(TRIM(BILL_NUMBER),4) AS UNSIGNED)) FROM operox.BILL WHERE BILL_NUMBER RLIKE  '"+storeName+"'";
		List<BigInteger> maxCountList = session.createSQLQuery(query).list();
		Long count = 0L;
		if(maxCountList != null && !maxCountList.isEmpty() && !maxCountList.contains(null)){
			count = (Long) maxCountList.iterator().next().longValue();
		}
		return count;
	}
	
	@Override
	@Transactional
	public List<Bill> getOnHoldInvoicesByCounterIdAndCashierUserId(Long counterId,Long cashierUserId) {
		
		Session session = this.sessionFactory.getCurrentSession();
		String query = "from Bill bill left join fetch bill.storeId  left join fetch bill.counterId left join fetch bill.cashierUserId where  bill.billStatus = 'ONHOLD' and bill.counterId.id = :counterId and bill.cashierUserId.id = :userId";
	    return session.createQuery(query).setLong("userId", cashierUserId).setLong("counterId", counterId).list();
	}

	@Override
	@Transactional
	public  Bill ecommOrderListByUserId(Long billId,String userCode) {
		
		Session session = this.sessionFactory.getCurrentSession();	
		String query = "from Bill bill where bill.id = :billId and bill.createdBy = :userCode";
		List<Bill> billList = session.createQuery(query).setLong("billId", billId).setParameter("userCode", userCode).setCacheable(true).list();
		return billList.get(0);
	}

	@Override
	@Transactional
	public List<Bill> ecommOrderListByUserCode(String userCode) {
		Session session = this.sessionFactory.getCurrentSession();
		String query = "from Bill bill where  bill.createdBy = :userCode";
	    List<Bill> oderbillList = session.createQuery(query).setParameter("userCode", userCode).list();         
		return oderbillList;
	}

	@Override
	@Transactional
	public String getMaxReceivedNumber() {
		Session session = this.sessionFactory.getCurrentSession();
		String query = "SELECT  MAX(CAST(SUBSTR(TRIM(BILL_NUMBER),2) AS UNSIGNED)) FROM operox.BILL";
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