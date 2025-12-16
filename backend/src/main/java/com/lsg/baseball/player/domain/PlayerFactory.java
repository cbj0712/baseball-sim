package com.lsg.baseball.player.domain;

import com.lsg.baseball.player.domain.command.PlayerCreateCommand;

import java.util.concurrent.ThreadLocalRandom;

public final class PlayerFactory {
    private static final int STAT_MAX = 100;

    private PlayerFactory() {}

    public static Player create(PlayerCreateCommand command) {
        int basePotential = randomStatFrom(60);

        Player player = buildBase(command);

        player.initBasic(command.uniformNumber(), command.subPositions());
        player.initStatus(command.condition(), command.fatigue(), command.fitness(), command.injuryStatus(), command.injuryDaysLeft());
        player.initMentalDefault();
        player.initRatings(
                command.potential(),
                command.overall(),
                command.stamina() != null ? command.stamina() : randomStatFrom(50),
                command.composure() != null ? command.composure() : randomStatFrom(50),
                basePotential
        );
        player.initTeam(command.teamId());
        player.normalizeInjury();

        return player;
    }

    private static Player buildBase(PlayerCreateCommand command) {
        return Player.builder()
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
    }

    private static int randomStatFrom(int minInclusive) {
        return ThreadLocalRandom.current().nextInt(minInclusive, STAT_MAX+1);
    }
}
