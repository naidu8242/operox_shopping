package com.bis.operox.inv.dao.enums;

public enum Stock_Status {
	
	PENDING("pending"),
	COMPLETE("complete");
	
    private String value;
	private Stock_Status(String value){
		this.value = value;
	}
	public String getValue() {
		return value;
	}
	public static Stock_Status getNameByValue(String status){
	   	  for(Stock_Status bill_Status : Stock_Status.values()){
	   	    if(status == bill_Status.value) 
	   	    	return bill_Status;
	   	  }
	   	  return null;
	}
}
