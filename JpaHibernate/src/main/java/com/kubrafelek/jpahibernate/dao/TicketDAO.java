package com.kubrafelek.jpahibernate.dao;

import com.kubrafelek.jpahibernate.entity.Ticket;

import java.util.List;

public interface TicketDAO {

    void addTicket(Ticket ticket);

    Ticket getTicketById(int ticketId);

   // List<TicketStatsByStatusDTO> findTicketStats();

    boolean removeTickets(List<Ticket> tickets);

    boolean removeTicket(Ticket ticket);

}