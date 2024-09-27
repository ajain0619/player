package com.practice.player;

import com.practice.player.controller.PlayerController;
import com.practice.player.exception.ApiException;
import com.practice.player.model.Player;
import com.practice.player.repository.PlayerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class PlayerControllerTest {

    @Mock
    private PlayerRepository playerRepository;

    @InjectMocks
    private PlayerController playerController;

    @Test
    public void testAddPlayer() {
        Player player = new Player();
        player.setPlayerId("abc");

        when(playerRepository.save(any(Player.class))).thenReturn(player);

        Player createdPlayer = playerController.addPlayer(player);

        assertEquals("abc", createdPlayer.getPlayerId());
    }

    @Test
    public void testGetPlayerById_NotFound() {
        when(playerRepository.findById("nonexistent")).thenReturn(Optional.empty());

        Assertions.assertThrows(ApiException.class, () -> {
            playerController.getPlayerById("nonexistent");
        });

        verify(playerRepository, times(1)).findById("nonexistent");
    }

    @Test
    public void testAddPlayer_NullPlayer() {
        Assertions.assertThrows(NullPointerException.class, () -> {
            playerController.addPlayer(null);
        });
    }


    @Test
    public void testGetPlayerById() {
        Player player = new Player();
        player.setPlayerId("abc");
        when(playerRepository.findById("abc")).thenReturn(Optional.of(player));

        Player foundPlayer = playerController.getPlayerById("abc");

        assertEquals("abc", foundPlayer.getPlayerId());
    }

    @Test
    public void testGetAllPlayers() {
        Player p = new Player();
        p.setPlayerId("abc");

        when(playerRepository.findAll()).thenReturn(List.of(p));

        List<Player> players = playerController.getAllPlayers();
        assertEquals("abc", players.get(0).getPlayerId());
    }
}
