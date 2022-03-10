package com.gabrielpolak.ticket.controller;

import com.gabrielpolak.ticket.model.DAO.Movie;
import com.gabrielpolak.ticket.repository.MovieRepository;
import com.gabrielpolak.ticket.repository.ScreeningRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class MovieControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private MovieController movieController;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private ScreeningRepository screeningRepository;

    String url = "/api/v1/movies";

    @BeforeEach
    void clear() {
        screeningRepository.deleteAll();
        movieRepository.deleteAll();
    }

    @Test
    void getAllMoviesShouldReturn200() throws Exception {
        Movie movie = Movie.createMovieWithTitle("Star wars");
        movieRepository.save(movie);

        mvc.perform(MockMvcRequestBuilders.get(url))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value(movie.getTitle()))
                .andReturn();
    }

}
