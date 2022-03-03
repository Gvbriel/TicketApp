package com.gabrielpolak.ticket.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class NotEnoughTicketsException extends RuntimeException {
    public NotEnoughTicketsException(String message){
        super(message);
    }
}
