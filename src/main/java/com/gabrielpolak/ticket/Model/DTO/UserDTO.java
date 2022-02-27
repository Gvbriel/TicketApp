package com.gabrielpolak.ticket.Model.DTO;

import com.gabrielpolak.ticket.Model.DAO.User;
import lombok.Data;

@Data
public class UserDTO {

    private String name;
    private String surname;
    private String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User from(UserDTO userDTO){
        User user = User.CreateDefaultUser();
        user.setEmail(userDTO.getEmail());
        user.setName(userDTO.getName());
        user.setSurname(userDTO.getSurname());

        return user;
    }
}
