package com.gabrielpolak.ticket.Service;

import com.gabrielpolak.ticket.Model.DAO.Screening;
import com.gabrielpolak.ticket.Repository.ScreeningRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ScreeningService {

    private final ScreeningRepository screeningRepository;

    @Autowired
    public ScreeningService(ScreeningRepository screeningRepository) {
        this.screeningRepository = screeningRepository;
    }

    public List<Screening> getScreenings(LocalDateTime dateTime) {
        return screeningRepository.findScreeningsByDateAfterOrderByDateAscMovietitleAsc(dateTime);
    }

    public List<Screening> getDayScreenings(LocalDateTime dateTime){
        if(dateTime == null){
            return screeningRepository.findByOrderByDateAscMovietitleAsc();
        }else{
            LocalDateTime dayStart = dateTime.withHour(0).withMinute(0).withSecond(0);
            LocalDateTime dayEnd = dateTime.withHour(23).withMinute(59).withSecond(59);
            return screeningRepository.findAllByDateBetweenOrderByDateAscMovietitleAsc(dayStart, dayEnd);
        }
    }

    public List<Screening> getScreeningsBetween(LocalDateTime from, LocalDateTime to) {
        LocalDateTime fromLDT = from.withHour(0).withMinute(0).withSecond(0);
        LocalDateTime toLDT = to.withHour(23).withMinute(59).withSecond(59);
        return screeningRepository.findAllByDateBetweenOrderByDateAscMovietitleAsc(fromLDT, toLDT);
    }
}
