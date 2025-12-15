package com.lsg.baseball.player.domain;

import com.lsg.baseball.common.entity.BaseEntity;
import com.lsg.baseball.player.domain.command.PlayerCreateCommand;
import com.lsg.baseball.player.domain.enums.*;
import com.lsg.baseball.player.domain.support.SubPositionsConverter;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

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
    private static final int STAT_MAX = 100;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "player_id")
    private Long id;
    
    // 기본 정보: 이름, 생년월일, 국적, 등번호
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private LocalDate birthDate;
    @Column(nullable = false)
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

    public static Player create(PlayerCreateCommand command) {
        int potentialMax = randomStatFrom(60);

        Player player = Player.builder()
                .name(command.name())
                .birthDate(command.birthDate())
                .nationality(command.nationality())
                .heightCm(command.heightCm())
                .weightKg(command.weightKg())
                .bodyType(command.bodyType())
                .mainPosition(command.mainPosition())
                .throwHand(command.throwHand())
                .batHand(command.batHand())
                .armSlot(command.armSlot())
                .build();


        player.uniformNumber = command.uniformNumber();

        player.changeSubPositions(command.subPositions());

        player.condition =
                command.condition() != null
                    ? command.condition()
                    : 100;

        player.fatigue =
                command.fatigue() != null
                    ? command.fatigue()
                    : 0;

        player.fitness =
                command.fitness() != null
                ? command.fitness()
                : 100;

        player.injuryStatus =
                command.injuryStatus() != null
                    ? command.injuryStatus()
                    : InjuryStatus.HEALTHY;

        player.injuryDaysLeft =
                command.injuryDaysLeft() != null
                    ? command.injuryDaysLeft()
                    : 0;

        player.satisfaction = 50;
        player.loyalty = 50;

        player.potential =
                command.potential() != null
                    ? command.potential()
                    : potentialMax;

        player.overall =
                command.overall() != null
                    ? command.overall()
                    : potentialMax - 5;

        player.stamina =
                command.stamina() != null
                    ? command.stamina()
                    : randomStatFrom(50);

        player.composure =
                command.composure() != null
                    ? command.composure()
                    : randomStatFrom(50);

        applyTeam(player, command);

        return player;
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

    private static int randomBetweenInclusive(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max+1);
    }

    private static int randomStatFrom(int minInclusive) {
        return randomBetweenInclusive(minInclusive, STAT_MAX);
    }

    private static void applyTeam(Player player, PlayerCreateCommand command) {
        player.teamId = command.teamId();
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
