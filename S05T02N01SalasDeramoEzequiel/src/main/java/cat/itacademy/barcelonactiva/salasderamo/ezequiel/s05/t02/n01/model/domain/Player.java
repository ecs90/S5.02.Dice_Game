package cat.itacademy.barcelonactiva.salasderamo.ezequiel.s05.t02.n01.model.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "player")
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long pk_playerID;
    @Column(name = "name", unique = true)
    private String name;
    @Column(name = "surename")
    private String surename;
    @Column(name = "user")
    private String user;
    @Column(name = "password")
    private String password;
    @Column(name = "creationDate")
    private LocalDateTime creationDate;


    public Player(String name, String surename, String user, String password) {
        this.name = name;
        this.surename = surename;
        this.user = user;
        this.password = password;
        this.creationDate = LocalDateTime.now();
    }
}
