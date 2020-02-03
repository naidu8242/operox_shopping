package com.bis.operox.inv.service;

import java.util.List;

import org.json.JSONObject;

import com.bis.operox.inv.dao.entity.Bill;
import com.bis.operox.inv.dao.entity.User;
import com.bis.operox.inv.dao.enums.Bill_Status;

public interface BillService {

	public Bill addBill(JSONObject jsonObj,User user,Bill_Status status,JSONObject paymentjsonjsonObj)throws Exception;
	
	public Bill getBillById(Long id);
	
	public List<Bill> getOnHoldInvoicesByCounterIdAndCashierUserId(Long counterId,Long cashierUserId);
	
	public Long getBillNumberByStoreName(String storeName);
	
	public List<Bill> listBill();
	
	public List<Bill> ecommOrderListByUserCode(String userCode);
	
	public Bill ecommOrderListByUserId(Long billId,String userCode);

	String getMaxReceivedNumber();
}
