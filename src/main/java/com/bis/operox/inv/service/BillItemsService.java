package com.bis.operox.inv.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bis.operox.inv.dao.entity.BillItems;

/**
 * @author shivayogiKadagad
 *  @date 23/9/2016
 *
 */
@Service
public interface BillItemsService {
	public BillItems saveBillItems(BillItems billItems);
	
	BillItems getBillItemsByStockId(Long StockId);
	
	public List<BillItems> getBillItemsByBillId(Long billId);
	
	public List<BillItems> getOrderBillItemsByBillIdAndUseCode(Long billId,String useCode);
}
