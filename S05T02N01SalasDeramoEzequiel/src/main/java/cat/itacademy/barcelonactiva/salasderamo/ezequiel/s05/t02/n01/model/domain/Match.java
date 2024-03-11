package cat.itacademy.barcelonactiva.salasderamo.ezequiel.s05.t02.n01.model.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "match")
public class Match {
    @Id
    long matchID;
    boolean win;
    long playerID;
    int score;
    int dice1;
    int dice2;

    public Match(long playerID) {
        this.playerID = playerID;
    }

    //(6-1) is the limits, meaning this function fluctuates between [1,6]
    //Second +1 indicates that the value is deplaced from origin by 1
    public int throwDice(){
        return (int) (Math.floor(Math.random()*(6-1+1)+1));
    }

    public void setWin() {
        this.win = score == 7;
    }
}
