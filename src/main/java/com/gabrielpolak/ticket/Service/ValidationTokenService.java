package com.gabrielpolak.ticket.Service;

import com.gabrielpolak.ticket.Model.DAO.Reservation;
import com.gabrielpolak.ticket.Model.DAO.Screening;
import com.gabrielpolak.ticket.Model.DAO.User;
import com.gabrielpolak.ticket.Model.DAO.ValidationToken;
import com.gabrielpolak.ticket.Repository.ValidationTokenRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ValidationTokenService {

    private final ValidationTokenRepository validationTokenRepository;

    public ValidationTokenService(ValidationTokenRepository validationTokenRepository) {
        this.validationTokenRepository = validationTokenRepository;
    }

    public void saveValidationToken(ValidationToken token){
        validationTokenRepository.save(token);
    }

    public void handleTokenSending(Reservation reservation, User user){
        String tokenUUID = UUID.randomUUID().toString();

        ValidationToken token = ValidationToken.createToken(tokenUUID, reservation, user);
        saveValidationToken(token);
    }


    public boolean validateToken(String token) {
        return validationTokenRepository.findByToken(token).isPresent();
    }
}
