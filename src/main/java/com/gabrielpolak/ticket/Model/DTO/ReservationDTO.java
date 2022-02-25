package com.gabrielpolak.ticket.Model.DTO;

import com.gabrielpolak.ticket.Model.Request.TicketRequest;
import lombok.Data;

import java.util.List;

@Data
public class ReservationDTO {
    private List<TicketRequest> tickets;
    private UserDTO user;

    public List<TicketRequest> getTickets(){
        return tickets;
    }

    public UserDTO getUser() {
        return user;
    }
}
