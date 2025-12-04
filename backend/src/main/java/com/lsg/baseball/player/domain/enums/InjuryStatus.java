package com.lsg.baseball.player.domain.enums;

public enum InjuryStatus {
    HEALTHY("HEALTHY", "건강"),
    DAY_TO_DAY("DAY_TO_DAY", "일단 결장 필요"),
    OUT_WEEKS("OUT_WEEKS", "주단위 결장 필요"),
    OUT_MONTHS("OUT_MONTHS", "월단위 결장 필요"),
    OUT_SEASON("OUT_SEASON", "시즌아웃");

    private final String label;
    private final String description;

    InjuryStatus(String label, String description) {
        this.label = label;
        this.description = description;
    }

    public String getLabel() {
        return label;
    }
}
