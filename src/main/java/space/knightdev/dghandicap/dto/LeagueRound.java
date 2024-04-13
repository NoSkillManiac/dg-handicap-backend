package space.knightdev.dghandicap.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class LeagueRound {
    private UUID courseId;
    private UUID leagueId;
    private Integer layoutId;
    private String roundFileLocation;
}
