package com.gabrielpolak.ticket.Service;

import com.gabrielpolak.ticket.Email.EmailService;
import com.gabrielpolak.ticket.Exceptions.InvalidTokenException;
import com.gabrielpolak.ticket.Model.DAO.Reservation;
import com.gabrielpolak.ticket.Model.DAO.User;
import com.gabrielpolak.ticket.Model.DAO.ValidationToken;
import com.gabrielpolak.ticket.Repository.ValidationTokenRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.UUID;

@Service
public class ValidationTokenService {

    private final ValidationTokenRepository validationTokenRepository;
    private final EmailService emailService;

    public ValidationTokenService(ValidationTokenRepository validationTokenRepository, EmailService emailService) {
        this.validationTokenRepository = validationTokenRepository;
        this.emailService = emailService;
    }

    public void saveValidationToken(ValidationToken token) {
        validationTokenRepository.save(token);
    }

    public void handleTokenSending(Reservation reservation, User user) {
        String tokenUUID = UUID.randomUUID().toString();

        ValidationToken token = ValidationToken.createToken(tokenUUID, reservation, user);
        saveValidationToken(token);

        String link = ServletUriComponentsBuilder.fromCurrentRequestUri().toUriString() + "/confirm?token=" + token.getToken();
        emailService.send(user.getEmail(), user.getName(), link);
    }


    public boolean validateToken(String token) {
        return validationTokenRepository.findByToken(token).isPresent();
    }

    public ValidationToken findToken(String token) {
        return validationTokenRepository.findByToken(token)
                .orElseThrow(() -> new InvalidTokenException("Invalid token " + token));
    }
}
