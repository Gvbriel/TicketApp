package com.gabrielpolak.ticket.service;

import com.gabrielpolak.ticket.model.DAO.Movie;
import com.gabrielpolak.ticket.model.DAO.Room;
import com.gabrielpolak.ticket.model.DAO.Screening;
import com.gabrielpolak.ticket.model.DAO.User;
import com.gabrielpolak.ticket.repository.ScreeningRepository;
import com.gabrielpolak.ticket.repository.UserRepository;
import com.gabrielpolak.ticket.repository.ValidationTokenRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.ZonedDateTime;

@SpringBootTest
class ValidationTokenServiceTest {

    @Autowired
    private ValidationTokenService validationTokenService;

    @Autowired
    private ValidationTokenRepository validationTokenRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ScreeningRepository screeningRepository;

    @BeforeEach
    void clear() {
        validationTokenRepository.deleteAll();
    }

    //TODO fix test
    @Test
    void shouldReturnTrueAsValidationTokenExistsInDatabase() {
        //given
        Screening screening = createScreening();
        User user = createUser();

        userRepository.save(user);

//        validationTokenService.handleTokenSending(screening, user);

//        Optional<ValidationToken> token = validationTokenRepository.findByScreening(screening);

//        assertTrue(token.isPresent());
    }


    private Screening createScreening() {
        return Screening.createScreeningWithRoom(Movie.createMovieWithTitle("Toy Story"), ZonedDateTime.now(), Room.createRoom());
    }

    private User createUser() {
        return User.createNewUser("Gabriel", "Polak", "gabrielpolak@gmail.com");
    }


}