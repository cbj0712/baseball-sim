package com.lsg.baseball.team.service;

import com.lsg.baseball.common.api.ErrorCode;
import com.lsg.baseball.common.exception.BusinessException;
import com.lsg.baseball.team.domain.Team;
import com.lsg.baseball.team.dto.request.TeamCreateRequest;
import com.lsg.baseball.team.dto.response.TeamResponse;
import com.lsg.baseball.team.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TeamServiceImpl implements TeamService {
    private final TeamRepository teamRepository;

    @Override
    @Transactional
    public TeamResponse createTeam(TeamCreateRequest request) {
        String name = request.getName() == null ? null : request.getName().trim();
        String code = request.getCode() == null ? null : request.getCode().trim().toUpperCase();
        String city = request.getCity() == null ? null : request.getCity().trim();
        String url = request.getLogoUrl() == null ? null : request.getLogoUrl().trim();
        String primaryColor = request.getPrimaryColor() == null ? null : request.getPrimaryColor().trim();

        if (name == null || name.isBlank()) {
            throw new BusinessException(ErrorCode.VALIDATION_ERROR, "팀명이 작성되지 않았습니다");
        }

        if (code == null || code.isBlank()) {
            throw new BusinessException(ErrorCode.VALIDATION_ERROR, "팀 코드가 작성되지 않았습니다");
        }

        if (teamRepository.existsByName(name)) {
            throw new BusinessException(ErrorCode.DUPLICATE_TEAM_NAME);
        }

        if (teamRepository.existsByCode(code)) {
            throw new BusinessException(ErrorCode.DUPLICATE_TEAM_CODE);
        }

        Team team = Team.create(name, code, url, primaryColor, city, request.getHomeStadiumId());
        Team saved = teamRepository.save(team);

        return toResponse(saved);
    }

    @Override
    public TeamResponse getTeam(Long teamId) {
        Team team = teamRepository.findById(teamId).orElseThrow(() -> new BusinessException(ErrorCode.TEAM_NOT_FOUND));

        return toResponse(team);
    }

    @Override
    public List<TeamResponse> getTeams() {
        List<Team> teams = teamRepository.findAll();

        return teams.stream().map(this::toResponse).toList();
    }

    private TeamResponse toResponse(Team team) {
        return TeamResponse.builder()
                .id(team.getId())
                .name(team.getName())
                .code(team.getCode())
                .status(team.getStatus())
                .logoUrl(team.getLogoUrl())
                .primaryColor(team.getPrimaryColor())
                .city(team.getCity())
                .homeStadiumId(team.getHomeStadiumId())
                .createdAt(team.getCreatedAt())
                .updatedAt(team.getUpdatedAt())
                .build();
    }
}
