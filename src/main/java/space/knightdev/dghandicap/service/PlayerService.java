package space.knightdev.dghandicap.service;

import space.knightdev.dghandicap.dao.player.Player;

import java.util.UUID;

public interface PlayerService {

    public Player createPlayer(final String uDiscName, final Integer pdgaNumber);

    public Player getPlayer(final UUID playerId);

    public Player updatePlayer(final Player player);

    public UUID deletePlayer(final UUID playerId);
}
