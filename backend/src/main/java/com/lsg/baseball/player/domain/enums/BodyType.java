package com.lsg.baseball.player.domain.enums;

import lombok.Getter;

@Getter
public enum BodyType {
    SLIM("SLIM", "마른 체형"),
    AVERAGE("AVERAGE", "평균 체형"),
    MUSCULAR("MUSCULAR", "근육 체형"),
    HEAVY("HEAVY", "큰 체형");

    private final String code;
    private final String description;

    BodyType(String code, String description) {
        this.code = code;
        this.description = description;
    }
}
