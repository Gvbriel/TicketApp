package com.gabrielpolak.ticket.Model.Request;

import com.gabrielpolak.ticket.TicketType;
import lombok.Data;

@Data
public class TicketRequest {
    private TicketType type;
    private int amount;

    public TicketType getType() {
        return type;
    }

    public int getAmount() {
        return amount;
    }

    public static TicketRequest createNewRequest(TicketType type, int amount){
        return new TicketRequest(type,amount);
    }

    public TicketRequest(TicketType type, int amount){
        this.amount = amount;
        this.type = type;
    }
}
