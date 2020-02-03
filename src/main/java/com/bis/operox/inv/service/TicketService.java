package com.bis.operox.inv.service;

import java.util.List;

import org.json.JSONObject;
import org.springframework.web.multipart.MultipartFile;

import com.bis.operox.inv.dao.entity.Ticket;
import com.bis.operox.inv.dao.entity.User;

public interface TicketService {

	
	Ticket addTicket(Ticket ticket);
	
	Ticket getTicketById(Long id);

	List<Ticket> ticketsList();
	
	void addTicketDetails(JSONObject jsonObj, User user, MultipartFile multiFile) throws Exception;


}
