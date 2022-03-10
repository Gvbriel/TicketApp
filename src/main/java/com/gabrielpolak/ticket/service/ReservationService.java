package com.gabrielpolak.ticket.service;

import com.gabrielpolak.ticket.exceptions.ReservationTimeException;
import com.gabrielpolak.ticket.model.DAO.*;
import com.gabrielpolak.ticket.model.DTO.UserDTO;
import com.gabrielpolak.ticket.model.request.TicketRequest;
import com.gabrielpolak.ticket.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final ScreeningService screeningService;
    private final UserService userService;
    private final ValidationTokenService validationTokenService;

    @Autowired
    public ReservationService(ReservationRepository reservationRepository, ScreeningService screeningService, UserService userService, ApplicationEventPublisher eventPublisher, ValidationTokenService validationTokenService) {
        this.reservationRepository = reservationRepository;
        this.screeningService = screeningService;
        this.userService = userService;
        this.validationTokenService = validationTokenService;
    }

    public List<Reservation> getReservations() {
        return reservationRepository.findAll();
    }

    public Reservation createReservation(Long screeningId, List<TicketRequest> ticketRequest, UserDTO userDTO) {
        Screening screening = screeningService.findScreeningById(screeningId);

        if (!ZonedDateTime.now().isBefore(screening.getDate().minusMinutes(15))) {
            throw new ReservationTimeException("It's too late to make reservation right now.");
        }

        List<Ticket> ticketList = Ticket.createMultipleTickets(ticketRequest);

        User user;

        if (!userService.checkIfUserExists(userDTO.getEmail())) {
            user = userService.createNewUser(userDTO);
        } else {
            user = userService.findUser(userDTO.getEmail());
        }

        screeningService.removeTicketsFromScreening(screening, ticketList.size());

        Reservation reservation = Reservation.createNewReservationWithUserAndExpirationTime(ticketList, screening, user, screening.getDate().minusMinutes(15));
        reservationRepository.save(reservation);

        validationTokenService.handleTokenSending(reservation, user);

        return reservation;
    }

    public Reservation confirmReservation(String token) {
        ValidationToken validationToken = validationTokenService.findToken(token);

        Reservation reservation = validationToken.getReservation();
        reservation.setConfirmed(true);
        reservationRepository.save(reservation);

        validationToken.setConfirmedAt(ZonedDateTime.now());
        validationTokenService.saveValidationToken(validationToken);

        return reservation;
    }
}
