package com.gabrielpolak.ticket.Integrational.Service;

import com.gabrielpolak.ticket.Model.DAO.Movie;
import com.gabrielpolak.ticket.Integrational.Repository.MovieRepository;
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
