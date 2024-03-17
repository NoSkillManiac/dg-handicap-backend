package space.knightdev.dghandicap.service;

import space.knightdev.dghandicap.dao.player.Player;
import space.knightdev.dghandicap.dao.player.PlayerHandicap;
import space.knightdev.dghandicap.dto.CoursePlayerRound;
import space.knightdev.dghandicap.dto.PlayerRound;

import java.util.UUID;

public interface HandicapService {

    PlayerHandicap updatePlayerHandicap(final PlayerHandicap player, final Double score, final boolean manualAdjust);

    Double calculateHandicapRoundScore(final Player player, final CoursePlayerRound round);
}
