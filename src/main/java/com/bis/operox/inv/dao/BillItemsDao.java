package com.bis.operox.inv.dao;

import java.util.List;

import com.bis.operox.inv.dao.entity.BillItems;

/**
 * @author shivayogiKadagad
 * @date 23/9/2016
 *
 */
public interface BillItemsDao {

	public BillItems saveBillItems(BillItems billItems);
	
	public BillItems deleteBillItems(Long id);
	
	public List<Long> getBillItemsIdsByBillId(Long billId);
	
	BillItems getBillItemsByStockId(Long StockId);
	
	public List<BillItems> getBillItemsByBillId(Long billId);
	
	public List<BillItems> getOrderBillItemsByBillIdAndUseCode(Long billId,String useCode);

}
