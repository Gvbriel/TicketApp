package com.gabrielpolak.ticket.Controller;

import com.gabrielpolak.ticket.Model.DAO.Reservation;
import com.gabrielpolak.ticket.Model.DTO.ReservationDTO;
import com.gabrielpolak.ticket.Service.ReservationService;
import com.gabrielpolak.ticket.TicketProperties;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping
    public List<Reservation> getReservations(){
        System.out.println(TicketProperties.getTicketPrices());
        return reservationService.getReservations();
    }

    @PostMapping
    public Reservation createReservation(
            @RequestBody ReservationDTO reservationDTO
    ){
        return reservationService.createReservation(reservationDTO.getScreeningId(), reservationDTO.getTickets(), reservationDTO.getUser());
    }
}
