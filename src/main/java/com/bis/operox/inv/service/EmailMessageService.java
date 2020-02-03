package com.bis.operox.inv.service;

import java.util.ArrayList;
import java.util.List;

import com.bis.operox.inv.dao.entity.EmailAttachment;
import com.bis.operox.inv.dao.entity.EmailMessage;
import com.bis.operox.inv.dao.entity.EmailRecipient;

/**
 * 
 * @author Laxman
 *
 */
public interface EmailMessageService {
	
	String sendEmail(EmailMessage emailMessage, ArrayList<EmailRecipient> emailRecipientList, List<EmailAttachment> attachmentList) throws Exception;

}
