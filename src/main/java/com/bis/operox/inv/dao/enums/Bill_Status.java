package com.bis.operox.inv.dao.enums;

public enum Bill_Status {
	
	NEW("new"),
	ONHOLD("hold"),
	PAID("paid"),
	INPROGRESS("inprogress");
	
    private String value;
	private Bill_Status(String value){
		this.value = value;
	}
	public String getValue() {
		return value;
	}
	public static Bill_Status getNameByValue(String status){
	   	  for(Bill_Status bill_Status : Bill_Status.values()){
	   	    if(status == bill_Status.value) 
	   	    	return bill_Status;
	   	  }
	   	  return null;
	}
}
