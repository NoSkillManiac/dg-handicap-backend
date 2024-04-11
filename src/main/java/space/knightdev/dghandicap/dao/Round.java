package space.knightdev.dghandicap.dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Round {
    @Id
    @Builder.Default
    private UUID roundId = UUID.randomUUID();
    @Builder.Default
    private long date = LocalDate.now().toEpochDay();
    private UUID courseId;
    private Integer layoutId;
    private List<UUID> players;
}
