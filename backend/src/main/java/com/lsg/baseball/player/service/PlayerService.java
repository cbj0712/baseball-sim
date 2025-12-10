package com.lsg.baseball.player.service;

import com.lsg.baseball.player.dto.request.PlayerCreateRequest;
import com.lsg.baseball.player.dto.response.PlayerResponse;

public interface PlayerService {
    PlayerResponse createPlayer(PlayerCreateRequest request);
    PlayerResponse getPlayer(Long playerId);
}
