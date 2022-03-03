package com.gabrielpolak.ticket.Service;

import com.gabrielpolak.ticket.Helpers.DateHelper;
import com.gabrielpolak.ticket.Model.DAO.Screening;
import com.gabrielpolak.ticket.Repository.ScreeningRepository;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;

@Service
public class ScreeningService {

    private final ScreeningRepository screeningRepository;

    public ScreeningService(ScreeningRepository screeningRepository) {
        this.screeningRepository = screeningRepository;
    }

    public List<Screening> getDayScreenings(ZonedDateTime dateTime){
        if(dateTime == null){
            return screeningRepository.findByOrderByDateAscMovietitleAsc();
        }else{
            return screeningRepository.findAllByDateBetweenOrderByDateAscMovietitleAsc(DateHelper.getDayStart(dateTime), DateHelper.getDayEnd(dateTime));
        }
    }

    public List<Screening> getScreeningsBetween(ZonedDateTime from, ZonedDateTime to) {
        return screeningRepository.findAllByDateBetweenOrderByDateAscMovietitleAsc(DateHelper.getDayStart(from), DateHelper.getDayEnd(to));
    }
}
