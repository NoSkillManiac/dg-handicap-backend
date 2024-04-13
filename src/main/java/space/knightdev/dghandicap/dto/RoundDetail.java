package space.knightdev.dghandicap.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoundDetail {
    private UUID courseId;
    private Integer layoutId;
    private UUID leagueId;
    @Builder.Default
    private List<PlayerRound> players = new ArrayList<>();
}
