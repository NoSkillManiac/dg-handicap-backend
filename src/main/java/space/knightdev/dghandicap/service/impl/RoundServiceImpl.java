package space.knightdev.dghandicap.service.impl;

import com.opencsv.bean.CsvToBeanBuilder;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.InvalidFileNameException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import space.knightdev.dghandicap.dao.CSVEntry;
import space.knightdev.dghandicap.dao.Round;
import space.knightdev.dghandicap.database.RoundDatabase;
import space.knightdev.dghandicap.dto.CoursePlayerRound;
import space.knightdev.dghandicap.dto.LeagueRound;
import space.knightdev.dghandicap.dto.PlayerRound;
import space.knightdev.dghandicap.dto.RoundDetail;
import space.knightdev.dghandicap.service.HandicapService;
import space.knightdev.dghandicap.service.PlayerService;
import space.knightdev.dghandicap.service.RoundService;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class RoundServiceImpl implements RoundService {

    private final RoundDatabase roundDatabase;
    private final PlayerService playerService;
    private final HandicapService handicapService;

    @Autowired
    public RoundServiceImpl(final RoundDatabase roundDatabase, final PlayerService playerService, final HandicapService handicapService) {
        this.roundDatabase = roundDatabase;
        this.playerService = playerService;
        this.handicapService = handicapService;
    }

    @Override
    public Round addRound(final LeagueRound leagueRound) throws FileNotFoundException {
        if (leagueRound.getRoundFileLocation() == null || !leagueRound.getRoundFileLocation().endsWith(".csv")) {
            throw new InvalidFileNameException(leagueRound.getRoundFileLocation(), "File is null or not a csv.");
        }
        // File will need to be stored on the local filestore, and retrieved after the fact
        List<PlayerRound> scoresForRound = consumeFile(leagueRound.getRoundFileLocation());

        //Building the round detail for transfer across the system
        RoundDetail roundDetail = RoundDetail.builder()
                .courseId(leagueRound.getCourseId())
                .layoutId(leagueRound.getLayoutId())
                .leagueId(leagueRound.getLeagueId())
                .players(scoresForRound)
                .build();

        //Sending the scores off to playerService for player and handicap management, return a list of PlayerIds
        List<PlayerRound> playersInRound = playerService.createPlayerRound(roundDetail);

        return roundDatabase.addRound(Round.builder()
                        .courseId(leagueRound.getCourseId())
                        .layoutId(leagueRound.getLayoutId())
                        .leagueId(leagueRound.getLeagueId())
                        .players(playersInRound)
                .build());
    }

    @Override
    public Round updateRound(RoundDetail roundDetail) {
        log.error("Method not yet implemented.");
        return null;
    }

    @Override
    public Round getRound(UUID roundId) {
        return roundDatabase.getRound(roundId);
    }

    @Override
    public List<Round> getRounds(UUID courseId, Integer layoutId) {
        return roundDatabase.getRounds(courseId, layoutId);
    }

    private List<PlayerRound> consumeFile(String fileName) throws FileNotFoundException {
        List<CSVEntry> rawScores = new CsvToBeanBuilder(new FileReader(fileName))
                .withType(CSVEntry.class)
                .withSkipLines(1) // Used to skip 1st line. Because columns headers are in 1st line
                .build()
                .parse();
        return rawScores.stream()
                .map(score -> PlayerRound.builder()
                        .uDiscName(score.getUDiscName())
                        .rawRoundScore(score.getRoundScore())
                        .build())
                .toList();
    }
}
