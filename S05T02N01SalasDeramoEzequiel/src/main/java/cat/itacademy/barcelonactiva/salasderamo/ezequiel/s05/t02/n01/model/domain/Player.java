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
    @Column(name = "name", unique = true, nullable = true)
    private String name;
    @Column(name = "user", unique = true, nullable = false)
    private String user;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "creationDate", nullable = false)
    private LocalDateTime creationDate;


    public Player(String name, String user, String password) {
        this.name = name;
        this.user = user;
        this.password = password;
        this.creationDate = LocalDateTime.now();
    }
}
