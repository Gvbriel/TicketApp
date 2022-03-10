package com.gabrielpolak.ticket.model.DAO;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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

    @OneToOne(targetEntity = Reservation.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "reservation_id")
    private Reservation reservation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "user_id")
    private User user;

    @NotNull
    private ZonedDateTime expiresAt;

    private ZonedDateTime confirmedAt;

    @NotNull
    private ZonedDateTime createdAt;


    private ZonedDateTime calculateExpiryTime() {
        return ZonedDateTime.now().plusMinutes(15);
    }

    public static ValidationToken createToken(String token, Reservation reservation, User user) {
        return new ValidationToken(token, reservation, user);
    }

    public ValidationToken(String token, Reservation reservation, User user) {
        this.token = token;
        this.reservation = reservation;
        this.expiresAt = calculateExpiryTime();
        this.createdAt = ZonedDateTime.now();
        this.user = user;
    }
}
