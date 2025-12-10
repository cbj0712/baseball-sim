package com.lsg.baseball.player.domain.enums;

import lombok.Getter;

@Getter
public enum ArmSlot {
    OVER_HAND("OVER_HAND", "오버핸드"),
    THREE_QUATER("THREE_QUATER", "쓰리쿼터"),
    SIDE_ARM("SIDE_ARM", "사이드암"),
    UNDER_HAND("UNDER_HAND", "언더핸드");

    private final String code;
    private final String description;

    ArmSlot(String code, String description) {
        this.code = code;
        this.description = description;
    }
}
