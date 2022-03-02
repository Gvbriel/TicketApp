package com.gabrielpolak.ticket.Integrational.Repository;

import com.gabrielpolak.ticket.Model.DAO.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}
