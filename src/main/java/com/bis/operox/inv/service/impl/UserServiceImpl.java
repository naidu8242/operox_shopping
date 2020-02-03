package com.bis.operox.inv.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bis.operox.OperoxPasswordEncoder;
import com.bis.operox.constants.Constants;
import com.bis.operox.inv.dao.AddressDao;
import com.bis.operox.inv.dao.UserDao;
import com.bis.operox.inv.dao.entity.Address;
import com.bis.operox.inv.dao.entity.Department;
import com.bis.operox.inv.dao.entity.EmailAttachment;
import com.bis.operox.inv.dao.entity.EmailMessage;
import com.bis.operox.inv.dao.entity.EmailRecipient;
import com.bis.operox.inv.dao.entity.EmailScenario;
import com.bis.operox.inv.dao.entity.Role;
import com.bis.operox.inv.dao.entity.Shift;
import com.bis.operox.inv.dao.entity.Store;
import com.bis.operox.inv.dao.entity.User;
import com.bis.operox.inv.dao.enums.Status;
import com.bis.operox.inv.service.EmailScenarioService;
import com.bis.operox.inv.service.UserService;
import com.bis.operox.util.CommonUtil;
import com.bis.operox.util.EmailScenerioUtil;
import com.bis.operox.util.OperoxRandomCodeHelper;
/**
 * This Service Class is used to get User Object 
 * 
 * @author: 
 * @date: 23-Sep-2016
 */

@Service
public class UserServiceImpl implements UserService {

	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private AddressDao addressDao;
	
	@Autowired
	private OperoxPasswordEncoder operoxPasswordEncoder;
	
	@Autowired
	EmailScenarioService emailScenarioService;
	
	@Autowired
	EmailScenerioUtil emailScenerioUtil;
	
	@Autowired
	CommonUtil commonUtil;
	
	@Value("${operoxUrl}")
    private String operoxUrl;
	
	@Override
	public User addUser(User user) {
		return userDao.addUser(user);
	}

	@Override
	public User getUserById(Long id) {
		return userDao.getUserById(id);
	}


	@Override
	public List<User> listUser() {
		return userDao.listUser();
	}

	@Override
	public User findByUserName(String username) {
		logger.debug("In UserService class...");
		return userDao.findByUserName(username);
	}

	@Override
	public List<User> getUserListByStoreId(Long companyId, String userCode,String role) throws Exception {
		return userDao.getUserListByStoreId(companyId, userCode,role);
	}

	@Override
	public User getUserByStoreIdAndRole(Long storeId, Long role) throws Exception {
		return userDao.getUserByStoreIdAndRole(storeId, role);
	}
	
	@Override
	public List<Long> getUserIdsByShiftId(Long shiftId) throws Exception {
		return userDao.getUserIdsByShiftId(shiftId);
	}
	

	@Override
	public Boolean validateEmployeeId(String employeeId) throws Exception {
		Boolean flag = userDao.validateEmployeeId(employeeId);
		return flag;
	}
	
	@Override
	public User findByEmail(String email) throws Exception {
		return userDao.findByEmail(email);
	}
	
	@Override
	public User saveUserProfile(JSONObject jsonObj, MultipartFile multiFile, User user) throws Exception {

		user.setFirstName(jsonObj.getString("firstName"));
		user.setLastName(jsonObj.getString("lastName"));
        if(null != multiFile.getContentType() && !multiFile.getContentType().equalsIgnoreCase("application/octet-stream")){
    	  try {
    	   		user.setFileContentType(multiFile.getContentType());
    	   		user.setUserImage(multiFile.getBytes());
    	   
	       }catch (Exception e) {
    	      	 e.printStackTrace();
			    }
        }
        userDao.addUser(user);
		
		return user; 
	
	}

