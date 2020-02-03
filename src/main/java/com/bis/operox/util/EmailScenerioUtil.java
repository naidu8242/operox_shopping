package com.bis.operox.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bis.operox.inv.dao.entity.EmailAttachment;
import com.bis.operox.inv.dao.entity.EmailMessage;
import com.bis.operox.inv.dao.entity.EmailRecipient;
import com.bis.operox.inv.dao.entity.EmailScenario;
import com.bis.operox.inv.dao.entity.Notifications;
import com.bis.operox.inv.dao.entity.User;
import com.bis.operox.inv.service.EmailMessageService;
import com.bis.operox.inv.service.EmailScenarioService;
import com.bis.operox.inv.service.NotificationsService;

import com.bis.operox.constants.Constants;

/**
 * This class is for covert subject,body,signature
 * @author Laxman
 *
 */
@Component
public class EmailScenerioUtil {
	
	@Autowired
	EmailMessageBodyConverter emailMessageBodyConverter;
	
	@Autowired
	 EmailScenarioService emailScenarioService;
	
	@Autowired
	EmailMessageService emailMessageService;
	
	@Autowired
	NotificationsService notificationsService;
  
	public String bulidSubject(String subject,Map<String, String> globalVarsMap) throws Exception{
		
		subject = emailMessageBodyConverter.convertBodyOfEmailTemplate(subject,globalVarsMap);
		return subject ;
	}
	
	public String buildBody(String body,Map<String, String> globalVarsMap) throws Exception{
		body = body.replaceAll("\n", "<br/>");
		body = body.replaceAll(" ", "&nbsp;");
		body = emailMessageBodyConverter.convertBodyOfEmailTemplate(body,globalVarsMap);
		return body ;
	}
	
	public String buildTempBody(String body,Map<String, String> globalVarsMap) throws Exception{
		body = body.replaceAll("\n", "<br/>");
		body = body.replaceAll(" ", "&nbsp;");
		body = emailMessageBodyConverter.convertTempBodyOfEmailTemplate(body,globalVarsMap);
		return body ;
	}
	
	public String buildSignature(String signature,Map<String, String> globalVarsMap) throws Exception{
		signature = signature.replaceAll("\n", "<br/>");
		signature = signature.replaceAll(" ", "&nbsp;");
		signature = emailMessageBodyConverter.convertBodyOfEmailTemplate(signature,globalVarsMap);
		return signature ;
	}

	public String AssembleEmailMessage(EmailScenario emailScenario, EmailMessage emailMessage,String subject , String body, String signature, String url){
		String emailTemplate = EmailTemplateHTMLConversionUtil.convertBodyOfEmailTemplateToHTML(body, signature,emailScenario.getCallToActionText(), emailScenario.getButtonText(), url , emailMessage);
		return emailTemplate ;
	} 
	
	public void sendEmail(EmailMessage emailMessage, ArrayList<EmailRecipient> emailRecipientList,List<EmailAttachment> attachmentList) throws Exception{
			emailMessageService.sendEmail(emailMessage,emailRecipientList,attachmentList);
		}
	
	
public EmailScenario validateEmailscenerioExists(String uniqueScenarioName,String entityType,String orgCode,String locationCode , EmailMessage message , String userCode) throws Exception{
	
	EmailScenario scenarioTemplates = emailScenarioService.getEmailScenarioByUniqueNameAndEntityTypeAndOrgCodeAndLocationsCode(uniqueScenarioName, entityType, orgCode, locationCode);
	    
		EmailScenario  emailScenario = emailScenarioService.getEmailScenarioByUniqueName(uniqueScenarioName, entityType);
		
		if(scenarioTemplates == null){
			
			EmailScenario scenario = new EmailScenario();
			
			scenario.setScenarioName(emailScenario.getScenarioName());
			scenario.setUniqueScenarioName(emailScenario.getUniqueScenarioName());
			scenario.setDescription(emailScenario.getDescription());
			scenario.setSubject(emailScenario.getSubject());
			scenario.setTemplateType("Org");
			scenario.setSignature(emailScenario.getSignature());
			scenario.setBody(emailScenario.getBody());
			scenario.setIsTemplateModified("N");
			scenario.setCallToActionText(emailScenario.getCallToActionText());
			scenario.setButtonText(emailScenario.getButtonText());
    		scenario.setLocationCode(locationCode);
    		scenario.setUseLogo("Yes");
    		scenario.setDisclaimerText(emailScenario.getDisclaimerText());
    		scenario.setCreatedDate(new Date());
    		scenario.setCreatedBy(userCode);
    		scenario.setOrgCode(orgCode);
			
    		emailScenarioService.save(scenario);
			
			scenarioTemplates = scenario;
		}
		
		return scenarioTemplates;
	}


public void sendNotification(String actionLink,User loginUser,User toUser, EmailScenario emailScenario) throws Exception{
	
	//new Notification logic
	Notifications notification = new Notifications();
	notification.setActionLink(actionLink);
	notification.setBody(emailScenario.getBody());
	notification.setNotificationFrom(loginUser);
	notification.setNotificationTo(toUser);
	notification.setNotificationScenarioId(emailScenario);
	notification.setMode(Constants.NOTIFICATION_UNREAD);
	notification.setCreatedBy(loginUser.getUserCode());
	notification.setOrgCode("operox");
	notification.setLocationCode("operoxIN");
	notification.setCreatedDate(new Date());
	notification.setUserNotificationStatus("Sent");
	notification.setNotificationStatus(Constants.NOTIFICATION_NOT_DELETED);

	Notifications notification1 = notificationsService.saveNotification(notification);
	
	}


}