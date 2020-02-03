package com.bis.operox.payroll.rest;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.bis.operox.constants.Constants;
import com.bis.operox.inv.dao.entity.Customer;
import com.bis.operox.inv.dao.entity.Tax;
import com.bis.operox.inv.dao.entity.User;
import com.bis.operox.inv.rest.CustomerRestController;
import com.bis.operox.inv.service.TaxService;
import com.bis.operox.inv.service.UserService;
import com.bis.operox.payroll.entity.Holiday;
import com.bis.operox.payroll.entity.Leaves;
import com.bis.operox.payroll.entity.PaySal;
import com.bis.operox.payroll.entity.Payroll;
import com.bis.operox.payroll.service.HolidayService;
import com.bis.operox.payroll.service.LeavesService;
import com.bis.operox.payroll.service.PayrollService;
import com.bis.operox.payroll.util.PayrollHelper;
import com.bis.operox.util.CommonUtil;
import com.bis.operox.util.DateUtil;
import com.bis.operox.util.OperoxSessionManager;

@RestController
@PropertySource("${propertyLocation:operoxapp.properties}")
public class PayrollRestController {
	
	private static final Logger logger = LoggerFactory.getLogger(CustomerRestController.class);
	
	@Value("${operoxUrl}")
	private String operoxUrl;
	
	
	@Autowired
	LeavesService leaveservice;
	
	@Autowired
	private PayrollService payrollService;
	
	@Autowired
	private CommonUtil commonUtil;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private HolidayService holidayService;
	
	@Autowired
	private  TaxService taxService;
	
	@Autowired
	private PayrollHelper payrollHelper;
	
	@RequestMapping(value = "/saveHoliday",  method = RequestMethod.POST)
	 @ResponseBody
	    public  String addHoliday(@RequestParam(value="json", required=false) String json,HttpServletRequest request) throws Exception {
		 
		    Holiday holiday = null;
		 	String json1 = "{" + json + "}";
			JSONObject jsonObj = new JSONObject(json1);
			User user = (User) OperoxSessionManager.getSessionAttribute(request, "user");
			holiday = holidayService.addHoliday(jsonObj,user);
			if(holiday != null){
				logger.info("customer added successfully.!!");
				return "success";
			}
			return "failed";
	    }
	
	 @RequestMapping(value = "/getHolidaysList",  method = RequestMethod.GET)
	 @ResponseBody
	    public  String getHolidaysList(HttpServletRequest request) throws Exception {
		 User user = (User) OperoxSessionManager.getSessionAttribute(request, "user");
		 List<Holiday> holidayList = holidayService.listHolidays(user.getId()); 
		 for(Holiday holiday : holidayList){
			 if(holiday.getHolidayDate() !=null){
					holiday.setHolidayDateStr(commonUtil.getDateString(holiday.getHolidayDate()));
					User user2 = userService.getUserByUserCode(holiday.getUpdatedBy());
					holiday.setUserName(user2.getFirstName()+user2.getLastName());
			 }
		 }
		 logger.info("the total nimber of customers"+holidayList.size());
		 return commonUtil.toJSON(holidayList);
		 
	    }
	 
	 @RequestMapping(value = "/deleteHoliday",method = RequestMethod.GET)
	 @ResponseBody
		public String deleteHoliday(@RequestParam(value="holidayId", required=false) String holidayId,HttpServletRequest request) throws Exception{
		 User user = (User) OperoxSessionManager.getSessionAttribute(request, "user");
		 Holiday holiday = holidayService.getHolidayById(Long.parseLong(holidayId));
			if(holiday != null){
				holiday.setStatus(0);
				holiday.setUpdatedBy(user.getFirstName());
				holiday.setUpdatedDate(new Date());
				holidayService.deleteHoliday(holiday);
			}
			return "success";
		}
	 
	 @RequestMapping(value = "/addLeaves",  method = RequestMethod.POST)
		@ResponseBody
		   public  String addLeave(@RequestParam(value="json", required=false) String json,HttpServletRequest request) throws Exception {
			 
			   Leaves newLeave = null;
				String json1 = "{" + json + "}";
				JSONObject jsonObj = new JSONObject(json1);
				User user = (User) OperoxSessionManager.getSessionAttribute(request, "user");
					leaveservice.addLeaves(jsonObj, user);
					return "success";
		   }
		 
