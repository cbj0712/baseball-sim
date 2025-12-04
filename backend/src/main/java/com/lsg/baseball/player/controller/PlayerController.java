package com.lsg.baseball.player.controller;

import com.lsg.baseball.common.ApiResponse;
import com.lsg.baseball.player.dto.PlayerSummaryResponse;
import com.lsg.baseball.player.service.PlayerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/players")
public class PlayerController {
    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping
    public ApiResponse<List<PlayerSummaryResponse>> getPlayers() {
        List<PlayerSummaryResponse> players = playerService.getPlayers();

        return ApiResponse.ok(players);
    }
}
