package com.lsg.baseball.player.domain.enums;

import lombok.Getter;

@Getter
public enum BodyType {
    SLIM("SLIM", "마른 체형"),
    AVERAGE("AVERAGE", "보통 체형"),
    MUSCULAR("MUSCULAR", "근육형 체형"),
    HEAVY("HEAVY", "큰 체형");

    private final String label;
    private final String description;

    BodyType(String label, String description) {
        this.label = label;
        this.description = description;
    }
}
