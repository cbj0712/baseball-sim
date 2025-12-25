package com.lsg.baseball.team.service;

import com.lsg.baseball.team.dto.request.TeamCreateRequest;
import com.lsg.baseball.team.dto.response.TeamResponse;

import java.util.List;

public interface TeamService {
    TeamResponse createTeam(TeamCreateRequest request);
    TeamResponse getTeam(Long teamId);
    List<TeamResponse> getTeams();
}
