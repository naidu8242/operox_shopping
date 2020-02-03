package com.bis.operox.inv.service;


import java.util.List;

import org.json.JSONObject;
import org.springframework.web.multipart.MultipartFile;

import com.bis.operox.inv.dao.entity.User;
public interface UserService  {

	public User addUser(User user);
	
	public User getUserById(Long id);
	
	public List<User> listUser();
	
	User findByUserName(String username);
	
	public User getUserByStoreIdAndRole(Long storeId, Long role) throws Exception;
	
	public List<User> getUserListByStoreId(Long storeId,String userCode,String role) throws Exception;
	
	public List<Long> getUserIdsByShiftId(Long shiftId) throws Exception;

	//public User getUserByUserCode(String updatedBy) throws Exception;
	

	//User getUserByUserCode(String userCode) throws Exception;


	void storeUser(JSONObject jsonObj,User loginUser) throws Exception;
	
	public User saveUserProfile(JSONObject jsonObj,MultipartFile multiFile,User user) throws Exception;

	User getUserByUserCode(String userCode) throws Exception;
	
	 User removeUserById(Long id);

	void sendEmail(User user, User loginUser, String password) throws Exception;

	Boolean validateEmployeeId(String employeeId) throws Exception;
	
	public User findByEmail(String email)  throws Exception;
	
	public List<User> getWorkOrderApprovers(Long storeId) throws Exception;

	public List<User> getUsersListByStoreId(Long storeId);

	public List<User> getUserByProductionUnit();

	public void mailSend(User user, User foundUser, String randomNumber)throws Exception;
	
	public void resetPasswordMail(User user, String verificationCode)throws Exception;

	public User getUserByVerificationCode(JSONObject jsonObj, String verificationCode)throws Exception;
	 void storeEcommUser(JSONObject jsonObj) throws Exception;

	

	

}	
		
	