	@Override
	public void storeUser(JSONObject jsonObj,User loginUser) throws Exception {
		String pattern = "MM/dd/yyyy";
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		User user = null;
		Address address = null;
		if(jsonObj.has("userId") && jsonObj.getString("userId") != null && !"".equals(jsonObj.getString("userId"))){
			user  = userDao.getUserById(Long.parseLong(jsonObj.getString("userId")));
			address = addressDao.getAddressById(user.getAddress().getId());
			user.setUpdatedDate(new Date());
			address.setUpdatedDate(new Date());
			user.setUpdatedBy(loginUser.getUserCode());
			address.setUpdatedBy(loginUser.getUserCode());
		}else{
			address = new Address();
			user = new User();
			
			Integer quarter = ((new Date().getMonth() / 3) + 1);
			if(quarter == 1){
				user.setLeaves(Constants.FIRST_QUARTER_LEAVES);
			}
			else if(quarter == 2){
				user.setLeaves(Constants.SECOND_QUARTER_LEAVES);
			}
			else if(quarter == 3){
				user.setLeaves(Constants.THIRD_QUARTER_LEAVES);
			}
			else if(quarter == 4){
				user.setLeaves(Constants.FOURTH_QUARTER_LEAVES);
			}
			
		}
		
		//Stroe Address
		if(jsonObj.has("country")){
			address.setCountry(jsonObj.getString("country"));
		}
		if(jsonObj.has("state")){
			address.setState(jsonObj.getString("state"));
		}
		if(jsonObj.has("city")){
			address.setCity(jsonObj.getString("city"));
		}
		if(jsonObj.has("address")){
			address.setAddress(jsonObj.getString("address"));
		}
		if(jsonObj.has("zipCode")){
			address.setZipcode(jsonObj.getString("zipCode"));
		}
		address.setStatus(1);
		address.setCreatedDate(new Date());
		address.setCreatedBy(loginUser.getUserCode());
		address=addressDao.addAddress(address);
		
		//Stroe User
		String password = null;
		if(jsonObj.has("employeeId")){
			user.setEmployeeId(jsonObj.getString("employeeId"));
		}
		if(jsonObj.has("firstName")){
			user.setFirstName(jsonObj.getString("firstName"));
		}
		if(jsonObj.has("lastName")){
			user.setLastName(jsonObj.getString("lastName"));
		}
		if(jsonObj.has("emial")){
			user.setEmail(jsonObj.getString("emial"));
			user.setUsername(jsonObj.getString("emial"));
		}
		if(jsonObj.has("gender")){
			user.setGender(jsonObj.getString("gender"));
		}
		
		if(jsonObj.has("dateofJoin")){
			if(jsonObj.getString("dateofJoin") !=null && !"".equalsIgnoreCase(jsonObj.getString("dateofJoin"))){
				Date dateofJoin = format.parse(jsonObj.getString("dateofJoin"));
				user.setDoj(dateofJoin);
			}
			
		}
		if(jsonObj.has("compensation")){
			user.setCompensation(jsonObj.getString("compensation"));
		}
		
		if(jsonObj.has("compensatationType")){
			user.setCompensatationType(jsonObj.getString("compensatationType"));
		}
		if(jsonObj.has("phoneNumber")){
			user.setPhone(jsonObj.getString("phoneNumber"));
		}
		if(jsonObj.has("shiftType")){
			Shift shift = new Shift();
			shift.setId(Long.valueOf(jsonObj.getString("shiftType")));
			user.setShift(shift);
		}
		
		if(jsonObj.has("storeType")){
			if(jsonObj.getString("storeType") != null && !"".equalsIgnoreCase(jsonObj.getString("storeType"))){
				Store store = new Store();
				store.setId(Long.valueOf(jsonObj.getString("storeType")));
				user.setStore(store);	
			}
			
		}
		if(jsonObj.has("department")){
			if(jsonObj.getString("department") != null && !"".equalsIgnoreCase(jsonObj.getString("department"))){
				Department department = new Department();
				department.setId(Long.valueOf(jsonObj.getString("department")));
				user.setDepartment(department);
			}
			
		}
		
		if(jsonObj.has("roleType")){
			String[] userRole = jsonObj.getString("roleType").split(",");
			Role role = new Role();
			role.setId(Long.valueOf(userRole[0]));
			user.setRole(role);
			user.setRoleName(userRole[1]);
		}
		if((jsonObj.has("isMailSent") && !"".equals(jsonObj.getString("isMailSent"))) && ("Y".equals(jsonObj.getString("isMailSent")))){
			user.setMailSent(Status.Y);
		}else{
			user.setMailSent(Status.N);
		}
		user.setUserState(Status.NOT_LOGGEDIN);
		user.setStatus(1);
		user.setAddress(address);
		user.setUserCode(OperoxRandomCodeHelper.generateRandomUserCode());
		user.setCreatedDate(new Date());
		user.setPayslipFromDate(new Date());
		user.setCreatedBy(loginUser.getUserCode());
		userDao.addUser(user);
		
		//send mail to user
				if((jsonObj.has("isMailSent") && !"".equals(jsonObj.getString("isMailSent"))) && ("Y".equals(jsonObj.getString("isMailSent")))){
					sendEmail(user,loginUser,password);
				}
	}
	
	
	
