package com.bis.operox.inv.dao;

import java.util.List;

import com.bis.operox.inv.dao.entity.Shift;

public interface ShiftDao {

	Shift storeShift(Shift shift);

	Shift getShiftById(Long id);
	
	public List<Shift> getAllShifts() throws Exception;

	List<Shift> shiftListByStoreId(Long storeId) throws Exception;
	
	List<Shift> shiftListByCompanyId(Long companyId) throws Exception;
	
}
