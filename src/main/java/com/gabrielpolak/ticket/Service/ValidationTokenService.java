package com.gabrielpolak.ticket.Service;

import com.gabrielpolak.ticket.Model.DAO.ValidationToken;
import com.gabrielpolak.ticket.Repository.ValidationTokenRepository;
import org.springframework.stereotype.Service;

@Service
public class ValidationTokenService {

    private final ValidationTokenRepository validationTokenRepository;

    public ValidationTokenService(ValidationTokenRepository validationTokenRepository) {
        this.validationTokenRepository = validationTokenRepository;
    }

    public void saveValidationToken(ValidationToken token){
        validationTokenRepository.save(token);
    }


}
