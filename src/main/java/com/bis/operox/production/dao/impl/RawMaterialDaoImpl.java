package com.bis.operox.production.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bis.operox.constants.Constants;
import com.bis.operox.inv.dao.entity.Supplier;
import com.bis.operox.production.dao.RawMaterialDao;
import com.bis.operox.production.dao.entity.RawMaterial;

@Repository
public class RawMaterialDaoImpl implements RawMaterialDao{

	@Autowired
	private SessionFactory sessionFactory;
	
	private static final Logger logger = LoggerFactory.getLogger(RawMaterialDaoImpl.class);

	@Override
	@Transactional(rollbackFor = { Exception.class })	
	public RawMaterial addRawMaterial(RawMaterial rawMaterial) {
		Session session = this.sessionFactory.getCurrentSession();
		session.saveOrUpdate(rawMaterial);
		logger.info("RawMaterial saved successfully, RawMaterial Details="+rawMaterial);
		return rawMaterial;
	}

	@Override
	@Transactional(rollbackFor = { Exception.class })	
	public RawMaterial getRawMaterialById(Long id) {
        Session session = this.sessionFactory.getCurrentSession();	
        RawMaterial rawMaterial = (RawMaterial) session.load(RawMaterial.class, new Long(id));
		logger.info("RawMaterial loaded successfully, RawMaterial details="+rawMaterial);
	    return rawMaterial;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(rollbackFor = { Exception.class })	
	public List<RawMaterial> rawMaterialsList() {
		Session session = this.sessionFactory.getCurrentSession();
		return session.createQuery("from RawMaterial rm where rm.status = "+Constants.RECORD_ACTIVE+"" ).list();
	}
    
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(rollbackFor = { Exception.class })	
	public List<RawMaterial> getAllRawMaterialByStatus(Integer status) throws Exception {
		
		Session session = this.sessionFactory.getCurrentSession();
		String subQuery = null;
		if(status.equals(Constants.Active)){
			  subQuery ="where rm.status = 1";
		}else if(status.equals(Constants.IN_Active)){
			  subQuery ="where rm.status = 0";
		}else{
			 subQuery ="where rm.status = 1 or where rm.status = 0";
		}
		
		List<RawMaterial> rawMaterialList = session.createQuery("from RawMaterial rm "+subQuery+"").setCacheable(true).list();
		return rawMaterialList;
	}

	@Override
	@Transactional(rollbackFor = { Exception.class })	
	public boolean isRawMaterialCodeValid(String rawMaterialCode, Long id) throws Exception {
		boolean israwMaterialCodeFound = false;
        Session session = this.sessionFactory.getCurrentSession();
        String query = "from RawMaterial rm where rm.searchCode = :rawMaterialCode and rm.status = 1";
        List<RawMaterial> rawMaterialslist = session.createQuery(query.toString()).setString("rawMaterialCode", rawMaterialCode).list();
          if (rawMaterialslist != null && !rawMaterialslist.isEmpty()) {
        	  israwMaterialCodeFound = true;
          }
          return israwMaterialCodeFound;
	}
}