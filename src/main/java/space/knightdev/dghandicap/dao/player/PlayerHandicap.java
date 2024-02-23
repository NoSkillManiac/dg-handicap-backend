package space.knightdev.dghandicap.dao.player;

import lombok.Data;

import java.util.UUID;

@Data
public class PlayerHandicap {
    private UUID courseId;
    private Integer layoutId;
    private Double handicap;
}
