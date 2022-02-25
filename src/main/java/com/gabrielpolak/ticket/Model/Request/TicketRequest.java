package com.gabrielpolak.ticket.Model.Request;

import com.gabrielpolak.ticket.TicketType;

public class TicketRequest {
    private TicketType type;
    private int amount;

    public TicketType getType() {
        return type;
    }

    public int getAmount() {
        return amount;
    }
}
