package com.lsg.baseball.player.dto;

import com.lsg.baseball.player.domain.Player;
import com.lsg.baseball.player.domain.enums.*;

import java.time.LocalDate;

public class PlayerResponse {
    private Long id;
    private String name;
    private String nationality;
    private Integer uniformNumber;
    private Position mainPosition;
    private LocalDate birthDate;

    private int overall;

    private ThrowHand throwHand;
    private BatHand batHand;
    private ArmSlot armSlot;

    private InjuryStatus injuryStatus;
    private Integer injuryDaysLeft;

    public static PlayerResponse from(Player player) {
        PlayerResponse dto = new PlayerResponse();

        dto.id = player.getId();
        dto.name = player.getName();
        dto.nationality = player.getNationality();
        dto.uniformNumber = player.getUniformNumber();
        dto.mainPosition = player.getMainPosition();
        dto.birthDate = player.getBirthDate();

        dto.overall = player.getOverall();

        dto.throwHand = player.getThrowHand();
        dto.batHand = player.getBatHand();
        dto.armSlot = player.getArmSlot();

        dto.injuryStatus = player.getInjuryStatus();
        dto.injuryDaysLeft = player.getInjuryDaysLeft();

        return dto;
    }
}
