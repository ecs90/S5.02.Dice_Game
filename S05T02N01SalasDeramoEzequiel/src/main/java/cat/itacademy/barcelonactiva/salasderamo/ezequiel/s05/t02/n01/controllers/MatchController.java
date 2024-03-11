package cat.itacademy.barcelonactiva.salasderamo.ezequiel.s05.t02.n01.controllers;

import cat.itacademy.barcelonactiva.salasderamo.ezequiel.s05.t02.n01.model.domain.Match;
import cat.itacademy.barcelonactiva.salasderamo.ezequiel.s05.t02.n01.model.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*POST /players/{id}/games/ : un jugador/a específic realitza una tirada dels daus.
DELETE /players/{id}/games: elimina les tirades del jugador/a.
GET /players/{id}/games: retorna el llistat de jugades per un jugador/a.*/
@RestController
@RequestMapping(name = "/players/{id}/games")
public class MatchController {
    @Autowired
    private MatchService matchService;

    @PostMapping("/")
    private ResponseEntity<Match> throwDice(@PathVariable Long id){
        Match match = new Match(id);
        match = matchService.throwDice(match);
        matchService.saveMatch(match);
        return new ResponseEntity<>(match, HttpStatus.CREATED);
    }

    @DeleteMapping
    private ResponseEntity<Void> deleteMatches(@PathVariable Long id){
        matchService.deleteMatches(id);
        return new ResponseEntity<>(null, HttpStatus.ACCEPTED);
    }

    @GetMapping
    private ResponseEntity<List<Match>> readMatches(@PathVariable Long id){
        List<Match> matches = matchService.findByPlayerId(id);
        return new ResponseEntity<>(matches, HttpStatus.OK);
    }
}
