package com.gabrielpolak.ticket.Model.DAO;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@Entity
@Data
public class ValidationToken {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String token;

    @OneToOne(targetEntity = Screening.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "screening_id")
    private Screening screening;

    private ZonedDateTime expiryDate;

    private ZonedDateTime calculateExpiryTime(){
        return ZonedDateTime.now().plusMinutes(30);
    }

}
