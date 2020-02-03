package com.bis.operox.util;

import java.io.Serializable;

import com.bis.operox.inv.dao.entity.EmailMessage;


/**
 * This class for build email template
 * @author Prasad salina
 *
 */
public class EmailTemplateHTMLConversionUtil implements Serializable {
	
	private static final long serialVersionUID = -3317550653234933840L;
	
	public static String convertBodyOfEmailTemplateToHTML(String body , String signature ,String callToActionText , String ButtonText , String url, EmailMessage emailMessage) {	
		 
		StringBuilder tempHTML = new StringBuilder();
		/*
		 * need to replace image location
		 * src='"+url+"/resources/images/mail-logo.png'
		 * 
		 * src='http://vannamnew.xp3.biz/html/images/mail-logo.png'
		 */
		
		
		tempHTML.append("<table width='100%' bgcolor='#F9F9F9' cellpadding='0' cellspacing='0' style='background-color:#F9F9F9;'>");
				tempHTML.append("<tr>");
						tempHTML.append("<td>");
								tempHTML.append("<table width='100%' align='center' cellpadding='0' cellspacing='0' style='padding:10px 0px 10px 0px; background-color:#10a0e2;'>");
										tempHTML.append("<tr>");
												tempHTML.append("<td>");
												  tempHTML.append("<table width='600' align='center'>");
												      tempHTML.append("<tr>");
												           tempHTML.append("<td><img src='"+url+"/resources/images/logo-operox.png' width='300px' height='71px'></td>");
										             tempHTML.append("</tr>");
								                 tempHTML.append("</table>");
						                      tempHTML.append("</td>");
				                         tempHTML.append("</tr>");
		                          tempHTML.append("</table> ");
		
		tempHTML.append("<table width='600'  bgcolor='#F9F9F9' align='center' style='background-color:#F9F9F9; margin:0px auto 12px auto; padding:25px 0px 10px 20px'  cellpadding='0' cellspacing='0'>");
				tempHTML.append("<tr>");
						tempHTML.append("<td>");
					  	      tempHTML.append("<table width='600' style='margin:38px auto; font-family:'Segoe UI'; align='center' cellpadding='0' cellspacing='0' >");
						           tempHTML.append("<tr>");
						                 tempHTML.append("<td>");
						                         tempHTML.append("<div style='font-size:14px; color:#333333;'>");
		                                         tempHTML.append(body);
		                                         tempHTML.append(" </div>");
		                                         
		                                         if(!isNullOrEmpty(signature)){
									                    tempHTML.append("<div style='font-size:14px; color:#333333; '><br/><br/>");
														tempHTML.append(signature);
													    tempHTML.append(" </div>");		
												 }
					                            								
					                     tempHTML.append("</td>");
					          tempHTML.append("</tr>");
					      tempHTML.append("</table>");
					  tempHTML.append(" </td>");
			    tempHTML.append("</tr>");
		tempHTML.append("</table>");
		
		tempHTML.append("<table width='100%' bgcolor='#ddd' align='center' cellpadding='0' cellspacing='0' style='padding:10px 0px 10px 0px; background-color:#ddd; font-family:'Segoe UI';'>");
			  tempHTML.append("<tr>");
					  tempHTML.append("<td>");
					       tempHTML.append("<table bgcolor='#ddd'  width='600' align='center' style='background-color:#ddd; padding:15px 0px;'>");
					           tempHTML.append("<tr>");
					                 tempHTML.append("<td>");
					                   tempHTML.append("<p style='color:#214b90; text-transform: uppercase; padding:5px 0px; margin:0px 0px; font-size:25px; font-weight:700;'>get started!</p>");
					                   tempHTML.append("<p style='padding:0px 0px 15px 0px; margin:0px 0px; font-weight:600;'>For more information about Operox, go to <a target='blank' href='http://www.operox.com/' style='text-decoration:none; color:#fff; display:inline-block;background-color:#e74c51; padding:6px 25px 8px; border-radius:15px; margin:0px 0px 0px 20px;'>operox.com</a></p>");
					                 tempHTML.append("</td>");
					            tempHTML.append("</tr>");
					         
					            tempHTML.append("<tr>");
					                  tempHTML.append("<td>");
					                       tempHTML.append("<p style='text-align:center; padding:10px 0px; margin:0px 0px; font-size:13px; font-weight:600; border-top:1px solid #333;'>");
					                       tempHTML.append("<a target='blank' href='http://www.operox.com/' style='text-decoration:none; color:#333;'>About Operox</a> | <a target='blank' href='http://www.operox.com/' style='text-decoration:none; color:#333;'>Contact Us</a> | <a target='blank' href='http://www.operox.com/' style='text-decoration:none; color:#333;'>Legal Notices</a> | <a target='blank' href='http://www.operox.com/' style='text-decoration:none; color:#333;'>Terms of Use</a> | <a target='blank' href='http://www.operox.com/' style='text-decoration:none; color:#333;'>Your Privacy Rights</a></p>");
					                  tempHTML.append("</td>");
					            tempHTML.append("</tr>");
					       tempHTML.append("</table>");
					 tempHTML.append("</td>");
			    tempHTML.append("</tr>");
        tempHTML.append("</table>"); 
        
	tempHTML.append("</td>");
 tempHTML.append("</tr>");
tempHTML.append("</table>");

		 return tempHTML.toString();
	}
	
	protected static boolean isNullOrEmpty(String str) {
		return (str == null || "".equals(str.trim()));
	}
	
}
