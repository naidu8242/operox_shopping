package com.bis.operox.payroll.service.impl;

import java.util.Date;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bis.operox.inv.dao.entity.User;
import com.bis.operox.payroll.dao.HolidayDao;
import com.bis.operox.payroll.entity.Holiday;
import com.bis.operox.payroll.service.HolidayService;
import com.bis.operox.util.CommonUtil;

@Service
public class HolidayServiceImpl implements HolidayService{

	@Autowired
	private HolidayDao holidayDao;
	
	@Autowired
	private CommonUtil commonUtil;
	
	@Override
	public Holiday addHoliday(JSONObject jsonObj, User user) throws Exception{
		Holiday holiday = null;
		if(jsonObj.has("holidayId") && jsonObj.getString("holidayId") != null && !"".equals(jsonObj.getString("holidayId"))){
			holiday = holidayDao.getHolidayById(Long.parseLong(jsonObj.getString("holidayId")));
			holiday.setUpdatedBy(user.getUserCode());
			holiday.setUpdatedDate(new Date());
		}
		else{
			holiday = new Holiday();
			holiday.setCreatedDate(new Date());
			holiday.setCreatedBy(user.getUserCode());
			holiday.setUpdatedBy(user.getUserCode());
			holiday.setUpdatedDate(new Date());
		}
		
		if (!"".equals(jsonObj.getString("holidayName"))
				&& jsonObj.getString("holidayName") != null) {
			holiday.setHolidayName(jsonObj.getString("holidayName"));
		}else{
			holiday.setHolidayName("");
		}
		
		if (!"".equals(jsonObj.getString("year"))
				&& jsonObj.getString("year") != null) {
			holiday.setYear(jsonObj.getString("year"));
		}else{
			holiday.setYear("");
		}
		
		if (!"".equals(jsonObj.getString("dateOfHoliday"))
				&& jsonObj.getString("dateOfHoliday") != null) {
			holiday.setHolidayDate(commonUtil.getDateFormat(jsonObj.getString("dateOfHoliday")));
		}else{
			holiday.setHolidayDate(null);
		}
		
		if (!"".equals(jsonObj.getString("description"))
				&& jsonObj.getString("description") != null) {
			holiday.setDescription(jsonObj.getString("description"));
		}else{
			holiday.setDescription("");
		}
		
		holiday.setUser(user);
		holiday.setStatus(1);
		return holidayDao.addHoliday(holiday);
	}

	@Override
	public Holiday getHolidayById(Long holidayId) throws Exception{
		return holidayDao.getHolidayById(holidayId);
	}

	@Override
	public List<Holiday> listHolidays(Long userId) throws Exception{
		return holidayDao.listHolidays(userId);
	}

	@Override
	public Holiday deleteHoliday(Holiday holiday) throws Exception {
		return  holidayDao.deleteHoliday(holiday);
	}

}
