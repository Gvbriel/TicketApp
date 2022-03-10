package com.gabrielpolak.ticket.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class NotEnoughTicketsException extends RuntimeException {
    public NotEnoughTicketsException(String message) {
        super(message);
    }
}
