package com.lsg.baseball.player.domain.support;

public final class StatGradeMapper {
    private StatGradeMapper() {

    }

    public static String toGrade(int value) {
        if (value >= 90)
            return "S";
        if (value >= 80)
            return "A";
        if (value >= 70)
            return "B";
        if (value >= 60)
            return "C";
        if (value >= 50)
            return "D";
        return "E";
    }
}
