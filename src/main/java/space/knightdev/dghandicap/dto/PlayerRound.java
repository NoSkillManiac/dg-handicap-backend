package space.knightdev.dghandicap.dto;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class PlayerRound {
    private UUID playerId;
    private String uDiscName;
    private String firstName;
    private String lastName;
    private Integer rawRoundScore;
    private Integer handicapRoundScore;
}
