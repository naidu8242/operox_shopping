package com.bis.operox.inv.dao.entity;

public class EmailAttachment {
	
	private String name;
	
	private String type;
	
	private String content;
	
	private Boolean binary;

	public String getName() {
		return name;
	}

	public String getType() {
		return type;
	}

	public String getContent() {
		return content;
	}

	public Boolean getBinary() {
		return binary;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setBinary(Boolean binary) {
		this.binary = binary;
	}

}
