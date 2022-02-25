package com.gabrielpolak.ticket.Service;

import com.gabrielpolak.ticket.Model.DAO.User;
import com.gabrielpolak.ticket.Model.DTO.UserDTO;
import com.gabrielpolak.ticket.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createNewUser(UserDTO userDTO){
        User user = User.CreateDefaultUser();
        user.setEmail(userDTO.getEmail());
        user.setName(userDTO.getName());
        user.setSurname(userDTO.getSurname());

        userRepository.save(user);

        return user;
    }
}
