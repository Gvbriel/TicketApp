package com.gabrielpolak.ticket.Model.DTO;

import com.gabrielpolak.ticket.Model.DAO.Movie;
import com.gabrielpolak.ticket.Model.DAO.Ticket;
import lombok.Data;

import java.util.Date;
import java.util.Set;

@Data
public class MovieDTO {
    private Long id;
    private String title;

    public static MovieDTO from(Movie movie){
        MovieDTO movieDTO = new MovieDTO();
        movieDTO.setId(movie.getId());
        movieDTO.setTitle(movie.getTitle());

        return movieDTO;
    }

}
