package com.lsg.baseball.player.service;

import com.lsg.baseball.player.domain.Player;
import com.lsg.baseball.player.dto.PlayerDetailResponse;
import com.lsg.baseball.player.dto.PlayerSummaryResponse;
import com.lsg.baseball.player.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PlayerService {
    private final PlayerRepository playerRepository;

    public List<PlayerSummaryResponse> getPlayers() {
        List<Player> players = playerRepository.findAll();

        return players.stream()
                .map(PlayerSummaryResponse::from)
                .collect(Collectors.toList());
    }

    public Optional<PlayerDetailResponse> getPlayerDetail(Long id) {
        return  playerRepository.findById(id)
                .map(PlayerDetailResponse::from);
    }
}
