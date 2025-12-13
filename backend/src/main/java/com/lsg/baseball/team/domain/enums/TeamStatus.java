package com.lsg.baseball.team.domain.enums;

import lombok.Getter;

@Getter
public enum TeamStatus {
    ACTIVE("운영"),
    INACTIVE( "해체");

    private final String description;

    TeamStatus(String description) {
        this.description = description;
    }
}
