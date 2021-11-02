package com.kubrafelek.jpahibernate.dao.impl;

import com.kubrafelek.jpahibernate.dao.TicketDAO;
import com.kubrafelek.jpahibernate.entity.Ticket;
import com.kubrafelek.jpahibernate.entity.dto.TicketStatsByStatusDTO;
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
    public List<TicketStatsByStatusDTO> findTicketStats() {

        /*-- SELECT new net.javaci.dbsample.springdatajpa1.entity.dto.TicketStatsByStatusDTO
         *    ( t.status, count(t.id), min(t.createDateTime), max(t.createDateTime))
         *    FROM Ticket t GROUP BY t.status
         */

        String jpql =
                "SELECT new " + TicketStatsByStatusDTO.class.getCanonicalName()
                        + "( "
                        + "t.status, count(t.id), min(t.createDate), max(t.createDateTime) "
                        + ") "
                        + "FROM " + Ticket.class.getSimpleName() + " t " // class name; not the table name
                        + "GROUP BY t.status";

        return entityManager
                .createQuery(jpql, TicketStatsByStatusDTO.class)
                .getResultList();
    }

    @Override
    public boolean removeTickets(List<Ticket> tickets) {
        for (Ticket ticket : tickets) {
            removeTicket(ticket);
        }
        return true;
    }

    @Override
    public boolean removeTicket(Ticket ticket) {
        // aynı ticket kullanamıyoruz detach state -> manage state geçirerek tekrar entity manager yönetimine geçmesini sağlıyoruz.
        entityManager.remove(entityManager.merge(ticket));
        return true;
    }
}
