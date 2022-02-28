package com.gabrielpolak.ticket.Model.DAO;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;

    @OneToMany
    private List<Screening> screeningList = new ArrayList<>();

    public static Movie createMovieWithTitle(String title){
        return new Movie(title);
    }

    public Movie(String title){
        this.title = title;
    }

    public Movie(){}

}
