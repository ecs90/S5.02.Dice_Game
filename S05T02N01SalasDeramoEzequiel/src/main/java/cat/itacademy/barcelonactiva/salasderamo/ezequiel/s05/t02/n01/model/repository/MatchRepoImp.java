package cat.itacademy.barcelonactiva.salasderamo.ezequiel.s05.t02.n01.model.repository;

import cat.itacademy.barcelonactiva.salasderamo.ezequiel.s05.t02.n01.model.domain.Match;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class MatchRepoImp implements MatchRepository{
    @Autowired
    private MatchRepository matchRepository;

    @Override
    public int findVictories(long playerID) {
        int victories = 0;
        List<Match> players = matchRepository.findByPlayerID(playerID);
        if (!players.isEmpty()){
            //TODO chatgptear como convertir a lambda
            for (int i = 0; i < players.size(); i++){
                if (players.get(i).isWin()){
                    victories++;
                }
            }
        }
        return victories;
    }

    @Override
    public List<Match> findByPlayerID(long playerID) {
        List<Match> matches = matchRepository.findAll();
        List<Match> matchesByPlayerID = new ArrayList<>();
        if (!matches.isEmpty()) {
            //TODO chatgptear como convertir a lambda
            for (int i = 0; i < matches.size(); i++) {
                if (matches.get(i).getPlayerID() == playerID) {
                    matchesByPlayerID.add(matches.get(i));
                }
            }
        }
        return matchesByPlayerID.isEmpty() ? Collections.emptyList() : matchesByPlayerID;
    }
}
