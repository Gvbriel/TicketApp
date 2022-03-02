package com.gabrielpolak.ticket.Repository;

import com.gabrielpolak.ticket.Model.DAO.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

}
