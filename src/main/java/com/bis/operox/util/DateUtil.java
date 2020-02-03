package com.bis.operox.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

/**
 * This class is used for...
 * 
 * @author: Srinivas Vemula
 * @date: 31-July-2016
 */

public  class DateUtil {
	protected static int getDayOffset(String day_Of_Week){
		int day_offset = 0;
        switch (day_Of_Week) {
           case "MON":  day_offset = Calendar.MONDAY;
                    break;
           case "TUE":  day_offset = Calendar.TUESDAY;
                    break;
           case "WED":  day_offset = Calendar.WEDNESDAY;
                break;
           case "THU":  day_offset = Calendar.THURSDAY;
               break;
           case "FRI":  day_offset = Calendar.FRIDAY;
               break;
           case "SAT":  day_offset = Calendar.SATURDAY;
                 break;
           case "SUN":  day_offset = Calendar.SATURDAY+1;
                break;
           default:   day_offset = Calendar.SUNDAY;
                    break;
       }
        return day_offset;
	}

	
	
	public static Date getNextWeekDay(String day_Of_Week) {
		int day_offset = getDayOffset(day_Of_Week);
		Calendar nowCal = Calendar.getInstance(TimeZone.getDefault());
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
			String nowStr = sdf.format(new Date().getTime());
			Date nowDate = sdf.parse(nowStr);
			nowCal.setTime(nowDate);
			if(day_offset == 8){
				nowCal.add(Calendar.DATE, 14);
				nowCal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
			}
			else{
				nowCal.add(Calendar.DATE, 7);
				nowCal.set(Calendar.DAY_OF_WEEK, day_offset);
			}
			return nowCal.getTime();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	public static Date getNextWeekDayForTimesheet(String day_Of_Week) {
		int day_offset = getDayOffset(day_Of_Week);
		Calendar nowCal = Calendar.getInstance(TimeZone.getDefault());
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			String nowStr = sdf.format(new Date().getTime());
			Date nowDate = sdf.parse(nowStr);
			nowCal.setTime(nowDate);
			if(day_offset == 8){
				nowCal.add(Calendar.DATE, 14);
				nowCal.set(Calendar.DAY_OF_WEEK,Calendar.SUNDAY);
			}
			else{
				nowCal.add(Calendar.DATE, 7);
				nowCal.set(Calendar.DAY_OF_WEEK, day_offset);
			}
			
			return nowCal.getTime();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static Date getCurrentWeekDay(String day_Of_Week) {
		int day_offset = getDayOffset(day_Of_Week);
		Calendar nowCal = Calendar.getInstance(TimeZone.getDefault());
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
			String nowStr = sdf.format(nowCal.getTime());
			Date nowDate = sdf.parse(nowStr);
			nowCal.setTime(nowDate);
			if(day_offset == 8){
				nowCal.add(Calendar.DATE, 7);
				nowCal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
			}
			else{
				nowCal.set(Calendar.DAY_OF_WEEK, day_offset);
			}
			return nowCal.getTime();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static Date getCurrentWeekDayForTimesheets(String day_Of_Week) {
		int day_offset = getDayOffset(day_Of_Week);
		Calendar nowCal = Calendar.getInstance(TimeZone.getDefault());
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			String nowStr = sdf.format(nowCal.getTime());
			Date nowDate = sdf.parse(nowStr);
			nowCal.setTime(nowDate);
			if(day_offset == 8){
				nowCal.add(Calendar.DATE, 7);
				nowCal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
			}
			else{
				nowCal.set(Calendar.DAY_OF_WEEK, day_offset);
			}
			return nowCal.getTime();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static Calendar getCurrentWeekDayByCalendarForTimesheets(Calendar calendar,String day_Of_Week) {
		Calendar cal = Calendar.getInstance(TimeZone.getDefault());
		int day_offset = getDayOffset(day_Of_Week);
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
			String nowStr = sdf.format(calendar.getTime());
			Date nowDate = sdf.parse(nowStr);
			cal.setTime(nowDate);
			if(day_offset == 8){
				cal.add(Calendar.DATE, 7);
				cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
			}
			else{
				cal.set(Calendar.DAY_OF_WEEK, day_offset);
			}
			return cal;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static Date getPreviousWeekDay(String day_Of_Week) {
		  
		int day_offset = getDayOffset(day_Of_Week);
		Calendar nowCal = Calendar.getInstance(TimeZone.getDefault());
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
			
			String nowStr = sdf.format(new Date().getTime());
			Date nowDate = sdf.parse(nowStr);
			nowCal.setTime(nowDate);
			
			if(day_offset == 8){
				nowCal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
			}
			else{
				nowCal.add(Calendar.DATE, -7);
				nowCal.set(Calendar.DAY_OF_WEEK, day_offset);
			}
			nowCal.getTime();
			return nowCal.getTime();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	
     public static Calendar getPreviousWeekDayByCalendar(Calendar calendar,String day_Of_Week) {
    	 Calendar cal = Calendar.getInstance(TimeZone.getDefault());
    	 int day_offset = getDayOffset(day_Of_Week);
 		try {
 			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
 			String nowStr = sdf.format(calendar.getTime());
 			Date nowDate = sdf.parse(nowStr);
 			cal.setTime(nowDate);
 			if(day_offset == 8){
 	 			cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
 			}
 			else{
 				cal.add(Calendar.DATE, -7);
 	 			cal.set(Calendar.DAY_OF_WEEK, day_offset);
 			}
 			
 			return cal;
 		} catch (Exception e) {
 			e.printStackTrace();
 		}
 		return null;
	}
	
	public static Calendar getCurrentWeekDayByCalendar(Calendar calendar,String day_Of_Week) {
		Calendar cal = Calendar.getInstance(TimeZone.getDefault());
		int day_offset = getDayOffset(day_Of_Week);
		
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
			String nowStr = sdf.format(calendar.getTime());
			Date nowDate = sdf.parse(nowStr);
			cal.setTime(nowDate);
			if(day_offset == 8){
				cal.add(Calendar.DATE, 7);
 	 			cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
			}
			else{
				cal.set(Calendar.DAY_OF_WEEK, day_offset);
			}
			
			return cal;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	

	public static Calendar getNextWeekDayByCalendar(Calendar calendar,String day_Of_Week) {
        int day_offset = getDayOffset(day_Of_Week);
        Calendar cal = Calendar.getInstance(TimeZone.getDefault());
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
			String nowStr = sdf.format(calendar.getTime());
			Date nowDate = sdf.parse(nowStr);
			cal.setTime(nowDate);
			if(day_offset == 8){
				cal.add(Calendar.DATE, 14);
				cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
			}
			else{
				cal.add(Calendar.DATE, 7);
				cal.set(Calendar.DAY_OF_WEEK, day_offset);
			}
			
			return cal;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		
	}
	
	public static Calendar getDateIncrementByincrement(Calendar calendar, int increment) throws Exception {
		Calendar nowCal = Calendar.getInstance(TimeZone.getDefault());
		nowCal.setTime(calendar.getTime());
		nowCal.add(Calendar.DAY_OF_MONTH, increment);
		return nowCal;
	}
    public static Date addDays(Date date, int days)
    {
        Calendar nowCal = Calendar.getInstance(TimeZone.getDefault());
        try {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		String nowStr = sdf.format(nowCal.getTime());
		Date nowDate = sdf.parse(nowStr);
		nowCal.setTime(nowDate);
		nowCal.add(Calendar.DATE, days);
		//nowCal.setTimeZone(Constants.TIMEZONE_CENTRAL);
        return nowCal.getTime();
        } catch (Exception e) {
			e.printStackTrace();
		}
        return null;
    }
	 
}
