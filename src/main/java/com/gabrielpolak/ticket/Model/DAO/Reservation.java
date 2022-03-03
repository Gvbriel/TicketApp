package com.gabrielpolak.ticket.Model.DAO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    //TODO przekazany obiekt zmienic na jsona
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "reservation_id")
    @JsonIgnoreProperties("screening")
    private List<Ticket> tickets = new ArrayList<>();

    @ManyToOne
    private Screening screening;

    private String reservationEmail;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private ZonedDateTime expirationTime;

    private BigDecimal ticketsPrice;

    private boolean confirmed;

    public Reservation() {

    }

    public static Reservation createNewReservationWithUserAndExpirationTime(List<Ticket> tickets, Screening screening, User user, ZonedDateTime expirationTime){
        return new Reservation(tickets, screening, user, expirationTime);
    }

    public Reservation(List<Ticket> tickets, Screening screening, User user, ZonedDateTime expirationTime) {
        this.tickets = tickets;
        this.screening = screening;
        this.user = user;
        this.expirationTime = expirationTime;
        this.ticketsPrice = getTicketsPrice();
        this.confirmed = false;
    }

    public BigDecimal getTicketsPrice(){
        BigDecimal price = new BigDecimal(0);
        for(Ticket ticket : tickets){
            price = price.add(ticket.getTicketPrice());
        }
        return price;
    }
}
