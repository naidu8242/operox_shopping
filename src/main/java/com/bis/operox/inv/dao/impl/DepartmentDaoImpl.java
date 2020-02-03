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
import com.bis.operox.inv.dao.DepartmentDao;
import com.bis.operox.inv.dao.entity.Department;
import com.bis.operox.inv.dao.entity.Store;

/**
 * @author Ajith Matta.
 *
 */
@Repository
public class DepartmentDaoImpl implements DepartmentDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private static final Logger logger = LoggerFactory.getLogger(DepartmentDaoImpl.class);

	@Override
	@Transactional
	public Department addDepartment(Department department) {
		Session session = this.sessionFactory.getCurrentSession();
		try {
			if (department != null) {
				session.saveOrUpdate(department);
			}
			logger.debug("Candidate Stored Successfully");
		} catch (Exception exception) {
			
		}
		
		return department;
	}
	
	@Override
	@Transactional
	public Department getDepartmentById(Long id) throws Exception{
		Session session = this.sessionFactory.getCurrentSession();			
		Department dept = session.load(Department.class, new Long(id));
		String query = "from Department dep where dep.id = :id";

		 List departmentList = session.createQuery(query).setLong("id", id).setCacheable(true).list();
         
		 Department department = null;
         if(departmentList != null && !departmentList.isEmpty())
         {
        	 department = (Department) departmentList.iterator().next();
         }
         return department;

	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Department> listDepartments() throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		Integer status = Constants.RECORD_ACTIVE;
		return session.createQuery("from Department dep where dep.status = "+status+" ").setCacheable(true).list();
				
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(rollbackFor = { Exception.class })	
	public Boolean validateDepartmentNameAndId(String departmentName, String departmentId) throws Exception {
		 logger.debug("In Department validateDepartmentName method");
		 Session session = this.sessionFactory.getCurrentSession();
		 Integer status = Constants.RECORD_ACTIVE;
		 boolean flag = false;
		 if(departmentName != null){
				String query = "from Department d where d.departmentName = :departmentName and d.status = "+status+" ";
				List<Department> departmentList = session.createQuery(query).setString("departmentName", departmentName).setCacheable(true).list();
				if (departmentList != null && departmentList.size() > 0) {
					flag = true;
				}
		 }
		 else if(departmentId != null) {
				String query = "from Department d where d.departmentId = :departmentId and d.status = "+status+" ";
				List<Department> departmentList = session.createQuery(query).setString("departmentId", departmentId).setCacheable(true).list();
				if (departmentList != null && departmentList.size() > 0) {
					flag = true;
				}
			 }
		return flag;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(rollbackFor = { Exception.class })	
	public  Department getDepartmentByDeptIdAndDepartmentName(String id, String departmentName, String departmentId) throws Exception {
		logger.debug("In Department getDepartmentByDeptIdAndDepartmentName method");
		Session session = this.sessionFactory.getCurrentSession();
		Integer status = Constants.RECORD_ACTIVE;
		String query = "";
		List<Department> departmentList = null;
		if(departmentName != null && !"".equals(departmentName)){
			 query = "from Department d where d.departmentName = :departmentName and d.id = :id and d.status = "+status+" ";
			 departmentList = session.createQuery(query).setString("id", id).setString("departmentName", departmentName).setCacheable(true).list();
		}else if(departmentId != null && !"".equals(departmentId)){
			 query = "from Department d where d.departmentId = :departmentId and d.id = :id and d.status = "+status+" ";
			 departmentList = session.createQuery(query).setString("id", id).setString("departmentId", departmentId).setCacheable(true).list();
		}
		Department department = null;
		if(departmentList != null && !departmentList.isEmpty())
		{
			department = departmentList.iterator().next();
		}
		return department;
	}

}
