package com.bis.operox.inv.dao.enums;

public enum Submission_Status {
	
	DRAFT("draft"),
	SAVE("save");
	
    private String value;
	private Submission_Status(String value){
		this.value = value;
	}
	public String getValue() {
		return value;
	}
	public static Submission_Status getNameByValue(String status){
	   	  for(Submission_Status bill_Status : Submission_Status.values()){
	   	    if(status == bill_Status.value) 
	   	    	return bill_Status;
	   	  }
	   	  return null;
	}
}
