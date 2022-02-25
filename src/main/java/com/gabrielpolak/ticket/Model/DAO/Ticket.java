package com.gabrielpolak.ticket.Model.DAO;

import com.gabrielpolak.ticket.Model.Request.TicketRequest;
import com.gabrielpolak.ticket.TicketType;
import lombok.Data;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private double price;
    private TicketType type;

    @ManyToOne(cascade = CascadeType.ALL)
    private Screening screening;

    public Ticket() {

    }

    public static Ticket CreateNewTicket(TicketType type, Screening screening){
        return new Ticket(type, screening);
    }

    public Ticket(TicketType type, Screening screening){
        this.type = type;
        this.price = getTicketPrice(type);
        this.screening = screening;
    }

    private double getTicketPrice(TicketType type){
        switch (type){
            case Adult -> {
                return 25;
            }
            case Child -> {
                return 12.50;
            }
            case Student -> {
                return 18;
            }
        }
        return 0;
    }

    public static List<Ticket> CreateMultipleTickets(List<TicketRequest> ticketRequests, Screening screening){
        List<Ticket> ticketList = new ArrayList<>();

        for(TicketRequest request: ticketRequests){
            for(int i = 0; i < request.getAmount(); i++){
                ticketList.add(Ticket.CreateNewTicket(request.getType(), screening));
            }
        }

        return ticketList;
    }
}
