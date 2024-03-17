package space.knightdev.dghandicap.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import space.knightdev.dghandicap.dao.course.CourseLayout;
import space.knightdev.dghandicap.dao.player.Player;
import space.knightdev.dghandicap.dao.player.PlayerHandicap;
import space.knightdev.dghandicap.database.CourseDatabase;
import space.knightdev.dghandicap.dto.CoursePlayerRound;
import space.knightdev.dghandicap.service.HandicapService;

@Service
public class HandicapServiceImpl implements HandicapService {

    private static final Double HANDICAP_MULTIPLIER = 0.65;

    private final CourseDatabase courseDatabase;

    @Autowired
    public HandicapServiceImpl(final CourseDatabase courseDatabase) {
        this.courseDatabase = courseDatabase;
    }

    @Override
    public PlayerHandicap updatePlayerHandicap(final PlayerHandicap player, final Double newScore, final boolean manualAdjust) {
        player.getScores().add(newScore.intValue());
        Integer scoreTotal = 0;
        for (Integer score : player.getScores()) {
            scoreTotal += score;
        }
        player.setHandicap(scoreTotal / player.getScores().size());
        return player;
    }

    @Override
    public Double calculateHandicapRoundScore(final Player player, final CoursePlayerRound round) {
        // get existing handicap for the player
        PlayerHandicap layoutHandicap;
        for (PlayerHandicap handicaps: player.getHandicap()) {
            if (round.getCourseId().equals(handicaps.getCourseId()) && round.getLayoutId().equals(handicaps.getLayoutId())) {
                layoutHandicap = handicaps;
                break;
            }
        }

        CourseLayout layout = courseDatabase.getCourseLayout(round.getCourseId(), round.getLayoutId());

        final Double ssaDelta = layout.getSsa() - round.getScore();
        final Double roundResult = ssaDelta * layoutHandicap.getHandicap();

        updatePlayerHandicap(layoutHandicap, roundResult, false);

        return roundResult;
    }
}
