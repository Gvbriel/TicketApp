package com.gabrielpolak.ticket.Model.DAO;

import com.gabrielpolak.ticket.Model.Request.TicketRequest;
import com.gabrielpolak.ticket.TicketProperties;
import com.gabrielpolak.ticket.TicketType;
import lombok.Data;

import javax.persistence.*;
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
    private BigDecimal price;
    private TicketType type;

    @ManyToOne(cascade = CascadeType.ALL)
    private Screening screening;

    public Ticket() {

    }

    public static Ticket createNewTicket(TicketType type, Screening screening){
        return new Ticket(type, screening);
    }

    public Ticket(TicketType type, Screening screening){
        this.type = type;
        this.price = getTicketPrice(type);
        this.screening = screening;
    }

    private BigDecimal getTicketPrice(TicketType type){
        Map<String, String> prices = TicketProperties.getTicketPrices();
        switch (type){
            case Adult -> {
                return new BigDecimal(prices.get("ADULT"));
            }
            case Child -> {
                return new BigDecimal(prices.get("CHILD"));
            }
            case Student -> {
                return new BigDecimal(prices.get("STUDENT"));
            }
        }
        return new BigDecimal(0);
    }

    public static List<Ticket> createMultipleTickets(List<TicketRequest> ticketRequests, Screening screening){
        List<Ticket> ticketList = new ArrayList<>();

        for(TicketRequest request: ticketRequests){
            for(int i = 0; i < request.getAmount(); i++){
                ticketList.add(Ticket.createNewTicket(request.getType(), screening));
            }
        }

        return ticketList;
    }
}
