package space.knightdev.dghandicap.dao.score;

import lombok.Data;
import lombok.NonNull;

import java.util.List;
import java.util.UUID;

@Data
public class CourseScore {
    @NonNull
    private UUID courseId;
    private Integer layoutId;
    private List<RoundScore> roundScores;
}
