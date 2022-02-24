package com.gabrielpolak.ticket.Model.DAO;

import com.gabrielpolak.ticket.Model.DTO.MovieDTO;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;

    @OneToMany
    private List<Screening> screeningList = new ArrayList<>();

    public static Movie CreateMovieWithTitle(String title){
        return new Movie(title);
    }

    public Movie(String title){
        this.title = title;
    }

    public Movie(){}


//    public static Movie from(MovieDTO movieDTO){
//        Movie movie = new Movie();
//        movie.setTitle(movieDTO.getTitle());
//
//        return movie;
//    }

    public String getTitle() {
        return title;
    }
}
