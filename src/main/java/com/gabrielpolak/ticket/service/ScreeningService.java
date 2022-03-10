package com.gabrielpolak.ticket.service;

import com.gabrielpolak.ticket.helpers.DateHelper;
import com.gabrielpolak.ticket.model.DAO.Screening;
import com.gabrielpolak.ticket.repository.ScreeningRepository;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;

@Service
public class ScreeningService {

    private final ScreeningRepository screeningRepository;

    public ScreeningService(ScreeningRepository screeningRepository) {
        this.screeningRepository = screeningRepository;
    }

    public List<Screening> getDayScreenings(ZonedDateTime dateTime) {
        if (dateTime == null) {
            return screeningRepository.findByOrderByDateAscMovietitleAsc();
        } else {
            return screeningRepository.findAllByDateBetweenOrderByDateAscMovietitleAsc(DateHelper.getDayStart(dateTime), DateHelper.getDayEnd(dateTime));
        }
    }

    public List<Screening> getScreeningsBetween(ZonedDateTime from, ZonedDateTime to) {
        return screeningRepository.findAllByDateBetweenOrderByDateAscMovietitleAsc(DateHelper.getDayStart(from), DateHelper.getDayEnd(to));
    }

    public void removeTicketsFromScreening(Screening screening, int amount) {
        screening.removeTickets(amount);
        screeningRepository.save(screening);
    }

    public Screening findScreeningById(Long screeningId) {
        return screeningRepository.findById(screeningId).orElseThrow(() -> new RuntimeException("Can't find screening"));
    }
}
