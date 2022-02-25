package com.gabrielpolak.ticket.Model.DAO;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table
public class Screening {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime date;

    @Column(name = "tickets")
    private int tickets = 16;

    @ManyToOne(cascade = CascadeType.ALL)
    private Movie movie;

    @ManyToOne(cascade = CascadeType.ALL)
    private Room room;

    public static Screening CreateScreeningWithRoom(Movie movie, LocalDateTime date, Room room){
        return new Screening(movie, date, room);
    }

    public Screening(Movie movie, LocalDateTime date, Room room){
        this.movie = movie;
        this.date = date;
        this.room = room;
    }

    public static Screening CreateScreening(Movie movie, LocalDateTime date){
        return new Screening(movie, date);
    }

    public Screening(Movie movie, LocalDateTime date){
        this.movie = movie;
        this.date = date;
    }

    public Screening(){

    }

    public void removeTickets(int amount){
        int pom = this.tickets;
        pom -= amount;
        if(pom < 0){
            throw new IllegalStateException("Not enough tickets!");
        }
        this.tickets -= amount;
    }
}
