package com.lsg.baseball.player.domain.enums;

import lombok.Getter;

@Getter
public enum PositionGroup {
    PITCHER("PITCHER", "투수"),
    CATCHER("CATCHER", "포수"),
    INFIELD("INFIELD", "내야수"),
    OUTFIELD("OUTFIELD", "외야수"),
    DESIGNATED_HITTER("DESIGNATED_HITTER", "지명타자");

    private final String code;
    private final String description;

    PositionGroup(String code, String description) {
        this.code = code;
        this.description = description;
    }
}
