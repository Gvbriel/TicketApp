package com.gabrielpolak.ticket.Controller;

import com.gabrielpolak.ticket.Model.DAO.Reservation;
import com.gabrielpolak.ticket.Model.DTO.ReservationDTO;
import com.gabrielpolak.ticket.Service.ReservationService;
import com.gabrielpolak.ticket.TicketProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    @Autowired
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
            @RequestParam("screeningId") Long screeningId,
            @RequestBody ReservationDTO reservationDTO
    ){
        return reservationService.createReservation(screeningId, reservationDTO.getTickets(), reservationDTO.getUser());
    }
}
