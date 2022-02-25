package com.gabrielpolak.ticket.Model.DAO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Screening> screenings = new ArrayList<>();

    public static Room CreateRoom(){
        return new Room();
    }

    public Room(){

    }

}
