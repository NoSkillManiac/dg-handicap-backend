package space.knightdev.dghandicap.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import space.knightdev.dghandicap.dao.player.Player;
import space.knightdev.dghandicap.service.PlayerService;

import java.util.UUID;

@Controller
public class PlayerController {

    private final PlayerService playerService;

    public PlayerController(final PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping("/player/{playerId}")
    public Player getPlayer(@PathVariable("playerId") UUID playerId) {
        return playerService.getPlayer(playerId);
    }

    @PostMapping("/player")
    public Player upsertPlayer(@RequestBody Player player) {
        return playerService.upsertPlayer(player);
    }

    @DeleteMapping("/player/{playerId}")
    public UUID deletePlayer(@PathVariable("playerId") UUID playerId) {
        return playerService.deletePlayer(playerId);
    }
}
