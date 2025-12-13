package com.lsg.baseball.team.domain;

import com.lsg.baseball.common.entity.BaseEntity;
import com.lsg.baseball.team.domain.enums.TeamStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "teams",
        uniqueConstraints = {
            @UniqueConstraint(columnNames = "name"),
            @UniqueConstraint(columnNames = "code")
        },
        indexes = {
            @Index(name = "idx_teams_status", columnList = "status"),
            @Index(name = "idx_teams_city", columnList = "city"),
            @Index(name = "idx_teams_home_stadium_id", columnList = "home_stadium_id")
        }
)
public class Team extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "team_id")
    private Long id;
    
    // 기본 정보: 구단명, 구단 코드, 활성화 여부, 로고, 팀컬러
    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false, length = 5)
    private String code;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TeamStatus status;

    @Column(name = "logo_url", length = 1000)
    private String logoUrl;
    @Column(name = "primary_color", length = 20)
    private String primaryColor;
    
    // 연고지, 홈구장
    @Column(length = 50)
    private String city;
    @Column(name = "home_stadium_id")
    private Long homeStadiumId;

    @Builder
    private Team(String name, String code, String logoUrl, String primaryColor, String city, Long homeStadiumId) {
        this.name = name;
        this.code = code;
        this.logoUrl = logoUrl;
        this.primaryColor = primaryColor;
        this.city = city;
        this.homeStadiumId = homeStadiumId;
    }

    public static Team create(String name, String code, String logoUrl, String primaryColor, String city, Long homeStadiumId) {
        Team team = Team.builder()
                .name(name == null ? null : name.trim())
                .code(code == null ? null : code.trim())
                .logoUrl(logoUrl)
                .primaryColor(primaryColor)
                .city(city == null ? null : city.trim())
                .homeStadiumId(homeStadiumId)
                .build();

        team.status = TeamStatus.ACTIVE;

        return team;
    }

    @Override
    protected void prePersistHook() {
        if (this.status == null) {
            this.status = TeamStatus.ACTIVE;
        }
    }

    public void activate() {
        this.status = TeamStatus.ACTIVE;
    }
    public void deactivate() {
        this.status = TeamStatus.INACTIVE;
    }
}