	@Override
	public void storeEcommUser(JSONObject jsonObj) throws Exception {
		User user = null;
		if(jsonObj.has("userId") && jsonObj.getString("userId") != null && !"".equals(jsonObj.getString("userId"))){
			user  = userDao.getUserById(Long.parseLong(jsonObj.getString("userId")));
		}else{
			user = new User();
		}
		String password = null;
		if(jsonObj.has("firstName")){
			user.setFirstName(jsonObj.getString("firstName"));
		}
		if(jsonObj.has("lastName")){
			user.setLastName(jsonObj.getString("lastName"));
		}
		if(jsonObj.has("emial")){
			user.setEmail(jsonObj.getString("emial"));
			user.setUsername(jsonObj.getString("emial"));
		}
		if(jsonObj.has("phoneNumber")){
			user.setPhone(jsonObj.getString("phoneNumber"));
		}
		user.setUserState(Status.ACTIVE);
		user.setStatus(1);
		if(jsonObj.has("password")){
			password = jsonObj.getString("password");
			user.setPassword(operoxPasswordEncoder.encode(password));
			Role role = new Role();
			role.setId(8l);
			user.setRole(role);
		}
		if(jsonObj.has("genderTyep")){
			user.setGender(jsonObj.getString("genderTyep"));
		}
		user.setUserCode(OperoxRandomCodeHelper.generateRandomUserCode());
		user.setCreatedDate(new Date());
		user.setPayslipFromDate(new Date());
		userDao.addUser(user);
	}
	
	

	@Override
	public User getUserByUserCode(String updatedBy) throws Exception {
		return userDao.getUserByUserCode(updatedBy);

	}

	@Override
	public User removeUserById(Long id) {
		User user = userDao.getUserById(id);
		user.setStatus(Constants.RECORD_IN_ACTIVE);
	     return userDao.addUser(user);
	}
	
	
	
