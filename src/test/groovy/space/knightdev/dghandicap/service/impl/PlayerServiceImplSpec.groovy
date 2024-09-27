package space.knightdev.dghandicap.service.impl

import space.knightdev.dghandicap.dao.player.Player
import space.knightdev.dghandicap.dao.player.PlayerHandicap
import space.knightdev.dghandicap.database.PlayerDatabase
import space.knightdev.dghandicap.dto.CoursePlayerRound
import space.knightdev.dghandicap.dto.PlayerRound
import space.knightdev.dghandicap.dto.RoundDetail
import space.knightdev.dghandicap.service.HandicapService
import spock.lang.Specification

class PlayerServiceImplSpec extends Specification {

    private PlayerServiceImpl playerService
    private PlayerDatabase playerDatabase
    private HandicapService handicapService

    def setup() {
        playerDatabase = Mock()
        handicapService = Mock()
        playerService = new PlayerServiceImpl(playerDatabase, handicapService)
    }

    def "Test UDisc name split functions as intended"() {
        expect:
        result == playerService.splitUDiscName(playerName).toArrayString()

        where:
        playerName                       || result
        ""                               || "[no name, nameSplitFailed]"
        "Josh"                           || "[Josh, nameSplitFailed]"
        "Josh Knight"                    || "[Josh, Knight]"
        "Brent \"the dyemaster\" Palmer" || "[Brent, Brent the dyemaster Palmer]"
    }

    def "Ensure createPlayer calls database with playerInformation"() {
        when:
        playerService.createPlayer("Josh", "Knight", "Josh Knight", 134711)

        then:
        1 * playerDatabase.upsertPlayer(player -> {
            player.firstName == "Josh" &&
                    player.lastName == "Knight" &&
                    player.pdga == 134711 &&
                    player.handicap.size() == 0
        })
    }

    def "Adding player handicap round to player works as expected for a single player"() {
        given:

        when:
        List<PlayerRound> roundResult = playerService.createPlayerRound(buildRoundDetail())

        then:
        1 * handicapService.updatePlayerHandicap(_ as PlayerHandicap, _ as CoursePlayerRound)

    }

    Player buildPlayer() {
        return Player.builder()
                .pdga(134711)
                .uDiscName("Josh Knight")
                .firstName("Josh")
                .lastName("Knight")
                .build();
    }

    PlayerRound buildPlayerRound() {
        return PlayerRound.builder()
                .firstName("Josh")
                .lastName("Knight")
                .uDiscName("Josh Knight")
                .rawRoundScore(48)
                .build()
    }

    RoundDetail buildRoundDetail() {
        return RoundDetail.builder()
                .courseId(UUID.randomUUID())
                .layoutId(0)
                .leagueId(UUID.randomUUID())
                .players(List.of(buildPlayerRound()))
                .build();
    }
}
