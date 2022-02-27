package com.gabrielpolak.ticket.Repository;

import com.gabrielpolak.ticket.Model.DAO.Screening;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;

@Repository
public interface ScreeningRepository extends JpaRepository<Screening, Long> {
    List<Screening> findScreeningsByDateAfter(LocalDateTime dateTime);
    List<Screening> findScreeningsByDateEquals(LocalDateTime dateTime);
    List<Screening> findAllByDateEquals(LocalDateTime dateTime);

    List<Screening> findAllByDateBetween(LocalDateTime from, LocalDateTime to);
}
