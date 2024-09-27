package com.practice.player.controller;

import com.practice.player.exception.ApiException;
import com.practice.player.repository.PlayerRepository;
import com.practice.player.model.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/players")
public class PlayerController {
    @Autowired
    private PlayerRepository playerRepository;

    @PostMapping
    public Player addPlayer(@RequestBody Player player) {
        if (player == null) {
            throw new NullPointerException("Player cannot be null");
        }
        return playerRepository.save(player);
    }


    @GetMapping("/{id}")
    public Player getPlayerById(@PathVariable String id) {
        return playerRepository.findById(id).orElseThrow(() -> new ApiException(id));

    }

    /**
     * GET /api/players - returns the list of all players
     */
    @GetMapping("/all")
    public List<Player> getAllPlayers() {
        return playerRepository.findAll();
    }

    @GetMapping
    ResponseEntity<Page<Player>> getPagedPlayers(Pageable pageable) {
        Page<Player> players = playerRepository.findAll(pageable);

        //http://localhost:8080/api/players?page=2&size=5
        return ResponseEntity.ok(players);
    }

}
