package com.gabrielpolak.ticket.Controller;

import com.gabrielpolak.ticket.Model.DAO.*;
import com.gabrielpolak.ticket.Model.DTO.UserDTO;
import com.gabrielpolak.ticket.Model.Request.TicketRequest;
import com.gabrielpolak.ticket.Repository.ScreeningRepository;
import com.gabrielpolak.ticket.Repository.ValidationTokenRepository;
import com.gabrielpolak.ticket.Service.ReservationService;
import com.gabrielpolak.ticket.TicketType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ValidationTokenControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ValidationTokenController validationTokenController;

    @Autowired
    private ValidationTokenRepository validationTokenRepository;

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private ScreeningRepository screeningRepository;

    String url = "/api/v1/reservations/confirm";

    private Reservation getReservation() {
        Screening screening = Screening.createScreeningWithRoom(Movie.createMovieWithTitle("Siema"), ZonedDateTime.now().plusDays(1), Room.createRoom());
        screeningRepository.save(screening);

        List<TicketRequest> ticketRequestList = new ArrayList<>();
        ticketRequestList.add(TicketRequest.createNewRequest(TicketType.Adult, 2));
        ticketRequestList.add(TicketRequest.createNewRequest(TicketType.Child, 3));
        ticketRequestList.add(TicketRequest.createNewRequest(TicketType.Student, 4));

        UserDTO userDTO = new UserDTO();
        userDTO.setEmail("gabrielpolak@gmail.com");
        userDTO.setName("Gabriel");
        userDTO.setSurname("Polak-Jablonski");

        return reservationService.createReservation(screening.getId(), ticketRequestList, userDTO);
    }

    @BeforeEach
    void clear() {
        validationTokenRepository.deleteAll();
    }

    @Test
    void shouldReturnTrueAsTokenIsValidated() throws Exception {
        //given
        Reservation reservation = getReservation();
        ValidationToken token = validationTokenRepository.findByReservation(reservation).get();

        //when
        mvc.perform(MockMvcRequestBuilders.get(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("token", token.getToken()))
                //then
                .andExpect(status().isOk())
                .andExpect(jsonPath("confirmed").value(true))
                .andReturn();
    }

    @Test
    void shouldReturn422AsTokenNotFound() throws Exception {
        //given
        //stworz rezerwacje i token
        Reservation reservation = getReservation();
        ValidationToken token = validationTokenRepository.findByReservation(reservation).get();

        //when
        mvc.perform(MockMvcRequestBuilders.get(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("token", token.getToken() + "s"))
                //then
                .andExpect(status().is4xxClientError())
                .andReturn();
    }

}