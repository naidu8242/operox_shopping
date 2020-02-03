package com.bis.operox.payroll.service.impl;

import java.util.Date;
import java.util.List;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bis.operox.constants.Constants;
import com.bis.operox.inv.dao.entity.Tax;
import com.bis.operox.inv.dao.entity.User;
import com.bis.operox.inv.service.TaxService;
import com.bis.operox.inv.service.UserService;
import com.bis.operox.payroll.dao.PayrollDao;
import com.bis.operox.payroll.entity.Payroll;
import com.bis.operox.payroll.service.PayrollService;
import com.bis.operox.payroll.util.PayrollHelper;
import com.bis.operox.util.CommonUtil;

@Service
public class PayrollServiceImpl implements PayrollService{
	
	private static final Logger logger = LoggerFactory.getLogger(PayrollServiceImpl.class);
	
	@Autowired
	private PayrollDao payrollDao;

	@Autowired
	private UserService userService;
	
	@Autowired 
	private TaxService taxService;
	
	@Autowired
	private CommonUtil commonUtil;
	
	@Autowired
	private PayrollHelper payrollHelper;
	
	@Override
	public Payroll addPayroll(Payroll payroll) {
		return payrollDao.addPayroll(payroll);
	}
	@Override
	public Payroll getPayrollById(Long payrollId) {
		return payrollDao.getPayrollById(payrollId);
	}
	@Override
	public List<Payroll> listPayrolls(User user, Date fromDate, Date toDate, Date startDate, Date endDate,String payRollType) {
		return payrollDao.listPayrolls(user, fromDate, toDate, startDate, endDate, payRollType);
	}

	@Override
	public List<User> gePayrollEmployees(String payRollType,Date fromDate, Date toDate) {
		return payrollDao.gePayrollEmployees(payRollType,fromDate,toDate);
	}
	@Override
	public Tax getPayrollTax(String taxValue) {
		return payrollDao.getPayrollTax(taxValue);
	}
	@Override
	public String savePayrollDetails(JSONObject jsonObj,User user) throws Exception {
		String maxValue = jsonObj.getString("maxValue");
		int maxCount = Integer.parseInt(maxValue);
		Tax tax = null;
		  User payrollUser = null;
		for(int i=0 ;i < maxCount ; i++){
			Payroll payroll = new Payroll();
			if(jsonObj.has("userID"+i) && jsonObj.getString("userID"+i) != null && !"".equals(jsonObj.getString("userID"+i))){
			   payrollUser = userService.getUserById(Long.parseLong(jsonObj.getString("userID"+i)));
			   payrollUser.setPayslipFromDate(commonUtil.getDateFormat(jsonObj.getString("fromDate")));
			   payrollUser.setPayslipToDate(commonUtil.getDateFormat(jsonObj.getString("toDate")));
			   payrollUser = userService.addUser(payrollUser);
			  tax = taxService.getTaxByUserId(Long.parseLong(jsonObj.getString("userID"+i)));
			  payroll.setUser(payrollUser);
			}
			if(jsonObj.has("attendance"+i) && jsonObj.getString("attendance"+i) != null && !"".equals(jsonObj.getString("attendance"+i))){
				 payroll.setAttendance(jsonObj.getString("attendance"+i));
				  logger.info("***************setAttendance " +jsonObj.getString("attendance"+i));
				}
			if(jsonObj.has("leave"+i) && jsonObj.getString("leave"+i) != null && !"".equals(jsonObj.getString("leave"+i))){
				 payroll.setTotalLeaves(Integer.parseInt(jsonObj.getString("leave"+i)));
				  logger.info("***************setTotalLeaves " +jsonObj.getString("leave"+i));
				}
			if(jsonObj.has("tax"+i) && jsonObj.getString("tax"+i) != null && !"".equals(jsonObj.getString("tax"+i))){
				 payroll.setTax(tax);
				}
			 if(jsonObj.has("lop"+i) && jsonObj.getString("lop"+i) != null && !"".equals(jsonObj.getString("lop"+i))){
				 payroll.setLop(jsonObj.getString("lop"+i));
				 logger.info("***************setLop " +jsonObj.getString("lop"+i));
				}
			 if(jsonObj.has("netPay"+i) && jsonObj.getString("netPay"+i) != null && !"".equals(jsonObj.getString("netPay"+i))){
				 payroll.setNetPay(jsonObj.getString("netPay"+i));
				 logger.info("***************setNetPay " +jsonObj.getString("netPay"+i));
				}
			 if(jsonObj.has("fromDate") && jsonObj.getString("fromDate") != null && !"".equals(jsonObj.getString("fromDate"))){
				 payroll.setFromDate(commonUtil.getDateFormat(jsonObj.getString("fromDate")));
				 logger.info("***************setFromDate " +jsonObj.getString("fromDate"));
				}
			 if(jsonObj.has("toDate") && jsonObj.getString("toDate") != null && !"".equals(jsonObj.getString("toDate"))){
				 payroll.setToDate(commonUtil.getDateFormat(jsonObj.getString("toDate")));
				 logger.info("***************toDate " +jsonObj.getString("toDate"));
				}
//			  generatePDFPaySlip(payrollUser,jsonObj.getString("tax"+i),jsonObj.getString("leave"+i),jsonObj.getString("lop"+i),jsonObj.getString("netPay"+i));
			 byte[] pdfData = payrollHelper.generatePDFPayroll(payrollUser,jsonObj.getString("tax"+i),jsonObj.getString("leave"+i),jsonObj.getString("lop"+i),jsonObj.getString("netPay"+i),jsonObj.getString("fromDate"),jsonObj.getString("toDate"));
     		 payroll.setPdfFile(pdfData);
			 payroll.setCreatedDate(new Date());
			 payroll.setCreatedBy(user.getUserCode());
			 payroll.setUpdatedDate(new Date());
			 payroll.setUpdatedBy(user.getUserCode());
			 payrollDao.addPayroll(payroll);
		}
		return "success";
	}
	/*private void generatePDFPaySlip(User user, String tax, String leaves, String lop, String netPay) {
	
		logger.info("Employee Id : "+user.getId());
		logger.info("Department name "+user.getDepartment().getDepartmentName());
		logger.info("Role is "+user.getRole().getDisplayName());
//		logger.info("Location is"+user.getl);
		logger.info("Name is "+user.getFirstName());
		logger.info("compensation Type is "+user.getCompensatationType());
		
		logger.info("tax is "+tax);
		logger.info("leaves "+leaves);
		logger.info("lop"+lop);
		logger.info("netPay"+netPay);
		
		
		
	}*/

}
