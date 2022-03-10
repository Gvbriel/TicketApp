package com.gabrielpolak.ticket.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gabrielpolak.ticket.model.DAO.Movie;
import com.gabrielpolak.ticket.model.DAO.Room;
import com.gabrielpolak.ticket.model.DAO.Screening;
import com.gabrielpolak.ticket.model.DTO.ReservationDTO;
import com.gabrielpolak.ticket.model.DTO.UserDTO;
import com.gabrielpolak.ticket.model.request.TicketRequest;
import com.gabrielpolak.ticket.repository.ScreeningRepository;
import com.gabrielpolak.ticket.TicketType;
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
public class ReservationControllerTest {

    @Autowired
    private MockMvc mvc;
    @Autowired
    private ScreeningRepository screeningRepository;

    ObjectMapper mapper = new ObjectMapper();

    String url = "/api/v1/reservations";


    @Test
    void creatingNewReservationShouldReturn422AsNotEnoughTickets() throws Exception {

        Screening screening = Screening.createScreeningWithRoom(Movie.createMovieWithTitle("Siema"), ZonedDateTime.now().plusDays(1), Room.createRoom());
        screeningRepository.save(screening);

        ReservationDTO reservationDTO = getReservationDTO(20, "gabrielpolak@gmail.com", "Gabriel", "Polak-Jablonski", screening);


        mvc.perform(MockMvcRequestBuilders.post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(reservationDTO))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError())
                .andReturn();
    }

    @Test
    void creatingNewReservationShouldReturn422AsTooLateToMakeReservation() throws Exception {

        Screening screening = Screening.createScreeningWithRoom(Movie.createMovieWithTitle("Siema"), ZonedDateTime.now().plusMinutes(1), Room.createRoom());
        screeningRepository.save(screening);

        ReservationDTO reservationDTO = getReservationDTO(2, "gabrielpolak@gmail.com", "Gabriel", "Polak-Jablonski", screening);

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

        ReservationDTO reservationDTO = getReservationDTO(2, "gabrielpolak@gmail.com", "Gabriel", "Polak-Jablonski", screening);

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

    @Test
    void creatingNewReservationShouldCalculateRightTicketPrice() throws Exception {

        Screening screening = Screening.createScreeningWithRoom(Movie.createMovieWithTitle("Siema"), ZonedDateTime.now().plusDays(1), Room.createRoom());
        screeningRepository.save(screening);

        ReservationDTO reservationDTO = getReservationDTO(2, "gabrielpolak@gmail.com", "Gabriel", "Polak-Jablonski", screening);

        ObjectMapper mapper = new ObjectMapper();

        mvc.perform(MockMvcRequestBuilders.post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(reservationDTO))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.ticketsPrice").value(159))
                .andReturn();
    }

    @Test
    void creatingNewReservationShouldReturn400WrongUserData() throws Exception {

        Screening screening = Screening.createScreeningWithRoom(Movie.createMovieWithTitle("Siema"), ZonedDateTime.now().plusDays(1), Room.createRoom());
        screeningRepository.save(screening);

        ReservationDTO reservationDTO = getReservationDTO(2, "gabr@gmail.com", "Ga", "P", screening);

        ObjectMapper mapper = new ObjectMapper();

        mvc.perform(MockMvcRequestBuilders.post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(reservationDTO))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError())
                .andReturn();
    }

    private ReservationDTO getReservationDTO(int amount, String email, String name, String surname, Screening screening) {
        List<TicketRequest> ticketRequestList = new ArrayList<>();
        ticketRequestList.add(TicketRequest.createNewRequest(TicketType.Adult, amount));
        ticketRequestList.add(TicketRequest.createNewRequest(TicketType.Child, 3));
        ticketRequestList.add(TicketRequest.createNewRequest(TicketType.Student, 4));

        UserDTO userDTO = new UserDTO();
        userDTO.setEmail(email);
        userDTO.setName(name);
        userDTO.setSurname(surname);

        ReservationDTO reservationDTO = new ReservationDTO();
        reservationDTO.setTickets(ticketRequestList);
        reservationDTO.setScreeningId(screening.getId());
        reservationDTO.setUser(userDTO);
        return reservationDTO;
    }
}
