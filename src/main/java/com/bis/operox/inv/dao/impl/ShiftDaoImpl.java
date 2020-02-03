package com.bis.operox.inv.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bis.operox.constants.Constants;
import com.bis.operox.inv.dao.ShiftDao;
import com.bis.operox.inv.dao.entity.Shift;
import com.bis.operox.inv.dao.entity.Store;

@Repository
public class ShiftDaoImpl implements ShiftDao{
	@Autowired
	private SessionFactory sessionFactory;
	
	private static final Logger logger = LoggerFactory.getLogger(ShiftDaoImpl.class);
	

	@Override
	@Transactional(rollbackFor = { Exception.class })	
	public Shift storeShift(Shift shift) {
		Session session = this.sessionFactory.getCurrentSession();
		session.saveOrUpdate(shift);
		logger.info("Shift saved successfully, Shift Details="+shift);
		return shift;
	}

	@Override
	@Transactional(rollbackFor = { Exception.class })	
	public Shift getShiftById(Long id) {
		Session session = this.sessionFactory.getCurrentSession();	
		Shift shift = (Shift) session.load(Shift.class, new Long(id));
		logger.info("Address loaded successfully, Address details="+shift);
		return shift;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Shift> getAllShifts() throws Exception{
		Integer status = Constants.RECORD_ACTIVE;
		Session session = this.sessionFactory.getCurrentSession();
		return session.createQuery("from Shift s where s.status = "+status+" ").setCacheable(true).list();
	}

	@Override
	@Transactional(rollbackFor = { Exception.class })	
	public List<Shift> shiftListByStoreId(Long storeId) throws Exception{
		Session session = this.sessionFactory.getCurrentSession();
		List<Shift> shiftList = session.createQuery(
				"Select shift from Shift shift where shift.store.id = :storeId and shift.status = '1' ")
				.setLong("storeId", storeId).setCacheable(true).list();
		return shiftList;
	}

	@Override
	@Transactional(rollbackFor = { Exception.class })
	public List<Shift> shiftListByCompanyId(Long companyId) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List<Shift> shiftList = session.createQuery(
				"Select shift from Shift shift where shift.store.company.id = :companyId and shift.status = '1' ")
				.setLong("companyId", companyId).setCacheable(true).list();
		return shiftList;
	}
}