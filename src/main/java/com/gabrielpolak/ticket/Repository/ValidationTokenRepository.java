package com.gabrielpolak.ticket.Repository;

import com.gabrielpolak.ticket.Model.DAO.Reservation;
import com.gabrielpolak.ticket.Model.DAO.ValidationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ValidationTokenRepository extends JpaRepository<ValidationToken, Long> {
    Optional<ValidationToken> findByToken(String token);

    Optional<ValidationToken> findByReservation(Reservation reservation);
}
