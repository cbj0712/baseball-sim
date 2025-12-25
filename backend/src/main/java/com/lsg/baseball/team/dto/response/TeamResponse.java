package com.lsg.baseball.team.dto.response;

import com.lsg.baseball.team.domain.enums.TeamStatus;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class TeamResponse {
    private Long id;
    private String name;
    private String code;
    private TeamStatus status;
    private String logoUrl;
    private String primaryColor;
    private String city;
    private Long homeStadiumId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
