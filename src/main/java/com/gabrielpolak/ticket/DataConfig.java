package com.gabrielpolak.ticket;

import com.gabrielpolak.ticket.Model.DAO.*;
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

            Room room1 = Room.CreateRoom();
            Room room2 = Room.CreateRoom();
            Room room3 = Room.CreateRoom();

//            Ticket ticket1 = Ticket.CreateNewTicket(TicketType.Adult);
//            Ticket ticket2 = Ticket.CreateNewTicket(TicketType.Student);

//            movieRepository.saveAll(List.of(
//                interstellar, dzienSwira, dallas, fight
//            ));

            screeningRepository.saveAll(List.of(
                    Screening.CreateScreeningWithRoom(interstellar, LocalDateTime.of( 2022 , 2 , 11 , 12, 30), room1),
                    Screening.CreateScreeningWithRoom(dzienSwira, LocalDateTime.of( 2022 , 2 , 11 , 12, 30), room2),
                    Screening.CreateScreeningWithRoom(dallas, LocalDateTime.of( 2022 , 2 , 11 , 12, 30), room3),
                    Screening.CreateScreeningWithRoom(fight, LocalDateTime.of( 2022 , 2 , 11 , 12, 30), room3)
            ));

            reservationRepository.saveAll(List.of(
            ));

        };
    }
}
