package com.lsg.baseball.player.service;

import com.lsg.baseball.player.domain.Player;
import com.lsg.baseball.player.dto.PlayerSummaryResponse;
import com.lsg.baseball.player.repository.PlayerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlayerService {
    private final PlayerRepository playerRepository;

    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public List<PlayerSummaryResponse> getPlayers() {
        List<Player> players = playerRepository.findAll();

        return players.stream()
                .map(PlayerSummaryResponse::from)
                .collect(Collectors.toList());
    }
}
