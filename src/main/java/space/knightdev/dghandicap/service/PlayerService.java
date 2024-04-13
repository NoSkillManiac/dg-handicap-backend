package space.knightdev.dghandicap.service;

import space.knightdev.dghandicap.dao.player.Player;
import space.knightdev.dghandicap.dto.PlayerRound;
import space.knightdev.dghandicap.dto.RoundDetail;

import java.util.List;
import java.util.UUID;

public interface PlayerService {

    Player createPlayer(final String firstName, final String lastName, final String uDiscName, final Integer pdgaNumber);

    Player createPlayerFromUDisc(final String uDiscName, final Integer pdgaNumber);

    Player getPlayer(final UUID playerId);

    Player updatePlayer(final Player player);

    UUID deletePlayer(final UUID playerId);

    List<PlayerRound> createPlayerRound(final RoundDetail roundDetail);

}
