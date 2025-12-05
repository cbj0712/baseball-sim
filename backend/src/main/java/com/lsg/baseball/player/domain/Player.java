package com.lsg.baseball.player.domain;

import com.lsg.baseball.player.domain.enums.*;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
// @Builder
@Table(name = "players")
public class Player {
    // 기본 정보: 아이디, 이름, 생년월일, 국적, 등번호
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "player_id")
    private Long id;
    private String name;
    @Column(name = "birth_date")
    private LocalDate birthDate;
    private String nationality;
    @Column(name = "uniform_number")
    private Integer uniformNumber;

    // 피지컬/체형: 키, 몸무게, 체형
    @Column(name = "height_cm")
    private Integer heightCm;
    @Column(name = "weight_kg")
    private Integer weightKg;
    @Enumerated(EnumType.STRING)
    @Column(name = "body_type")
    private BodyType bodyType;

    // 포지션/투타 타입: 메인 포지션, 서브 포지션, 송구 손, 타격 위치, 투구폼
    @Enumerated(EnumType.STRING)
    @Column(name = "main_position")
    private Position mainPosition;
    @Column(name = "sub_positions")
    @Convert(converter = SubPositionsConverter.class)
    private List<Position> subPositions;
    @Enumerated(EnumType.STRING)
    @Column(name = "throw_hand")
    private ThrowHand throwHand;
    @Enumerated(EnumType.STRING)
    @Column(name = "bat_hand")
    private BatHand batHand;
    @Enumerated(EnumType.STRING)
    @Column(name = "arm_slot")
    private ArmSlot armSlot;

    // 상태/멘탈/장기 스텟: 컨디션, 피로도, 체력, 부상 상태, 필요 결장 일수, 만족도, 충성도
    private int condition;
    private int fatigue;
    private int fitness;
    @Enumerated(EnumType.STRING)
    @Column(name = "injury_status")
    private InjuryStatus injuryStatus;
    @Column(name = "injury_days_left")
    private Integer injuryDaysLeft;
    private int satisfaction;
    private int loyalty;

    // 공통 능력치: 잠재력, 현재 능력,  스테미나, 침착도/멘탈
    private int potential;
    private int overall;
    private int stamina;
    private int composure;
    
    // 타자 능력치: 컨텍, 파워, 선구안, 주루, 수비, 어깨
    @Column(name = "hit_contact")
    private int hitContact;
    @Column(name = "hit_power")
    private int hitPower;
    @Column(name = "plate_discipline")
    private int plateDiscipline;
    private int baserunning;
    private int fielding;
    @Column(name = "arm_strength")
    private int armStrength;

    // 투수 능력치: 구속, 제구, 구위, 변화구, 견제
    @Column(name = "pitch_velocity")
    private int pitchVelocity;
    @Column(name = "pitch_control")
    private int pitchControl;
    @Column(name = "pitch_stuff")
    private int pitchStuff;
    @Column(name = "breaking_ball")
    private int breakingBall;
    private int pickoff;
    
    // 생성 및 수정 시각
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    public void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
