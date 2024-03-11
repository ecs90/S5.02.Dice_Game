package cat.itacademy.barcelonactiva.salasderamo.ezequiel.s05.t02.n01.model.service;

import cat.itacademy.barcelonactiva.salasderamo.ezequiel.s05.t02.n01.model.domain.Match;
import cat.itacademy.barcelonactiva.salasderamo.ezequiel.s05.t02.n01.model.repository.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatchServiceImplementation implements MatchService {
    @Autowired
    private MatchRepository matchRepository;

    @Override
    public Match throwDice(Match match){
        match.setDice1(match.throwDice());
        match.setDice2(match.throwDice());
        match.setScore(match.getDice1() + match.getDice2());
        match.setWin();

        return match;
    }

    @Override
    public void saveMatch(Match match) {
        matchRepository.save(match);
    }

    @Override
    public void deleteMatches(Long id) {
        matchRepository.deleteAll(findByPlayerId(id));
    }

    @Override
    public List<Match> findByPlayerId(Long id) {
        return matchRepository.findByPlayerID(id);
    }
}
