package com.lsg.baseball.player.domain.enums;

import lombok.Getter;

@Getter
public enum ThrowHand {
    R("R", "우투"),
    L("L", "좌투");

    private final String code;
    private final String description;

    ThrowHand(String code, String description) {
        this.code = code;
        this.description = description;
    }
}
