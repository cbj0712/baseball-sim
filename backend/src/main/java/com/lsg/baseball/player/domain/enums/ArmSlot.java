package com.lsg.baseball.player.domain.enums;

public enum ArmSlot {
    OVERHAND("OVERHAND", "오버핸드"),
    THREE_QUARTER("THREE_QUARTER", "쓰리쿼터"),
    SIDEARM("SIDEARM", "사이드암"),
    UNDERHAND("UNDERHAND", "언더핸드");

    private final String label;
    private final String description;

    ArmSlot(String label, String description) {
        this.label = label;
        this.description = description;
    }

    public String getLabel() {
        return label;
    }
}
