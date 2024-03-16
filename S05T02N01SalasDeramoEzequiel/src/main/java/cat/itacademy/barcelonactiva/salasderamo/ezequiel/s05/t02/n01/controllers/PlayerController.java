package cat.itacademy.barcelonactiva.salasderamo.ezequiel.s05.t02.n01.controllers;

import cat.itacademy.barcelonactiva.salasderamo.ezequiel.s05.t02.n01.model.EntityDTOSMapper;
import cat.itacademy.barcelonactiva.salasderamo.ezequiel.s05.t02.n01.model.domain.Player;
import cat.itacademy.barcelonactiva.salasderamo.ezequiel.s05.t02.n01.model.dto.PlayerDTO;
import cat.itacademy.barcelonactiva.salasderamo.ezequiel.s05.t02.n01.model.service.interfaces.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*POST: /players: crea un jugador/a.
PUT /players: modifica el nom del jugador/a.
GET /players/: retorna el llistat de tots  els jugadors/es del sistema amb el seu  percentatge mitjà d’èxits.

GET /players/ranking: retorna el ranking mig de tots els jugadors/es del sistema. És a dir, el  percentatge mitjà d’èxits.
GET /players/ranking/loser: retorna el jugador/a  amb pitjor percentatge d’èxit.
GET /players/ranking/winner: retorna el  jugador amb pitjor percentatge d’èxit. */
@RestController
@RequestMapping("/players")
public class PlayerController {
    @Autowired
    private PlayerService playerService;
    @Autowired
    private EntityDTOSMapper mapper;

    @PostMapping
    public ResponseEntity<PlayerDTO> signUp(@RequestBody PlayerDTO playerDTO){
        Player player = mapper.dtoToEntity(playerDTO);
        playerService.savePlayer(player);
        playerDTO.setId(player.getPk_playerID());
        return new ResponseEntity<>(playerDTO, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<PlayerDTO> updatePlayer(@RequestBody PlayerDTO playerDTO, String name){
        if (playerDTO.getId() != null){
            playerDTO.setName(name);
            Player player = mapper.dtoToEntityWID(playerDTO);
            playerService.savePlayer(player);
            return new ResponseEntity<>(playerDTO, HttpStatus.OK);
        }
        return signUp(playerDTO);
    }

    @GetMapping("/")
    public ResponseEntity<List<PlayerDTO>> allPSuccessPercentage(){
        return new ResponseEntity<>(playerService.playerSuccessList(), HttpStatus.OK);
    }

    @GetMapping("/ranking")
    public ResponseEntity<List<PlayerDTO>> getRanking(){
        List<PlayerDTO> playerDTOS = playerService
                .rearrangeToWinningPositionsFirst(playerService.playerSuccessList());
        return new ResponseEntity<>(playerDTOS, HttpStatus.OK);
    }

    @GetMapping("/ranking/loser")
    public ResponseEntity<PlayerDTO> getMostLoser(){
        List<PlayerDTO> playerDTOS = getRanking().getBody();
        assert playerDTOS != null;
        return new ResponseEntity<>(playerDTOS.get(playerDTOS.size()-1), HttpStatus.OK);
    }

    @GetMapping("/ranking/winner")
    public ResponseEntity<PlayerDTO> getMostWinner(){
        List<PlayerDTO> playerDTOS = getRanking().getBody();
        assert playerDTOS != null;
        return new ResponseEntity<>(playerDTOS.get(0), HttpStatus.OK);
    }
}
