package com.gabrielpolak.ticket.Repository;

import com.gabrielpolak.ticket.Helpers.DateHelper;
import com.gabrielpolak.ticket.Model.DAO.Movie;
import com.gabrielpolak.ticket.Model.DAO.Room;
import com.gabrielpolak.ticket.Model.DAO.Screening;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.ZonedDateTime;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ScreeningRepositoryTest {

    @Autowired
    private ScreeningRepository screeningRepository;

    @BeforeEach
    void clear() {
        screeningRepository.deleteAll();
    }


    @Test
    void itShouldFindScreeningsByDate() {
        Screening screening = getScreening();
        screeningRepository.save(screening);
        Optional<Screening> foundScreening = screeningRepository.findScreeningsByDateAfterOrderByDateAscMovietitleAsc(DateHelper.getDayStart(ZonedDateTime.now())).stream().findFirst();

        assertAll("Should return data about screening",
                () -> assertThat(foundScreening.isPresent()).isTrue(),
                () -> assertEquals(foundScreening.get().getId(), screening.getId()),
                () -> assertEquals(foundScreening.get().getDate(), screening.getDate()),
                () -> assertEquals(foundScreening.get().getMovie().getTitle(), screening.getMovie().getTitle())
        );
    }

    @Test
    void itShouldFindScreeningsByDateBetween() {
        Screening screening = getScreening();
        screeningRepository.save(screening);
        Optional<Screening> found = screeningRepository.findAllByDateBetweenOrderByDateAscMovietitleAsc(DateHelper.getDayStart(ZonedDateTime.now()), DateHelper.getDayEnd(ZonedDateTime.now().plusWeeks(3))).stream().findFirst();

        assertAll("Should return data about screening in days between query",
                () -> assertThat(found.isPresent()).isTrue(),
                () -> assertEquals(screening.getId(), found.get().getId()),
                () -> assertEquals(screening.getMovie().getTitle(), found.get().getMovie().getTitle()),
                () -> assertEquals(screening.getDate(), found.get().getDate()));
    }

    private Screening getScreening() {
        return Screening.createScreeningWithRoom(Movie.createMovieWithTitle("Interstellar"), ZonedDateTime.now(), Room.createRoom());
    }
}