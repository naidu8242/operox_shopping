package com.bis.operox.util;

import javax.servlet.http.HttpServletRequest;

public class OperoxSessionManager {
	

	public static void setSessionAttribute(HttpServletRequest request, String attribute, Object obj) {
		request.getSession().setAttribute("OperoxWeb_"+attribute, obj);
	}
	
	public static Object getSessionAttribute(HttpServletRequest request, String attribute) {
		return request.getSession().getAttribute("OperoxWeb_"+attribute);
	}

	public static void removeSessionAttribute(HttpServletRequest request, String attribute) {
		request.getSession().removeAttribute("OperoxWeb_"+attribute);
	}


}
