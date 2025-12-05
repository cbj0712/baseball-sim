package com.lsg.baseball.player.dto;

import com.lsg.baseball.player.domain.Player;
import com.lsg.baseball.player.domain.enums.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.Period;

@Getter
@NoArgsConstructor
public class PlayerSummaryResponse {

    // 기본 정보
    private Long id;
    private String name;
    private Position mainPosition;

    // 요약 능력치
    private int overall;
    private int age;

    // 피지컬/투타/투구폼
    private BodyType bodyType;
    private ThrowHand throwHand;
    private BatHand batHand;
    private ArmSlot armSlot;

    // 표시용 한글 설명
    private String throwHandDescription;
    private String batHandDescription;
    private String mainPositionDescription;
    private String armSlotDescription;
    // 필요하면 나중에 bodyTypeDescription도 추가 가능

    public static PlayerSummaryResponse from(Player player) {
        PlayerSummaryResponse dto = new PlayerSummaryResponse();

        // 기본 정보
        dto.id = player.getId();
        dto.name = player.getName();
        dto.mainPosition = player.getMainPosition();

        // 능력치/나이
        dto.overall = player.getOverall();
        dto.age = calculateAge(player.getBirthDate());

        // 피지컬/투타/투구폼
        dto.bodyType = player.getBodyType();
        dto.throwHand = player.getThrowHand();
        dto.batHand = player.getBatHand();
        dto.armSlot = player.getArmSlot();

        // 한글 설명
        dto.throwHandDescription = toDescription(player.getThrowHand());
        dto.batHandDescription = toDescription(player.getBatHand());
        dto.mainPositionDescription = toDescription(player.getMainPosition());
        dto.armSlotDescription = toDescription(player.getArmSlot());

        return dto;
    }

    // ====== 내부 helper 메서드들 ======

    private static int calculateAge(LocalDate birthDate) {
        if (birthDate == null) {
            return 0;
        }
        // 만 나이 기준
        return Period.between(birthDate, LocalDate.now()).getYears() + 1;
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
}
