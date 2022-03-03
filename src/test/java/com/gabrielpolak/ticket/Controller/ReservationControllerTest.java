package com.gabrielpolak.ticket.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gabrielpolak.ticket.Model.DAO.Movie;
import com.gabrielpolak.ticket.Model.DAO.Room;
import com.gabrielpolak.ticket.Model.DAO.Screening;
import com.gabrielpolak.ticket.Model.DTO.ReservationDTO;
import com.gabrielpolak.ticket.Model.DTO.UserDTO;
import com.gabrielpolak.ticket.Model.Request.TicketRequest;
import com.gabrielpolak.ticket.Repository.ReservationRepository;
import com.gabrielpolak.ticket.Repository.ScreeningRepository;
import com.gabrielpolak.ticket.Service.ReservationService;
import com.gabrielpolak.ticket.Service.ScreeningService;
import com.gabrielpolak.ticket.TicketType;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.RequestEntity.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ReservationControllerTest {

    @Autowired
    private MockMvc mvc;
    @Autowired
    private ReservationController reservationController;
    @Autowired
    private ReservationService reservationService;
    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private ScreeningService screeningService;
    @Autowired
    private ScreeningRepository screeningRepository;

    String url = "/api/v1/reservations";

    @Test
    void creatingNewReservationShouldReturn422() throws Exception {

        Screening screening = Screening.createScreeningWithRoom(Movie.createMovieWithTitle("Siema"), ZonedDateTime.now().plusDays(1), Room.createRoom());
        screeningRepository.save(screening);

        List<TicketRequest> ticketRequestList = new ArrayList<>();
        ticketRequestList.add(TicketRequest.createNewRequest(TicketType.Adult, 20));
        ticketRequestList.add(TicketRequest.createNewRequest(TicketType.Child, 3));
        ticketRequestList.add(TicketRequest.createNewRequest(TicketType.Student, 4));

        UserDTO userDTO = new UserDTO();
        userDTO.setEmail("gabrielpolak@gmail.com");
        userDTO.setName("Gabriel");
        userDTO.setSurname("Polak-Jablonski");

        ReservationDTO reservationDTO = new ReservationDTO();
        reservationDTO.setTickets(ticketRequestList);
        reservationDTO.setScreeningId(screening.getId());
        reservationDTO.setUser(userDTO);

        ObjectMapper mapper = new ObjectMapper();

        mvc.perform(MockMvcRequestBuilders.post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(reservationDTO))
                        .accept(MediaType.APPLICATION_JSON))
                        .andExpect(status().is4xxClientError())
                        .andReturn();
    }

    @Test
    void creatingNewReservationShouldReturn200() throws Exception {

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

        ReservationDTO reservationDTO = new ReservationDTO();
        reservationDTO.setTickets(ticketRequestList);
        reservationDTO.setScreeningId(screening.getId());
        reservationDTO.setUser(userDTO);

        ObjectMapper mapper = new ObjectMapper();

        mvc.perform(MockMvcRequestBuilders.post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(reservationDTO))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void getReservationsShouldReturnTrue() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get(url)).andExpect(status().isOk()).andReturn();
    }
}
