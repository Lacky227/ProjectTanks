package com.veedev.playerservice.service;

import com.veedev.playerservice.model.Player;
import java.util.Optional;

public interface PlayerService {
    Player createPlayer(String username);
    Optional<Player> getPlayer(String username);
}
