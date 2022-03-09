package com.gabrielpolak.ticket.Model.DAO;

import com.gabrielpolak.ticket.Exceptions.NotSupportedTicketTypeException;
import com.gabrielpolak.ticket.Model.Request.TicketRequest;
import com.gabrielpolak.ticket.TicketProperties;
import com.gabrielpolak.ticket.TicketType;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Data
@Entity
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private TicketType type;

    public Ticket() {

    }

    public Ticket(TicketType type) {
        this.type = type;
    }

    public static Ticket createNewTicket(TicketType type) {
        return new Ticket(type);
    }

    public static List<Ticket> createMultipleTickets(List<TicketRequest> ticketRequests) {
        List<Ticket> ticketList = new ArrayList<>();

        for (TicketRequest request : ticketRequests) {
            for (int i = 0; i < request.getAmount(); i++) {
                if (request.getType() != null) {
                    ticketList.add(Ticket.createNewTicket(request.getType()));
                } else {
                    throw new NotSupportedTicketTypeException("Null not supported");
                }
            }
        }

        return ticketList;
    }

    public BigDecimal getTicketPrice() {
        Map<String, String> prices = TicketProperties.getTicketPrices();
        if (prices.get(type.name()) != null) {
            return new BigDecimal(prices.get(type.name()));
        }
        throw new NotSupportedTicketTypeException("Not supported type named" + type.name());
    }

}
