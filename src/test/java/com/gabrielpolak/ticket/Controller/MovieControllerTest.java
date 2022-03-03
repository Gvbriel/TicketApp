package com.gabrielpolak.ticket.Controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class MovieControllerTest {
    @Autowired
    private MockMvc mvc;
    @Autowired
    private MovieController movieController;

    String url = "/api/v1/movies";

    @Test
    void getAllMoviesShouldReturn200() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get(url)).andExpect(status().isOk()).andReturn();
    }

}
