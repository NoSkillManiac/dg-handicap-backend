package space.knightdev.dghandicap.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import space.knightdev.dghandicap.dao.player.Player;
import space.knightdev.dghandicap.dao.player.PlayerHandicap;
import space.knightdev.dghandicap.database.PlayerDatabase;
import space.knightdev.dghandicap.dto.CoursePlayerRound;
import space.knightdev.dghandicap.dto.PlayerRound;
import space.knightdev.dghandicap.dto.RoundDetail;
import space.knightdev.dghandicap.service.HandicapService;
import space.knightdev.dghandicap.service.PlayerService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PlayerServiceImpl implements PlayerService {

    private final PlayerDatabase playerDatabase;
    private final HandicapService handicapService;

    @Autowired
    public PlayerServiceImpl(final PlayerDatabase playerDatabase, final HandicapService handicapService){
        this.playerDatabase = playerDatabase;
        this.handicapService = handicapService;
    }

    @Override
    public Player createPlayer(final String firstName, final String lastName, final String uDiscName, final Integer pdgaNumber) {
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
    public Player createPlayerFromUDisc(final String uDiscName, final Integer pdgaNumber) {
        String[] nameSplit = splitUDiscName(uDiscName);
        return createPlayer(nameSplit[0], nameSplit[1], uDiscName, pdgaNumber);
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

    @Override
    public List<PlayerRound> createPlayerRound(final RoundDetail roundDetail) {
        List<PlayerRound> rounds = new ArrayList<>();
        for (PlayerRound playerRound : roundDetail.getPlayers()) {
            List<Player> playerLookup = playerDatabase.getPlayerByUDisc(playerRound.getUDiscName());
            Player player;
            if (playerLookup.isEmpty()) {
                player = createPlayerFromUDisc(playerRound.getUDiscName(), 0);
            } else {
                player = playerLookup.get(0);
            }
            CoursePlayerRound coursePlayerRound = CoursePlayerRound.builder()
                    .playerId(player.getPlayerId())
                    .courseId(roundDetail.getCourseId())
                    .layoutId(roundDetail.getLayoutId())
                    .leagueId(roundDetail.getLeagueId())
                    .score(playerRound.getRawRoundScore())
                    .build();
            rounds.add(handicapService.calculateHandicapRoundScore(player, coursePlayerRound));
        }
        return rounds;
    }

    private String[] splitUDiscName(final String uDiscName) {
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
