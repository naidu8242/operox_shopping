package com.bis.operox.payroll.service;

import java.util.List;

import org.json.JSONObject;

import com.bis.operox.inv.dao.entity.User;
import com.bis.operox.payroll.entity.Holiday;

public interface HolidayService {
	
    Holiday addHoliday(JSONObject jsonObj, User user) throws Exception;
	
    Holiday getHolidayById(Long holidayId)throws Exception;

	List<Holiday> listHolidays(Long userId)throws Exception;

	Holiday deleteHoliday(Holiday holiday)throws Exception;
}
