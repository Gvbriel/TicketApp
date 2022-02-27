package com.gabrielpolak.ticket.Controller;

import com.gabrielpolak.ticket.Model.DAO.Screening;
import com.gabrielpolak.ticket.Service.ScreeningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/screening")
public class ScreeningController {

    private final ScreeningService screeningService;

    @Autowired
    public ScreeningController(ScreeningService screeningService) {
        this.screeningService = screeningService;
    }

    @GetMapping
    public List<Screening> getScreenings(
            @RequestParam(value = "date", required = false) LocalDateTime date
    ){
        return screeningService.getScreenings(date);
    }

    @GetMapping("/day")
    public List<Screening> getDayScreenings(
            @RequestParam(value = "date") @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") LocalDateTime date
    ){
        return screeningService.getDayScreenings(date);
    }

    @GetMapping("/between")
    public List<Screening> getScreeningsBetween(
            @RequestParam(value = "from") @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") LocalDateTime from,
            @RequestParam(value = "to") @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") LocalDateTime to)
    {
        return screeningService.getScreeningsBetween(from, to);
    }

}
