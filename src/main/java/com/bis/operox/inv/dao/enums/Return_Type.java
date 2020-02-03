package com.bis.operox.inv.dao.enums;

public enum Return_Type {
	
	RETURN("return"),
	TRANSFER("transfer");
	
    private String value;
	private Return_Type(String value){
		this.value = value;
	}
	public String getValue() {
		return value;
	}
	public static Return_Type getNameByValue(String status){
	   	  for(Return_Type bill_Status : Return_Type.values()){
	   	    if(status == bill_Status.value) 
	   	    	return bill_Status;
	   	  }
	   	  return null;
	}
}
