package com.gabrielpolak.ticket.Integrational;

import com.gabrielpolak.ticket.Controller.ScreeningController;
import com.gabrielpolak.ticket.Service.ScreeningService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.ZonedDateTime;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ScreeningControllerTest {

    @Autowired
    private MockMvc mvc;

    String url = "/api/v1/screenings";

    @Test
    void getScreeningsShouldReturn200() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get(url))
                .andExpect(status().isOk())
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
