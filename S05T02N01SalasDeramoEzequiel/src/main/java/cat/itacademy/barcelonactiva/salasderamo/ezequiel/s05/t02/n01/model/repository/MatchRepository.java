package cat.itacademy.barcelonactiva.salasderamo.ezequiel.s05.t02.n01.model.repository;

import cat.itacademy.barcelonactiva.salasderamo.ezequiel.s05.t02.n01.model.domain.Match;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatchRepository extends MongoRepository<Match, Long> {

    int deleteByPlayerID(long playerID);
    List<Match> findByPlayerID(long playerID);
}
