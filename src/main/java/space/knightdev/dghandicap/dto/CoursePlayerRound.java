package space.knightdev.dghandicap.dto;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
public class CoursePlayerRound {
    private String uDiscName;
    private Integer score;
    private UUID courseId;
    private Integer layoutId;
    private UUID leagueId;
}
