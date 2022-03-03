package com.gabrielpolak.ticket.Integrational;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.gabrielpolak.ticket.Model.DAO.Movie;
import com.gabrielpolak.ticket.Model.DAO.Room;
import com.gabrielpolak.ticket.Model.DAO.Screening;
import com.gabrielpolak.ticket.Repository.ScreeningRepository;
import com.gabrielpolak.ticket.Service.ScreeningService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ScreeningControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ScreeningService screeningService;

    @Autowired
    private ScreeningRepository screeningRepository;

    String url = "/api/v1/screenings";

    static ObjectMapper mapper = new ObjectMapper();

    @BeforeAll
    static void setUp(){
        mapper.registerModule(new JavaTimeModule());
    }

    @Test
    void getScreeningsShouldReturn200() throws Exception {
        //given
        Screening screening = Screening.createScreeningWithRoom(Movie.createMovieWithTitle("Interstellar"), ZonedDateTime.now().plusMinutes(240), Room.createRoom());
        screeningRepository.save(screening);

        List<Screening> screeningList = new ArrayList<>();
        screeningList.add(screening);

        //when
        mvc.perform(MockMvcRequestBuilders.get(url).contentType(MediaType.APPLICATION_JSON))
                //then
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(mapper.writer().writeValueAsString(screeningList)))
//                .andExpect(jsonPath("$[0].id").value(mapper.writer().writeValueAsString(screening.getId())))
                .andReturn();
    }

    @Test
    void getScreeningsWithZonedDateTimeShouldReturn200() throws Exception{
        mvc.perform(MockMvcRequestBuilders.get(url)
                .contentType(MediaType.APPLICATION_JSON)
                .param("date",ZonedDateTime.now().toString()))
                .andExpect(status().isOk()).andReturn();
    }

    @Test
    void getScreeningsBetweenShouldReturn200() throws Exception{
        mvc.perform(MockMvcRequestBuilders.get(url+"/between")
                .contentType(MediaType.APPLICATION_JSON)
                .param("from", ZonedDateTime.now().minusDays(2).toString())
                .param("to", ZonedDateTime.now().plusDays(2).toString()))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void getScreeningsBetweenWithOneArgumentShouldReturn400() throws Exception{
        mvc.perform(MockMvcRequestBuilders.get(url+"/between")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("to", ZonedDateTime.now().plusDays(2).toString()))
                .andExpect(status().is4xxClientError())
                .andReturn();
    }
}
