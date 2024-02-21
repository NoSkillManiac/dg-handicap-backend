package space.knightdev.dghandicap.dto;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class RoundScores {
    private UUID courseId;
    private int layoutId;
    private List<RoundPlayers> players;
}
