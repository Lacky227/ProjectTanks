package com.veedev.playerservice.impl;

import com.veedev.playerservice.model.Player;
import com.veedev.playerservice.repository.PlayerRepository;
import com.veedev.playerservice.service.PlayerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class PlayerServiceImpl implements PlayerService {
    private final PlayerRepository playerRepository;
    @Override
    public Player createPlayer(String username) {
        Optional<Player> player = playerRepository.findByUsername(username);
        if (player.isPresent()) {
            throw new RuntimeException("Player already exists");
        }
        Player newPlayer = new Player();
        newPlayer.setUsername(username);
        newPlayer.setLevel(0);
        newPlayer.setExperience(0);
        newPlayer.setAvatar("default.png");
        return playerRepository.save(newPlayer);
    }

    @Override
    public Optional<Player> getPlayer(String username) {
        Optional<Player> player = playerRepository.findByUsername(username);
        if (player.isEmpty()) {
            throw new RuntimeException("Player not found");
        }
        return player;
    }
}
