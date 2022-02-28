package com.gabrielpolak.ticket.Model.DAO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL)
    @JsonIgnoreProperties("screening")
    private List<Ticket> tickets = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "screening_id")
    private Screening screening;

    private String reservationEmail;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private LocalDateTime expirationTime;

    public Reservation() {

    }


    public static Reservation createNewReservationWithUserAndExpirationTime(List<Ticket> tickets, Screening screening, User user, LocalDateTime expirationTime){
        return new Reservation(tickets, screening, user, expirationTime);
    }

    public Reservation(List<Ticket> tickets, Screening screening, User user, LocalDateTime expirationTime) {
        this.tickets = tickets;
        this.screening = screening;
        this.user = user;
        this.expirationTime = expirationTime;
    }

    public BigDecimal getTicketsPrice(){
        BigDecimal price = new BigDecimal(0);
        for(Ticket ticket : tickets){
            price = price.add(ticket.getPrice());
        }
        return price;
    }
}
