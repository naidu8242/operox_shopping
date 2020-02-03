package com.bis.operox.inv.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bis.operox.constants.Constants;
import com.bis.operox.inv.dao.TicketDao;
import com.bis.operox.inv.dao.entity.Ticket;

@Repository
public class TicketDaoImpl implements TicketDao{

	@Autowired
	private SessionFactory sessionFactory;
	
	private static final Logger logger = LoggerFactory.getLogger(TicketDaoImpl.class);

	@Override
	@Transactional(rollbackFor = { Exception.class })	
	public Ticket addTicket(Ticket ticket) {
		Session session = this.sessionFactory.getCurrentSession();
		session.saveOrUpdate(ticket);
		logger.info("Ticket saved successfully, Ticket Details="+ticket);
		return ticket;
	}

	@Override
	@Transactional(rollbackFor = { Exception.class })	
	public Ticket getTicketById(Long id) {
        Session session = this.sessionFactory.getCurrentSession();	
        Ticket ticket = (Ticket) session.load(Ticket.class, new Long(id));
		logger.info("Ticket loaded successfully, Ticket details="+ticket);
	    return ticket;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(rollbackFor = { Exception.class })	
	public List<Ticket> ticketsList() {
		Session session = this.sessionFactory.getCurrentSession();
		return session.createQuery("from Ticket ticket where ticket.status = "+Constants.RECORD_ACTIVE+"" ).list();
	}
}