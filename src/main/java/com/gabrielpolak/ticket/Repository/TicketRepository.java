package com.gabrielpolak.ticket.Repository;

import com.gabrielpolak.ticket.Model.DAO.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

}