	@Override
	public void sendEmail(User user, User loginUser, String password) throws Exception {
		 EmailMessage emailMessage = new EmailMessage();
		 emailMessage.setFirstName(user.getFirstName());
		 emailMessage.setLastName(user.getLastName());
		 emailMessage.setToEmail(user.getEmail());
		 emailMessage.setFromEmail("peopleaxisindia@gmail.com");
		 emailMessage.setFromName(loginUser.getFirstName()+" "+loginUser.getLastName());
		 
		//setting the class filed values
		 Map<String, String> globalVarsMap = new HashMap<String, String>();
		 globalVarsMap.put("firstName", emailMessage.getFirstName());
		 globalVarsMap.put("lastName",emailMessage.getLastName());
		 globalVarsMap.put("administratorFirstName", loginUser.getFirstName());
		 globalVarsMap.put("administratorLastName",loginUser.getLastName());
		 globalVarsMap.put("role",user.getRoleName());
		 globalVarsMap.put("loginURL","<a href='"+operoxUrl+"/"+"' style='text-decoration:none; '><div style=' width:220px; border-radius:30px; background-color:#214b90; color:#FFF; font-size:18px; font-weight:500; margin:10px auto 5px auto; padding:10px 0px; text-align:center; letter-spacing:2px;'>Login</div></a>");
		 globalVarsMap.put("userEmailId",user.getEmail());
		if(null != user && user.getUserState().equals(Status.NOT_LOGGEDIN)){
			 globalVarsMap.put("password",password);
		}else{
			 globalVarsMap.put("password","Note : You have already registered. Login with your registered password");
		 }
		 
		
		 //setting the Recipient information
		 ArrayList<EmailRecipient> emailRecipientList = new ArrayList<EmailRecipient>();
		 EmailRecipient emailRecipient = new EmailRecipient();
		 emailRecipient.setEmail(emailMessage.getToEmail());
		 emailRecipient.setName(emailMessage.getFirstName()+" "+emailMessage.getLastName());
		 emailRecipientList.add(emailRecipient);
		
		 //getting the current EmailScenario by EmailScenario unique name and entity type
		 EmailScenario  emailScenario = emailScenarioService.getEmailScenarioByUniqueName(Constants.INVITE_USER,Constants.ENTITY_TYPE_EMAIL);
		
		 //covert subject,body and signature
		 String subject = emailScenerioUtil.bulidSubject(emailScenario.getSubject(),globalVarsMap);
		 String body = emailScenerioUtil.buildTempBody(emailScenario.getBody(),globalVarsMap);
		 String signature = emailScenerioUtil.buildSignature(emailScenario.getSignature(),globalVarsMap);
		 String url = operoxUrl;
		 
		 //construct HTML template using required data
		 String emailTemplate = emailScenerioUtil.AssembleEmailMessage(emailScenario,emailMessage, subject, body, signature, url);
		 emailMessage.setSubject(subject);
		 emailMessage.setMessageBody(emailTemplate);
		 
		 //calling the sendEmail method of emailScenerioUtil
		 emailScenerioUtil.sendEmail(emailMessage,emailRecipientList,new ArrayList<EmailAttachment>());
		
	}

	@Override
	public List<User> getWorkOrderApprovers(Long storeId) throws Exception {
		return userDao.getWorkOrderApprovers(storeId);
	}

	@Override
	public List<User> getUsersListByStoreId(Long storeId) {
		return userDao.getUsersListByStoreId(storeId);
	}

	@Override
	public List<User> getUserByProductionUnit() {
		return userDao.getUserByProductionUnit();
	}

	@Override
	public void mailSend(User user, User foundUser, String verificationCode) throws Exception {
		 EmailMessage emailMessage = new EmailMessage();
		 emailMessage.setFirstName(user.getFirstName());
		 emailMessage.setLastName(user.getLastName());
		 emailMessage.setToEmail(user.getEmail());
		 emailMessage.setFromEmail("peopleaxisindia@gmail.com");
		 emailMessage.setFromName(foundUser.getFirstName()+" "+foundUser.getLastName());
		 
		//setting the class filed values
		 String operoxecommUrl = "http://localhost:8080/operoxEcomm";
		 Map<String, String> globalVarsMap = new HashMap<String, String>();
		 globalVarsMap.put("firstName", emailMessage.getFirstName());
		 globalVarsMap.put("lastName",emailMessage.getLastName());
		 globalVarsMap.put("resetPasswordURL","<a href='"+operoxecommUrl+"/public/forgotpasswordView"+"' style='text-decoration:none; '>Click Here</a>");
		 globalVarsMap.put("verificationCode",verificationCode);
		 //setting the Recipient information
		 ArrayList<EmailRecipient> emailRecipientList = new ArrayList<EmailRecipient>();
		 EmailRecipient emailRecipient = new EmailRecipient();
		 emailRecipient.setEmail(emailMessage.getToEmail());
		 emailRecipient.setName(emailMessage.getFirstName()+" "+emailMessage.getLastName());
		 emailRecipientList.add(emailRecipient);
		
		 //getting the current EmailScenario by EmailScenario unique name and entity type
		 EmailScenario  emailScenario = emailScenarioService.getEmailScenarioByUniqueName(Constants.FORGOT_PASSWORD,Constants.ENTITY_TYPE_EMAIL);
		
		 //covert subject,body and signature
		 String subject = emailScenerioUtil.bulidSubject(emailScenario.getSubject(),globalVarsMap);
		 String body = emailScenerioUtil.buildTempBody(emailScenario.getBody(),globalVarsMap);
		 String signature = emailScenerioUtil.buildSignature(emailScenario.getSignature(),globalVarsMap);
		 String url = operoxUrl;
		 
		 //construct HTML template using required data
		 String emailTemplate = emailScenerioUtil.AssembleEmailMessage(emailScenario,emailMessage, subject, body, signature, url);
		 emailMessage.setSubject(subject);
		 emailMessage.setMessageBody(emailTemplate);
		 
		 //calling the sendEmail method of emailScenerioUtil
		 emailScenerioUtil.sendEmail(emailMessage,emailRecipientList,new ArrayList<EmailAttachment>());
	}
	
	
	
