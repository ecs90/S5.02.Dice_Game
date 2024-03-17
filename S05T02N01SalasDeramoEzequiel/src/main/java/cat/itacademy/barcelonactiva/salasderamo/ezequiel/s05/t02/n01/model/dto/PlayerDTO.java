package cat.itacademy.barcelonactiva.salasderamo.ezequiel.s05.t02.n01.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PlayerDTO {
    private Long id;
    private String name;
    private String user;
    private String password;
    private LocalDateTime creationDate;
    private Float successRate;
    private String token;
}
