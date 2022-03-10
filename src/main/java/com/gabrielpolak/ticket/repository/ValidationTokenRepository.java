package com.gabrielpolak.ticket.repository;

import com.gabrielpolak.ticket.model.DAO.Reservation;
import com.gabrielpolak.ticket.model.DAO.ValidationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ValidationTokenRepository extends JpaRepository<ValidationToken, Long> {
    Optional<ValidationToken> findByToken(String token);

    Optional<ValidationToken> findByReservation(Reservation reservation);
}
