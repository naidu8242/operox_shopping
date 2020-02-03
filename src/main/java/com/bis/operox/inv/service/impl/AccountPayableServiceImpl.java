package com.bis.operox.inv.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bis.operox.inv.dao.AccountPayableDao;
import com.bis.operox.inv.dao.entity.AccountPayable;
import com.bis.operox.inv.dao.entity.PurchaseOrder;
import com.bis.operox.inv.dao.entity.SupplierInvoice;
import com.bis.operox.inv.dao.entity.User;
import com.bis.operox.inv.service.AccountPayableService;

@Service
public class AccountPayableServiceImpl implements AccountPayableService{
	
	@Autowired
	private AccountPayableDao accountPayableDao;



	@Override
	public AccountPayable addAccountPayableDetails(JSONObject jsonObj, User user) throws NumberFormatException, Exception {
		
		AccountPayable  accountPayable = new AccountPayable();
		
		/*SupplierInvoice supplierInvoice = new SupplierInvoice();
		supplierInvoice.setId(Long.parseLong("supplierInvoice"));
		accountPayable.setSupplierInvoiceId(supplierInvoice);*/
		
		String pattern = "YYYY/MM/DD";
        SimpleDateFormat format = new SimpleDateFormat(pattern);
	 	
        
    	if((jsonObj.has("dateOfPay") && !jsonObj.getString("dateOfPay").isEmpty())){
    		Date dateOfPay = format.parse(jsonObj.getString("dateOfPay"));
    		accountPayable.setDateOfPay(dateOfPay);
    	}
		
		if(jsonObj.has("paymentMode") && !jsonObj.getString("paymentMode").isEmpty()){
			accountPayable.setPaymentMode(jsonObj.getString("paymentMode"));
		}
		if(jsonObj.has("paymentStatus") && !jsonObj.getString("paymentStatus").isEmpty())
		{
		 accountPayable.setPaymentStatus(jsonObj.getString("paymentStatus"));
	     }
		accountPayable.setCreatedDate(new Date());
		accountPayable.setUpdatedBy(user.getUserCode());
		accountPayable.setCreatedBy(user.getUserCode());
		accountPayable.setUpdatedDate(new Date());
		return accountPayableDao.addAccountPayable(accountPayable);
	}

	
}
