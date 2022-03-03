package com.gabrielpolak.ticket.Service;

import com.gabrielpolak.ticket.Model.DAO.Reservation;
import com.gabrielpolak.ticket.Model.DAO.Screening;
import com.gabrielpolak.ticket.Model.DAO.Ticket;
import com.gabrielpolak.ticket.Model.DAO.User;
import com.gabrielpolak.ticket.Model.DTO.UserDTO;
import com.gabrielpolak.ticket.Model.Request.TicketRequest;
import com.gabrielpolak.ticket.Repository.ReservationRepository;
import com.gabrielpolak.ticket.Repository.ScreeningRepository;
import com.gabrielpolak.ticket.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final ScreeningRepository screeningRepository;
    private final UserRepository userRepository;
    private final UserService userService;
    private final ApplicationEventPublisher eventPublisher;


    @Autowired
    public ReservationService(ReservationRepository reservationRepository, ScreeningRepository screeningRepository, UserRepository userRepository, UserService userService, ApplicationEventPublisher eventPublisher) {
        this.reservationRepository = reservationRepository;
        this.screeningRepository = screeningRepository;
        this.userRepository = userRepository;
        this.userService = userService;
        this.eventPublisher = eventPublisher;
    }

    public List<Reservation> getReservations() {
        return reservationRepository.findAll();
    }

    public Reservation createReservation(Long screeningId, List<TicketRequest> ticketRequest, UserDTO userDTO) {
        Screening screening = screeningRepository.findById(screeningId)
                .orElseThrow(() -> new RuntimeException("Can't find screening."));

        if(!ZonedDateTime.now().isBefore(screening.getDate().minusMinutes(15))){
            throw new RuntimeException("It's too late to make reservation right now.");
        }

        List<Ticket> ticketList = Ticket.createMultipleTickets(ticketRequest);

        User user;

        if(!userRepository.findUserByEmail(userDTO.getEmail()).isPresent()){
            user = userService.createNewUser(userDTO);
            userRepository.save(user);
        }else{
            user = userRepository.findUserByEmail(userDTO.getEmail()).get();
        }

        screening.removeTickets(ticketList.size());
        screeningRepository.save(screening);

        Reservation reservation = Reservation.createNewReservationWithUserAndExpirationTime(ticketList, screening, user, screening.getDate().minusMinutes(15));

        reservationRepository.save(reservation);


        return reservation;
    }
}
