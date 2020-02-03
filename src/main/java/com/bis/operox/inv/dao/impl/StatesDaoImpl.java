package com.bis.operox.inv.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bis.operox.inv.dao.StatesDao;
import com.bis.operox.inv.dao.entity.States;

/**
 * 
 * @author Prasad Salina
 *
 */
@Repository
public class StatesDaoImpl implements StatesDao{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);



	@Override
	@Transactional(rollbackFor = { Exception.class })
	public List<States> getAllStatesByCountryCode(String countryCode) throws Exception {
		logger.debug("In StatesDaoImpl getAllStatesByCountryCode method");
		Session session = this.sessionFactory.getCurrentSession();
		Query query= session.createQuery("from States states where states.countryCode = :countryCode order by states.stateName ASC");
		List<States> statesList = query.setString("countryCode", countryCode).setCacheable(true).list();
		return statesList;
	}

	@Override
	@Transactional(rollbackFor = { Exception.class })
	public States getStatesByStateName(String name) {
		Session session = this.sessionFactory.getCurrentSession();
		List<States> states = new ArrayList<States>();
		logger.debug("StatesDaoImpl getStatesByStateName");
		states = session.createQuery("from States where stateName = :name" ).setString( "name", name ).setCacheable(true).list();
		if (states.size() > 0) {
			return states.get(0);
		} else {
			return null;
		}
   	}

	@Override
	@Transactional(rollbackFor = { Exception.class })
	public List<String> getStatesCodesByStateName(String name) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List<String> stateCodesList = new ArrayList<String>();
		List<String> statesList = null;
		String[] states = name.split(",");
		for (String stateName : states) {
			String query = "select state.stateCode from States state where state.stateName = :name";
			statesList =  session.createQuery(query).setString( "name", stateName ).setCacheable(true).list();
			stateCodesList.addAll(statesList);
		}
		return stateCodesList;
	}
}
