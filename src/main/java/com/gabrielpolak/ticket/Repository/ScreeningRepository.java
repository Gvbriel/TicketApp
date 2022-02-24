package com.gabrielpolak.ticket.Repository;

import com.gabrielpolak.ticket.Model.DAO.Screening;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScreeningRepository extends JpaRepository<Screening, Long> {

}
