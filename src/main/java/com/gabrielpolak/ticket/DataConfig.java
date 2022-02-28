package com.gabrielpolak.ticket;

import com.gabrielpolak.ticket.Model.DAO.*;
import com.gabrielpolak.ticket.Repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.List;

@Configuration
public class DataConfig {

    @Bean
    CommandLineRunner commandLineRunner(MovieRepository movieRepository, ScreeningRepository screeningRepository, TicketRepository ticketRepository, ReservationRepository reservationRepository, UserRepository userRepository){
        return args -> {
            Movie interstellar = Movie.createMovieWithTitle("Interstellar");
            Movie dzienSwira = Movie.createMovieWithTitle("Dzień Świra");
            Movie dallas = Movie.createMovieWithTitle("Dallas Buyers Club");
            Movie fight = Movie.createMovieWithTitle("Fight Club");

            Room room1 = Room.createRoom();
            Room room2 = Room.createRoom();
            Room room3 = Room.createRoom();

            User stachu = User.createNewUser("Stachu", "Jones", "stachu@jones.pl");
            User mirek = User.createNewUser("Mirek", "Jones", "mirek@jones.pl");
            User andrzej = User.createNewUser("Andrzej", "Jones", "andrzej@jones.pl");

            userRepository.saveAll(List.of(
                    stachu, mirek, andrzej
            ));

//            Ticket ticket1 = Ticket.CreateNewTicket(TicketType.Adult);
//            Ticket ticket2 = Ticket.CreateNewTicket(TicketType.Student);

//            movieRepository.saveAll(List.of(
//                interstellar, dzienSwira, dallas, fight
//            ));

            screeningRepository.saveAll(List.of(
                    Screening.createScreeningWithRoom(interstellar, LocalDateTime.of( 2022 , 2 , 28 , 11, 20), room1),
                    Screening.createScreeningWithRoom(dzienSwira, LocalDateTime.of( 2022 , 2 , 28 , 12, 30), room2),
                    Screening.createScreeningWithRoom(dallas, LocalDateTime.of( 2022 , 3 , 11 , 12, 30), room3),
                    Screening.createScreeningWithRoom(fight, LocalDateTime.of( 2022 , 3 , 15 , 12, 30), room3)
            ));

            reservationRepository.saveAll(List.of(

            ));

        };
    }
}
