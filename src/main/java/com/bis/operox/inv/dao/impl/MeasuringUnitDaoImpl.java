package com.bis.operox.inv.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bis.operox.inv.dao.MeasuringUnitDao;
import com.bis.operox.inv.dao.entity.MeasuringUnit;

@Repository
public class MeasuringUnitDaoImpl implements MeasuringUnitDao{

	@Autowired
	private SessionFactory sessionFactory;

	private static final Logger logger = LoggerFactory.getLogger(MeasuringUnitDaoImpl.class);

	@Override
	@Transactional(rollbackFor = { Exception.class })	
	public MeasuringUnit addMeasuringUnit(MeasuringUnit measuringUnit) {
		Session session = this.sessionFactory.getCurrentSession();
		session.saveOrUpdate(measuringUnit);
		logger.info("MeasuringUnit saved successfully, MeasuringUnit Details="+measuringUnit);
		return measuringUnit;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(rollbackFor = { Exception.class })	
	public MeasuringUnit getMeasuringUnitById(Long measuringUnitId) {
		Session session = this.sessionFactory.getCurrentSession();	
		 String query = "from MeasuringUnit mu where mu.id = :measuringUnitId";	     
		List<MeasuringUnit> measuringUnitList = session.createQuery(query).setLong("measuringUnitId", measuringUnitId).setCacheable(true).list();
	    return measuringUnitList.get(0);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(rollbackFor = { Exception.class })
	public List<MeasuringUnit> listMeasuringUnit() {
		Session session = this.sessionFactory.getCurrentSession();
		Integer status = 1;
		String query = "from MeasuringUnit mu where mu.status = :status";
		List<MeasuringUnit> measuringUnitList =  session.createQuery(query).setInteger("status", status).setCacheable(true).list();
		return measuringUnitList;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public Boolean validateMeasuringUnit(String measuringUnit) {
		boolean isValidMeasuringUnit = true;
		Integer status = 1;
		Session session = this.sessionFactory.getCurrentSession();
		String query = "from MeasuringUnit mu where mu.measuringUnit = :measuringUnit and mu.status = :status";
		List<MeasuringUnit> measuringUnitList = session.createQuery(query).setString("measuringUnit", measuringUnit).setInteger("status", status).setCacheable(true).list();
		if(measuringUnitList != null && measuringUnitList.size() > 0){
			isValidMeasuringUnit = false;
		}
		return isValidMeasuringUnit;
	}
}