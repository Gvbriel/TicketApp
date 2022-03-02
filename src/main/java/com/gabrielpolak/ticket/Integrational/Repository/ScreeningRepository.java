package com.gabrielpolak.ticket.Integrational.Repository;

import com.gabrielpolak.ticket.Model.DAO.Screening;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;
import java.util.List;

@Repository
public interface ScreeningRepository extends JpaRepository<Screening, Long> {

    List<Screening> findByOrderByDateAscMovietitleAsc();
    List<Screening> findScreeningsByDateAfterOrderByDateAscMovietitleAsc(ZonedDateTime dateTime);
    List<Screening> findAllByDateBetweenOrderByDateAscMovietitleAsc(ZonedDateTime from, ZonedDateTime to);
}
