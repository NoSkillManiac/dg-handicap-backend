package space.knightdev.dghandicap.dto;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class RoundDetail {
    private UUID courseId;
    private Integer layoutId;
    private List<RoundPlayers> players;
}
