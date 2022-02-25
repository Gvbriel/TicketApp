package com.gabrielpolak.ticket.Model.DAO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Data
@Entity
@Table
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

    public Reservation() {

    }

    public static Reservation CreateNewReservationWithoutTickets(Screening screening, String reservationEmail){
        return new Reservation(screening, reservationEmail);
    }

    public Reservation(Screening screening, String reservationEmail){
        this.screening = screening;
        this.reservationEmail = reservationEmail;
    }

    public static Reservation CreateNewReservation(List<Ticket> tickets, Screening screening, String reservationEmail){
        return new Reservation(tickets, screening, reservationEmail);
    }

    public static Reservation CreateNewReservationWithUser(List<Ticket> tickets, Screening screening, User user){
        return new Reservation(tickets, screening, user);
    }

    public Reservation(List<Ticket> tickets, Screening screening, User user) {
        this.tickets = tickets;
        this.screening = screening;
        this.user = user;
    }

    public Reservation(List<Ticket> tickets, Screening screening, String reservationEmail) {
        this.tickets = tickets;
        this.screening = screening;
        this.reservationEmail = reservationEmail;
    }

    public double getTicketsPrice(){
        double price = 0;
        for(Ticket ticket : tickets){
            price += ticket.getPrice();
        }
        return price;
    }
}
