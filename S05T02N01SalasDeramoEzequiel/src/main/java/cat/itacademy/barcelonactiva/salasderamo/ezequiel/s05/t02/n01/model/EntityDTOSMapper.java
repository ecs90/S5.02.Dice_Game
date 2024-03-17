package cat.itacademy.barcelonactiva.salasderamo.ezequiel.s05.t02.n01.model;

import cat.itacademy.barcelonactiva.salasderamo.ezequiel.s05.t02.n01.model.domain.Player;
import cat.itacademy.barcelonactiva.salasderamo.ezequiel.s05.t02.n01.model.dto.PlayerDTO;
import org.springframework.stereotype.Component;

@Component
public class EntityDTOSMapper {

    public Player dtoToEntity(PlayerDTO playerDTO){
        return new Player(playerDTO.getName(), playerDTO.getUser(),
                playerDTO.getPassword());
    }

    public Player dtoToEntityWID(PlayerDTO playerDTO){
        return new Player(playerDTO.getId(), playerDTO.getName(), playerDTO.getUser(),
                playerDTO.getPassword(), playerDTO.getCreationDate());
    }

    public PlayerDTO entityToDTO(Player player, Float successRate){
        return new PlayerDTO(player.getPk_playerID(), player.getName(),
                player.getUser(), player.getPassword(), player.getCreationDate(),
                successRate, null);
    }
}
