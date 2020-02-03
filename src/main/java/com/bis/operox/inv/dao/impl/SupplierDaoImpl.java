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
import com.bis.operox.inv.dao.SupplierDao;
import com.bis.operox.inv.dao.entity.Supplier;

/**
 * @author Ajith Matta
 * @Date 23/09/2016
 *
 */
@Repository
public class SupplierDaoImpl implements SupplierDao{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private static final Logger logger = LoggerFactory.getLogger(SupplierDaoImpl.class);

	/**
	 * 
	 * This method is used for to store/add Supplier into DB
	 * @author Prasad K
	 * @return supplier
	 */

	@Override
	@Transactional
	public Supplier addSupplier(Supplier supplier) {
		Session session = this.sessionFactory.getCurrentSession();
		session.saveOrUpdate(supplier);
		logger.info("Supplier saved successfully, Supplier Details="+supplier);
		return supplier;
		
	}



	@Override
	@Transactional
	public Supplier getSupplierById(Long id) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();	
		Supplier supplier = (Supplier) session.load(Supplier.class, new Long(id));
		logger.info("supplier loaded successfully, supplier details="+supplier);
		return supplier;
	}


	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Supplier> getAllSupplier() throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List<Supplier> supplierList = session.createQuery("from Supplier").setCacheable(true).list();
		return supplierList;
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Supplier> getAllSupplierByStatus(Integer status) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		String subQuery = null;
		if(status.equals(Constants.Active)){
			  subQuery ="where supplier.status = 1";
		}else if(status.equals(Constants.IN_Active)){
			  subQuery ="where supplier.status = 0";
		}else{
			 subQuery ="where supplier.status = 1 or where supplier.status = 0";
		}
		
		List<Supplier> supplierList = session.createQuery("from Supplier supplier "+subQuery+"").setCacheable(true).list();
		return supplierList;
	}



	@Override
	@Transactional(rollbackFor = { Exception.class })
	public boolean isSupplierNameValid(String supplierName, Long id) throws Exception {
		 
		boolean isSupplierFound = false;
          Session session = this.sessionFactory.getCurrentSession();
          String query = "from Supplier sup where sup.name = :supplierName and sup.status = 1";
          List<Supplier> supplierslist = session.createQuery(query.toString()).setString("supplierName", supplierName).list();
            if (supplierslist != null && !supplierslist.isEmpty()) {
            	isSupplierFound = true;
            }
            return isSupplierFound;
	}
	
	
	
}