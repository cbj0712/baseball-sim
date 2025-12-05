package com.lsg.baseball.player.dto;

import com.lsg.baseball.player.domain.Player;
import com.lsg.baseball.player.domain.enums.*;
import com.lsg.baseball.player.domain.support.StatGradeMapper;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@Getter
@NoArgsConstructor
public class PlayerDetailResponse {

    // 기본 정보
    private Long id;
    private String name;
    private LocalDate birthDate;
    private String nationality;
    private Integer uniformNumber;

    // 피지컬/체형
    private Integer heightCm;
    private Integer weightKg;
    private BodyType bodyType;

    // 포지션/투타 타입
    private Position mainPosition;
    private List<Position> subPositions;
    private ThrowHand throwHand;
    private BatHand batHand;
    private ArmSlot armSlot;

    // 상태/멘탈
    private int condition;
    private int fatigue;
    private int fitness;
    private InjuryStatus injuryStatus;
    private Integer injuryDaysLeft;
    private int satisfaction;
    private int loyalty;

    // 공통 능력치
    private int potential;
    private String potentialGrade;

    private int overall;
    private String overallGrade;

    private int stamina;
    private int composure;

    // 타자 능력치
    private int hitContact;
    private String hitContactGrade;
    private int hitPower;
    private String hitPowerGrade;
    private int plateDiscipline;
    private String plateDisciplineGrade;
    private int baserunning;
    private String baserunningGrade;
    private int fielding;
    private String fieldingGrade;
    private int armStrength;
    private String armStrengthGrade;

    // 투수 능력치
    private int pitchVelocity;
    private String pitchVelocityGrade;
    private int pitchControl;
    private String pitchControlGrade;
    private int pitchStuff;
    private String pitchStuffGrade;
    private int breakingBall;
    private String breakingBallGrade;
    private int pickoff;
    private String pickoffGrade;

    // enum 한글 설명 (표시용)
    private String throwHandDescription;
    private String batHandDescription;
    private String mainPositionDescription;
    private List<String> subPositionDescriptions;
    private String armSlotDescription;
    private String bodyTypeDescription;

    public static PlayerDetailResponse from(Player player) {
        PlayerDetailResponse dto = new PlayerDetailResponse();

        // 기본 정보
        dto.id = player.getId();
        dto.name = player.getName();
        dto.birthDate = player.getBirthDate();
        dto.nationality = player.getNationality();
        dto.uniformNumber = player.getUniformNumber();

        // 피지컬/체형
        dto.heightCm = player.getHeightCm();
        dto.weightKg = player.getWeightKg();
        dto.bodyType = player.getBodyType();

        // 포지션/투타 타입
        dto.mainPosition = player.getMainPosition();
        dto.subPositions = player.getSubPositions();
        dto.throwHand = player.getThrowHand();
        dto.batHand = player.getBatHand();
        dto.armSlot = player.getArmSlot();

        // 상태/멘탈
        dto.condition = player.getCondition();
        dto.fatigue = player.getFatigue();
        dto.fitness = player.getFitness();
        dto.injuryStatus = player.getInjuryStatus();
        dto.injuryDaysLeft = player.getInjuryDaysLeft();
        dto.satisfaction = player.getSatisfaction();
        dto.loyalty = player.getLoyalty();

        // 공통 능력치
        dto.potential = player.getPotential();
        dto.potentialGrade = grade(player.getPotential());

        dto.overall = player.getOverall();
        dto.overallGrade = grade(player.getOverall());

        dto.stamina = player.getStamina();
        dto.composure = player.getComposure();

        // 타자 능력치
        dto.hitContact = player.getHitContact();
        dto.hitContactGrade = grade(player.getHitContact());

        dto.hitPower = player.getHitPower();
        dto.hitPowerGrade = grade(player.getHitPower());

        dto.plateDiscipline = player.getPlateDiscipline();
        dto.plateDisciplineGrade = grade(player.getPlateDiscipline());

        dto.baserunning = player.getBaserunning();
        dto.baserunningGrade = grade(player.getBaserunning());

        dto.fielding = player.getFielding();
        dto.fieldingGrade = grade(player.getFielding());

        dto.armStrength = player.getArmStrength();
        dto.armStrengthGrade = grade(player.getArmStrength());

        // 투수 능력치
        dto.pitchVelocity = player.getPitchVelocity();
        dto.pitchVelocityGrade = grade(player.getPitchVelocity());

        dto.pitchControl = player.getPitchControl();
        dto.pitchControlGrade = grade(player.getPitchControl());

        dto.pitchStuff = player.getPitchStuff();
        dto.pitchStuffGrade = grade(player.getPitchStuff());

        dto.breakingBall = player.getBreakingBall();
        dto.breakingBallGrade = grade(player.getBreakingBall());

        dto.pickoff = player.getPickoff();
        dto.pickoffGrade = grade(player.getPickoff());

        // enum 한글 설명
        dto.throwHandDescription = toDescription(player.getThrowHand());
        dto.batHandDescription = toDescription(player.getBatHand());
        dto.mainPositionDescription = toDescription(player.getMainPosition());
        dto.subPositionDescriptions = toDescriptionList(player.getSubPositions());
        dto.armSlotDescription = toDescription(player.getArmSlot());
        dto.bodyTypeDescription = toDescription(player.getBodyType());

        return dto;
    }

    // ====== 내부 helper 메서드들 ======

    private static String grade(int value) {
        return StatGradeMapper.toGrade(value);
    }

    private static String toDescription(ThrowHand hand) {
        return hand != null ? hand.getDescription() : null;
    }

    private static String toDescription(BatHand hand) {
        return hand != null ? hand.getDescription() : null;
    }

    private static String toDescription(Position position) {
        return position != null ? position.getDescription() : null;
    }

    private static String toDescription(ArmSlot armSlot) {
        return armSlot != null ? armSlot.getDescription() : null;
    }

    private static String toDescription(BodyType bodyType) {
        return bodyType != null ? bodyType.getDescription() : null;
    }

    private static List<String> toDescriptionList(List<Position> positions) {
        if (positions == null || positions.isEmpty()) {
            return Collections.emptyList();
        }
        return positions.stream()
                .map(PlayerDetailResponse::toDescription)
                .toList();
    }
}
