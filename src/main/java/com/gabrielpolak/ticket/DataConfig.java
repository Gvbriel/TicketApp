package com.gabrielpolak.ticket;

import com.gabrielpolak.ticket.Model.DAO.Movie;
import com.gabrielpolak.ticket.Model.DAO.Reservation;
import com.gabrielpolak.ticket.Model.DAO.Screening;
import com.gabrielpolak.ticket.Model.DAO.Ticket;
import com.gabrielpolak.ticket.Repository.MovieRepository;
import com.gabrielpolak.ticket.Repository.ReservationRepository;
import com.gabrielpolak.ticket.Repository.ScreeningRepository;
import com.gabrielpolak.ticket.Repository.TicketRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Configuration
public class DataConfig {

    @Bean
    CommandLineRunner commandLineRunner(MovieRepository movieRepository, ScreeningRepository screeningRepository, TicketRepository ticketRepository, ReservationRepository reservationRepository){
        return args -> {
            Movie interstellar = Movie.CreateMovieWithTitle("Interstellar");
            Movie dzienSwira = Movie.CreateMovieWithTitle("Dzień Świra");
            Movie dallas = Movie.CreateMovieWithTitle("Dallas Buyers Club");
            Movie fight = Movie.CreateMovieWithTitle("Fight Club");

//            Ticket ticket1 = Ticket.CreateNewTicket(TicketType.Adult);
//            Ticket ticket2 = Ticket.CreateNewTicket(TicketType.Student);

//            movieRepository.saveAll(List.of(
//                interstellar, dzienSwira, dallas, fight
//            ));

            screeningRepository.saveAll(List.of(
                    Screening.CreateScreening(interstellar, LocalDateTime.of( 2022 , 2 , 11 , 12, 30)),
                    Screening.CreateScreening(dzienSwira, LocalDateTime.of( 2022 , 2 , 11 , 12, 30)),
                    Screening.CreateScreening(dallas, LocalDateTime.of( 2022 , 2 , 11 , 12, 30)),
                    Screening.CreateScreening(fight, LocalDateTime.of( 2022 , 2 , 11 , 12, 30))
            ));

            reservationRepository.saveAll(List.of(
            ));

        };
    }
}
