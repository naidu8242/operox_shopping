package com.bis.operox.inv.dao.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bis.operox.constants.Constants;
import com.bis.operox.inv.dao.UserDao;
import com.bis.operox.inv.dao.entity.Product;
import com.bis.operox.inv.dao.entity.User;

@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	private SessionFactory sessionFactory;

	private static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);
	
	protected boolean isNullOrEmpty(String str) {
		return (str == null || "".equals(str.trim()));
	}
	

	@Override
	@Transactional
	public User addUser(User user) {
		Session session = this.sessionFactory.getCurrentSession();
		session.saveOrUpdate(user);
		logger.info("User saved successfully.");
		return user;
	}


	@Override
	@Transactional
	public User getUserById(Long id) {
		Session session = this.sessionFactory.getCurrentSession();	
		String query = "from User usr where usr.id = :id";
		List<User> usersList = session.createQuery(query).setLong("id", id).setCacheable(true).list();
		return usersList.get(0);
	}


	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<User> listUser() {
		Session session = this.sessionFactory.getCurrentSession();
		Integer status = Constants.RECORD_ACTIVE;
		List<User> usersList = session.createQuery("from User user where user.status = "+status+" ").list();
		return usersList;
	}


	@Override
	@Transactional
	public User findByUserName(String username) {
		Session session = this.sessionFactory.getCurrentSession();
		List<User> users = new ArrayList<User>();
		logger.debug("findByUserName start");
		users = session.createQuery("from User where username=?").setParameter(0, username).setCacheable(true).list();
		logger.debug("findByUserName end");
		if (users.size() > 0) {
			return users.get(0);
		} else {
			return null;
		}
	}


	@Override
	@Transactional
	public List<User> getUserListByStoreId(Long companyId, String userCode,String role) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();

		
		String subQuery = "";
		if(!role.equalsIgnoreCase("ROLE_ADMIN")){
			subQuery = "and user.createdBy = '" + userCode + "' ";
		}

		
		return session.createQuery(
				"Select user from User user where user.store.company.id = :companyId and user.status = '1' and user.userCode <> :userCode "
						+ subQuery + " ")
				.setLong("companyId", companyId).setString("userCode", userCode).setCacheable(true).list();
	}
	
	@Override
	@Transactional
	public User getUserByStoreIdAndRole(Long storeId, Long role) {
		Session session = this.sessionFactory.getCurrentSession();	
		String query = "from User usr where usr.store = :storeId and usr.role = :role";
		List<User> usersList = session.createQuery(query).setLong("storeId", storeId).setLong("role", role).setCacheable(true).list();
		if (usersList.size() > 0) {
			return usersList.get(0);
		} else {
			return null;
		}
	}
	
	@Override
	@Transactional(rollbackFor = { Exception.class })
	public List<Long> getUserIdsByShiftId(Long shiftId) {
		    Session session = this.sessionFactory.getCurrentSession();
			String query = "select user.id from User user where user.shift.id =:shiftId";
			List<Long> userIds =  session.createQuery(query.toString())
										.setLong("shiftId", shiftId)
										.setCacheable(true).list();
		return userIds;
	}
	
	@Override
	@Transactional(rollbackFor = { Exception.class })
	public User getUserByUserCode(String userCode) throws Exception {
			Session session = this.sessionFactory.getCurrentSession();
			List<User> users = new ArrayList<User>();
			logger.debug("getUserBy user code start");
			users = session.createQuery("from User user where user.userCode =:userCode" ).setCacheable(true).setString( "userCode", userCode ).setCacheable(true).list();
			logger.debug("getUserBy user code end");
			if (users.size() > 0) {
				return users.get(0);
			} else {
				return null;
			}
	}


	@Override
	@Transactional(rollbackFor = { Exception.class })	
	public Boolean validateEmployeeId(String employeeId) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		 boolean flag = false;
				String query = "from User user where user.employeeId = :employeeId ";
				Collection<User> validateUserList = session.createQuery(query.toString()).setString("employeeId", employeeId).list();
				if (validateUserList != null && validateUserList.size() > 0) {
					flag = true;
				}
		return flag;
	}


	@Override
	@Transactional(rollbackFor = { Exception.class })	
	public User findByEmail(String email) {
		Session session = this.sessionFactory.getCurrentSession();
		User user = null;
		logger.debug("findByEmail start");
		List<User> users = session.createQuery("from User where email=:email" ).setString( "email", email ).setCacheable(true).list();
		logger.debug("findByEmail end");
		if (users.size() > 0) {
			user =  users.get(0);
		} 
		return user;
	}


	@Override
	@Transactional(rollbackFor = { Exception.class })	
	public List<User> getWorkOrderApprovers(Long storeId) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		
		String subQuery = "";
			subQuery = "(user.role.id = '1' OR user.role.id = '6')";
		
		return session.createQuery(
				"Select user from User user where user.store.id = :storeId and user.status = '1' and "
						+ subQuery + " ")
				.setLong("storeId", storeId).setCacheable(true).list();
	}


	@Override
	@Transactional(rollbackFor = { Exception.class })	
	public List<User> getUsersListByStoreId(Long storeId) {
		Session session = this.sessionFactory.getCurrentSession();
		return session.createQuery("from User user where user.store.id = :storeId and user.status = '1'")
				.setLong("storeId", storeId).setCacheable(true).list();
	}


	@Override
	@Transactional(rollbackFor = { Exception.class })	
	public List<User> getUserByProductionUnit() {
		Session session = this.sessionFactory.getCurrentSession();
		return session.createQuery("from User user where user.store.storeType = 'Production Unit'").setCacheable(true).list();
	}


	@Override
	@Transactional(rollbackFor = { Exception.class })
	public User getUserByVerificationCode(String verificationCode) {
		Session session = this.sessionFactory.getCurrentSession();	
		String query = "from User usr where usr.verificationCode = :verificationCode";
		List<User> usersList = session.createQuery(query).setString("verificationCode", verificationCode).setCacheable(true).list();
		return usersList.get(0);
	}




	

}