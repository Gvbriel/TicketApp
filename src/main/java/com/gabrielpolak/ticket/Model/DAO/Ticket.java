package com.gabrielpolak.ticket.Model.DAO;

import com.gabrielpolak.ticket.TicketType;
import lombok.Data;
import lombok.Getter;

import javax.persistence.*;

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
}
