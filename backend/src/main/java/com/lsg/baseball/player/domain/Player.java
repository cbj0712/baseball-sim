package com.lsg.baseball.player.domain;

import com.lsg.baseball.common.entity.BaseEntity;
import com.lsg.baseball.player.domain.enums.*;
import com.lsg.baseball.player.domain.support.SubPositionsConverter;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Entity
@Table(name = "players")
public class Player extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "player_id")
    private Long id;
    
    // 기본 정보: 이름, 생년월일, 국적, 등번호
    private String name;
    private LocalDate birthDate;
    private String nationality;
    private Integer uniformNumber;

    // 피지컬: 키, 몸무게, 체형
    @Column(name = "height_cm")
    private int heightCm;

    @Column(name = "weight_kg")
    private int weightKg;

    @Enumerated(EnumType.STRING)
    @Column(name = "body_type")
    private BodyType bodyType;

    // 포지션 / 투타: 메인포지션, 서브포지션 리스트, 주손, 타격 위치, 투구폼
    @Enumerated(EnumType.STRING)
    @Column(name = "main_position")
    private Position mainPosition;

    @Convert(converter = SubPositionsConverter.class)
    @Column(name = "sub_positions")
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

    // 상태/멘탈: 컨디션, 피로도, 시즌 체력, 부상상태, 부상일수, 만족도, 충성도
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
    
    // 공통 능력치: 잠재력, 능력치, 스태미나, 침착도
    private int potential;
    private int overall;
    private int stamina;
    private int composure;
    
    // TODO: 게임 시뮬레이션 설계 후 세부 능력치(타자/투수별) 별도 객체로 추가 예정
    // TODO: 선수 계약 정보(계약기간, 연봉, 인센티브 등)를 별도 객체로 추가 예정 
}
