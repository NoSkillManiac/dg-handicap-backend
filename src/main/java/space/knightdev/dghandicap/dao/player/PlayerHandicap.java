package space.knightdev.dghandicap.dao.player;

import lombok.Data;

import java.util.UUID;

@Data
public class PlayerHandicap {
    private UUID courseId;
    private int layoutId;
    private int handicap;
}
