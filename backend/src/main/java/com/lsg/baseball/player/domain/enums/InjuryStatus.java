package com.lsg.baseball.player.domain.enums;

import lombok.Getter;

@Getter
public enum InjuryStatus {
    HEALTHY("HEALTHY", "건강"),
    DAY_TO_DAY("DAY_TO_DAY", "일단위 부상"),
    OUT_WEEKS("OUT_WEEKS", "주단위 부상"),
    OUT_MONTHS("OUT_MONTHS", "월단위 부상"),
    OUT_SEASON("OUT_SEASON", "시즌아웃");

    private final String code;
    private final String description;

    InjuryStatus(String code, String description) {
        this.code = code;
        this.description = description;
    }
}
