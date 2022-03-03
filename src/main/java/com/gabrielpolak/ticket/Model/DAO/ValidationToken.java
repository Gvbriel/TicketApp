package com.gabrielpolak.ticket.Model.DAO;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@Entity
@Data
@NoArgsConstructor
public class ValidationToken {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private String token;

    @OneToOne(targetEntity = Screening.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "screening_id")
    private Screening screening;

    private ZonedDateTime expiresAt;
    private ZonedDateTime confirmedAt;

    @NotNull
    private ZonedDateTime createdAt;

    private ZonedDateTime calculateExpiryTime(){
        return ZonedDateTime.now().plusMinutes(30);
    }

    public ValidationToken(String token, Screening screening, ZonedDateTime expiresAt, ZonedDateTime createdAt, ZonedDateTime confirmedAt) {
        this.token = token;
        this.screening = screening;
        this.expiresAt = expiresAt;
        this.createdAt = createdAt;
        this.confirmedAt = confirmedAt;
    }
}
