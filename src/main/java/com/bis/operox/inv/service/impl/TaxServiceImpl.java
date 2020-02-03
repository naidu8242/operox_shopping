package com.bis.operox.inv.service.impl;

import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bis.operox.constants.Constants;
import com.bis.operox.inv.dao.TaxDao;
import com.bis.operox.inv.dao.UserDao;
import com.bis.operox.inv.dao.entity.Category;
import com.bis.operox.inv.dao.entity.Store;
import com.bis.operox.inv.dao.entity.Tax;
import com.bis.operox.inv.dao.entity.User;
import com.bis.operox.inv.service.TaxService;
import com.bis.operox.util.CommonUtil;

/**
 * 
 * @author Prasad Salina
 *
 */
@Service
public class TaxServiceImpl implements TaxService{
	
	@Autowired
	private TaxDao taxDao;
	
	@Autowired
	private CommonUtil commonUtil;
	
	@Autowired
	private UserDao userDao;
	
	
	@Override
	public Tax storeTax(JSONObject jsonObj, User user,Store store) throws Exception {
		
		Tax tax = null;
		if(jsonObj.has("taxid") && jsonObj.getString("taxid") != null && !"".equals(jsonObj.getString("taxid"))){
			tax = taxDao.getTaxById(Long.parseLong(jsonObj.getString("taxid")));
		}else{
			tax = new Tax();
		}
		if(jsonObj.has("taxName") && jsonObj.getString("taxName") != null && !"".equals(jsonObj.getString("taxName"))){
			tax.setTaxName(jsonObj.getString("taxName"));
		}
		if(jsonObj.has("taxValue") && jsonObj.getString("taxValue") != null && !"".equals(jsonObj.getString("taxValue"))){
			tax.setTaxValue(jsonObj.getString("taxValue"));
		}
		if(jsonObj.has("category") && jsonObj.getString("category") != null && !"".equals(jsonObj.getString("category"))){
			Category category = new Category();
			category.setId(Long.parseLong(jsonObj.getString("category")));
			tax.setCategory(category);
		}
		tax.setStore(store);
		tax.setStatus(Constants.RECORD_ACTIVE);
		tax.setTaxValue(jsonObj.getString("taxValue"));
		tax.setCreatedDate(commonUtil.currentDate());
		tax.setUpdatedBy(user.getUserCode());
		tax.setCreatedBy(user.getUserCode());
		tax.setUpdatedDate(commonUtil.currentDate());
		return taxDao.addTax(tax);
	}
	
	
	
	@Override
	public Tax getTaxById(Long id)  throws Exception{
		Tax tax =   taxDao.getTaxById(id);
		if(tax!=null){
			if(tax.getCategory()!=null){
				tax.setCategoryName(tax.getCategory().getCategoryName());
			}
			if(tax.getUpdatedBy()!=null){
				User user   = userDao.getUserByUserCode(tax.getUpdatedBy());
				if(user!=null){
					tax.setUpdatedBy(user.getFullName());

				}
			  
			}
		}		
				
		return tax;
	}

	@Override
	public List<Tax> getAll()  throws Exception{
		
		List<Tax> taxList = taxDao.getAll();
		for(Tax tax :taxList){
			if(tax.getCategory() != null){
				tax.setCategoryName(tax.getCategory().getCategoryName());
			}
			if(tax.getUpdatedBy()!=null){
					User user = userDao.getUserByUserCode(tax.getUpdatedBy());
				if(user!=null){
					tax.setUpdatedBy(user.getFullName());
					tax.setUpdatedBy(user.getFirstName() +""+user.getLastName());
				}
			}
			
		}
		return taxList;
	}

	@Override
	public Tax getTaxByUserId(Long userID) throws Exception {
		return taxDao.getTaxByUserId(userID);
	}


	@Override
	public Tax getTaxByTaxName( String taxName) throws Exception {
		Tax tax = taxDao.getTaxByTaxName(taxName);
		return tax;
	}

	@Override
	public Tax removeTaxById(Long id, String userCode) throws Exception {
		
		Tax tax = taxDao.getTaxById(id);
		tax.setUpdatedBy(userCode);
		tax.setStatus(Constants.RECORD_IN_ACTIVE);
	    return taxDao.addTax(tax);
	}



	@Override
	public Tax addTax(Tax tax) throws Exception {
		return taxDao.addTax(tax);
	}



	@Override
	public List<Tax> getTaxListByStoreId(Long storeId) throws Exception {
		
		List<Tax> taxList = taxDao.getTaxListByStoreId(storeId);
		for(Tax tax :taxList){
			User user =null;
			user  = userDao.getUserByUserCode(tax.getUpdatedBy());
			if(tax.getCategory() != null){
				tax.setCategoryName(tax.getCategory().getCategoryName());
			}
			if(user!=null){
				tax.setUpdatedBy(user.getFullName());
			}
			
		}
		return taxList;
	}
	
}
