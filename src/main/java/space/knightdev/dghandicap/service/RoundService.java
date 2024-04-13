package space.knightdev.dghandicap.service;

import space.knightdev.dghandicap.dao.Round;
import space.knightdev.dghandicap.dto.LeagueRound;
import space.knightdev.dghandicap.dto.RoundDetail;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.UUID;

public interface RoundService {

    Round addRound(final LeagueRound leagueRound) throws FileNotFoundException;

    Round updateRound(final RoundDetail roundDetail);

    Round getRound(final UUID roundId);

    List<Round> getRounds(final UUID courseId, final Integer layoutId);

}
