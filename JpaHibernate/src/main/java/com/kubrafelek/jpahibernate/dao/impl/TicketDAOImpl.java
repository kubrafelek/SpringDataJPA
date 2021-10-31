package com.kubrafelek.jpahibernate.dao.impl;

import com.kubrafelek.jpahibernate.dao.TicketDAO;
import com.kubrafelek.jpahibernate.entity.Ticket;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Transactional
@Repository
public class TicketDAOImpl implements TicketDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void addTicket(Ticket ticket) {
        entityManager.persist(ticket);
    }

    @Override
    public Ticket getTicketById(int ticketId) {
        return entityManager.find(Ticket.class, ticketId);
    }

    @Override
    public boolean removeTickets(List<Ticket> tickets) {
        return false;
    }

    @Override
    public boolean removeTicket(Ticket ticket) {
        return false;
    }
}
