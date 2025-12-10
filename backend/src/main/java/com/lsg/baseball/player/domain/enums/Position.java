package com.lsg.baseball.player.domain.enums;

import lombok.Getter;

import java.util.Arrays;
import java.util.Locale;
import java.util.Optional;


@Getter
public enum Position {
    SP("SP", "선발투수", PositionGroup.PITCHER),
    RP("RP", "중계투수", PositionGroup.PITCHER),
    _1B("1B", "1루수", PositionGroup.INFIELD),
    _2B("2B", "2루수", PositionGroup.INFIELD),
    _3B("3B", "3루수", PositionGroup.INFIELD),
    SS("SS", "유격수", PositionGroup.INFIELD),
    C("C", "포수", PositionGroup.CATCHER),
    LF("LF", "좌익수", PositionGroup.OUTFIELD),
    CF("CF", "중견수", PositionGroup.OUTFIELD),
    RF("RF", "우익수", PositionGroup.OUTFIELD),
    DH("DH", "지명타자", PositionGroup.DESIGNATED_HITTER),;

    private final String code;
    private final String description;
    private final PositionGroup group;

    Position(String code, String description,  PositionGroup group) {
        this.code = code;
        this.description = description;
        this.group = group;
    }

    public static Optional<Position> safeFromCode(String code) {
        if (code == null || code.isBlank()) {
            return Optional.empty();
        }

        String normalized = code.trim().toUpperCase(Locale.ROOT);

        return Arrays.stream(values())
                .filter(p -> p.code.equals(normalized))
                .findFirst();
    }
}