		@RequestMapping(value = "/getLeavesList",  method = RequestMethod.GET)
		@ResponseBody
		   public  String getLeavesList(HttpServletRequest request) throws Exception {
			User user = (User) OperoxSessionManager.getSessionAttribute(request, "user");
			List<Leaves> LeavesList = leaveservice.listLeaves(); 
			for(Leaves leaves : LeavesList){
				User user2 = userService.getUserByUserCode(leaves.getUpdatedBy());
				leaves.setUserName(user2.getFirstName()+user2.getLastName());
			}
			logger.info("total leaves"+LeavesList.size());
			return commonUtil.toJSON(LeavesList);
			 
		   }
		 
		@RequestMapping(value = "/deleteLeaves",method = RequestMethod.GET)
		@ResponseBody
			public String deleteLeaves(@RequestParam(value="leaveId", required=false) Long leaveId,HttpServletRequest request){
			User user = (User) OperoxSessionManager.getSessionAttribute(request, "user");
				Leaves leaves = leaveservice.getLeavesById(leaveId);
				if(leaves != null){
					leaves.setStatus(0);
					leaves.setUpdatedBy(user.getFirstName());
					leaves.setUpdatedDate(new Date());
					leaveservice.deleteLeaveById(leaves);
				}
				return "success";
			}
		
		 @RequestMapping(value = "/getPayRollEmployees",  method = RequestMethod.GET)
		 @ResponseBody
		 public  String getPayRollEmployees(@RequestParam(value="payRollType", required=false) String payRollType,@RequestParam(value="fromDate", required=false) String payrollFromDate,@RequestParam(value="toDate", required=false) String payrollToDate,HttpServletRequest request) throws Exception {
		 logger.info("Payroll Type is : "+payRollType);
		 Date toDate = null;
		 Date fromDate = null;
		 if(payrollFromDate != null && !"".equalsIgnoreCase(payrollFromDate)){
			 fromDate = commonUtil.getDateFormat(payrollFromDate);
		 }
		 if(payrollToDate != null && !"".equalsIgnoreCase(payrollToDate)){
			 toDate = commonUtil.getDateFormat(payrollToDate);
		 }
		 List<User> emplyeesPayrollList = payrollService.gePayrollEmployees(payRollType,fromDate,toDate);
		 Tax tax = payrollService.getPayrollTax(Constants.TAX_VALUE);
		      for(User usr : emplyeesPayrollList) {
		    	  usr.setTaxValue(tax.getTaxValue());
		      }
		 return commonUtil.toJSON(emplyeesPayrollList);
			 
		    }
		 
//		 calculateNetPay
		 
