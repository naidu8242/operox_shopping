package com.bis.operox.inv.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bis.operox.constants.Constants;
import com.bis.operox.inv.dao.TicketDao;
import com.bis.operox.inv.dao.entity.Category;
import com.bis.operox.inv.dao.entity.Customer;
import com.bis.operox.inv.dao.entity.Department;

import com.bis.operox.inv.dao.entity.EmailAttachment;
import com.bis.operox.inv.dao.entity.EmailMessage;
import com.bis.operox.inv.dao.entity.EmailRecipient;

import com.bis.operox.inv.dao.entity.EmailScenario;
import com.bis.operox.inv.dao.entity.Store;
import com.bis.operox.inv.dao.entity.Ticket;
import com.bis.operox.inv.dao.entity.User;

import com.bis.operox.inv.dao.enums.Status;
import com.bis.operox.inv.service.CustomerService;

import com.bis.operox.inv.service.EmailScenarioService;
import com.bis.operox.inv.service.TicketService;
import com.bis.operox.inv.service.UserService;
import com.bis.operox.util.EmailScenerioUtil;

import com.bis.operox.constants.Constants;


@Service
public class TicketServiceImpl implements TicketService{
	
	@Autowired
	private TicketDao ticketDao;
	
	@Value("${operoxUrl}")
    private String operoxUrl;
	
	@Autowired
	EmailScenarioService emailScenarioService;
	
	@Autowired
	CustomerService customerService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	EmailScenerioUtil emailScenerioUtil;
	
	@Override
	public Ticket addTicket(Ticket ticket) {
		return ticketDao.addTicket(ticket);
	}

	@Override
	public Ticket getTicketById(Long id) {
		return ticketDao.getTicketById(id);
	}

	@Override
	public List<Ticket> ticketsList() {
		return this.ticketDao.ticketsList();
	}

