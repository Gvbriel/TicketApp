package com.gabrielpolak.ticket.repository;

import com.gabrielpolak.ticket.model.DAO.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

}
