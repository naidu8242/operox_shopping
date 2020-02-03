package com.bis.operox.util;

import java.io.Serializable;

public class SessionMaintainanceData implements  Serializable{

	private static final long serialVersionUID = 1069912622144070540L;

	private String nav;
	
	private String subNav;

	public String getNav() {
		return nav;
	}

	public void setNav(String nav) {
		this.nav = nav;
	}

	public String getSubNav() {
		return subNav;
	}

	public void setSubNav(String subNav) {
		this.subNav = subNav;
	}

	
}
