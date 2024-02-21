package space.knightdev.dghandicap.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import space.knightdev.dghandicap.dao.Round;
import space.knightdev.dghandicap.dto.RoundScores;

import java.io.File;
import java.util.UUID;

@Controller
public class RoundController {

    @PostMapping("/round")
    public Round addRound(final File uDiscRound) {
        return new Round(UUID.randomUUID());
    }

    @PutMapping("/round")
    public Round updateRound(final RoundScores roundScores) {
        return new Round(UUID.randomUUID());
    }
}
