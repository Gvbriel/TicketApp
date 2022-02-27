package com.gabrielpolak.ticket;

import com.gabrielpolak.ticket.Model.DAO.*;
import com.gabrielpolak.ticket.Repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;

@Configuration
public class DataConfig {

    @Bean
    CommandLineRunner commandLineRunner(MovieRepository movieRepository, ScreeningRepository screeningRepository, TicketRepository ticketRepository, ReservationRepository reservationRepository, UserRepository userRepository){
        return args -> {
            Movie interstellar = Movie.CreateMovieWithTitle("Interstellar");
            Movie dzienSwira = Movie.CreateMovieWithTitle("Dzień Świra");
            Movie dallas = Movie.CreateMovieWithTitle("Dallas Buyers Club");
            Movie fight = Movie.CreateMovieWithTitle("Fight Club");

            Room room1 = Room.CreateRoom();
            Room room2 = Room.CreateRoom();
            Room room3 = Room.CreateRoom();

            User stachu = User.CreateNewUser("Stachu", "Jones", "stachu@jones.pl");
            User mirek = User.CreateNewUser("Mirek", "Jones", "mirek@jones.pl");
            User andrzej = User.CreateNewUser("Andrzej", "Jones", "andrzej@jones.pl");

            userRepository.saveAll(List.of(
                    stachu, mirek, andrzej
            ));

//            Ticket ticket1 = Ticket.CreateNewTicket(TicketType.Adult);
//            Ticket ticket2 = Ticket.CreateNewTicket(TicketType.Student);

//            movieRepository.saveAll(List.of(
//                interstellar, dzienSwira, dallas, fight
//            ));

            screeningRepository.saveAll(List.of(
                    Screening.CreateScreeningWithRoom(interstellar, LocalDateTime.of( 2022 , 2 , 28 , 11, 20), room1),
                    Screening.CreateScreeningWithRoom(dzienSwira, LocalDateTime.of( 2022 , 2 , 28 , 12, 30), room2),
                    Screening.CreateScreeningWithRoom(dallas, LocalDateTime.of( 2022 , 3 , 11 , 12, 30), room3),
                    Screening.CreateScreeningWithRoom(fight, LocalDateTime.of( 2022 , 3 , 15 , 12, 30), room3)
            ));

            reservationRepository.saveAll(List.of(

            ));

        };
    }
}
