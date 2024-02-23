package space.knightdev.dghandicap.dao.score;

import lombok.Data;
import lombok.NonNull;

import java.util.UUID;

@Data
public class RoundScore {
    @NonNull
    private UUID roundId;
    private Integer score;
}
