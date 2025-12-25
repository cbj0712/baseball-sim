package com.lsg.baseball.team.controller;

import com.lsg.baseball.common.api.ApiResponse;
import com.lsg.baseball.team.dto.request.TeamCreateRequest;
import com.lsg.baseball.team.dto.response.TeamResponse;
import com.lsg.baseball.team.service.TeamService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("/api/teams")
@RequiredArgsConstructor
public class TeamController {
    private final TeamService teamService;

    @PostMapping-
    public ResponseEntity<ApiResponse<TeamResponse>> createTeam(@Valid @RequestBody TeamCreateRequest request) {
        TeamResponse response = teamService.createTeam(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.ok(response));
    }

    @GetMapping("/{teamId}")
    public ResponseEntity<ApiResponse<TeamResponse>> getTeam(@Positive @PathVariable Long teamId) {
        TeamResponse response = teamService.getTeam(teamId);

        return ResponseEntity.ok(ApiResponse.ok(response));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<TeamResponse>>> getTeams() {
        List<TeamResponse> response = teamService.getTeams();

        return ResponseEntity.ok(ApiResponse.ok(response));
    }
}
