package com.bis.operox.inv.dao;

import java.util.List;

import com.bis.operox.inv.dao.entity.Ticket;

public interface TicketDao {
	

	Ticket addTicket(Ticket ticket);
	
	Ticket getTicketById(Long id);

	List<Ticket> ticketsList();
}