package com.gabrielpolak.ticket.Model.DAO;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Screening> screenings = new ArrayList<>();

    public static Room createRoom(){
        return new Room();
    }

    public Room(){

    }

}
