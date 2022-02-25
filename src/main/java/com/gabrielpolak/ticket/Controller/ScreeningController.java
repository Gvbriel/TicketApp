package com.gabrielpolak.ticket.Controller;

import com.gabrielpolak.ticket.Model.DAO.Screening;
import com.gabrielpolak.ticket.Service.ScreeningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

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
            @RequestParam("date") @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date dateTime
    ){
        return screeningService.getScreenings();
    }

}
