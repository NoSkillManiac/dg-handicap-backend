package space.knightdev.dghandicap.dao.player;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Player {
    @Id
    @Builder.Default
    private UUID playerId = UUID.randomUUID();
    private String firstName;
    private String lastName;
    private Integer pdga;
    private String uDiscName;
    private String alias;
    @Builder.Default
    private List<PlayerHandicap> handicap = new ArrayList<>();
}
