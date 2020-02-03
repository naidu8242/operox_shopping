package com.bis.operox.inv.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bis.operox.inv.dao.BillItemsDao;
import com.bis.operox.inv.dao.entity.BillItems;
import com.bis.operox.inv.service.BillItemsService;

/**
 * @author shivayogiKadagad
 * @date 23/9/2016
 *
 */
@Service
public class BillItemsServiceImpl implements BillItemsService {

	
	@Autowired
	private BillItemsDao billItemsDao;
	
	@Override
	public BillItems saveBillItems(BillItems billItems) {
		billItemsDao.saveBillItems(billItems);
		return billItems;
	}
	
	@Override
	public BillItems getBillItemsByStockId(Long StockId) {
		return billItemsDao.getBillItemsByStockId(StockId);
	}

	@Override
	public List<BillItems> getBillItemsByBillId(Long billId) {
		return billItemsDao.getBillItemsByBillId(billId);
	}

	@Override
	public List<BillItems> getOrderBillItemsByBillIdAndUseCode(Long billId, String useCode) {
		return billItemsDao.getOrderBillItemsByBillIdAndUseCode(billId, useCode);
	}

}
