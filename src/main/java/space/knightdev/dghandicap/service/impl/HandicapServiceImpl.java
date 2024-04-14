package space.knightdev.dghandicap.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import space.knightdev.dghandicap.dao.course.CourseLayout;
import space.knightdev.dghandicap.dao.player.Player;
import space.knightdev.dghandicap.dao.player.PlayerHandicap;
import space.knightdev.dghandicap.database.CourseDatabase;
import space.knightdev.dghandicap.dto.CoursePlayerRound;
import space.knightdev.dghandicap.dto.PlayerRound;
import space.knightdev.dghandicap.service.HandicapService;
import space.knightdev.dghandicap.service.PDGAService;

@Slf4j
@Service
public class HandicapServiceImpl implements HandicapService {

    private static final double PDGA_MULTIPLIER = 0.8; // This may need to be configurable.
    private static final int SCRATCH_RATING = 1000;
    private static final int STROKE_RATINGS_AVG = 10;

    private final CourseDatabase courseDatabase;
    private final PDGAService pdgaService;

    @Autowired
    public HandicapServiceImpl(final CourseDatabase courseDatabase,
                               final PDGAService pdgaService) {
        this.courseDatabase = courseDatabase;
        this.pdgaService = pdgaService;
    }

    @Override
    public PlayerHandicap updatePlayerHandicap(final PlayerHandicap playerHandicap, final Double ssaDelta, final boolean manualAdjust) {
        playerHandicap.getSsaDeltas().add(ssaDelta);
        Double deltaTotal = 0d;
        for (Double delta : playerHandicap.getSsaDeltas()) {
            deltaTotal += delta;
        }
        playerHandicap.setHandicap(deltaTotal / playerHandicap.getSsaDeltas().size());
        return playerHandicap;
    }

    private PlayerHandicap buildNewPlayerHandicap(final Player player, final CoursePlayerRound round) {
        final PlayerHandicap startingHandicap = PlayerHandicap.builder()
                .handicapOverride(true)
                .courseId(round.getCourseId())
                .leagueID(round.getLeagueId())
                .handicap(0d)
                .build();

        if (player.getPdga() != null && player.getPdga() > 0) {
            log.info("Searching PDGA for player with PDGA number: {}", player.getPdga());
            final Integer pdgaRating = pdgaService.getPdgaRatingForPlayer(player.getPdga());
            if (pdgaRating == null || pdgaRating == Integer.MIN_VALUE) {
                log.warn("Unable to lookup player with PDGA number {} or player does not have rating, returning empty " +
                        "handicap", player.getPdga());
                return startingHandicap;
            }
            final Double pdgaHandicap = ((SCRATCH_RATING - pdgaRating) / STROKE_RATINGS_AVG) * PDGA_MULTIPLIER;
            startingHandicap.setHandicap(pdgaHandicap);
            startingHandicap.getSsaDeltas().add(pdgaHandicap);
        }

        return startingHandicap;
    }

    @Override
    public PlayerRound calculateHandicapRoundScore(final Player player, final CoursePlayerRound round) {
        PlayerHandicap layoutHandicap = null;
        for (PlayerHandicap handicaps: player.getHandicap()) {
            if (round.getCourseId().equals(handicaps.getCourseId()) && round.getLeagueId().equals(handicaps.getLeagueID())) {
                log.debug("Existing handicap at course {} found for player with playerId {}", round.getCourseId(),
                        player.getPlayerId());
                layoutHandicap = handicaps;
                break;
            }
        }
        if (layoutHandicap == null) {
            layoutHandicap = buildNewPlayerHandicap(player, round);
        }

        final CourseLayout layout = courseDatabase.getCourseLayout(round.getCourseId(), round.getLayoutId()).get(0);
        final double roundResult = round.getScore() + layoutHandicap.getHandicap();
        final Double ssaDelta = layout.getSsa() - round.getScore();

        updatePlayerHandicap(layoutHandicap, ssaDelta, false);

        return PlayerRound.builder()
                .playerId(player.getPlayerId())
                .rawRoundScore(round.getScore())
                .handicapRoundScore((int) roundResult)
                .uDiscName(player.getUDiscName())
                .firstName(player.getFirstName())
                .lastName(player.getLastName())
                .build();
    }
}
