package space.knightdev.dghandicap.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import space.knightdev.dghandicap.dao.player.Player;
import space.knightdev.dghandicap.database.PlayerDatabase;
import space.knightdev.dghandicap.service.PlayerService;

import java.util.ArrayList;
import java.util.UUID;

@Service
public class PlayerServiceImpl implements PlayerService {

    private PlayerDatabase playerDatabase;

    @Autowired
    public PlayerServiceImpl(final PlayerDatabase playerDatabase){
        this.playerDatabase = playerDatabase;
    }

    @Override
    public Player createPlayer(final String uDiscName, final Integer pdgaNumber) {
        String[] nameSplit = splitUDiscName(uDiscName);
        Player newPlayer = Player.builder()
                .playerId(UUID.randomUUID())
                .uDiscName(uDiscName)
                .firstName(nameSplit[0])
                .lastName(nameSplit[1])
                .pdga(pdgaNumber)
                .handicap(new ArrayList<>())
                .build();
        return playerDatabase.upsertPlayer(newPlayer);
    }

    @Override
    public Player getPlayer(final UUID playerId) {
        return playerDatabase.getPlayer(playerId);
    }

    @Override
    public Player updatePlayer(Player player) {
        return playerDatabase.upsertPlayer(player);
    }

    @Override
    public UUID deletePlayer(UUID playerId) {
        return playerDatabase.deletePlayer(playerId);
    }

    final String[] splitUDiscName(final String uDiscName) {
        String modifiedName = uDiscName.replace("\"", "");
        modifiedName = modifiedName.trim();
        String[] nameSplit = modifiedName.split(" ");
        return switch (nameSplit.length) {
            case 0, 1 -> new String[]{modifiedName, "nameSplitFailed"};
            case 2 -> nameSplit;
            default -> new String[]{nameSplit[0], String.join(" ", nameSplit)};
        };
    }
}
