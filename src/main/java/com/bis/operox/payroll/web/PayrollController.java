package com.bis.operox.payroll.web;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.bis.operox.inv.dao.entity.User;
import com.bis.operox.inv.service.UserService;
import com.bis.operox.payroll.entity.Holiday;
import com.bis.operox.payroll.entity.Leaves;
import com.bis.operox.payroll.entity.Payroll;
import com.bis.operox.payroll.service.HolidayService;
import com.bis.operox.payroll.service.LeavesService;
import com.bis.operox.payroll.service.PayrollService;
import com.bis.operox.util.CommonUtil;

@Controller
@PropertySource("${propertyLocation:operoxapp.properties}")
public class PayrollController {
	
	private static final Logger logger = LoggerFactory.getLogger(PayrollController.class);
	
	@Value("${operoxUrl}")
	private String operoxUrl;
	
	@Autowired
	CommonUtil commonUtil;
	
	@Autowired
	private HolidayService holidayService;
	
	@Autowired
	private LeavesService leaveservice;
	
	@Autowired
	PayrollService payrollService;
	
	@Autowired
	private UserService userService;
	
	/**
	 * Returns holiday list page
	 * @param request
	 * @return model
	 */
	@RequestMapping(value = "/holiday")
	public ModelAndView holidayHome(HttpServletRequest request){
		commonUtil.highLightMenu(Constants.Setup, request);
		commonUtil.highLightSubMenu(Constants.Holiday, request);
		ModelAndView model = new ModelAndView();
		model.setViewName("payroll/holidayList");
		return model;
	}
	
	/**
	 * Returns Leaves list page
	 * @param request
	 * @return model
	 */
	@RequestMapping(value = "/leaves")
	public ModelAndView leavesHome(HttpServletRequest request){
		commonUtil.highLightMenu(Constants.Setup, request);
		commonUtil.highLightSubMenu(Constants.Leave, request);
		ModelAndView model = new ModelAndView();
		model.setViewName("payroll/leavesList");
		return model;
	}
	
//	/addHoilday
	
	/**
	 * Returns add leaves  page
	 * @param request
	 * @return model
	 */
	@RequestMapping(value = "/addLeaves")
	public ModelAndView addLeaves(HttpServletRequest request){
		commonUtil.highLightMenu(Constants.Setup, request);
		ModelAndView model = new ModelAndView();
		model.setViewName("payroll/addLeaves");
		return model;
	}
	
	/**
	 * Returns add holiday  page
	 * @param request
	 * @return model
	 */
	@RequestMapping(value = "/addHoilday")
	public ModelAndView addHoliday(HttpServletRequest request){
		commonUtil.highLightMenu(Constants.Setup, request);
		ModelAndView model = new ModelAndView();
		model.setViewName("payroll/addHoliday");
		return model;
	}
	
	@RequestMapping(value = "/editHoliday/{holidayId}")
	public ModelAndView editHoliday(@PathVariable Long holidayId,HttpServletRequest request) throws Exception{
		commonUtil.highLightMenu(Constants.Setup, request);
		ModelAndView model = new ModelAndView();
		Holiday holiday = holidayService.getHolidayById(holidayId);
		if(holiday.getHolidayDate() !=null){
			holiday.setHolidayDateStr(commonUtil.getDateString(holiday.getHolidayDate()));
		}
		model.addObject("holiday",holiday);
		model.addObject("operoxUrl",operoxUrl);
		model.setViewName("payroll/addHoliday");
		return model;
	}
	
	@RequestMapping(value = "/viewHoliday/{holidayId}")
	public ModelAndView viewCustomer(@PathVariable Long holidayId,HttpServletRequest request) throws Exception{
		commonUtil.highLightMenu(Constants.Customers, request);
		ModelAndView model = new ModelAndView();
		Holiday holiday = holidayService.getHolidayById(holidayId);
		if(holiday.getHolidayDate() !=null){
			holiday.setHolidayDateStr(commonUtil.getDateString(holiday.getHolidayDate()));
		}
		if(holiday.getUpdatedDate()!=null){
			holiday.setUpdatedDateStr(commonUtil.getDateString(holiday.getUpdatedDate()));	
		}
		model.addObject("holiday",holiday);
		model.addObject("operoxUrl",operoxUrl);
		model.setViewName("payroll/viewHoliday");
		return model;
	}
	
	@RequestMapping(value = "/editLeave/{leaveId}")
	public ModelAndView editLeave(@PathVariable Long leaveId,HttpServletRequest request){
		ModelAndView model = new ModelAndView();
		Leaves leaves = leaveservice.getLeavesById(leaveId);
		model.addObject("leaves",leaves);
		model.addObject("operoxUrl",operoxUrl);
		model.setViewName("payroll/addLeaves");
		return model;
	}
	
//	viewLeave
	@RequestMapping(value = "/viewLeave/{leaveId}")
	public ModelAndView viewLeave(@PathVariable Long leaveId,HttpServletRequest request){
		ModelAndView model = new ModelAndView();
		Leaves leaves = leaveservice.getLeavesById(leaveId);
		if(leaves.getUpdatedDate()!=null){
			leaves.setUpdatedDateStr(commonUtil.getDateString(leaves.getUpdatedDate()));	
		}
		model.addObject("leaves",leaves);
		model.addObject("operoxUrl",operoxUrl);
		model.setViewName("payroll/leaveview");
		return model;
	}
//	/payroll
	
	@RequestMapping(value = "/payroll")
	public ModelAndView payrollHome(HttpServletRequest request){
		commonUtil.highLightMenu(Constants.Setup, request);
		commonUtil.highLightSubMenu(Constants.PayRoll, request);
		ModelAndView model = new ModelAndView();
		model.addObject("operoxUrl",operoxUrl);
		model.setViewName("payroll/payrollList");
		return model;
	}
	
	@RequestMapping(value = "/runPayroll")
	public ModelAndView runPayRollHome(){
		ModelAndView model = new ModelAndView();
		model.addObject("operoxUrl",operoxUrl);
		model.setViewName("payroll/runPayroll");
		return model;
	}
	
	@RequestMapping(value = "/downloadPayslip/{payrollId}/{userId}")
	public void downloadPay(@PathVariable Long payrollId,@PathVariable Long userId,HttpServletRequest request,HttpServletResponse response) throws IOException{
		
		Payroll payRoll = payrollService.getPayrollById(payrollId);
		User user = userService.getUserById(userId);
		byte[] pdfData = payRoll.getPdfFile();
		FileOutputStream fileOuputStream =null;
		try { 
		     fileOuputStream = new FileOutputStream(user.getFirstName()+"_payslip"); 
		    fileOuputStream.write(pdfData);
		 } finally {
		    fileOuputStream.close();
		 }
		String mimeType = "application/pdf";
        if (mimeType == null) {        
            mimeType = "application/octet-stream";
        }              
        response.setContentType(mimeType);
        String headerKey = "Content-Disposition";
         String headerValue = String.format("attachment; filename=\"%s\"", user.getFirstName()+"_payslip");
         response.setHeader(headerKey, headerValue);
        InputStream inputStream = new ByteArrayInputStream( payRoll.getPdfFile());
		try {
			OutputStream outStream = response.getOutputStream();
            
            byte[] buffer = new byte[500];
            int bytesRead = -1;
             
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outStream.write(buffer, 0, bytesRead);
            }
             
            inputStream.close();
            outStream.close();   
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
