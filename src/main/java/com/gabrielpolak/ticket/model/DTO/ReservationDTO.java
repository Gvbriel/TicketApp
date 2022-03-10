package com.gabrielpolak.ticket.model.DTO;

import com.gabrielpolak.ticket.model.request.TicketRequest;
import lombok.Data;

import java.util.List;

@Data
public class ReservationDTO {
    private List<TicketRequest> tickets;
    private UserDTO user;
    private Long screeningId;

    public Long getScreeningId() {
        return screeningId;
    }

    public List<TicketRequest> getTickets() {
        return tickets;
    }

    public UserDTO getUser() {
        return user;
    }
}
