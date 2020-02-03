package com.bis.operox.inv.service;

import java.util.List;

import org.json.JSONObject;

import com.bis.operox.inv.dao.entity.Shift;
import com.bis.operox.inv.dao.entity.User;

public interface ShiftService {
	Shift storeShift(Shift shift);
	
	Shift getShiftById(Long id);

	List<Shift> shiftListByStoreId(Long storeId) throws Exception;
	
	public List<Shift> getAllShifts() throws Exception;
	
	public void addShiftDetails(JSONObject jsonObj, User user) throws Exception;
	
	List<Shift> shiftListByCompanyId(Long companyId) throws Exception;
}