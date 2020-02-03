package com.bis.operox.inv.dao;

import java.util.List;

import com.bis.operox.inv.dao.entity.Bill;

public interface BillDao {
	
	public Bill addBill(Bill bill);
	
	public Bill getBillById(Long id);
	
	public Bill getBillByBillNumber(String billNumber);

	public List<Bill> listBill();
	
	public Long getBillNumberByStoreName(String storeName);
	
	public List<Bill> getOnHoldInvoicesByCounterIdAndCashierUserId(Long counterId,Long cashierUserId);
	
	public Bill ecommOrderListByUserId(Long billId,String userCode);

	public List<Bill> ecommOrderListByUserCode(String userCode);
	
	String getMaxReceivedNumber();
}