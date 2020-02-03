package com.bis.operox.payroll.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.bis.operox.payroll.dao.HolidayDao;
import com.bis.operox.payroll.entity.Holiday;

@Repository
public class HolidayDaoImpl implements HolidayDao{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private static final Logger logger = LoggerFactory.getLogger(HolidayDaoImpl.class);

	@Override
	@Transactional
	public Holiday addHoliday(Holiday holiday) {
		Session session = this.sessionFactory.getCurrentSession();
		session.saveOrUpdate(holiday);
		return holiday;
	}

	@Override
	@Transactional
	public Holiday getHolidayById(Long holidayId) {
		Session session = this.sessionFactory.getCurrentSession();	
		 String query = "from Holiday holy where holy.id = :holidayId";
	     List<Holiday> holidayList = session.createQuery(query).setLong("holidayId", holidayId).setCacheable(true).list();
	    return holidayList.get(0);
	}

	@Override
	@Transactional
	public List<Holiday> listHolidays(Long userId) {
		Integer status = 1;
	    Session session = this.sessionFactory.getCurrentSession();
        String query = "from Holiday holiday where holiday.user.id = :userId and holiday.status = :status";
        List<Holiday> holidayList = session.createQuery(query).setLong("userId", userId).setInteger("status", status).setCacheable(true).list();
        return holidayList;
	}

	@Override
	@Transactional
	public Holiday deleteHoliday(Holiday holiday) {
		Session session = this.sessionFactory.getCurrentSession();
		session.saveOrUpdate(holiday);
		return holiday;
	}

}
