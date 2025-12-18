package com.lsg.baseball.player.domain;

import com.lsg.baseball.common.entity.BaseEntity;
import com.lsg.baseball.player.domain.enums.*;
import com.lsg.baseball.player.domain.support.SubPositionsConverter;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(
        name = "players",
        indexes = {
                @Index(name = "idx_players_team_id", columnList = "team_id")
        }
)
public class Player extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "player_id")
    private Long id;
    
    // 기본 정보: 이름, 생년월일, 국적, 등번호
    @Column(nullable = false, length = 50)
    private String name;
    @Column(nullable = false)
    private LocalDate birthDate;
    @Column(nullable = false, length = 50)
    private String nationality;

    private Integer uniformNumber;

    // 피지컬: 키, 몸무게, 체형
    @Column(name = "height_cm", nullable = false)
    private int heightCm;

    @Column(name = "weight_kg", nullable = false)
    private int weightKg;

    @Enumerated(EnumType.STRING)
    @Column(name = "body_type", nullable = false)
    private BodyType bodyType;

    // 포지션 / 투타: 메인포지션, 서브포지션 리스트, 주손, 타격 위치, 투구폼
    @Enumerated(EnumType.STRING)
    @Column(name = "main_position", nullable = false)
    private Position mainPosition;

    @Convert(converter = SubPositionsConverter.class)
    @Column(name = "sub_positions", length = 500)
    private List<Position> subPositions;

    @Enumerated(EnumType.STRING)
    @Column(name = "throw_hand", nullable = false)
    private ThrowHand throwHand;

    @Enumerated(EnumType.STRING)
    @Column(name = "bat_hand", nullable = false)
    private BatHand batHand;

    @Enumerated(EnumType.STRING)
    @Column(name = "arm_slot", nullable = false)
    private ArmSlot armSlot;

    // 상태/멘탈: 컨디션, 피로도, 시즌 체력, 부상상태, 부상일수, 만족도, 충성도
    @Column(nullable = false)
    private int condition;
    @Column(nullable = false)
    private int fatigue;
    @Column(nullable = false)
    private int fitness;

    @Enumerated(EnumType.STRING)
    @Column(name = "injury_status", nullable = false)
    private InjuryStatus injuryStatus;

    @Column(name = "injury_days_left", nullable = false)
    private Integer injuryDaysLeft;

    @Column(nullable = false)
    private int satisfaction;
    @Column(nullable = false)
    private int loyalty;
    
    // 공통 능력치: 잠재력, 능력치, 스태미나, 침착도
    @Column(nullable = false)
    private int potential;
    @Column(nullable = false)
    private int overall;
    @Column(nullable = false)
    private int stamina;
    @Column(nullable = false)
    private int composure;

    @Column(name = "team_id")
    private Long teamId;

    @Builder
    private Player(String name, LocalDate birthDate, String nationality, int heightCm, int weightKg, BodyType bodyType, ThrowHand throwHand, BatHand batHand, ArmSlot armSlot, Position mainPosition) {
        this.name = name;
        this.birthDate = birthDate;
        this.nationality = nationality;

        this.heightCm = heightCm;
        this.weightKg = weightKg;
        this.bodyType = bodyType;

        this.throwHand = throwHand;
        this.batHand = batHand;
        this.armSlot = armSlot;

        this.mainPosition = mainPosition;
        this.subPositions = new ArrayList<>();
    }

    void initBasic(Integer uniformNumber, List<Position> subPositions) {
        this.uniformNumber = uniformNumber;
        changeSubPositions(subPositions);
    }

    void initStatus(Integer condition, Integer fatigue, Integer fitness, InjuryStatus injuryStatus, Integer injuryDaysLeft) {
        this.condition =
                condition != null
                        ? condition
                        : 100;

        this.fatigue =
                fatigue != null
                        ? fatigue
                        : 0;

        this.fitness =
                fitness!= null
                        ? fitness
                        : 100;

        this.injuryStatus =
                injuryStatus != null
                        ? injuryStatus
                        : InjuryStatus.HEALTHY;

        this.injuryDaysLeft =
                injuryDaysLeft != null
                        ? injuryDaysLeft
                        : 0;
    }

    void initMentalDefault() {
        this.satisfaction = 50;
        this.loyalty = 50;
    }

    void initRatings(Integer potential, Integer overall, Integer stamina, Integer composure, int basePotential) {
        this.potential =
                potential!= null
                        ? potential
                        : basePotential;

        int computedOverall = overall != null ? overall : (basePotential - 5);
        this.overall = Math.max(0, computedOverall);

        this.stamina =
                stamina != null
                        ? stamina
                        : 0;

        this.composure =
                composure != null
                        ? composure
                        : 0;
    }

    void initTeam(Long teamId) {
        this.teamId = teamId;
    }

    void normalizeInjury() {
        int days = (injuryDaysLeft == null || injuryDaysLeft < 0) ? 0 : injuryDaysLeft;

        this.injuryDaysLeft = days;

        if (days == 0) {
            this.injuryStatus = InjuryStatus.HEALTHY;
        } else if (days <= 7) {
            this.injuryStatus = InjuryStatus.DAY_TO_DAY;
        } else if (days <= 30) {
            this.injuryStatus = InjuryStatus.OUT_WEEKS;
        } else if (days <= 180) {
            this.injuryStatus = InjuryStatus.OUT_MONTHS;
        } else {
            this.injuryStatus = InjuryStatus.OUT_SEASON;
        }
    }

    public void changeUniformNumber(Integer newUniformNumber) {
        if (newUniformNumber == null) {
            this.uniformNumber = null;
            return;
        }

        if (newUniformNumber < 0 || newUniformNumber > 200) {
            throw new IllegalStateException("유니폼 번호로 사용할 수 없는 번호입니다");
        }

        this.uniformNumber = newUniformNumber;
    }

    public void changeSubPositions(List<Position> positions) {
        if (positions == null || positions.isEmpty()) {
            this.subPositions = new ArrayList<>();
            return;
        }

        LinkedHashSet<Position> newPositions = new LinkedHashSet<>(positions);
        newPositions.remove(this.mainPosition);
        newPositions.remove(null);

        this.subPositions = new ArrayList<>(newPositions);
    }

    public void transferTo(Long newTeamId) {
        if (newTeamId == null) {
            throw new IllegalStateException("팀 ID는 null일 수 없습니다");
        }

        this.teamId = newTeamId;
    }

    public void releaseTeam() {
        this.teamId = null;
    }
    
    // TODO: 게임 시뮬레이션 설계 후 세부 능력치(타자/투수별) 별도 객체로 추가 예정
    // TODO: 선수 계약 정보(계약기간, 연봉, 인센티브 등)를 별도 객체로 추가 예정 
}
