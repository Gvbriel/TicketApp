package com.gabrielpolak.ticket.Controller;

import com.gabrielpolak.ticket.Model.DAO.Reservation;
import com.gabrielpolak.ticket.Model.DTO.ReservationDTO;
import com.gabrielpolak.ticket.Model.DTO.TicketDTO;
import com.gabrielpolak.ticket.Model.DTO.UserDTO;
import com.gabrielpolak.ticket.Model.Request.TicketRequest;
import com.gabrielpolak.ticket.Service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping
    public List<Reservation> getReservations(){
        return reservationService.getReservations();
    }

    @PostMapping
    public Reservation createReservation(
            @RequestParam Long screening_id,
            @RequestBody ReservationDTO reservationDTO
    ){
        return reservationService.createReservation(screening_id, reservationDTO.getTickets(), reservationDTO.getUser());
    }
}
