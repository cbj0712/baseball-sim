package com.lsg.baseball.player.domain.enums;

public enum BatHand {
    R("R", "우타"),
    L("L", "좌타"),
    S("S", "스위치히터");

    private final String label;
    private final String description;

    BatHand(String label, String description) {
        this.label = label;
        this.description = description;
    }

    public String getLabel() {
        return label;
    }
}
