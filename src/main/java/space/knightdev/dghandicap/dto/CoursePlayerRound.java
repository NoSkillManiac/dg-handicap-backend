package space.knightdev.dghandicap.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
public class CoursePlayerRound {
    private UUID playerId;
    private Integer score;
    private UUID courseId;
    private Integer layoutId;
    private UUID leagueId;
}
