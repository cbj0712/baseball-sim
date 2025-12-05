package com.lsg.baseball.player.domain.enums;

import lombok.Getter;

@Getter
public enum ThrowHand {
    R("R", "우투"),
    L("L", "좌투");

    private final String label;
    private final String description;

    ThrowHand(String label, String description) {
        this.label = label;
        this.description = description;
    }
}
