package com.gabrielpolak.ticket.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class NotSupportedTicketTypeException extends RuntimeException {
    public NotSupportedTicketTypeException(String mes) {
        super(mes);
    }
}
