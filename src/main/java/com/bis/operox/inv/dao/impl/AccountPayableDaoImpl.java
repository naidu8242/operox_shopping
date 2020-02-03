package com.bis.operox.inv.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bis.operox.inv.dao.AccountPayableDao;
import com.bis.operox.inv.dao.entity.AccountPayable;

@Repository
public class AccountPayableDaoImpl implements AccountPayableDao{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private static final Logger logger = LoggerFactory.getLogger(AccountPayableDaoImpl.class);

   /**
    * @author Prasad K
    * 
    * This method is used for to store Account Payable details into DB
    */
	@Override
	@Transactional
	public AccountPayable addAccountPayable(AccountPayable accountPayable) {
		Session session = this.sessionFactory.getCurrentSession();
		session.saveOrUpdate(accountPayable);
		logger.info("AccountPayable saved successfully, AccountPayable Details="+accountPayable);
		return accountPayable;
		
	}


}