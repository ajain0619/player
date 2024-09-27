package com.practice.player;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PlayerController {

    private final PlayerRepository playerRepository;

    PlayerController(PlayerRepository repository) {this.playerRepository = repository; }



    /**
     * GET /api/players - returns the list of all players
     */
    @GetMapping("/api/players")
    List<Player> getAllPlayers() { return playerRepository.findAll();}

}
