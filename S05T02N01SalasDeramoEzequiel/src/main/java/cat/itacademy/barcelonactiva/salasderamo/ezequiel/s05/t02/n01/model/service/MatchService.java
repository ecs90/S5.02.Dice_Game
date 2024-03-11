package cat.itacademy.barcelonactiva.salasderamo.ezequiel.s05.t02.n01.model.service;


import cat.itacademy.barcelonactiva.salasderamo.ezequiel.s05.t02.n01.model.domain.Match;

import java.util.List;

public interface MatchService {
    Match throwDice(Match match);

    void saveMatch(Match match);

    void deleteMatches(Long id);
    List<Match> findByPlayerId(Long id);
}
