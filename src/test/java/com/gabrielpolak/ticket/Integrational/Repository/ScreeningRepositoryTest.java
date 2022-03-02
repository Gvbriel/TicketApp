package com.gabrielpolak.ticket.Integrational.Repository;

import com.gabrielpolak.ticket.Helpers.DateHelper;
import com.gabrielpolak.ticket.Model.DAO.Movie;
import com.gabrielpolak.ticket.Model.DAO.Room;
import com.gabrielpolak.ticket.Model.DAO.Screening;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.ZonedDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class ScreeningRepositoryTest {

    @Autowired
    private ScreeningRepository screeningRepository;

    @Test
    void itShouldFindScreeningsByDate() {
        Screening screening = Screening.createScreeningWithRoom(Movie.createMovieWithTitle("Interstellar"), ZonedDateTime.now(), Room.createRoom());
        screeningRepository.save(screening);
        boolean found = screeningRepository.findScreeningsByDateAfterOrderByDateAscMovietitleAsc(DateHelper.getDayStart(ZonedDateTime.now())).isEmpty();
        assertThat(found).isFalse();
    }

    @Test
    void itShouldFindScreeningsByDateBetween() {
        Screening screening = Screening.createScreeningWithRoom(Movie.createMovieWithTitle("Interstellar"), ZonedDateTime.now(), Room.createRoom());
        screeningRepository.save(screening);
        boolean found = screeningRepository.findAllByDateBetweenOrderByDateAscMovietitleAsc(DateHelper.getDayStart(ZonedDateTime.now()), DateHelper.getDayEnd(ZonedDateTime.now().plusWeeks(3))).isEmpty();

        assertThat(found).isFalse();
    }
}