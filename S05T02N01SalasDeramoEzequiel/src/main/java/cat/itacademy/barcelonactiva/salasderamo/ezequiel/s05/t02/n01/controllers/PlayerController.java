package cat.itacademy.barcelonactiva.salasderamo.ezequiel.s05.t02.n01.controllers;

import cat.itacademy.barcelonactiva.salasderamo.ezequiel.s05.t02.n01.model.EntityDTOSwapper;
import cat.itacademy.barcelonactiva.salasderamo.ezequiel.s05.t02.n01.model.domain.Player;
import cat.itacademy.barcelonactiva.salasderamo.ezequiel.s05.t02.n01.model.dto.PlayerDTO;
import cat.itacademy.barcelonactiva.salasderamo.ezequiel.s05.t02.n01.model.service.PlayerService;
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
@RequestMapping(name = "/players")
public class PlayerController {
    @Autowired
    private PlayerService playerService;

    @PostMapping
    private ResponseEntity<PlayerDTO> addPlayer(@RequestBody PlayerDTO playerDTO){
        Player player = EntityDTOSwapper.DTOtoEntity(playerDTO);
        playerService.savePlayer(player);
        playerDTO.setPk_playerID(player.getPk_playerID());
        return new ResponseEntity<>(playerDTO, HttpStatus.CREATED);
    }

    @PutMapping
    private ResponseEntity<PlayerDTO> updatePlayer(@RequestBody PlayerDTO playerDTO, String name){
        if (playerDTO.getPk_playerID() != null){
            playerDTO.setName(name);
            Player player = EntityDTOSwapper.DTOtoEntityWID(playerDTO);
            playerService.savePlayer(player);
            return new ResponseEntity<>(playerDTO, HttpStatus.OK);
        }
        return addPlayer(playerDTO);
    }

    @GetMapping("/")
    private ResponseEntity<List<PlayerDTO>> allPSuccessPercentage(){
        return new ResponseEntity<>(playerService.playerSuccessList(), HttpStatus.OK);
    }

    @GetMapping("/ranking")
    private ResponseEntity<List<PlayerDTO>> getRanking(){
        List<PlayerDTO> playerDTOS = playerService
                .rearrangeToWinningPositionsFirst(playerService.playerSuccessList());
        return new ResponseEntity<>(playerDTOS, HttpStatus.OK);
    }

    @GetMapping("/ranking/loser")
    private ResponseEntity<PlayerDTO> getMostLoser(){
        List<PlayerDTO> playerDTOS = getRanking().getBody();
        assert playerDTOS != null;
        return new ResponseEntity<>(playerDTOS.get(playerDTOS.size()-1), HttpStatus.OK);
    }

    @GetMapping("/ranking/winner")
    private ResponseEntity<PlayerDTO> getMostWinner(){
        List<PlayerDTO> playerDTOS = getRanking().getBody();
        assert playerDTOS != null;
        return new ResponseEntity<>(playerDTOS.get(0), HttpStatus.OK);
    }
}
