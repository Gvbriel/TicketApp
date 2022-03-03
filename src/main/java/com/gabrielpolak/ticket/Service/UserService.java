package com.gabrielpolak.ticket.Service;

import com.gabrielpolak.ticket.Model.DAO.User;
import com.gabrielpolak.ticket.Model.DTO.UserDTO;
import com.gabrielpolak.ticket.Repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createNewUser(UserDTO userDTO){
        User user = userDTO.from(userDTO);
        userRepository.save(user);
        return user;
    }
}
