package space.knightdev.dghandicap.dao;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.List;
import java.util.UUID;

@Data
@Builder
public class LeagueInfo {
    @Id
    @Builder.Default
    private UUID leagueId = UUID.randomUUID();
    private String leagueName;
    private List<UUID> courses;
    private List<UUID> rounds;
}
