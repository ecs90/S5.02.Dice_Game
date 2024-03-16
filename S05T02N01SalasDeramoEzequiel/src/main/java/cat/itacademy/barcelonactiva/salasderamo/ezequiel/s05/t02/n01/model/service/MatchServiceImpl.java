package cat.itacademy.barcelonactiva.salasderamo.ezequiel.s05.t02.n01.model.service;

import cat.itacademy.barcelonactiva.salasderamo.ezequiel.s05.t02.n01.model.domain.Match;
import cat.itacademy.barcelonactiva.salasderamo.ezequiel.s05.t02.n01.model.repository.MatchRepository;
import cat.itacademy.barcelonactiva.salasderamo.ezequiel.s05.t02.n01.model.service.interfaces.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatchServiceImpl implements MatchService {
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

    @Override
    public float calculateSuccessRate(long playerID){
        long result = 0;
        int playersSize = matchRepository.findByPlayerID(playerID).size();
        if (playersSize != 0){
            result = countVictories(playerID) / playersSize;
        }

        return result;
    }

    @Override
    public int countVictories(long playerID) {
        //int victories = 0;
        List<Match> players = matchRepository.findByPlayerID(playerID);
        /*if (!players.isEmpty()){
            for (int i = 0; i < players.size(); i++){
                if (players.get(i).isWin()){
                    victories++;
                }
            }
        }
        return victories;*/
        return (int) players.stream()
                .filter(Match::isWin)
                .count();
    }

}
