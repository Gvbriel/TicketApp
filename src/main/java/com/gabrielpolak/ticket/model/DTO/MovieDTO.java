package com.gabrielpolak.ticket.model.DTO;

import com.gabrielpolak.ticket.model.DAO.Movie;
import lombok.Data;

@Data
public class MovieDTO {
    private Long id;
    private String title;

    public static MovieDTO from(Movie movie) {
        MovieDTO movieDTO = new MovieDTO();
        movieDTO.setId(movie.getId());
        movieDTO.setTitle(movie.getTitle());

        return movieDTO;
    }

}
