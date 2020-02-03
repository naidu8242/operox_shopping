package com.bis.operox.inv.service.impl;

import java.util.Date;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bis.operox.inv.dao.ShiftDao;
import com.bis.operox.inv.dao.entity.Shift;
import com.bis.operox.inv.dao.entity.User;
import com.bis.operox.inv.service.ShiftService;

@Service
public class ShiftServiceImpl implements ShiftService {

	@Autowired
	private ShiftDao shiftDao;
	
	@Override
	public Shift storeShift(Shift shift) {
		return shiftDao.storeShift(shift);
	}

	@Override
	public Shift getShiftById(Long id) {
		return shiftDao.getShiftById(id);
	}
	
	@Override
	public List<Shift> getAllShifts() throws Exception {
		return this.shiftDao.getAllShifts();
	}

	@Override
	public List<Shift> shiftListByStoreId(Long storeId) throws Exception {
		return this.shiftDao.shiftListByStoreId(storeId);
	}

	@Override
	public void addShiftDetails(JSONObject jsonObj, User user) throws JSONException {
		
		Shift shift = null;
		if(jsonObj.getString("shiftId") != null && !"".equals(jsonObj.getString("shiftId"))){
			 shift = shiftDao.getShiftById(Long.parseLong(jsonObj.getString("shiftId")));
		}else{
			 shift = new Shift();
		}
		
		shift.setShiftName(jsonObj.getString("shiftName"));
		shift.setIntime(jsonObj.getString("intime"));
		shift.setOuttime(jsonObj.getString("outtime"));
		shift.setDescription(jsonObj.getString("description"));
		shift.setStore(user.getStore());
		shift.setStatus(1);
		shift.setCreatedBy(user.getUserCode());
		shift.setCreatedDate(new Date());
		
		shiftDao.storeShift(shift);
		
	}

	@Override
	public List<Shift> shiftListByCompanyId(Long companyId) throws Exception {
		return shiftDao.shiftListByCompanyId(companyId);
	}

}