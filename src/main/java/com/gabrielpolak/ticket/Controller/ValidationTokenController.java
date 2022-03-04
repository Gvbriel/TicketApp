package com.gabrielpolak.ticket.Controller;

import com.gabrielpolak.ticket.Model.DAO.Reservation;
import com.gabrielpolak.ticket.Service.ReservationService;
import com.gabrielpolak.ticket.Service.ValidationTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/reservations/confirm")
public class ValidationTokenController {

    private final ValidationTokenService validationTokenService;
    private final ReservationService reservationService;

    @Autowired
    public ValidationTokenController(ValidationTokenService validationTokenService, ReservationService reservationService) {
        this.validationTokenService = validationTokenService;
        this.reservationService = reservationService;
    }

    @GetMapping
    public Reservation confirmReservation(
            @RequestParam("token") String token
    ) {
        return reservationService.confirmReservation(token);
    }
}
