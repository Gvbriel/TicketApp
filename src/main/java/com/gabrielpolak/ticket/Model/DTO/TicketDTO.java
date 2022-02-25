package com.gabrielpolak.ticket.Model.DTO;

import com.gabrielpolak.ticket.Model.Request.TicketRequest;
import lombok.Data;

import java.util.List;

@Data
public class TicketDTO {
    private List<TicketRequest> tickets;

    public List<TicketRequest> getTickets(){
        return tickets;
    }

    public void setTickets(List<TicketRequest> tickets) {
        this.tickets = tickets;
    }
}
