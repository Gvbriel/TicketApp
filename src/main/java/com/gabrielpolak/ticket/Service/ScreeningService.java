package com.gabrielpolak.ticket.Service;

import com.gabrielpolak.ticket.Model.DAO.Screening;
import com.gabrielpolak.ticket.Repository.ScreeningRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ScreeningService {

    private final ScreeningRepository screeningRepository;

    @Autowired
    public ScreeningService(ScreeningRepository screeningRepository) {
        this.screeningRepository = screeningRepository;
    }

    public List<Screening> getScreenings(LocalDateTime dateTime) {
        return screeningRepository.findScreeningsByDateAfter(dateTime);
    }

    public List<Screening> getDayScreenings(LocalDateTime dateTime){
        LocalDateTime dayStart = dateTime.withHour(0).withMinute(0).withSecond(0);
        LocalDateTime dayEnd = dateTime.withHour(23).withMinute(59).withSecond(59);
        return screeningRepository.findAllByDateBetween(dayStart, dayEnd);
    }

    public List<Screening> getScreeningsBetween(LocalDateTime from, LocalDateTime to) {
        LocalDateTime fromLDT = from.withHour(0).withMinute(0).withSecond(0);
        LocalDateTime toLDT = to.withHour(23).withMinute(59).withSecond(59);
        return screeningRepository.findAllByDateBetween(fromLDT, toLDT);
    }
}
