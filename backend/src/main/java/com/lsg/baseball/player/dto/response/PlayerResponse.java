package com.lsg.baseball.player.dto.response;

import com.lsg.baseball.player.domain.enums.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PlayerResponse {
    private final Long id;

    private final String name;
    private final LocalDate birthDate;
    private final String nationality;
    private final Integer uniformNumber;

    private final Integer heightCm;
    private final Integer weightKg;
    private final BodyType bodyType;

    private final Position mainPosition;
    private final List<Position> subPositions;
    private final ThrowHand throwHand;
    private final BatHand batHand;
    private final ArmSlot armSlot;

    private final Integer condition;
    private final Integer fatigue;
    private final Integer fitness;
    private final InjuryStatus injuryStatus;
    private final Integer injuryDaysLeft;
    private final Integer satisfaction;
    private final Integer loyalty;

    private final Integer potential;
    private final Integer overall;
    private final Integer stamina;
    private final Integer composure;
}
