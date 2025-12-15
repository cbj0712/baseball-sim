package com.lsg.baseball.player.domain.command;

import com.lsg.baseball.player.domain.enums.*;
import com.lsg.baseball.player.dto.request.PlayerCreateRequest;

import java.time.LocalDate;
import java.util.List;

public record PlayerCreateCommand (
        String name,
        LocalDate birthDate,
        String nationality,
        Integer uniformNumber,
        int heightCm,
        int weightKg,
        BodyType bodyType,
        Position mainPosition,
        List<Position> subPositions,
        ThrowHand throwHand,
        BatHand batHand,
        ArmSlot armSlot,
        Integer condition,
        Integer fatigue,
        Integer fitness,
        InjuryStatus injuryStatus,
        Integer injuryDaysLeft,
        Integer potential,
        Integer overall,
        Integer stamina,
        Integer composure,
        Long teamId
) {
    public static PlayerCreateCommand from (PlayerCreateRequest request) {
        return new PlayerCreateCommand(
                request.getName(),
                request.getBirthDate(),
                request.getNationality(),
                request.getUniformNumber(),
                request.getHeightCm(),
                request.getWeightKg(),
                request.getBodyType(),
                request.getMainPosition(),
                request.getSubPositions(),
                request.getThrowHand(),
                request.getBatHand(),
                request.getArmSlot(),
                request.getCondition(),
                request.getFatigue(),
                request.getFitness(),
                request.getInjuryStatus(),
                request.getInjuryDaysLeft(),
                request.getPotential(),
                request.getOverall(),
                request.getStamina(),
                request.getComposure(),
                request.getTeamId()
        );
    }
}

