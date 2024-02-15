package space.knightdev.dghandicap.dao;

import lombok.Data;
import lombok.NonNull;
import org.springframework.data.annotation.Id;

import java.util.UUID;

@Data
public class Players {
    @Id
    @NonNull
    private UUID playerId;
    private String firstName;
    private String lastName;
    private int pdga;
    private String uDiscName;
    private String alias;
    private int handicap;
}
