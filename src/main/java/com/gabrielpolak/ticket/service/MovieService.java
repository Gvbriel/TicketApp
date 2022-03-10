package com.gabrielpolak.ticket.service;

import com.gabrielpolak.ticket.model.DAO.Movie;
import com.gabrielpolak.ticket.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<Movie> getMovies() {
        return movieRepository.findAll();
    }
}
