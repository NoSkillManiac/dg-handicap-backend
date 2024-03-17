package space.knightdev.dghandicap.dao.player;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.UUID;

@Data
@Builder
@Document("players")
@NoArgsConstructor
@AllArgsConstructor
public class Player {
    @Id
    private UUID playerId;
    private String firstName;
    private String lastName;
    private Integer pdga;
    private String uDiscName;
    private String alias;
    private List<PlayerHandicap> handicap;
}
