package com.gabrielpolak.ticket.Repository;

import com.gabrielpolak.ticket.Model.DAO.Screening;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ScreeningRepository extends JpaRepository<Screening, Long> {

    List<Screening> findByOrderByDateAscMovietitleAsc();
    List<Screening> findScreeningsByDateAfterOrderByDateAscMovietitleAsc(LocalDateTime dateTime);
    List<Screening> findAllByDateBetweenOrderByDateAscMovietitleAsc(LocalDateTime from, LocalDateTime to);
}
