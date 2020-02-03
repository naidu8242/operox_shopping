package com.bis.operox.util;

import java.io.Writer;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import com.bis.operox.inv.service.DepartmentService;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.json.JsonHierarchicalStreamDriver;
import com.thoughtworks.xstream.io.json.JsonWriter;

@Component
public class CommonUtil {
	
	@Autowired
	private DepartmentService departmentService;
	
	public void highLightMenu(String menuName,HttpServletRequest request){
		SessionMaintainanceData sessionData = (SessionMaintainanceData)request.getSession().getAttribute("sessionData");
		if(sessionData == null)
		{
			sessionData = new SessionMaintainanceData();
		}
		sessionData.setNav(menuName);
		request.getSession().setAttribute("sessionData",sessionData);
	}
	
	
	public void highLightSubMenu(String subMenuName,HttpServletRequest request){
		SessionMaintainanceData sessionData = (SessionMaintainanceData)request.getSession().getAttribute("sessionData");
		if(sessionData == null)
		{
			sessionData = new SessionMaintainanceData();
		}
		sessionData.setSubNav(subMenuName);
		request.getSession().setAttribute("sessionData",sessionData);
	}
	

	/**
	 * Return String Date in Date format
	 * @param dateString
	 * @return
	 */
	public  Date  getDateFormat(String dateString){
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy"); 
	    Date date = null;
	    try {
	        date = df.parse(dateString);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
		return date;
	}
	
	public static Date currentDate() {
        return new Date();
    }
	
	/**
     * this method converts list to Json String
     * @param obj
     * @return
     */
        public String toJSON(Object obj){
            XStream xstream = new XStream(new JsonHierarchicalStreamDriver() {
                public HierarchicalStreamWriter createWriter(Writer writer) {
                    return new JsonWriter(writer, JsonWriter.DROP_ROOT_MODE);
                }
            });
            return xstream.toXML(obj);
        }
      /**
       * return Date to String date  
       * @param date
       * @return
       */
        public String getDateString(Date date){
        	DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        	String text = df.format(date);
			return text;
        }
       
}
