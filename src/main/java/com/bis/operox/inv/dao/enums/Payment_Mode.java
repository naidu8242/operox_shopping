package com.bis.operox.inv.dao.enums;

public enum Payment_Mode {
	
	CARD("card"),
	CASH("cash"),
	GIFTCARD("giftcard"),
	COUPON("coupon");
	
	
    private String value;
	private Payment_Mode(String value){
		this.value = value;
	}
	public String getValue() {
		return value;
	}
	public static Payment_Mode getNameByValue(String status){
	   	  for(Payment_Mode payment_mode : Payment_Mode.values()){
	   	    if(status == payment_mode.value) 
	   	    	return payment_mode;
	   	  }
	   	  return null;
	}
}
