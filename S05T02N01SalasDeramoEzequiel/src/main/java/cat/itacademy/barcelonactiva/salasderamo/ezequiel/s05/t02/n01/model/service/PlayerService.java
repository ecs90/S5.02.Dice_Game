package cat.itacademy.barcelonactiva.salasderamo.ezequiel.s05.t02.n01.model.service;

import cat.itacademy.barcelonactiva.salasderamo.ezequiel.s05.t02.n01.model.domain.Player;

import java.util.HashMap;
import java.util.List;

public interface PlayerService {
    float calculateSuccessRate(long playerID);
    void savePlayer(Player player);
    List<Player> findAll();

    HashMap<Player, Float> playerSuccessHashM();
}
