package cat.itacademy.barcelonactiva.salasderamo.ezequiel.s05.t02.n01.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MatchDTO {
    long matchID;
    boolean result;
    long playerID;
    int score;
    int dice1;
    int dice2;

}
