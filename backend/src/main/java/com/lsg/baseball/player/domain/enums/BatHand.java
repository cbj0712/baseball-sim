package com.lsg.baseball.player.domain.enums;

import lombok.Getter;

@Getter
public enum BatHand {
    R("R", "우타"),
    L("L", "좌타"),
    S("S", "스위치히터");

    private final String code;
    private final String description;

    BatHand(String code, String description) {
        this.code = code;
        this.description = description;
    }
}
