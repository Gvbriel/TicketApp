package com.gabrielpolak.ticket;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties
public class TicketProperties {
    
    private static Map<String, String> ticketPrices;

    public TicketProperties() {
    }

    public static Map<String, String> getTicketPrices() {
        return ticketPrices;
    }

    public void setTicketPrices(Map<String, String> ticketPrices) {
        TicketProperties.ticketPrices = ticketPrices;
    }
}
