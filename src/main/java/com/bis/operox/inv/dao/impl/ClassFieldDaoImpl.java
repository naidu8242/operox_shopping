package com.bis.operox.inv.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bis.operox.inv.dao.ClassFieldDao;
import com.bis.operox.inv.dao.entity.ClassField;

/**
 * 
 * @author Laxman
 *
 */
@Repository
public class ClassFieldDaoImpl implements ClassFieldDao{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private static final Logger logger = LoggerFactory.getLogger(ClassFieldDaoImpl.class);

	/**
	 * this method for get the class filed by delimiterStr
	 */
	@Override
	@Transactional(rollbackFor = { Exception.class })
	public ClassField getClassFieldByDelimiterStr(String delimiterStr) throws Exception{
		logger.debug("In ClassFieldDaoImpl class getClassFieldByDelimiterStr method");
		Session session = this.sessionFactory.getCurrentSession();
		String query = " select cf  from ClassField cf left join fetch cf.entityTypeId where cf.delimStr = :delimiterStr";
		List cfList = session.createQuery(query)
							 .setString("delimiterStr", delimiterStr)
							 .list();
		
		ClassField classField = null;
		if(cfList != null && !cfList.isEmpty())
		{
			classField = (ClassField) cfList.iterator().next();
		}
			
		return classField;
	}

}
