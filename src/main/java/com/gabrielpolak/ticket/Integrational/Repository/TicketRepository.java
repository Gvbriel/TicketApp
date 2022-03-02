package com.gabrielpolak.ticket.Integrational.Repository;

import com.gabrielpolak.ticket.Model.DAO.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

}
