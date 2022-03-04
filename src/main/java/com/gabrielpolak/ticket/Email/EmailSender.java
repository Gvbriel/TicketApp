package com.gabrielpolak.ticket.Email;

import javax.mail.MessagingException;

public interface EmailSender {
    void send(String to, String name, String link) throws MessagingException;
}
