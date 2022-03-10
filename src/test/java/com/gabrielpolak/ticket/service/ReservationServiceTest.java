package com.gabrielpolak.ticket.service;


import com.gabrielpolak.ticket.model.DAO.Movie;
import com.gabrielpolak.ticket.model.DAO.Reservation;
import com.gabrielpolak.ticket.model.DAO.Room;
import com.gabrielpolak.ticket.model.DAO.Screening;
import com.gabrielpolak.ticket.model.DTO.UserDTO;
import com.gabrielpolak.ticket.model.request.TicketRequest;
import com.gabrielpolak.ticket.repository.ReservationRepository;
import com.gabrielpolak.ticket.repository.ScreeningRepository;
import com.gabrielpolak.ticket.TicketType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class ReservationServiceTest {

    @Autowired
    private ReservationService reservationService;
    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private ScreeningRepository screeningRepository;

    @Test
    void creatingNewReservationShouldReturnTrue() {

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

        Reservation reservation = reservationService.createReservation(screening.getId(), ticketRequestList, userDTO);

        assertThat(reservationRepository.findById(reservation.getId()).isPresent()).isTrue();
    }

}
