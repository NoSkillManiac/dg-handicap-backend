package space.knightdev.dghandicap.dao;

import lombok.Data;
import lombok.NonNull;
import org.springframework.data.annotation.Id;

import java.util.List;
import java.util.UUID;

@Data
public class Rounds {
    @Id
    @NonNull
    private UUID roundId;
    private long date;
    private UUID courseId;
    private List<UUID> players;
}
