package com.gabrielpolak.ticket.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class ReservationTimeException extends RuntimeException {
    public ReservationTimeException(String message) {
        super(message);
    }
}
