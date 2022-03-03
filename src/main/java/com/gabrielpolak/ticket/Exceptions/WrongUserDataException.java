package com.gabrielpolak.ticket.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class WrongUserDataException extends RuntimeException{
    public WrongUserDataException(String message){
        super(message);
    }
}
