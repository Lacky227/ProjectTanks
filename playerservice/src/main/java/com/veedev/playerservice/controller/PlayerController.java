package com.veedev.playerservice.controller;

import com.veedev.playerservice.model.Player;
import com.veedev.playerservice.service.PlayerService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/player")
@AllArgsConstructor
public class PlayerController {
    private final PlayerService playerService;

    @PostMapping("/{username}")
    public ResponseEntity<Player> create(@PathVariable String username) {
        return ResponseEntity.ok(playerService.createPlayer(username));
    }

    @GetMapping("/{username}")
    public ResponseEntity<Player> get(@PathVariable String username) {
        return playerService.getPlayer(username)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
