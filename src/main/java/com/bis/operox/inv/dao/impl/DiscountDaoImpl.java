package com.bis.operox.inv.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bis.operox.inv.dao.DiscountDao;
import com.bis.operox.inv.dao.entity.Discount;

/**
 * 
 * @author Prasad Salina
 *
 */

@Repository
public class DiscountDaoImpl implements DiscountDao {

	@Autowired
	private SessionFactory sessionFactory;

	private static final Logger logger = LoggerFactory.getLogger(DiscountDaoImpl.class);

	@Override
	public Discount addDiscount(Discount discount) {
		Session session = this.sessionFactory.getCurrentSession();
		session.saveOrUpdate(discount);
		logger.info("discount saved successfully, discount Details=" + discount);
		return discount;
	}

	@Override
	public Discount getDiscountById(Long id) {
		Session session = this.sessionFactory.getCurrentSession();
		Discount discount = session.load(Discount.class, new Long(id));
		logger.info("discount loaded successfully, discount details=" + discount);
		return discount;
	}

	@Override
	public List<Discount> getAll() {
		Session session = this.sessionFactory.getCurrentSession();
		List<Discount> discountList = session.createQuery("from Discount").list();
		return discountList;
	}

}