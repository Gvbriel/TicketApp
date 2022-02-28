package com.gabrielpolak.ticket;

import com.gabrielpolak.ticket.Controller.ScreeningController;
import com.gabrielpolak.ticket.Service.ScreeningService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpRange;

import java.net.http.HttpResponse;

class ScreeningControllerTest {

    @Test
    void createScreening(){
        ScreeningService screeningService = Mockito.mock(ScreeningService.class);
        ScreeningController screeningController = new ScreeningController(screeningService);
    }
}
