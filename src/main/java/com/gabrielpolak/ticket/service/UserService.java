package com.gabrielpolak.ticket.service;

import com.gabrielpolak.ticket.model.DAO.User;
import com.gabrielpolak.ticket.model.DTO.UserDTO;
import com.gabrielpolak.ticket.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createNewUser(UserDTO userDTO) {
        User user = userDTO.from(userDTO);
        userRepository.save(user);
        return user;
    }

    public User findUser(String email) {
        return userRepository.findUserByEmail(email).get();
    }

    public boolean checkIfUserExists(String email) {
        return userRepository.findUserByEmail(email).isPresent();
    }
}
