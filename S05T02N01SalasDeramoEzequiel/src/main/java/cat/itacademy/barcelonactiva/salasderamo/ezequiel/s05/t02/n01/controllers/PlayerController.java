package cat.itacademy.barcelonactiva.salasderamo.ezequiel.s05.t02.n01.controllers;

import cat.itacademy.barcelonactiva.salasderamo.ezequiel.s05.t02.n01.model.domain.Player;
import cat.itacademy.barcelonactiva.salasderamo.ezequiel.s05.t02.n01.model.dto.PlayerDTO;
import cat.itacademy.barcelonactiva.salasderamo.ezequiel.s05.t02.n01.model.service.MatchService;
import cat.itacademy.barcelonactiva.salasderamo.ezequiel.s05.t02.n01.model.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/*POST: /players: crea un jugador/a.
PUT /players: modifica el nom del jugador/a.
TODO POST /players/{id}/games/ : un jugador/a específic realitza una tirada dels daus.
TODO DELETE /players/{id}/games: elimina les tirades del jugador/a.
TODO GET /players/{id}/games: retorna el llistat de jugades per un jugador/a.

TODO GET /players/: retorna el llistat de tots  els jugadors/es del sistema amb el seu  percentatge mitjà d’èxits.

TODO GET /players/ranking: retorna el ranking mig de tots els jugadors/es del sistema. És a dir, el  percentatge mitjà d’èxits.
TODO GET /players/ranking/loser: retorna el jugador/a  amb pitjor percentatge d’èxit.
TODO GET /players/ranking/winner: retorna el  jugador amb pitjor percentatge d’èxit. */
@RestController
@RequestMapping(name = "/players")
public class PlayerController {
    @Autowired
    private PlayerService playerService;

    @PostMapping
    private ResponseEntity<PlayerDTO> addPlayer(@RequestBody PlayerDTO playerDTO){
        Player player = DTOtoEntity(playerDTO);
        playerService.savePlayer(player);
        playerDTO.setPk_playerID(player.getPk_playerID());
        return new ResponseEntity<>(playerDTO, HttpStatus.CREATED);
    }

    @PutMapping
    private ResponseEntity<PlayerDTO> updatePlayer(@RequestBody PlayerDTO playerDTO, String name){
        if (playerDTO.getPk_playerID() != null){
            playerDTO.setName(name);
            Player player = DTOtoEntityWID(playerDTO);
            playerService.savePlayer(player);
            return new ResponseEntity<>(playerDTO, HttpStatus.CREATED);
        }
        return addPlayer(playerDTO);
    }

    @GetMapping("/")
    private ResponseEntity<HashMap<PlayerDTO, Float>> allPSuccessPercentage(){
        HashMap<PlayerDTO, Float> playerSuccess = new HashMap<>();

        return new ResponseEntity<>(playerSuccess, HttpStatus.CREATED);
    }



}
