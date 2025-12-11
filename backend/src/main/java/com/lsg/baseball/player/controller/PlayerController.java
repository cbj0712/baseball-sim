package com.lsg.baseball.player.controller;

import com.lsg.baseball.common.api.ApiResponse;
import com.lsg.baseball.player.dto.request.PlayerCreateRequest;
import com.lsg.baseball.player.dto.response.PlayerResponse;
import com.lsg.baseball.player.service.PlayerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/players")
@RequiredArgsConstructor
public class PlayerController {
    private final PlayerService playerService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ApiResponse<PlayerResponse>> createPlayer(@Valid @RequestBody PlayerCreateRequest request) {
        PlayerResponse response = playerService.createPlayer(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.ok(response));
    }

    @GetMapping("/{playerId}")
    public ResponseEntity<ApiResponse<PlayerResponse>> getPlayer(@PathVariable Long playerId) {
        PlayerResponse response = playerService.getPlayer(playerId);

        return ResponseEntity.ok(ApiResponse.ok(response));
    }
}
