package cat.itacademy.barcelonactiva.salasderamo.ezequiel.s05.t02.n01.model.service;


import cat.itacademy.barcelonactiva.salasderamo.ezequiel.s05.t02.n01.model.EntityDTOSwapper;
import cat.itacademy.barcelonactiva.salasderamo.ezequiel.s05.t02.n01.model.domain.Player;
import cat.itacademy.barcelonactiva.salasderamo.ezequiel.s05.t02.n01.model.dto.PlayerDTO;
import cat.itacademy.barcelonactiva.salasderamo.ezequiel.s05.t02.n01.model.repository.MatchRepository;
import cat.itacademy.barcelonactiva.salasderamo.ezequiel.s05.t02.n01.model.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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
    public List<PlayerDTO> playerSuccessList(){
        /*List<PlayerDTO> list = new ArrayList<>();
        for (Player player : findAll()){
            PlayerDTO playerDTO = EntityDTOSwapper.entityToDTO(player);
            list.add(playerDTO);
        }
        return list;*/
        return findAll().stream()
                .map(EntityDTOSwapper::entityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<PlayerDTO> rearrangeToWinningPositionsFirst(List<PlayerDTO> playerDTOS) {
        return playerDTOS.stream()
                .sorted(Comparator.comparingDouble(PlayerDTO::getSuccessRate).reversed())
                .collect(Collectors.toList());
    }
}
