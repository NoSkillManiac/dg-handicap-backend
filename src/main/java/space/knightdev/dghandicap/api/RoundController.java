package space.knightdev.dghandicap.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import space.knightdev.dghandicap.dao.Round;
import space.knightdev.dghandicap.dto.RoundDetail;
import space.knightdev.dghandicap.service.RoundService;

import java.io.File;
import java.util.List;
import java.util.UUID;

@Controller
public class RoundController {

    private final RoundService roundService;

    @Autowired
    public RoundController(final RoundService roundService) {
        this.roundService = roundService;
    }

    @PostMapping("/round")
    public Round addRound(@RequestBody final File uDiscRound) {
        return roundService.addRound(uDiscRound);
    }

    @PutMapping("/round")
    public Round updateRound(@RequestBody final RoundDetail roundDetail) {
        return roundService.updateRound(roundDetail);
    }

    @GetMapping("/round/{roundId}")
    public Round getRound(@PathVariable("roundId") final UUID roundId) {
        return roundService.getRound(roundId);
    }

    @GetMapping("/round/{courseId}/{layoutId}")
    public List<Round> getRounds(@PathVariable("courseId") final UUID courseId,
                                 @PathVariable("layoutId") final Integer layoutId) {
        return roundService.getRounds(courseId,layoutId);
    }
}
