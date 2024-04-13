package space.knightdev.dghandicap.dao.score;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.List;
import java.util.UUID;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Score {
    @Id
    private UUID playerId;
    private List<CourseScore> courseScores;
}