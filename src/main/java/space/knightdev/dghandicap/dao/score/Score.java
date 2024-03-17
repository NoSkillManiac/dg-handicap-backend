package space.knightdev.dghandicap.dao.score;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.UUID;

@Builder
@Document("score")
@NoArgsConstructor
@AllArgsConstructor
public class Score {
    @Id
    private UUID playerId;
    private List<CourseScore> courseScores;
}