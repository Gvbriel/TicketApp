package com.gabrielpolak.ticket.Integrational.Controller;

import com.gabrielpolak.ticket.Model.DAO.*;
import com.gabrielpolak.ticket.Model.DTO.UserDTO;
import com.gabrielpolak.ticket.Model.Request.TicketRequest;
import com.gabrielpolak.ticket.Integrational.Repository.ReservationRepository;
import com.gabrielpolak.ticket.Integrational.Repository.ScreeningRepository;
import com.gabrielpolak.ticket.Integrational.Service.ReservationService;
import com.gabrielpolak.ticket.Integrational.Service.ScreeningService;
import com.gabrielpolak.ticket.TicketType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class ReservationControllerTest {

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


    @Test
    void creatingNewReservationShouldReturnTrue(){

        Screening screening = Screening.createScreeningWithRoom(Movie.createMovieWithTitle("Siema"), ZonedDateTime.now().plusDays(1),Room.createRoom());
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
