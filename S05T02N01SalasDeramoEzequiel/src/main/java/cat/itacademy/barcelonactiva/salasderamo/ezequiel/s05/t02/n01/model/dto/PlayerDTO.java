package cat.itacademy.barcelonactiva.salasderamo.ezequiel.s05.t02.n01.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PlayerDTO {
    private Long pk_playerID;
    private String name;
    private String user;
    private String password;
    private LocalDateTime creationDate;
    private float successRate;

}
