package com.bis.operox.inv.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bis.operox.inv.dao.BillItemsDao;
import com.bis.operox.inv.dao.entity.BillItems;

/**
 * @author shivayogiKadagad
 *
 */
@Repository
public class BillItemsDaoImpl implements BillItemsDao{

	@Autowired
	SessionFactory sessionfactory;
	
	@Override
	@Transactional(rollbackFor = { Exception.class })
	public BillItems saveBillItems(BillItems billItems) {
		sessionfactory.getCurrentSession().saveOrUpdate(billItems);
		return billItems;
	}
	@Override
	@Transactional(rollbackFor = { Exception.class })
	public BillItems deleteBillItems(Long id) {
		Session session = this.sessionfactory.getCurrentSession();
		String query = "from BillItems billItems left join fetch billItems.productStockId  where billItems.id = :id";
		List<BillItems> itemList = session.createQuery(query).setLong("id", id).list();
		BillItems billItems =null;
		if(itemList != null && !itemList.isEmpty()){
			billItems = itemList.iterator().next();
		}
		sessionfactory.getCurrentSession().delete(billItems);
		return billItems;
	}
	
	@Override
	@Transactional(rollbackFor = { Exception.class })
	public BillItems getBillItemsByStockId(Long StockId) {
		Session session = this.sessionfactory.getCurrentSession();	
        String query = "from BillItems billItems where billItems.productStockId.id = :StockId";
	     List<BillItems> billItemsList = session.createQuery(query).setLong("StockId", StockId).setCacheable(true).list();
	    return billItemsList.get(0);
	}
	
	@Override
	@Transactional
	public List<BillItems> getBillItemsByBillId(Long billId) {
		Session session = this.sessionfactory.getCurrentSession();	
		String query = "from BillItems bi left join fetch bi.billId bil left join fetch bil.customerId left join fetch bil.storeId left join fetch bil.counterId left join fetch bil.cashierUserId left join fetch bil.currency where bi.billId.id = :billId";
	    return session.createQuery(query).setLong("billId", billId).setCacheable(true).list();
	}
	@Override
	@Transactional
	public List<Long> getBillItemsIdsByBillId(Long billId) {
		
		Session session = this.sessionfactory.getCurrentSession();	
		String query = "select bi.id from BillItems bi where bi.billId.id = :billId";
	    List<Long> billItemsIdsList = session.createQuery(query).setLong("billId", billId).setCacheable(true).list();
	    
		return billItemsIdsList;
	}
	
	@Override
	@Transactional(rollbackFor = { Exception.class })
	public List<BillItems> getOrderBillItemsByBillIdAndUseCode(Long billId, String useCode) {
		Session session = this.sessionfactory.getCurrentSession();	
		String query = "from BillItems bi where bi.billId.id = :billId and bi.createdBy =:useCode";
	    List<BillItems> billItemsIdsList = session.createQuery(query).setLong("billId", billId).setParameter("useCode", useCode).setCacheable(true).list();
		return billItemsIdsList;
	}

	
	

}
