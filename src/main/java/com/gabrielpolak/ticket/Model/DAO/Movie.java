package com.gabrielpolak.ticket.Model.DAO;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @NotBlank
    private String title;

    @OneToMany(cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Screening> screeningList = new ArrayList<>();

    public static Movie createMovieWithTitle(String title){
        return new Movie(title);
    }

    public Movie(String title){
        this.title = title;
    }

    public Movie(){}

}
