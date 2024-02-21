package space.knightdev.dghandicap.dao;

import lombok.Data;
import lombok.NonNull;
import org.springframework.data.annotation.Id;

import java.util.List;
import java.util.UUID;

@Data
public class Round {
    @Id
    @NonNull
    private UUID roundId;
    private long date;
    private UUID courseId;
    private int layoutId;
    private List<UUID> players;
}
