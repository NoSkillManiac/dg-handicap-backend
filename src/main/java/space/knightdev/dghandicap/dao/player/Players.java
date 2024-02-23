package space.knightdev.dghandicap.dao.player;

import lombok.Data;
import lombok.NonNull;
import org.springframework.data.annotation.Id;

import java.util.List;
import java.util.UUID;

@Data
public class Players {
    @Id
    @NonNull
    private UUID playerId;
    private String firstName;
    private String lastName;
    private Integer pdga;
    private String uDiscName;
    private String alias;
    private List<PlayerHandicap> handicap;
}
