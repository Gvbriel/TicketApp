package com.gabrielpolak.ticket.Service;

import com.gabrielpolak.ticket.Helpers.DateHelper;
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
            return screeningRepository.findAllByDateBetweenOrderByDateAscMovietitleAsc(DateHelper.getDayStart(dateTime), DateHelper.getDayEnd(dateTime));
        }
    }

    public List<Screening> getScreeningsBetween(LocalDateTime from, LocalDateTime to) {
        return screeningRepository.findAllByDateBetweenOrderByDateAscMovietitleAsc(DateHelper.getDayStart(from), DateHelper.getDayEnd(to));
    }
}
