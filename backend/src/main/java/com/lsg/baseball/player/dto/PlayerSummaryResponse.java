package com.lsg.baseball.player.dto;

import com.lsg.baseball.player.domain.Player;
import com.lsg.baseball.player.domain.enums.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class PlayerSummaryResponse {
    private Long id;
    private String name;
    private Position mainPosition;

    private int overall;
    private int age;

    private BodyType bodyType;
    private ThrowHand throwHand;
    private BatHand batHand;

    public static PlayerSummaryResponse from(Player player) {
        PlayerSummaryResponse dto = new PlayerSummaryResponse();

        dto.id = player.getId();
        dto.name = player.getName();
        dto.mainPosition = player.getMainPosition();

        dto.overall = player.getOverall();
        dto.age = LocalDate.now().getYear() - player.getBirthDate().getYear();

        dto.bodyType = player.getBodyType();
        dto.throwHand = player.getThrowHand();
        dto.batHand = player.getBatHand();

        return dto;
    }
}
