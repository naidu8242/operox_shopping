package com.bis.operox.inv.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bis.operox.constants.Constants;
import com.bis.operox.inv.dao.entity.Customer;
import com.bis.operox.inv.dao.entity.Department;
import com.bis.operox.inv.dao.entity.Store;
import com.bis.operox.inv.dao.entity.Ticket;
import com.bis.operox.inv.dao.entity.User;
import com.bis.operox.inv.dao.impl.CustomerDaoImpl;
import com.bis.operox.inv.service.CustomerService;
import com.bis.operox.inv.service.DepartmentService;
import com.bis.operox.inv.service.StoreService;
import com.bis.operox.inv.service.TicketService;
import com.bis.operox.inv.service.UserService;
import com.bis.operox.util.CommonUtil;
import com.bis.operox.util.OperoxSessionManager;

@Controller
@PropertySource("${propertyLocation:operoxapp.properties}")
public class CustomersController {
	
	private static final Logger logger = LoggerFactory.getLogger(CustomersController.class);
	@Value("${operoxUrl}")
	private String operoxUrl;
	
	@Autowired
	 DepartmentService departmentService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	StoreService storeService;
	
	@Autowired
	CommonUtil commonUtil;
	
	@Autowired
	CustomerService customerService;
	
	@Autowired
	TicketService ticketService;
	
	
	@RequestMapping(value = "/customers")
	public ModelAndView customersHome(HttpServletRequest request){
		commonUtil.highLightMenu(Constants.Customers, request);
		commonUtil.highLightSubMenu(Constants.Customer, request);
		ModelAndView model = new ModelAndView();
		model.addObject("operoxUrl",operoxUrl);
		model.setViewName("customers/customersHome");
		return model;
	}
	
	@RequestMapping(value = "/addCustomer")
	public ModelAndView addCustomer(HttpServletRequest request){
		commonUtil.highLightMenu(Constants.Customers, request);
		commonUtil.highLightSubMenu(Constants.Customer, request);
		ModelAndView model = new ModelAndView();
		model.addObject("operoxUrl",operoxUrl);
		model.setViewName("customers/addCustomer");
		return model;
	}

	@RequestMapping(value = "/editCustomer/{customerId}")
	public ModelAndView editCustomer(@PathVariable Long customerId,HttpServletRequest request){
		commonUtil.highLightMenu(Constants.Customers, request);
		ModelAndView model = new ModelAndView();
		Customer customer = customerService.getCustomerById(customerId);
		if(customer.getDob()!=null){
			customer.setDateStr(commonUtil.getDateString(customer.getDob()));
		}
		model.addObject("customer",customer);
		model.addObject("operoxUrl",operoxUrl);
		model.setViewName("customers/addCustomer");
		return model;
	}
	
	
	@RequestMapping(value = "/viewCustomer/{customerId}")
	public ModelAndView viewCustomer(@PathVariable Long customerId,HttpServletRequest request){
		commonUtil.highLightMenu(Constants.Customers, request);
		ModelAndView model = new ModelAndView();
		Customer customer = customerService.getCustomerById(customerId);
		if(customer.getDob()!=null){
			customer.setDateStr(commonUtil.getDateString(customer.getDob()));
		}
		if(customer.getUpdatedDate()!=null){
			customer.setUpdatedDateStr(commonUtil.getDateString(customer.getUpdatedDate()));	
		}
		model.addObject("customer",customer);
		model.addObject("operoxUrl",operoxUrl);
		model.setViewName("customers/viewCustomer");
		return model;
	}
	
	@RequestMapping(value = "/ticket")
	public ModelAndView ticketHome(HttpServletRequest request){
		commonUtil.highLightMenu(Constants.Customers, request);
		commonUtil.highLightSubMenu(Constants.Ticket, request);
		
		ModelAndView model = new ModelAndView();
		model.addObject("operoxUrl",operoxUrl);
		model.setViewName("ticket/ticketHome");
		return model;

	}
	
	@RequestMapping(value = "/addTicket")
	public ModelAndView addTicket(HttpServletRequest request) throws Exception{
		commonUtil.highLightMenu(Constants.Customers, request);
		commonUtil.highLightSubMenu(Constants.Ticket, request);
		ModelAndView model = new ModelAndView();
		 List<Department>   departmentList = departmentService.listDepartments();
		 List<User> userList = userService.listUser();
		 User user = (User) OperoxSessionManager.getSessionAttribute(request, "user");
		 List<Customer> customersList = customerService.listCustomer(user.getStore().getCompany().getId()); 
		 List<Store> storeList = storeService.listStores();
		 model.addObject("departmentList",departmentList);
		 model.addObject("userList",userList);
		 model.addObject("storeList",storeList);
		 model.addObject("customersList",customersList); 
		model.addObject("operoxUrl",operoxUrl);
		model.setViewName("ticket/addTicket");
		return model;
	}
	

	@RequestMapping(value = "/editTicket/{ticketId}")
	public ModelAndView editTicket(@PathVariable Long ticketId,HttpServletRequest request) throws Exception{
		commonUtil.highLightMenu(Constants.Customers, request);
		ModelAndView model = new ModelAndView();
		Ticket ticket = ticketService.getTicketById(ticketId);
		if(ticket.getIssueDate()!=null){
			ticket.setIssueDateStr(commonUtil.getDateString(ticket.getIssueDate()));
		}
		if(ticket.getDueDate()!=null){
			ticket.setDueDateStr(commonUtil.getDateString(ticket.getDueDate()));
		}
		List<Department>   departmentList = departmentService.listDepartments();
		 List<User> userList = userService.listUser();
		 User user = (User) OperoxSessionManager.getSessionAttribute(request, "user");
		 List<Customer> customersList = customerService.listCustomer(user.getStore().getCompany().getId()); 
		 List<Store> storeList = storeService.listStores();
		 model.addObject("departmentList",departmentList);
		 model.addObject("userList",userList);
		 model.addObject("storeList",storeList);
		 model.addObject("customersList",customersList); 
		model.addObject("ticket",ticket);
		model.addObject("operoxUrl",operoxUrl);
		model.setViewName("ticket/addTicket");
		return model;
	}
	
	@RequestMapping(value = "/ticketView/{ticketId}")
    public ModelAndView ticketView(@PathVariable Long ticketId,HttpServletRequest request) throws Exception{
        commonUtil.highLightMenu(Constants.Customers, request);
        ModelAndView model = new ModelAndView();
        Ticket ticket = ticketService.getTicketById(ticketId);
        model.addObject("ticket",ticket);
         List<Department>   departmentList = departmentService.listDepartments();
         List<User> userList = userService.listUser();
        User user = (User) OperoxSessionManager.getSessionAttribute(request, "user");
        if(user != null){
            List<Customer> customersList = customerService.listCustomer(user.getStore().getCompany().getId());
             model.addObject("customersList",customersList);
        }
         
         List<Store> storeList = storeService.listStores();
         model.addObject("departmentList",departmentList);
         model.addObject("userList",userList);
         model.addObject("storeList",storeList);
         
        model.addObject("operoxUrl",operoxUrl);
        model.setViewName("ticket/ticketView");
        return model;
    }
}
