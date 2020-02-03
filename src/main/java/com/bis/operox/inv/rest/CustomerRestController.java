package com.bis.operox.inv.rest;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bis.operox.inv.dao.entity.Brand;
import com.bis.operox.inv.dao.entity.Customer;
import com.bis.operox.inv.dao.entity.User;
import com.bis.operox.inv.service.CustomerService;
import com.bis.operox.inv.service.UserService;
import com.bis.operox.util.CommonUtil;
import com.bis.operox.util.OperoxSessionManager;

@RestController
@PropertySource("${propertyLocation:operoxapp.properties}")
public class CustomerRestController {
	
			private static final Logger logger = LoggerFactory.getLogger(CustomerRestController.class);
				
			@Value("${operoxUrl}")
			private String operoxUrl;
				
			@Autowired
			CommonUtil commonUtil;

			@Autowired
			private CustomerService customerService;
			
			@Autowired
			UserService userService;
	
	 @RequestMapping(value = "/saveCustomer",  method = RequestMethod.POST)
	 @ResponseBody
	    public  String addCustomer(@RequestParam(value="json", required=false) String json,HttpServletRequest request) throws Exception {
		 
		    Customer newCustomer = null;
		 	String json1 = "{" + json + "}";
			JSONObject jsonObj = new JSONObject(json1);
			User user = (User) OperoxSessionManager.getSessionAttribute(request, "user");
		    newCustomer = customerService.addCustomer(jsonObj, user);
			if(newCustomer != null){
				logger.info("customer added successfully.!!");
				return "success";
			}
			return "failed";
	    }
	 
	 @RequestMapping(value = "/getCustomersList",  method = RequestMethod.GET)
	 @ResponseBody
	    public  String getCustomersList(HttpServletRequest request) throws Exception {
		 User user = (User) OperoxSessionManager.getSessionAttribute(request, "user");
		 List<Customer> customersList = customerService.listCustomer(user.getStore().getCompany().getId());
		 for (Customer customer : customersList) {
				User user2 = userService.getUserByUserCode(customer.getUpdatedBy());
				customer.setUserName(user2.getFirstName()+user2.getLastName());
				}
		 logger.info("the total nimber of customers"+customersList.size());
		 return commonUtil.toJSON(customersList);
		 
	    }
	 
	 @RequestMapping(value = "/deleteCustomer",method = RequestMethod.GET)
	 @ResponseBody
		public String deleteCustomer(@RequestParam(value="customerId", required=false) String customerId,HttpServletRequest request){
		 User user = (User) OperoxSessionManager.getSessionAttribute(request, "user");
			Customer customer = customerService.getCustomerById(Long.parseLong(customerId));
			if(customer != null){
				customer.setStatus(0);
				customer.setUpdatedBy(user.getFirstName());
				customer.setUpdatedDate(new Date());
				customerService.deleteCustomerById(customer);
			}
			return "success";
		}
	 
	 @RequestMapping(value = "/validatePhone",  method = RequestMethod.GET)
		@ResponseBody Boolean validatePhone(@RequestParam(value = "phone", required = false) String phone) throws Exception {
		logger.info("Entered phone number is "+phone);
		return customerService.getCustomerByPhone(phone);
		}
}
