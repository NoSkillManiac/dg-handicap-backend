package space.knightdev.dghandicap.dao.player;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.UUID;

@Data
@Builder
@Document
@NoArgsConstructor
@AllArgsConstructor
public class PlayerHandicap {
    private UUID courseId;
    private UUID leagueID;
    private Boolean handicapOverride;
    private List<Double> ssaDeltas;
    private Double handicap;
}
