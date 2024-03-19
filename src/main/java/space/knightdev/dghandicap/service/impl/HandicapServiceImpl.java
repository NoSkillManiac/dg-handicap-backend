package space.knightdev.dghandicap.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import space.knightdev.dghandicap.dao.course.CourseLayout;
import space.knightdev.dghandicap.dao.player.Player;
import space.knightdev.dghandicap.dao.player.PlayerHandicap;
import space.knightdev.dghandicap.database.CourseDatabase;
import space.knightdev.dghandicap.database.PlayerDatabase;
import space.knightdev.dghandicap.dto.CoursePlayerRound;
import space.knightdev.dghandicap.service.HandicapService;
import space.knightdev.dghandicap.service.PDGAService;

@Service
public class HandicapServiceImpl implements HandicapService {

    private static final double PDGA_MULTIPLIER = 0.8;
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
    public PlayerHandicap updatePlayerHandicap(final PlayerHandicap player, final Double ssaDelta, final boolean manualAdjust) {
        player.getSsaDeltas().add(ssaDelta);
        Double deltaTotal = 0d;
        for (Double delta : player.getSsaDeltas()) {
            deltaTotal += delta;
        }
        player.setHandicap(deltaTotal / player.getSsaDeltas().size());
        return player;
    }

    private PlayerHandicap buildBasePlayerHandicap(final Player player, final CoursePlayerRound round) {
        final PlayerHandicap startingHandicap = PlayerHandicap.builder()
                .handicapOverride(true)
                .courseId(round.getCourseId())
                .layoutId(round.getLayoutId())
                .handicap(0d)
                .build();

        if (player.getPdga() != null && player.getPdga() != 0) {
            final Integer pdgaRating = pdgaService.getPdgaRatingForPlayer(player.getPdga());
            final Double pdgaHandicap = (SCRATCH_RATING - pdgaRating) / STROKE_RATINGS_AVG * PDGA_MULTIPLIER;
            startingHandicap.setHandicap(pdgaHandicap);
            startingHandicap.getSsaDeltas().add(pdgaHandicap);
        }

        return startingHandicap;
    }

    @Override
    public Integer calculateHandicapRoundScore(final Player player, final CoursePlayerRound round) {
        PlayerHandicap layoutHandicap = null;
        for (PlayerHandicap handicaps: player.getHandicap()) {
            if (round.getCourseId().equals(handicaps.getCourseId()) && round.getLayoutId().equals(handicaps.getLayoutId())) {
                layoutHandicap = handicaps;
                break;
            }
        }
        if (layoutHandicap == null) {
            layoutHandicap = buildBasePlayerHandicap(player, round);
        }

        final CourseLayout layout = courseDatabase.getCourseLayout(round.getCourseId(), round.getLayoutId());
        final double roundResult = round.getScore() + layoutHandicap.getHandicap();
        final Double ssaDelta = layout.getSsa() - round.getScore();

        updatePlayerHandicap(layoutHandicap, ssaDelta, false);

        return (int)roundResult;
    }
}
