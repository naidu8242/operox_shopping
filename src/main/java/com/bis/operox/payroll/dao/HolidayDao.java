package com.bis.operox.payroll.dao;

import java.util.List;

import com.bis.operox.payroll.entity.Holiday;

public interface HolidayDao {

    Holiday addHoliday(Holiday holiDay);
	
    Holiday getHolidayById(Long holidayId);

	List<Holiday> listHolidays(Long userId);

	Holiday deleteHoliday(Holiday holiday);
}
