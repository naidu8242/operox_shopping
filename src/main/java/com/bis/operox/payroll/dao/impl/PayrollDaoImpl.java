package com.bis.operox.payroll.dao.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bis.operox.constants.Constants;
import com.bis.operox.inv.dao.entity.Tax;
import com.bis.operox.inv.dao.entity.User;
import com.bis.operox.payroll.dao.PayrollDao;
import com.bis.operox.payroll.entity.Payroll;

@Repository
public class PayrollDaoImpl implements PayrollDao{
	
	private static final Logger logger = LoggerFactory.getLogger(LeavesDaoImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	@Transactional
	public Payroll addPayroll(Payroll payroll) {
		Session session = this.sessionFactory.getCurrentSession();
		session.saveOrUpdate(payroll);
		return payroll;
	}

	@Override
	@Transactional
	public Payroll getPayrollById(Long payrollId) {
		Session session = this.sessionFactory.getCurrentSession();	
		 String query = "from Payroll payroll where payroll.id = :payrollId";
	     List<Payroll> payrollList = session.createQuery(query).setLong("payrollId", payrollId).setCacheable(true).list();
		return payrollList.get(0);
	}

	@Override
	@Transactional
	public List<Payroll> listPayrolls(User user, Date fromDate, Date toDate, Date startDate, Date endDate,String payRollType) {
		Session session = this.sessionFactory.getCurrentSession();
		String query = null;
		Date currDate = new Date();
		List<Payroll> payrollList = null;
		if(!payRollType.equalsIgnoreCase("null")){
			if(fromDate != null && toDate != null){
				query = "from Payroll pay left join fetch pay.user usr  where usr.compensatationType =:payRollType and pay.fromDate >= :fromDate and  pay.toDate <= :toDate";
				payrollList = session.createQuery(query).setDate("fromDate", fromDate).setDate("toDate", toDate).setString("payRollType", payRollType).setCacheable(true).list();
			}else if(fromDate != null && toDate == null){
				query = "from Payroll pay left join fetch pay.user usr where usr.compensatationType =:payRollType and pay.fromDate =:fromDate";
				payrollList = session.createQuery(query).setDate("fromDate", fromDate).setString("payRollType", payRollType).setCacheable(true).list();
			}else if(fromDate == null && toDate != null){
				query = "from Payroll pay left join fetch pay.user usr where usr.compensatationType =:payRollType and pay.toDate <= :toDate";
				payrollList = session.createQuery(query).setDate("toDate", toDate).setString("payRollType", payRollType).setCacheable(true).list();
			}else if(fromDate == null && toDate == null){
				
				query = "from Payroll pay left join fetch pay.user usr where usr.compensatationType =:payRollType and pay.fromDate BETWEEN :startDate and :endDate or pay.fromDate <= :currDate";
				payrollList = session.createQuery(query).setDate("startDate", startDate).setDate("endDate", endDate).setDate("currDate", currDate).setString("payRollType", payRollType).setCacheable(true).list();
			}
		}else{
			if(fromDate != null && toDate != null ){
				query = "from Payroll pay left join fetch pay.user usr  where"
						+ " pay.fromDate >= :fromDate and  pay.toDate <= :toDate";
				payrollList = session.createQuery(query).setDate("fromDate", fromDate).setDate("toDate", toDate).setCacheable(true).list();
			}else if(fromDate != null && toDate == null){
				query = "from Payroll pay left join fetch pay.user  where pay.fromDate =:fromDate";
				payrollList = session.createQuery(query).setDate("fromDate", fromDate).setCacheable(true).list();
			}else if(fromDate == null && toDate != null){
				query = "from Payroll pay left join fetch pay.user  where pay.toDate <= :toDate";
				payrollList = session.createQuery(query).setDate("toDate", toDate).setCacheable(true).list();
			}else if(fromDate == null && toDate == null){
				
				query = "from Payroll pay left join fetch pay.user  where pay.fromDate BETWEEN :startDate and :endDate or pay.fromDate <= :currDate";
				payrollList = session.createQuery(query).setDate("startDate", startDate).setDate("endDate", endDate).setDate("currDate", currDate).setCacheable(true).list();
			}
		}
		return payrollList;
	}

	
	@Override
	@Transactional
	public List<User> gePayrollEmployees(String payRollType,Date fromDate, Date toDate) {
	     Session session = this.sessionFactory.getCurrentSession();	
	     List<User> payrollEmplyeesList = null;
	    	 String query = "from User user where user.compensatationType = :payRollType";
		     payrollEmplyeesList = session.createQuery(query).setString("payRollType", payRollType).setCacheable(true).list();
		return payrollEmplyeesList;
	}

	@Override
	@Transactional
	public Tax getPayrollTax(String taxValue) {
		 Session session = this.sessionFactory.getCurrentSession();	
		 String query = "from Tax tax where tax.taxName = :taxName";
	     List<Tax> payrollEmplyeesTax = session.createQuery(query).setString("taxName", taxValue).setCacheable(true).list();
		return payrollEmplyeesTax.get(0);
		
	}
	

}
