package space.knightdev.dghandicap.dao.player;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Builder
@Document
@NoArgsConstructor
@AllArgsConstructor
public class PlayerHandicap {
    private UUID courseId;
    private Integer layoutId;
    private Double handicap;
}
