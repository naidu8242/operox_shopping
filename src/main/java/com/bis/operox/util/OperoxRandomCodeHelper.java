package com.bis.operox.util;

import org.apache.commons.lang.RandomStringUtils;

public class OperoxRandomCodeHelper {

	public static String generateRandomUserCode() {
		String userCode = RandomStringUtils.randomAlphanumeric(8);
		return userCode;
	}
	
	public static String generateRandomOrgCode() {
		String orgCode = RandomStringUtils.randomAlphanumeric(4);
		return orgCode;
	}
	
	public static String generateRandomVerificationCode() {
		String verificatinCode = RandomStringUtils.randomAlphabetic(5);
		return verificatinCode;
	}
	
	public static String generateRandomPassword() {
		String password = RandomStringUtils.randomAlphabetic(6);
		return password;
	}
	
	public static String generateUUID() {
		String UUID = RandomStringUtils.randomAlphabetic(15);
		return UUID;
	}
}
