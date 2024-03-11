package cat.itacademy.barcelonactiva.salasderamo.ezequiel.s05.t02.n01.model.service;


import cat.itacademy.barcelonactiva.salasderamo.ezequiel.s05.t02.n01.model.EntityDTOSwapper;
import cat.itacademy.barcelonactiva.salasderamo.ezequiel.s05.t02.n01.model.domain.Player;
import cat.itacademy.barcelonactiva.salasderamo.ezequiel.s05.t02.n01.model.dto.PlayerDTO;
import cat.itacademy.barcelonactiva.salasderamo.ezequiel.s05.t02.n01.model.repository.MatchRepository;
import cat.itacademy.barcelonactiva.salasderamo.ezequiel.s05.t02.n01.model.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

@Service
public class PlayerServiceImplementation implements PlayerService{
    @Autowired
    private PlayerRepository playerRepository;
    @Autowired
    private MatchRepository matchRepository;

    @Override
    public float calculateSuccessRate(long playerID){
        long result = 0;
        int playersSize = matchRepository.findByPlayerID(playerID).size();
        if (playersSize != 0){
            result = matchRepository.findVictories(playerID) / playersSize;
        }

        return result;
    }
    public void savePlayer(Player player){
        playerRepository.save(player);
    }

    @Override
    public List<Player> findAll(){
        if (!playerRepository.findAll().isEmpty()){
            return playerRepository.findAll();
        } else {
            return Collections.emptyList();
        }
    }

    @Override
    public HashMap<Player, Float> playerSuccessHashM(){
        HashMap<Player, Float> hashMap = new HashMap<>();
        for (Player player : findAll()){
            hashMap.put(player, calculateSuccessRate(player.getPk_playerID()));
        }
        return hashMap;
    }
}
