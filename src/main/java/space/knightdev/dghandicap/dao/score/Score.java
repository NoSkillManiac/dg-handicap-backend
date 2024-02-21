package space.knightdev.dghandicap.dao.score;

import lombok.Data;
import lombok.NonNull;
import org.springframework.data.annotation.Id;

import java.util.List;
import java.util.UUID;

@Data
public class Score {
    @Id
    @NonNull
    private UUID playerId;
    private List<CourseScore> courseScores;
}