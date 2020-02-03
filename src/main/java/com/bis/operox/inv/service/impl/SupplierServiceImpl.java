package com.bis.operox.inv.service.impl;

import java.util.Date;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bis.operox.constants.Constants;
import com.bis.operox.inv.dao.AddressDao;
import com.bis.operox.inv.dao.SupplierDao;
import com.bis.operox.inv.dao.entity.Address;
import com.bis.operox.inv.dao.entity.Supplier;
import com.bis.operox.inv.dao.entity.User;
import com.bis.operox.inv.service.SupplierService;
import com.bis.operox.util.CommonUtil;

/**
 * @author Ajith Matta
 * @Date 23/09/2016
 *
 */
@Service
public class SupplierServiceImpl implements SupplierService{
	
	@Autowired
	private SupplierDao supplierDao;
	
	@Autowired
	private AddressDao addressDao;
	
	@Autowired
	CommonUtil commonUtil;
	/**
	 * This method is used for to store/add supplier into DB(supplier.address tables)
	 * @author Prasad K
	 * @param jsonObj
	 * @param user
	 * @return supplier
	 * @exception NullPointerException
	 */
	
	@Override
	public Supplier addSupplier(JSONObject jsonObj,User user) throws Exception {
		
		Supplier supplier = null;
		Address address = null;
		
		if(jsonObj.has("supplierId") && jsonObj.getString("supplierId") != null && !"".equals(jsonObj.getString("supplierId"))){
			supplier = supplierDao.getSupplierById(Long.parseLong(jsonObj.getString("supplierId")));
		}else{
			supplier = new Supplier();
		}
		
		if(jsonObj.has("name")){
        supplier.setName(jsonObj.getString("name"));
		}
		
		if(jsonObj.has("supplierContactPhone")){
			supplier.setSupplierContactPhone(jsonObj.getString("supplierContactPhone"));;
		}
		
		if(jsonObj.has("supplierContactName")){
		supplier.setSupplierContactName(jsonObj.getString("supplierContactName"));
		}
		
		if(jsonObj.has("supplierContactEmail")){
		supplier.setSupplierContactEmail(jsonObj.getString("supplierContactEmail"));
		}
		
		if(jsonObj.getString("tin") != null && jsonObj.getString("tin") != "" && jsonObj.getString("tin") !="undefined"){
		if(jsonObj.has("tin") && !jsonObj.getString("tin").isEmpty()){
			supplier.setTin(jsonObj.getString("tin"));
		}
		}
		supplier.setStatus(Constants.Active);
		
		if(supplier != null && supplier.getAddress() != null && supplier.getAddress().getId() != null){
			address = addressDao.getAddressById(supplier.getAddress().getId());
		}else{
			address = new Address();
		}
		
		address = getAddressDetails(address,jsonObj,user);
		address = addressDao.addAddress(address);
		supplier.setAddress(address);
		
		supplier.setCreatedDate(commonUtil.currentDate());
		supplier.setCreatedDateStr(Constants.DF_DMY.format(supplier.getCreatedDate()));
		supplier.setCreatedBy(user.getUserCode());
		supplier.setUpdatedDate(commonUtil.currentDate());
		supplier.setUpdatedDateStr(Constants.DF_DMY.format(supplier.getUpdatedDate()));
		supplier.setUpdatedBy(user.getUserCode());
		
		
		
		Supplier savedSupplier = supplierDao.addSupplier(supplier);
		return savedSupplier;
	}
	
	private Address getAddressDetails(Address address, JSONObject jsonObj, User user) throws JSONException {
		address.setAddress(jsonObj.getString("address"));
		address.setCountry(jsonObj.getString("country"));
		address.setState(jsonObj.getString("state"));
		address.setCity(jsonObj.getString("city"));
		address.setZipcode(jsonObj.getString("zipCode"));
		address.setCountry(jsonObj.getString("country"));
		address.setStatus(Constants.Active);
		address.setAddress(jsonObj.getString("address"));
		if(user.getUserCode() != null){
		address.setCreatedBy(user.getUserCode() );
		}
		address.setCreatedDate(new Date());
		if(user.getUserCode() != null && !user.getUserCode().isEmpty()){
		address.setUpdatedBy(user.getUserCode());
		}
		address.setUpdatedDate(new Date());
		return address;
	}


	@Override
	public Supplier getSupplierById(Long id) throws Exception {
		Supplier  supplier = supplierDao.getSupplierById(id);
		supplier.setUpdatedDateStr(Constants.DF_DMY.format(supplier.getUpdatedDate()));
		
		return supplier;
	}


	@Override
	public List<Supplier> getAllSupplier() throws Exception {
		return supplierDao.getAllSupplier();
	}
	
	
	@Override
	public List<Supplier> getAllSupplierByStatus(Integer status) throws Exception {
		return supplierDao.getAllSupplierByStatus(status);
	}

	@Override
	public Supplier saveOrUpdateSupplier(Supplier supplier) throws Exception {
		return supplierDao.addSupplier(supplier);
	}

	@Override
	public boolean isSupplierNameValid(String supplierName, Long id) throws Exception {
		return supplierDao.isSupplierNameValid(supplierName, id);
	}
	
	
}