	@Override
	public void resetPasswordMail(User user, String verificationCode) throws Exception {
		 EmailMessage emailMessage = new EmailMessage();
		 emailMessage.setFirstName(user.getFirstName());
		 emailMessage.setLastName(user.getLastName());
		 emailMessage.setToEmail(user.getEmail());
		 emailMessage.setFromEmail("info.operox@gmail.com");
		 emailMessage.setFromName("Operox Admin");		 
		//setting the class filed values
		 Map<String, String> globalVarsMap = new HashMap<String, String>();
		 globalVarsMap.put("firstName", emailMessage.getFirstName());
		 globalVarsMap.put("lastName",emailMessage.getLastName());
		 globalVarsMap.put("resetPasswordURL","<a href='"+operoxUrl+"/resetUserPassword"+"' style='text-decoration:none; '>Click Here</a>");
		 globalVarsMap.put("verificationCode",verificationCode);
		 //setting the Recipient information
		 ArrayList<EmailRecipient> emailRecipientList = new ArrayList<EmailRecipient>();
		 EmailRecipient emailRecipient = new EmailRecipient();
		 emailRecipient.setEmail(emailMessage.getToEmail());
		 emailRecipient.setName(emailMessage.getFirstName()+" "+emailMessage.getLastName());
		 emailRecipientList.add(emailRecipient);
		
		 //getting the current EmailScenario by EmailScenario unique name and entity type
		 EmailScenario  emailScenario = emailScenarioService.getEmailScenarioByUniqueName(Constants.FORGOT_PASSWORD,Constants.ENTITY_TYPE_EMAIL);
		
		 //covert subject,body and signature
		 String subject = emailScenerioUtil.bulidSubject(emailScenario.getSubject(),globalVarsMap);
		 String body = emailScenerioUtil.buildTempBody(emailScenario.getBody(),globalVarsMap);
		 String signature = emailScenerioUtil.buildSignature(emailScenario.getSignature(),globalVarsMap);
		 String url = operoxUrl;
		 
		 //construct HTML template using required data
		 String emailTemplate = emailScenerioUtil.AssembleEmailMessage(emailScenario,emailMessage, subject, body, signature, url);
		 emailMessage.setSubject(subject);
		 emailMessage.setMessageBody(emailTemplate);
		 
		 //calling the sendEmail method of emailScenerioUtil
		 emailScenerioUtil.sendEmail(emailMessage,emailRecipientList,new ArrayList<EmailAttachment>());
	}

	@Override
	public User getUserByVerificationCode(JSONObject jsonObj, String verificatinCode) throws Exception {
		
		User user = null;
		user = userDao.getUserByVerificationCode(verificatinCode);
		if(user != null){
			if(jsonObj.has("password")){
				user.setPassword(operoxPasswordEncoder.encode(jsonObj.getString("password")));
				}
		}
		return userDao.addUser(user);
		//return userDao.getUserByVerification(verificatinCode);
	}


}
