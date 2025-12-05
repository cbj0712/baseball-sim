package com.lsg.baseball.player.controller;

import com.lsg.baseball.common.ApiResponse;
import com.lsg.baseball.common.error.ErrorCode;
import com.lsg.baseball.player.dto.PlayerDetailResponse;
import com.lsg.baseball.player.dto.PlayerSummaryResponse;
import com.lsg.baseball.player.service.PlayerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    public ResponseEntity<ApiResponse<List<PlayerSummaryResponse>>> getPlayers() {
        List<PlayerSummaryResponse> players = playerService.getPlayers();

        return ResponseEntity.ok(ApiResponse.ok(players));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<PlayerDetailResponse>> getPlayerById(@PathVariable Long id) {
        return playerService.getPlayerDetail(id)
                .map(dto -> ResponseEntity.ok(ApiResponse.ok(dto)))
                .orElseGet(() -> {
                    ErrorCode error = ErrorCode.PLAYER_NOT_FOUND;

                    return ResponseEntity.status(error.getHttpStatus())
                            .body(ApiResponse.error(error));
                });
    }
}
