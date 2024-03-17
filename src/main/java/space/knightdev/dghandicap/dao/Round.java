package space.knightdev.dghandicap.dao;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.UUID;

@Builder
@Document("round")
@NoArgsConstructor
@AllArgsConstructor
public class Round {
    @Id
    private UUID roundId = UUID.randomUUID();
    private long date;
    private UUID courseId;
    private Integer layoutId;
    private List<UUID> players;
}