		 @RequestMapping(value = "/calculateNetPay",  method = RequestMethod.GET)
		 @ResponseBody
		 public  String calculateNetPay(@RequestParam(value="attendance", required=false) String attendance,
				                       @RequestParam(value="totalLeaves", required=false) Integer totalLeaves,
				                       @RequestParam(value="userId", required=false) Long userId,
				                       @RequestParam(value="payType", required=false) String payType,
				                       @RequestParam(value="taxValue", required=false) Integer taxValue,
				                       HttpServletRequest request) throws Exception {
		    logger.info("tatol number of attendance: "+attendance);
		    logger.info("total number of leaves : "+totalLeaves);
		    User seessionUser = (User) OperoxSessionManager.getSessionAttribute(request, "user");
		     User user = userService.getUserById(userId);
		     PaySal paySal = new PaySal();
		     float netPay = 0;
		            
		         Tax tax = taxService.getTaxByUserId(userId);
		         if(tax == null){
		        	tax = new Tax();
		         }
		    	 tax.setUser(user);
		    	 tax.setTaxValue(taxValue.toString());
		    	 tax.setStore(user.getStore());
		    	 tax.setStatus(1);
		    	 tax.setCreatedDate(new Date());
		    	 tax.setCreatedBy(user.getUserCode());
		    	 tax.setUpdatedDate(new Date());
		    	 tax.setUpdatedBy(user.getUserCode());
		    	 taxService.addTax(tax);
		    	
		     if(attendance !=null && !"".equalsIgnoreCase(attendance)){
		    	 String basicSalary = user.getCompensation();
		    	 float salary = Float.parseFloat(basicSalary);
		    	 float taxDeductionAmount = (float)(salary / 100)*taxValue;
		    	 logger.info("tax deduction : "+taxDeductionAmount);
		    	 int numberOfleaves = 0;
		    	 float lop = 0;
		    	 float perDaySal =0;
		    	  if(totalLeaves != null){
		    		  int actualLeaves = user.getLeaves();
		    		  if(totalLeaves > actualLeaves){
		    			  numberOfleaves = totalLeaves-actualLeaves;
		    			  logger.info("The total number of leaves"+numberOfleaves);
		    			 if("weekly".equalsIgnoreCase(payType)){
		    				  perDaySal = salary/5;
		    				  logger.info("per day salary for weekly "+perDaySal);
		    			 }else if("bi-weekly".equalsIgnoreCase(payType)){
		    				  perDaySal = salary/10;
		    				  logger.info("per day salary for bi-weekly "+perDaySal);
		    			 }else{
		    				  perDaySal = salary/30;
		    				  logger.info("per day salary for monthly "+perDaySal);
		    			 }
		    			 lop =  perDaySal * numberOfleaves;
		    			 logger.info("loss of pay is "+lop);
		    	    	  float lossSum =  lop;
		    	    	  float losspay =  lop+taxDeductionAmount;
		    	    	  netPay = salary - losspay;
		    	    	  paySal.setNetPay(String.valueOf(netPay));
		    	    	  paySal.setLop(String.valueOf(lossSum));
		    	    	  logger.info("The Net Pay is "+netPay);
		    		  }else{
		    			  netPay = salary - taxDeductionAmount;
		    			  paySal.setNetPay(String.valueOf(netPay));
		    			  int decreaseLeaves = actualLeaves - totalLeaves;
		    			  user.setLeaves(decreaseLeaves);
		    			  userService.addUser(user);
		    		  }
		    	  }else{
		    		  netPay = salary - taxDeductionAmount;
		    		  paySal.setNetPay(String.valueOf(netPay));
		    	  }
		     }
			 return commonUtil.toJSON(paySal);
		    }
	 
		 @RequestMapping(value = "/runPayRoll",  method = RequestMethod.GET)
		 @ResponseBody
		 public  String runPayRoll(@RequestParam(value="jsonData", required=false) String jsonData,
				 @RequestParam(value="fromDate", required=false) String fromDate,
				 @RequestParam(value="toDate", required=false) String toDate,
				 HttpServletRequest request) throws Exception {
			 User user = (User) OperoxSessionManager.getSessionAttribute(request, "user");
			    String json = "{" + jsonData + "}";
				JSONObject jsonObj = new JSONObject(json);
				jsonObj.put("fromDate", fromDate);
				jsonObj.put("toDate", toDate);
				
				 payrollService.savePayrollDetails(jsonObj,user);
				
			
				
			return "success";
		    }
		 
		 
		 @RequestMapping(value = "/getpayrollList",  method = RequestMethod.GET)
		 @ResponseBody
		    public  String getpayrollList(@RequestParam(value="fromDate", required=false) String payrollFromDate,@RequestParam(value="toDate", required=false) String payrollToDate,@RequestParam(value="payRollType", required=false) String payRollType, HttpServletRequest request) throws Exception {
			 User user = (User) OperoxSessionManager.getSessionAttribute(request, "user");
			 HashMap<String,Date> dateList = (HashMap<String, Date>) payrollHelper.getPreviousMonthStartDateAndEndDate();
			 Date startDate = dateList.get("startDate");
			 Date endDate = dateList.get("endDate");
			 Date toDate = null;
			 Date fromDate = null;
			 if(payrollFromDate != null && !"".equalsIgnoreCase(payrollFromDate)){
				 fromDate = commonUtil.getDateFormat(payrollFromDate);
			 }
			 if(payrollToDate != null && !"".equalsIgnoreCase(payrollToDate)){
				 toDate = commonUtil.getDateFormat(payrollToDate);
			 }
			 List<Payroll> payrollList = payrollService.listPayrolls(user,fromDate,toDate,startDate,endDate,payRollType);
			 for(Payroll pay: payrollList){
				 pay.setCompensationType(pay.getUser().getCompensatationType());
			 }
			 return commonUtil.toJSON(payrollList);
		    }
				
}
