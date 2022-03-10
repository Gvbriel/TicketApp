package com.gabrielpolak.ticket.repository;

import com.gabrielpolak.ticket.model.DAO.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

}