	@Override
	public void addTicketDetails(JSONObject jsonObj,User user,MultipartFile multiFile) throws Exception {
		Ticket ticket = null;
		String pattern = "MM/dd/yyyy";
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		if(jsonObj.has("id") && jsonObj.getString("id") != null && !"".equals(jsonObj.getString("id"))){
			ticket = ticketDao.getTicketById(Long.parseLong(jsonObj.getString("id")));
		}else{
			ticket = new Ticket();
		}
		if(jsonObj.has("issueDate") && jsonObj.getString("issueDate") != null && !"".equals(jsonObj.getString("issueDate"))){
			Date issueDate = format.parse(jsonObj.getString("issueDate"));
			ticket.setIssueDate(issueDate);
		}
		if(jsonObj.has("dueDate") && jsonObj.getString("dueDate") != null && !"".equals(jsonObj.getString("dueDate"))){
			Date dueDate = format.parse(jsonObj.getString("dueDate"));
			ticket.setDueDate(dueDate);
		}
		if(jsonObj.has("subject") && jsonObj.getString("subject") != null && !"".equals(jsonObj.getString("subject"))){
			ticket.setSubject(jsonObj.getString("subject"));
		}
		if(jsonObj.has("sourceType") && jsonObj.getString("sourceType") != null && !"".equals(jsonObj.getString("sourceType"))){
			ticket.setSourceType(jsonObj.getString("sourceType"));
		}
		if(jsonObj.has("store") && jsonObj.getString("store") != null && !"".equals(jsonObj.getString("store"))){
			Store store = new Store();
			store.setId(Long.parseLong(jsonObj.getString("store")));
			ticket.setStore(store);
		}
		if(jsonObj.has("department") && jsonObj.getString("department") != null && !"".equals(jsonObj.getString("department"))){
			Department department = new Department();
			department.setId(Long.parseLong(jsonObj.getString("department")));
			ticket.setDepartment(department);
		}
		if(jsonObj.has("priority") && jsonObj.getString("priority") != null && !"".equals(jsonObj.getString("priority"))){
			ticket.setPriority(jsonObj.getString("priority"));
		}
		else{
			ticket.setPriority("");
		}
		if(jsonObj.has("customer") && jsonObj.getString("customer") != null && !"".equals(jsonObj.getString("customer"))){
			Customer customer = new Customer();
			customer.setId(Long.parseLong(jsonObj.getString("customer")));
			ticket.setCustomer(customer);
		}
		
		if(jsonObj.has("createUser") && jsonObj.getString("createUser") != null && !"".equals(jsonObj.getString("createUser"))){
			User createUser = new User();
			createUser.setId(Long.parseLong(jsonObj.getString("createUser")));
			ticket.setTicketCreatedBy(createUser);
		}
		
		if(jsonObj.has("assignUser") && jsonObj.getString("assignUser") != null && !"".equals(jsonObj.getString("assignUser"))){
			User assignedUser = new User();
			assignedUser.setId(Long.parseLong(jsonObj.getString("assignUser")));
			ticket.setUser(assignedUser);
		}
		if(jsonObj.has("message") && jsonObj.getString("message") != null && !"".equals(jsonObj.getString("message"))){
			ticket.setMessage(jsonObj.getString("message"));
		}
		else{
			ticket.setMessage("");
		}
		if(jsonObj.has("ticketStatus") && jsonObj.getString("ticketStatus") != null && !"".equals(jsonObj.getString("ticketStatus"))){
			ticket.setTicketStatus(jsonObj.getString("ticketStatus"));
		}
		else{
			ticket.setTicketStatus("");
		}
		if(multiFile != null){
			ticket.setDocFile(multiFile.getBytes());
			ticket.setFileName(multiFile.getOriginalFilename());
			ticket.setFileContentType(multiFile.getContentType());
		 }
		String actionLink = null; 
		ticket.setStatus(1);
		ticket.setCreatedDate(new Date());
		ticket.setUpdatedDate(new Date());

		ticket.setCreatedBy(user.getUserCode());
		ticket.setUpdatedBy(user.getUserCode());
		//ticket.setCreatedBy(user.getUserCode());
		//ticket.setUpdatedBy(user.getUserCode());
		ticketDao.addTicket(ticket);
		Map<String, String> globalVarsMap = new HashMap<String, String>();
	
		Ticket currentTicket = ticketDao.getTicketById(ticket.getId());
		actionLink=operoxUrl+"/editTicket/"+ticket.getId();
		globalVarsMap.put("ticketId", currentTicket.getId().toString());
		EmailScenario  notificationScenario = emailScenarioService.getEmailScenarioByUniqueName(Constants.NOTIFICATION_TO_ASSIGNED_USER,Constants.ENTITY_TYPE_NOTIFICATION);
		String notificationBody = emailScenerioUtil.buildBody(notificationScenario.getBody(),globalVarsMap);
		notificationScenario.setBody(notificationBody);
		User assignUser = userService.getUserById(Long.parseLong(jsonObj.getString("assignUser")));
		emailScenerioUtil.sendNotification(actionLink, user, assignUser, notificationScenario);
		
		if(jsonObj.has("customer") && jsonObj.getString("customer") != null && !"".equals(jsonObj.getString("customer"))){
			Customer customer = customerService.getCustomerById(Long.parseLong(jsonObj.getString("customer")));
			if(customer.getEmail() != null){
				sendEmailCustomer(ticket,user,customer);
			}
		}
		
		if(jsonObj.has("assignUser") && jsonObj.getString("assignUser") != null && !"".equals(jsonObj.getString("assignUser"))){
			User assignedUser = userService.getUserById(Long.parseLong(jsonObj.getString("assignUser")));
			if(assignedUser.getEmail() != null){
				sendEmailAssignedToUser(ticket,user,assignedUser);	
			}
			
		}
		
		
	}

	
	private void sendEmailCustomer(Ticket ticket, User user,Customer customer) throws Exception {
		
		 EmailMessage emailMessage = new EmailMessage();
		 emailMessage.setFirstName(customer.getCustomerName());
		 emailMessage.setToEmail(customer.getEmail());
		 emailMessage.setFromEmail("peopleaxisindia@gmail.com");
		 emailMessage.setFromName(user.getFirstName()+" "+user.getLastName());
		 
		//setting the class filed values
		 Map<String, String> globalVarsMap = new HashMap<String, String>();
		 globalVarsMap.put("customerName", emailMessage.getFirstName());
		 globalVarsMap.put("administratorFirstName", user.getFirstName());
		 globalVarsMap.put("administratorLastName",user.getLastName());
		 
		 globalVarsMap.put("ticketNo",ticket.getId().toString());
		 globalVarsMap.put("ticketStatus",ticket.getTicketStatus());
		 globalVarsMap.put("ticketSubject",ticket.getSubject());
		 if(ticket.getIssueDate() != null){
			 globalVarsMap.put("ticketIssueDate",Constants.DF_DMY.format(ticket.getIssueDate()));	 
		 }
		 if(ticket.getDueDate() != null){
			 globalVarsMap.put("ticketDueDate",Constants.DF_DMY.format(ticket.getDueDate()));	 
		 }
		 globalVarsMap.put("ticketDescription",ticket.getMessage());
		 globalVarsMap.put("ticketSourceType",ticket.getSourceType());
		 
//		 globalVarsMap.put("loginURL","<a href='"+operoxUrl+"/"+"' style='text-decoration:none; '><div style=' width:220px; border-radius:30px; background-color:#214b90; color:#FFF; font-size:18px; font-weight:500; margin:10px auto 5px auto; padding:10px 0px; text-align:center; letter-spacing:2px;'>Login</div></a>");
		 
		
		 //setting the Recipient information
		 ArrayList<EmailRecipient> emailRecipientList = new ArrayList<EmailRecipient>();
		 EmailRecipient emailRecipient = new EmailRecipient();
		 emailRecipient.setEmail(emailMessage.getToEmail());
		 emailRecipient.setName(emailMessage.getFirstName());
		 emailRecipientList.add(emailRecipient);
		
		 //getting the current EmailScenario by EmailScenario unique name and entity type
		 EmailScenario  emailScenario = emailScenarioService.getEmailScenarioByUniqueName(Constants.TICKET_BY_CUSTOMER,Constants.ENTITY_TYPE_EMAIL);
		
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
	
	
	private void sendEmailAssignedToUser(Ticket ticket, User user,User assignUser) throws Exception {
		 EmailMessage emailMessage = new EmailMessage();
		 emailMessage.setFirstName(assignUser.getFirstName());
		 emailMessage.setLastName(assignUser.getLastName());
		 emailMessage.setToEmail(assignUser.getEmail());
		 emailMessage.setFromEmail("peopleaxisindia@gmail.com");
		 emailMessage.setFromName(user.getFirstName()+" "+user.getLastName());
		 
		//setting the class filed values
		 Map<String, String> globalVarsMap = new HashMap<String, String>();
		 globalVarsMap.put("firstName", emailMessage.getFirstName());
		 globalVarsMap.put("lastName", emailMessage.getLastName());
		 globalVarsMap.put("administratorFirstName", user.getFirstName());
		 globalVarsMap.put("administratorLastName",user.getLastName());
		 
		 globalVarsMap.put("ticketNo",ticket.getId().toString());
		 globalVarsMap.put("ticketStatus",ticket.getTicketStatus());
		 globalVarsMap.put("ticketSubject",ticket.getSubject());
		 
    	 globalVarsMap.put("loginURL","<a href='"+operoxUrl+"/"+"' style='text-decoration:none; '><div style=' width:220px; border-radius:30px; background-color:#214b90; color:#FFF; font-size:18px; font-weight:500; margin:10px auto 5px auto; padding:10px 0px; text-align:center; letter-spacing:2px;'>Login</div></a>");
		 
		
		 //setting the Recipient information
		 ArrayList<EmailRecipient> emailRecipientList = new ArrayList<EmailRecipient>();
		 EmailRecipient emailRecipient = new EmailRecipient();
		 emailRecipient.setEmail(emailMessage.getToEmail());
		 emailRecipient.setName(emailMessage.getFirstName());
		 emailRecipientList.add(emailRecipient);
		
		 //getting the current EmailScenario by EmailScenario unique name and entity type
		 EmailScenario  emailScenario = emailScenarioService.getEmailScenarioByUniqueName(Constants.TICKET_ASSIGNED_USER,Constants.ENTITY_TYPE_EMAIL);
		
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

}
