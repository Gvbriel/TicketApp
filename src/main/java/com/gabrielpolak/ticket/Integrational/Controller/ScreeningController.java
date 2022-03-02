package com.gabrielpolak.ticket.Integrational.Controller;

import com.gabrielpolak.ticket.Model.DAO.Screening;
import com.gabrielpolak.ticket.Integrational.Service.ScreeningService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.ZonedDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/screening")
public class ScreeningController {

    private final ScreeningService screeningService;

    public ScreeningController(ScreeningService screeningService) {
        this.screeningService = screeningService;
    }

    @GetMapping
    public List<Screening> getDayScreenings(
            @RequestParam(value = "date", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) ZonedDateTime date
    ){
        return screeningService.getDayScreenings(date);
    }

    @GetMapping("/between")
    public List<Screening> getScreeningsBetween(
            @RequestParam(value = "from") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) ZonedDateTime from,
            @RequestParam(value = "to") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) ZonedDateTime to)
    {
        return screeningService.getScreeningsBetween(from, to);
    }

}
