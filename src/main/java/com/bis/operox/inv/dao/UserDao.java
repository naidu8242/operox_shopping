package com.bis.operox.inv.dao;

import java.util.List;

import com.bis.operox.inv.dao.entity.User;

public interface UserDao {

	public User addUser(User user);
	
	public User getUserById(Long id);
	
	public List<User> listUser();
	
	User findByUserName(String username);
	
	public User getUserByStoreIdAndRole(Long storeId, Long role) throws Exception;
	
	public List<User> getUserListByStoreId(Long storeId,String userCode,String role) throws Exception;

	public List<Long> getUserIdsByShiftId(Long shiftId) throws Exception;


	public User getUserByUserCode(String updatedBy) throws Exception;

	public Boolean validateEmployeeId(String employeeId) throws Exception;
	
	User findByEmail(String email);
	
	//User getUserByUserCode(String userCode) throws Exception;
	
	public List<User> getWorkOrderApprovers(Long storeId) throws Exception;

	public List<User> getUsersListByStoreId(Long storeId);

	public List<User> getUserByProductionUnit();

	public User getUserByVerificationCode(String verificatinCode)throws Exception;


}