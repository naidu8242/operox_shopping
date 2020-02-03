package com.bis.operox.inv.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bis.operox.inv.dao.CountriesDao;
import com.bis.operox.inv.dao.entity.Countries;

@Repository
public class CountriesDaoImpl implements CountriesDao{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	DataSource dataSource;
	
	Connection con = null;

	private static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

	@Override
	@Transactional(rollbackFor = { Exception.class })
	public Countries getCountriesByCountryName(String name) {
		
		Session session = this.sessionFactory.getCurrentSession();
		List<Countries> countires = new ArrayList<Countries>();
		logger.debug("findByUserName start");
		countires = session.createQuery("from Countries where name = :name" ).setString( "name", name ).list();
		logger.debug("findByUserName end");
		if (countires.size() > 0) {
			return countires.get(0);
		} else {
			return null;
		}
   	}

	@Override
	@Transactional(rollbackFor = { Exception.class })
	public List<Countries> getAllCountries() {
		logger.debug("In Countries getAllCountries method");
        Session session = this.sessionFactory.getCurrentSession();
        List<Countries> countriesList= session.createQuery("from Countries c order by c.name ASC " ).setCacheable(true).list();
        return countriesList;
	}
	
	@Override
	@Transactional(rollbackFor = { Exception.class })
	public List<Countries> getAllSourceLeadCountries() {
		logger.debug("In Countries getAllCountries method");
        Session session = this.sessionFactory.getCurrentSession();
        List<Countries> countriesList= session.createQuery("from Countries c where c.name = 'Australia' OR c.name = 'France' OR c.name = 'India' OR c.name = 'Singapore' OR c.name = 'United Kingdom' OR c.name = 'United States' order by c.name ASC " ).setCacheable(true).list();
        return countriesList;
	}

	@Override
	@Transactional(rollbackFor = { Exception.class })
	public List<String> getAllStatesAndCitiesByCountryCode(String countryCode) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		ArrayList<String> cityStatesList = new ArrayList<String>();
		try{
			     con = dataSource.getConnection();
				 Statement stm = con.createStatement();
		         String query = "select s.stateName as state, c.cityName as city from states s, webgeocities c where c.stateCode = s.stateCode and c.countryCode = '"+countryCode+"' and s.countryCode = '"+countryCode+"' ";
		         ResultSet re = stm.executeQuery(query);
				 while (re.next()) {
						cityStatesList.add(re.getString("city")+","+re.getString("state"));
				 }
				
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				/*if (con != null) {
					con.close();
				}*/
			} catch (Exception sqlE) {
				sqlE.printStackTrace();
			}
		}
		
		/*String query = "select s.stateName, c.cityName from States s, Webgeocities c where c.stateCode = s.stateCode and c.countryCode = '"+countryCode+"' and s.countryCode = '"+countryCode+"' ";
		List<String> modulesIdList =  session.createQuery(query.toString())
									.setCacheable(true).list();*/
		
		
		return cityStatesList;
		
	}

	
}
