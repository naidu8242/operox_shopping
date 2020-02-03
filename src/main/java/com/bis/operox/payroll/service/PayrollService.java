package com.bis.operox.payroll.service;

import java.util.Date;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.bis.operox.inv.dao.entity.Tax;
import com.bis.operox.inv.dao.entity.User;
import com.bis.operox.payroll.entity.Payroll;

public interface PayrollService {
     
	Payroll addPayroll(Payroll payroll);
	
	Payroll getPayrollById(Long payrollId);

	List<Payroll> listPayrolls(User user, Date fromDate, Date toDate, Date startDate, Date endDate, String payRollType);
	
	List<User> gePayrollEmployees(String payRollType, Date fromDate, Date toDate);

	Tax getPayrollTax(String tAX_VALUE);
	
	String savePayrollDetails(JSONObject json, User user) throws Exception;
}
