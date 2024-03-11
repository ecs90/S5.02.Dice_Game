package cat.itacademy.barcelonactiva.salasderamo.ezequiel.s05.t02.n01.model;

import cat.itacademy.barcelonactiva.salasderamo.ezequiel.s05.t02.n01.model.domain.Player;
import cat.itacademy.barcelonactiva.salasderamo.ezequiel.s05.t02.n01.model.dto.PlayerDTO;
import cat.itacademy.barcelonactiva.salasderamo.ezequiel.s05.t02.n01.model.service.PlayerService;

public class EntityDTOSwapper {
    private static PlayerService playerService;

    public static Player DTOtoEntity(PlayerDTO playerDTO){
        return new Player(playerDTO.getName(), playerDTO.getSurename(), playerDTO.getUser(),
                playerDTO.getPassword());
    }

    public static Player DTOtoEntityWID(PlayerDTO playerDTO){
        return new Player(playerDTO.getPk_playerID(), playerDTO.getName(), playerDTO.getSurename(), playerDTO.getUser(),
                playerDTO.getPassword(), playerDTO.getCreationDate());
    }

    public static PlayerDTO entityToDTO(Player player){
        return new PlayerDTO(player.getPk_playerID(), player.getName(), player.getSurename(),
                player.getUser(), player.getPassword(), player.getCreationDate(),
                playerService.calculateSuccessRate(player.getPk_playerID()));
    }
}
