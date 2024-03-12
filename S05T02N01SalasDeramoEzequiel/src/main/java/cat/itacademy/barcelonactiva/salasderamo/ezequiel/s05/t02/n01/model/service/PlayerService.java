package cat.itacademy.barcelonactiva.salasderamo.ezequiel.s05.t02.n01.model.service;

import cat.itacademy.barcelonactiva.salasderamo.ezequiel.s05.t02.n01.model.domain.Player;
import cat.itacademy.barcelonactiva.salasderamo.ezequiel.s05.t02.n01.model.dto.PlayerDTO;

import java.util.List;

public interface PlayerService {
    float calculateSuccessRate(long playerID);
    void savePlayer(Player player);
    List<Player> findAll();

    List<PlayerDTO> playerSuccessList();

    List<PlayerDTO> rearrangeToWinningPositionsFirst(List<PlayerDTO> playerDTOS);
}
